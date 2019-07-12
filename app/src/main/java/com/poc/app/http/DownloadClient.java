package com.poc.app.http;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.poc.app.utils.APPNetworkUtil;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


/**
 * Created by Mathan on 17/08/18.
 */

public class DownloadClient extends VolleyClient implements com.android.volley.Response.Listener<byte[]> {

    /**
     * @param c   Context of Application
     * @param m   Request method {@link Request.Method}.Method.POST / others from Request.Method.*
     * @param url Url of the request
     * @param rt  Unique request type from constants. This param may be deprecated in future release.
     */
    public DownloadClient(Context c, int m, String url, int rt) {
        super(c, m, url, rt);
        setTimeout(120);
    }


    @Override
    public void onResponse(byte[] response) {
        try {
            if (l != null) {
                if (responseType == null) {
                    responseType = Response.class;
                }
                Response r;

                String filename = headers.get("Content-Disposition");
                if (filename != null) {
                    FileResponse fr = new FileResponse();
                    r = fr;
                    r.requestType = requestType;

                    filename = filename.split("=")[1].trim();

//                String filename = arrTag[1];
                    filename = filename.replace(":", ".");
//                    filename = filename.replace("/", "-");
                    Log.d("DownloadClient", filename);

                    try {
                        // covert response to input stream
                        InputStream input = new ByteArrayInputStream(response);

                        //Create a file on desired path and write stream data to it
                        File path = Environment.getExternalStorageDirectory();
                        path = new File(path.getAbsoluteFile() + "/Kone");
                        path.mkdir();

                        File file = new File(path, filename);
                        fr.filename = file.getAbsolutePath();

                        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
                        byte data[] = new byte[1024];

                        long total = 0;
                        int count;
                        while ((count = input.read(data)) != -1) {
                            total += count;
                            output.write(data, 0, count);
                        }

                        output.flush();
                        output.close();

                        input.close();
                        Log.d("DownloadClient", String.format("File Downloaded. Name: %s with size %s KB", filename, (double) total / 1024));
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                    r.responseCode = 200;
                    r.message = "Success";
                } else {
                    r = parseResponse(new String(response), responseType);
                    if (r != null) {
                        r.requestType = requestType;
                    }
                }
                r.extraOutput = extraOutput;
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

                Request<byte[]> request = new Request<byte[]>(method, url, this) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        addHeader("Content-Type",
                                "application/x-www-form-urlencoded");
//                    headers.put("Authorization", basicAuth);
                        addHeader("Accept-Encoding", "");
                        return headers;
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        return params;
                    }

                    @Override
                    protected com.android.volley.Response<byte[]> parseNetworkResponse(NetworkResponse response) {
                        if (response != null && response.headers != null) {
                            addHeaders(response.headers);
                        }
                        if (response == null) throw new AssertionError();
                        return com.android.volley.Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
                    }

                    @Override
                    protected void deliverResponse(byte[] response) {
                        onResponse(response);
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
            Log.e("DownloadClient", "Exception-------------------\n");
            throw e;
        }
    }
}
