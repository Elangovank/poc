package com.poc.app.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.poc.app.utils.APPNetworkUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


/**
 * Created by Mathan on 17/08/18.
 */

public class RestClient extends VolleyClient implements com.android.volley.Response.Listener<String> {

    /**
     * @param c   Context of Application
     * @param m   Request method {@link Request.Method}.Method.POST / others from Request.Method.*
     * @param url Url of the request
     * @param rt  Unique request type from constants. This param may be deprecated in future release.
     */
    public RestClient(Context c, int m, String url, int rt) {
        super(c, m, url, rt);
    }

    /**
     * Default is 50 seconds
     *
     * @param timeout Set time out in seconds
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout * 1000;
    }

    @Override
    public void onResponse(String response) {
        try {
            if (l != null) {
                if (responseType == null) {
                    responseType = Response.class;
                }
                Response r = parseResponse(response, responseType);
                if (r != null) {
                    r.requestType = requestType;
                    r.extraOutput = extraOutput;
                }
                l.onResponse(r);
            } else {
                Log.w("RestClient", "Response received but not listened.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes the url request that is preset in constructor
     *
     * @param l            Response Listener which is implemented in the activity
     * @param responseType Response type from one of model default is {@link Response}
     */
    public void execute(ResponseListener l, Type responseType) throws Exception {
        this.l = l;
        this.responseType = responseType;

        try {
            if (APPNetworkUtil.isInternetOn(c)) {
                HurlStack hurlStack = new HurlStack() {
                    @Override
                    protected HttpURLConnection createConnection(URL url) throws IOException {
                        HttpURLConnection connection = super.createConnection(url);
                        Log.i("VolleyClient", "Connection created");
//                    if(connection instanceof HttpsURLConnection) {
//                        ((HttpsURLConnection)connection).setSSLSocketFactory(getSSLSocketFactory(c));
//                        ((HttpsURLConnection)connection).setHostnameVerifier(getHostnameVerifier(url));
//                    }
                        return connection;
                    }
                };

                RequestQueue queue = Volley.newRequestQueue(c, hurlStack);

                StringRequest request = new StringRequest(method, url, this, this) {
                    @Override
                    public Map<String, String> getHeaders() {
                        addHeader("Content-Type", "application/x-www-form-urlencoded");
//                    headers.put("Authorization", basicAuth);
                        addHeader("Accept-Encoding", "");
                        return headers;
                    }

                    @Override
                    protected Map<String, String> getParams() {
                        return params;
                    }
                };

                // Request Time out
                request.setRetryPolicy(new DefaultRetryPolicy(timeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                System.setProperty("http.keepAlive", "false");

                // Default request queue
                queue.add(request);
            } else {
                throw new ConnectException("No network access detected");
            }
        } catch (Exception e) {
            Log.e("RestClint", "Exception-------------------\n");
            throw e;
        }
    }
}
