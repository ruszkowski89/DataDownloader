package com.ruszkowski.datadownloader.httprequestexecutor;

public class HttpRequestExecutionException extends Exception {

    public HttpRequestExecutionException(String errorMsg) {
        super(String.format("Sorry, data could not be downloaded from external service. %s", errorMsg));
    }

}
