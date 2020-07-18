package com.javainuse.controllers;


import java.io.IOException;
import java.util.List;

import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;

/**
 * @author PhanHoang
 * 7/18/2020
 */
@RestController
public class ConsumerService {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RemoteCallService loadBalancer;

    @GetMapping
    public Object getObject() {
        Employee employee = new Employee();
        try {
            employee = loadBalancer.getData();
            System.out.println(employee.getEmpId());
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return employee;
    }

    @GetMapping("/no-use")
    public Object getEmployee() throws IOException {
        List<ServiceInstance> instances = discoveryClient.getInstances("employee-zuul-service");
        ServiceInstance serviceInstance = instances.get(0);

        String baseUrl = serviceInstance.getUri().toString();
        baseUrl = baseUrl + "producer/employee";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = null;
        responseEntity = restTemplate.exchange(baseUrl,
                HttpMethod.GET, getHeaders(), String.class);
        return responseEntity.toString();
    }

    private static HttpEntity<?> getHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }

}


