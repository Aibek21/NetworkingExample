package kz.kbtu.networkingexample;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aibekkuralbaev on 14.11.16.
 */



public class BaseResponse<T> {

    @SerializedName("success")
    @Nullable
    @Expose
    private Boolean success;
    @SerializedName("error")
    @Nullable
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Nullable
    @Expose
    private String message;
    @SerializedName("data")
    @Nullable
    @Expose
    private T data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
