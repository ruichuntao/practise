package rui.utils;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

public abstract class ResultCallBack {
    public abstract void onError(Request request, Exception e);

    public abstract void onResponse(Response response) throws IOException;
}
