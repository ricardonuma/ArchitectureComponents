package com.example.ricardonuma.architecturecomponents;

import android.app.Application;

import com.example.ricardonuma.architecturecomponents.data.Repository;

public class MyApplication extends Application {

    private Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Repository getRepository() {
        return repository != null ? repository : new ServiceLocator().provideRepository(this);
    }
}
