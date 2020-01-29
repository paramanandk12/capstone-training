package com.mindtree.mystay.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreFilter extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger(PreFilter.class);
	
	@Override
	public boolean shouldFilter() {
		logger.debug("in should filter method");
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();

	    if(request != null)
	    	logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

		return null;
	}

	@Override
	public String filterType() {
		logger.debug("in filter type method");
		return "pre";
	}

	@Override
	public int filterOrder() {
		logger.debug("in filter order method");
		return 1;
	}

}
