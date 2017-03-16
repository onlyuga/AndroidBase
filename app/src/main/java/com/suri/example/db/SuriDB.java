package com.suri.example.db;

import android.content.Context;

import javax.inject.Inject;

/**
 * Created by K53SV on 3/13/2017.
 */

public class SuriDB {
    Context context;

    @Inject
    public SuriDB(Context context){
        this.context = context;
    }

    public String getName(){
        return context.getPackageName().toLowerCase();
    }
}
