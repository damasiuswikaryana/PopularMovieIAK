package id.co.yakini.damasiusw.popularmovie;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.yakini.damasiusw.popularmovie.adapter.PopularMovieAdapter;
import id.co.yakini.damasiusw.popularmovie.model.PopularMovie;
import id.co.yakini.damasiusw.popularmovie.model.PopularMovieResult;
import id.co.yakini.damasiusw.popularmovie.utilities.Constant;

public class MainActivity extends AppCompatActivity
implements PopularMovieAdapter.ItemClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    public List<PopularMovieResult> popularMovieResults = new ArrayList<>();
    @BindView(R.id.rv_popular_movie) RecyclerView mRecyclerView;

    private PopularMovieAdapter mAdapter;
    private Gson gson = new Gson();
    private DividerItemDecoration mDividerItemDecoration;

    @BindView(R.id.line_network_retry) LinearLayout mLinearLayoutRetry;
    @BindView(R.id.tv_error_message) TextView mDisplayErrorMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAdapter = new PopularMovieAdapter(popularMovieResults, this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        mRecyclerView.setLayoutManager(layoutManager);
        mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(mDividerItemDecoration);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

//      String url = Constant.URL_API_MOVIE + Constant.POPULAR + Constant.PARAM_API_KEY + Constant.API_KEY;
//      Log.d(TAG, "ini url popular = "+ url);

        if (isNetworkConnected() || isWifiConnected()) {
            getDataFromAPI();
        }
        else {
            mRecyclerView.setVisibility(View.INVISIBLE);
            mLinearLayoutRetry.setVisibility(View.VISIBLE);
        }

        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setElevation(0);
        }
    }

    private void getDataFromAPI() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = Constant.URL_API_MOVIE + Constant.POPULAR + Constant.PARAM_API_KEY + Constant.API_KEY;
        Log.d(TAG, "ini urlnya: "+url);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            PopularMovie popularMovie = gson.fromJson(response, PopularMovie.class);
                            for (PopularMovieResult item : popularMovie.getList()) {
                                popularMovieResults.add(item);
                            }
                            mAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null) {
                            Log.e(TAG, error.getMessage());
                        }
                        else {
                            Log.e(TAG, "Something error happened!");
                        }
                    }
                }
        );
        requestQueue.add(stringRequest);
    }


    @Override
    public void onItemClick(PopularMovieResult data, int position) {
        Intent startDetailActivity = new Intent(MainActivity.this, MoveDetail.class);
        startDetailActivity.putExtra("data", gson.toJson(data));
        startDetailActivity.putExtra("position", position);
        startActivity(startDetailActivity);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private boolean isWifiConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected() && (ConnectivityManager.TYPE_WIFI == networkInfo.getType());
    }


}
