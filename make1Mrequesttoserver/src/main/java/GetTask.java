import java.io.*;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.*;
import org.apache.http.impl.client.*;

import org.apache.http.protocol.*;

class GetTask implements Runnable {
    private final CloseableHttpClient httpClient;
    private final HttpContext context;
    private final HttpGet httpget;
    Thread runner;
    public GetTask(CloseableHttpClient httpClient, HttpGet httpget) {
        this.httpClient = httpClient;
        this.context = HttpClientContext.create();
        this.httpget = httpget;
    }

    public void run() {
        try {
            CloseableHttpResponse response = httpClient.execute(
                    httpget, context);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println(entity.getContent());
            } finally {
                response.close();
            }
        } catch (ClientProtocolException ex) {
            // handle protocol errors
        } catch (IOException ex) {
            // handle I/O errors
        }
    }
}