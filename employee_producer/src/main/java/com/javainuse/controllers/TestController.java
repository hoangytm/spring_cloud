package com.javainuse.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.model.Employee;

@RestController
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getDataFallback")
    public Employee firstPage() throws Exception {

        Employee emp = new Employee();
        emp.setName("emp1");
        emp.setDesignation("manager");
        emp.setEmpId("1");
        emp.setSalary(3000);
//        if (emp.getName() != null) throw new Exception("new exception");
        return emp;
    }

    public Employee getDataFallback() {
        Employee emp = new Employee();
        emp.setName("fallback-emp1");
        emp.setDesignation("fallback-manager");
        emp.setEmpId("fallback-1");
        emp.setSalary(3000);
        System.out.println(" da vao ham fallback");
        logger.info("this is emp",emp.toString());
        return emp;
    }

}
