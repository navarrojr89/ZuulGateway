package com.lamn.microservices.zuulgateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Pre consumed time filter.
 */
@Component
public class PreConsumedTimeFilter extends ZuulFilter {

    private static final Logger LOG = LoggerFactory.getLogger(PreConsumedTimeFilter.class);

    @Override
    public String filterType() {
        return "pre";
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
        LOG.info(String.format("[%s] request routed to [%s]", request.getMethod(), request.getRequestURL().toString()));

        Long initTime = System.currentTimeMillis();
        request.setAttribute("initTime", initTime);

        return null;
    }
}
