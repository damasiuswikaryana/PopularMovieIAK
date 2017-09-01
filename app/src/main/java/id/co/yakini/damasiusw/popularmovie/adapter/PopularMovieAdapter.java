package id.co.yakini.damasiusw.popularmovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.yakini.damasiusw.popularmovie.MainActivity;
import id.co.yakini.damasiusw.popularmovie.R;
import id.co.yakini.damasiusw.popularmovie.model.PopularMovieResult;

/**
 * Created by angel on 8/14/2017.
 */

public class PopularMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = PopularMovieAdapter.class.getSimpleName();

    private List<PopularMovieResult> popMovieList = new ArrayList<>();
    private final ItemClickListener mOnClickListener;
    private Context context;

    public interface ItemClickListener {
        void onItemClick(PopularMovieResult data, int position);
    }

    public PopularMovieAdapter(Context context, List<PopularMovieResult> popMovieList, ItemClickListener mOnClickListener) {
        this.context = context;
        this.popMovieList = popMovieList;
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PopularViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PopularViewHolder) holder).bind(
                popMovieList.get(position), position, mOnClickListener
        );
    }

    @Override
    public int getItemCount() {
        return popMovieList.size();
    }


    public class PopularViewHolder extends RecyclerView.ViewHolder {
        //@BindView(R.id.iv_image_pop_movie) ImageView weatherIcon;
        @BindView(R.id.tv_title_pop_movie) TextView titlepop;
        @BindView(R.id.iv_image_pop_movie) ImageView imageMovie;

        public PopularViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final PopularMovieResult data, final int position, final ItemClickListener itemClickListener) {
            //gambar disini
            Picasso.with(context)
                    .load("https://image.tmdb.org/t/p/w185_and_h278_bestv2"+data.getPoster_path())
                    .into(imageMovie);
            titlepop.setText(data.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(data, position);
                }
            });
        }
    }
}
