package id.co.yakini.damasiusw.popularmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import id.co.yakini.damasiusw.popularmovie.utilities.Constant;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = Constant.URL_API_MOVIE + Constant.POPULAR + Constant.PARAM_API_KEY + Constant.API_KEY;
        Log.d(TAG, "ini url popular = "+ url);
    }
}
