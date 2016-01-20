package com.aiculabs.melchord.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aiculabs.melchord.BoilerplateApplication;
import com.aiculabs.melchord.injection.component.ActivityComponent;
import com.aiculabs.melchord.injection.component.DaggerActivityComponent;
import com.aiculabs.melchord.injection.module.ActivityModule;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(BoilerplateApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

}
