package com.yzg.myapplication.model.net;

/**
 * Created by yzg on 2017/5/2.
 */

public class GankResponse<T> {
    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
