package com.javainuse.controllers;

import com.javainuse.model.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public Employee firstPage()  {
        Employee emp = new Employee();
        emp.setName("emp1");
        emp.setDesignation("manager");
        emp.setEmpId("1");
        emp.setSalary(3000);
        logger.info(emp.toString());
        return emp;
    }


}
