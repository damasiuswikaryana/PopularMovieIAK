package id.co.yakini.damasiusw.popularmovie.model;

import java.util.List;

/**
 * Created by angel on 8/13/2017.
 */

public class PopularMovie {
    private List<PopularMovieResult> results;

    public List<PopularMovieResult> getList() {
        return results;
    }

    public void setList(List<PopularMovieResult> results) {
        this.results = results;
    }
}
