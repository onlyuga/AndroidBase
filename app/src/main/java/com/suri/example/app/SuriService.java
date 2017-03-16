package com.suri.example.app;

import com.suri.example.model.entity.Repository;
import com.suri.example.model.entity.User;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;


public class SuriService {

	private SuriApi mApi;

	public SuriService(SuriApi githubApi) {
		mApi = githubApi;
	}

	public Observable<User> signIn(String token) {
		return mApi.signIn(token);
	}

	public Observable<List<Repository>> getUserRepos(String user, int page, Integer pageSize) {
		return mApi.getUserRepos(user, page, pageSize);
	}
}
