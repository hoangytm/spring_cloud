package com.hoangytm.zuulservice.filter;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author PhanHoang
 * 7/18/2020
 */
public class RouteFilter extends ZuulFilter {
    Logger logger = LoggerFactory.getLogger(RouteFilter.class);

    @Override
    public String filterType() {
        return "route";
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
        logger.info("using route filter");
        return null;
    }
}
