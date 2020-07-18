package com.hoangytm.zuulservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author PhanHoang
 * 7/18/2020
 */
public class PreFilter extends ZuulFilter {
    Logger logger = LoggerFactory.getLogger(PreFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        logger.info(" request: " + request + " requestURL: " + request.getRequestURI().toString());
        return null;
    }
}
