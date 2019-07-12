package com.poc.app.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mathan on 17/08/18.
 */

public abstract class VolleyClient implements com.android.volley.Response.ErrorListener {

    // Expremental
    String extraOutput;
    int timeout = 60000; // 60 seconds - time out
    Context c;
    Map<String, String> headers;
    Map<String, String> params;
    String url;

    int method;
    int requestType;
    ResponseListener l;
    Type responseType;

    /**
     * @param c   Context of Application
     * @param m   Request method {@link Request.Method}.Method.POST / others from Request.Method.*
     * @param url Url of the request
     * @param rt  Unique request type from constants. This param may be deprecated in future release.
     */
    VolleyClient(Context c, int m, String url, int rt) {
        this.c = c;
        this.method = m;
        this.url = url;
        this.requestType = rt;
        responseType = Response.class;
        headers = new HashMap<>();
        params = new HashMap<>();
        Log.i("Url", url);
    }

    /**
     * @param rs    Response String
     * @param model {@link Type} of Sub class of {@link Response}
     * @return object of @param model
     */
    static synchronized Response parseResponse(String rs, Type model) {
        try {

            Log.i("Response", "Response-----------\n" + rs);

            Gson gson = new GsonBuilder().serializeNulls().create();

//            converting or parsing the content
            return gson.fromJson(rs, model);

        } catch (JsonParseException e) {
            Log.e("Response", "Exception ----------------------------------------------------");
            e.printStackTrace();
            return null;
        } catch (IllegalStateException e) {
            Log.e("Response", "Exception ----------------------------------------------------");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e("Response", "Exception ----------------------------------------------------");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("VolleyClient", "ErrorResponse----:  " + error.getMessage());
        if (l != null) {
            Response r = new Response();
            r.requestType = requestType;
            r.responseCode = 404;
            r.message = "Failure";
            r.customMessage = "Url not found";
            r.extraOutput = extraOutput;
            l.onResponse(r);
        }
    }

    /**
     * Default is 50 seconds
     *
     * @param timeout Set time out in seconds
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout * 1000;
    }

    /**
     * @param key   Param name / key
     * @param value Param value
     */
    public void addParam(String key, String value) {
        params.put(key, value);
        Log.v("Params ------- ", value);

    }

    /**
     * @param key   Header name / key
     * @param value Header value
     */
    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    /**
     * All Collection
     *
     * @param headers Map of Params
     */
    void addHeaders(Map<String, String> headers) {
        this.headers.putAll(headers);
    }
}
