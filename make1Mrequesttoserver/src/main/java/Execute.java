import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;


/**
 * @author PhanHoang
 * 6/16/2020
 */
public class Execute {
    public static void main(String[] args) throws InterruptedException {

        PoolingHttpClientConnectionManager cm =
                new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200); // increase max total connection to 200
        cm.setDefaultMaxPerRoute(20); // increase max connection per route to 20
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
        String url = "http://localhost:8080/";
        String[] urisToGet = {url,url,url,url,url,url,url,url};
// start a thread for each URI
// (if there are many URIs, a thread pool would be better)
        Thread[] threads = new Thread[urisToGet.length];
        for (int i = 0; i < threads.length; i++) {
            HttpGet httpget = new HttpGet(urisToGet[i]);
            threads[i] = new Thread(new GetTask(httpClient, httpget));
            threads[i].start();
        }
// wait for all the threads to finish
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
    }
}
