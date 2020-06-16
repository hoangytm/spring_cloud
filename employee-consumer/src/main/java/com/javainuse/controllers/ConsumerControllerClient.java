package com.javainuse.controllers;


import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

@RestController
public class ConsumerControllerClient {
	@Autowired
	LoadBalancerClient loadBalancer;
@GetMapping
	public void getEmployee() throws RestClientException, InterruptedException {
		PoolingHttpClientConnectionManager cm =
				new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(200); // increase max total connection to 200
		cm.setDefaultMaxPerRoute(20); // increase max connection per route to 20
//		CloseableHttpClient httpClient = HttpClients.custom()
//				.setConnectionManager(cm)
//				.build();
		Thread[] threads = new Thread[1000];
		for(int i=0;i<1000;i++){
			threads[i] = new Thread(new Threadsss(loadBalancer));
			threads[i].start();
		}
		for (int i = 0; i < 1000; i++) {
			threads[i].join();
		}
	}


}