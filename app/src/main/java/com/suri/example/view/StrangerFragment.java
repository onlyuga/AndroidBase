package com.suri.example.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suri.core.fragment.BaseFragment;
import com.suri.example.R;
import com.suri.example.contract.HomeContract;
import com.suri.example.contract.StrangerContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by K53SV on 3/14/2017.
 */

public class StrangerFragment extends BaseFragment implements StrangerContract.View {

    private StrangerContract.Presenter presenter;
    private Unbinder unbinder;

    //@BindView(R.id.tv_stranger)
    //TextView tvName;

    public static StrangerFragment newInstance() {

        return new StrangerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stranger, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void setPresenter(StrangerContract.Presenter presenter) {
            this.presenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        assert presenter != null;
        presenter.subscribe();
    }


    @Override
    public void onPause() {
        super.onPause();
        presenter.unSubscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void displayInformation(String name) {
       // tvName.setText(name);
    }
}
