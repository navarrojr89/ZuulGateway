package com.lamn.microservices.zuulgateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Post consumed time filter.
 */
@Component
public class PostConsumedTimeFilter extends ZuulFilter {

    private static final Logger LOG = LoggerFactory.getLogger(PostConsumedTimeFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        LOG.info("Getting into post filter");

        Long initTime = (Long) request.getAttribute("initTime");
        Long endTime = System.currentTimeMillis();
        long consumedTime = endTime - initTime;

        LOG.info(String.format("Consumed time [%s] s", (double) consumedTime /1000.00));
        LOG.info(String.format("Consumed time [%s] ms", consumedTime));

        return null;
    }
}
