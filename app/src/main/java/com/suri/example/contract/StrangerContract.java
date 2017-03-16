package com.suri.example.contract;

import com.suri.core.presenter.BasePresenter;
import com.suri.core.view.BaseView;

/**
 * Created by K53SV on 3/13/2017.
 */

public interface StrangerContract {

    interface View extends BaseView<Presenter> {

        void displayInformation(String name);
    }

    interface Presenter extends BasePresenter {

        void loadName(String name);
    }
}
