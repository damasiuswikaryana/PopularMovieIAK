package id.co.yakini.damasiusw.popularmovie;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by satyakresna on 12-Aug-17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}