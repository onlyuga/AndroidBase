package com.suri.example.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.suri.example.app.SuriService;
import com.suri.example.contract.HomeContract;
import com.suri.example.db.SuriDB;
import com.suri.example.rx.RxBus;
import com.suri.example.util.ActivityScoped;
import com.suri.example.util.AuthUtils;
import com.suri.example.util.Config;
import com.suri.example.util.Utils;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

@ActivityScoped
public class HomePresenter implements HomeContract.Presenter {
    private Context context;
    private static final String TAG = HomePresenter.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    private final HomeContract.View view;

    @Inject
    SuriDB db;
    @Inject
    SuriService apiService;

    private CompositeSubscription subscriptions;

    @Inject
    HomePresenter(Context context, HomeContract.View view) {
        this.context = context;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
        view.setPresenter(this);
//      SuriApplication.getInstance().getApplicationComponent().inject(this);
//      DaggerHomePresenterComponent.builder()
//                .applicationModule(new ApplicationModule(context))
//                .build().inject(this);
    }

    public void registerFCM(){
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(context, "Push notification: " + message, Toast.LENGTH_LONG).show();

                    loadName(message);
                }
            }
        };

        displayFirebaseRegId();
        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(context).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(context).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));
    }

    private void displayFirebaseRegId() {
        SharedPreferences pref = context.getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId))
            loadName("Firebase Reg Id: " + regId);
        else
            loadName("Firebase Reg Id is not received yet!");
    }

    @Override
    public void subscribe() {
        //registerFCM();
    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
        LocalBroadcastManager.getInstance(context).unregisterReceiver(mRegistrationBroadcastReceiver);
    }

    @Override
    public void loadName(String _name) {
        String token = "Basic " + Base64.encodeToString(_name.getBytes(), Base64.NO_WRAP);
        Subscription subscription = apiService.signIn(token)
                .doOnNext(user -> AuthUtils.setToken(token))
                .compose(Utils.applySchedulers())
                .subscribe(user -> {
                    view.displayInformation(user.getName());
                }, exception -> {
                    Toast.makeText(context, "Error: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    exception.fillInStackTrace();
                });
        subscriptions.add(subscription);
    }

    @Override
    public void startStranger() {
        //ActivityUtils.addFragmentToActivity(activity.getSupportFragmentManager(), fragment, R.id.fragment_container);
        RxBus.publish(RxBus.SUBJECT_MY_SUBJECT, "HELLO");

    }
}
