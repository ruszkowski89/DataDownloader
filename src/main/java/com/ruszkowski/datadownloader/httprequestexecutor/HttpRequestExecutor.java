package com.ruszkowski.datadownloader.httprequestexecutor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.ExecutionException;

import static java.net.http.HttpResponse.BodyHandlers;

@Service
public class HttpRequestExecutor {
    private static final HttpClient client = HttpClient.newHttpClient();

    public String downloadData(URI uri) throws HttpRequestExecutionException {
        HttpRequest request = HttpRequest.newBuilder(uri)
                                         .timeout(Duration.ofSeconds(30))
                                         .build();
        HttpResponse<String> response;
        try {
            response = client.sendAsync(request, BodyHandlers.ofString()).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new HttpRequestExecutionException(e.getMessage());
        } finally {
            Thread.currentThread().interrupt();
        }

        checkStatusCode(response);
        return response.body();
    }

    private void checkStatusCode(HttpResponse<String> response) throws HttpRequestExecutionException {
        if (!HttpStatus.valueOf(response.statusCode()).is2xxSuccessful()) {
            throw new HttpRequestExecutionException(response.toString());
        }
    }
}
