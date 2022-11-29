package es.udc.psi.p34lopez;

import android.app.Application;

import butterknife.ButterKnife;
import es.udc.psi.p34lopez.BuildConfig;

public class GlobalApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        initButterKnifeDebug();
    }

    private void initButterKnifeDebug() {

        ButterKnife.setDebug(BuildConfig.DEBUG);
    }
}
