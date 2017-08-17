package id.co.yakini.damasiusw.popularmovie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.yakini.damasiusw.popularmovie.R;
import id.co.yakini.damasiusw.popularmovie.model.PopularMovieResult;

/**
 * Created by angel on 8/14/2017.
 */

public class PopularMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = PopularMovieAdapter.class.getSimpleName();

    private List<PopularMovieResult> popMovieList = new ArrayList<>();
    private final ItemClickListener mOnClickListener;

    public interface ItemClickListener {
        void onItemClick(PopularMovieResult data, int position);
    }

    public PopularMovieAdapter(List<PopularMovieResult> popMovieList, ItemClickListener mOnClickListener) {
        this.popMovieList = popMovieList;
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PopularViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_popular_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PopularViewHolder) holder).bind(popMovieList.get(position), position, mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return popMovieList.size();
    }


    public class PopularViewHolder extends RecyclerView.ViewHolder {
        //@BindView(R.id.iv_image_pop_movie) ImageView weatherIcon;
        @BindView(R.id.tv_title_pop_movie) TextView titlepop;

        public PopularViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final PopularMovieResult data, final int position, final ItemClickListener itemClickListener) {
            //gambar disini
            titlepop.setText(position);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(data, position);
                }
            });
        }
    }
}
