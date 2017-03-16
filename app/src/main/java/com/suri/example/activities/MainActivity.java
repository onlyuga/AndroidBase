package com.suri.example.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.suri.core.activity.BaseActivity;
import com.suri.core.util.ActivityUtils;
import com.suri.example.R;
import com.suri.example.app.SuriApplication;
import com.suri.example.activities.home.component.DaggerHomeComponent;
import com.suri.example.rx.RxBus;
import com.suri.example.activities.home.module.HomeModule;
import com.suri.example.presenter.HomePresenter;
import com.suri.example.presenter.StrangerPresenter;
import com.suri.example.view.HomeFragment;
import com.suri.example.view.StrangerFragment;

import javax.inject.Inject;

public class MainActivity extends BaseActivity{
    @Inject
    HomePresenter homePresenter;
    @Inject
    StrangerPresenter strangerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        HomeFragment homePageFragment = HomeFragment.newInstance();
        StrangerFragment strangerFragment = StrangerFragment.newInstance();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), homePageFragment, R.id.fragment_container);

        DaggerHomeComponent.builder()
                .applicationComponent(SuriApplication.getInstance().getApplicationComponent())
                .homeModule(new HomeModule(homePageFragment, strangerFragment))
                .build().inject(this);

        RxBus.subscribe(RxBus.SUBJECT_MY_SUBJECT, "F1", message -> {
            if (message instanceof String) {
                ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), strangerFragment, R.id.fragment_container);
                Log.v("Testing", message.toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.unregister("F1");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
