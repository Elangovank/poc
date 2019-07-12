package com.poc.app.http;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mathan on 17/08/18.
 */
public class Response implements Serializable {

    @SerializedName("response_code")
    public int responseCode;

    @SerializedName("response_message")
    public String message;

    @SerializedName("custom_message")
    public String customMessage;

    @SerializedName("xmlns")
    public String xmlns;

    @SerializedName("content")
    public String content;

    /**
     * unique integer number for the request
     */
    public int requestType;


    public String extraOutput;

    /**
     * @return true if the response gets success id
     */
    public boolean isSuccess() {
        return responseCode == 200;
    }

    @Override
    public String toString() {
        if (customMessage == null) {
            return message;
        }
        return message + ". \t" + customMessage;
    }
}
