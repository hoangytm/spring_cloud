package com.javainuse.controllers;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping
public class ConsumerControllerClient {
    private final LoadBalancerClient loadBalancer;

    public ConsumerControllerClient(LoadBalancerClient loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    private static HttpEntity<?> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }

    @GetMapping
    public void getEmployee() throws RestClientException {
        //get instance by load balancer
        ServiceInstance serviceInstance = loadBalancer.choose("EMPLOYEE-PRODUCER");

        String baseUrl = serviceInstance.getUri().toString();

        baseUrl = baseUrl + "/employee";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(baseUrl,
                    HttpMethod.GET, getHeaders(), String.class);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        assert response != null;
        System.out.println(response.getBody());
    }

}