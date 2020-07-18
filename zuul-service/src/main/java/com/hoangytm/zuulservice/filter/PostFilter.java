package com.hoangytm.zuulservice.filter;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author PhanHoang
 * 7/18/2020
 */
public class PostFilter extends ZuulFilter {
    Logger logger = LoggerFactory.getLogger(PostFilter.class);

    @Override
    public String filterType() {
        return "post";
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
        logger.info("using post function");
        return null;
    }
}
