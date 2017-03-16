package com.suri.example.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.suri.core.fragment.BaseFragment;
import com.suri.example.R;
import com.suri.example.contract.HomeContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by K53SV on 3/14/2017.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {

    private HomeContract.Presenter presenter;
    private Unbinder unbinder;

    @BindView(R.id.email)
    TextView tvEmail;
    @BindView(R.id.password)
    TextView tvPassword;

    public static HomeFragment newInstance() {

        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_in, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick(R.id.email_sign_in_button)
    public void onClickButton(View v){
        presenter.loadName(String.format("%s:%s", tvEmail.getText().toString(),tvPassword.getText().toString()));
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
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
        Toast.makeText(getActivity(), "User: " + name, Toast.LENGTH_SHORT).show();
        presenter.startStranger();
    }
}
