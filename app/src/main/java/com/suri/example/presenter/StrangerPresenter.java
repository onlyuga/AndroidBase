package com.suri.example.presenter;

import com.suri.example.contract.StrangerContract;
import com.suri.example.util.ActivityScoped;


import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by K53SV on 3/13/2017.
 */

@ActivityScoped
public class StrangerPresenter implements StrangerContract.Presenter {

    private final StrangerContract.View view;


    private CompositeSubscription subscriptions;

    @Inject
    StrangerPresenter(StrangerContract.View view) {
        this.view = view;
        this.subscriptions = new CompositeSubscription();
        view.setPresenter(this);
        /*DaggerPresenterComponent.builder()
                .applicationModule(new ApplicationModule(context))
                .build().inject(this);*/
    }



    @Override
    public void subscribe() {
        loadName("Hello XXX");
    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }

    @Override
    public void loadName(String _name) {
      view.displayInformation(_name);
    }
}
