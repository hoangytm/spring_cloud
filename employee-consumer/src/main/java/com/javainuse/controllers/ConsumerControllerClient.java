package com.javainuse.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
@Controller
public class ConsumerControllerClient {

	public void getEmployee() throws RestClientException, InterruptedException {
		PoolingHttpClientConnectionManager cm =
				new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(200); // increase max total connection to 200
		cm.setDefaultMaxPerRoute(20); // increase max connection per route to 20
//		CloseableHttpClient httpClient = HttpClients.custom()
//				.setConnectionManager(cm)
//				.build();
		Thread[] threads = new Thread[10];
		for(int i=0;i<10;i++){
			threads[i] = new Thread(new Threadsss());
			threads[i].start();
		}
		for (int i = 0; i < 10; i++) {
			threads[i].join();
		}
	}


}