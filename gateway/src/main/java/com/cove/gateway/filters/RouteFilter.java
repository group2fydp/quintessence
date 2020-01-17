package com.cove.gateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

public class RouteFilter extends ZuulFilter {
    @Override
    public String filterType(){
        return "route";
    }

    @Override
    public int filterOrder(){
        return 1;
    }

    @Override
    public boolean shouldFilter(){
        return true;
    }

    @Override
    public Object run(){
        System.out.println("Inside Route Filter");
        return null;
    }
}
