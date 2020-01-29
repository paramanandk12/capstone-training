package com.mindtree.migrationaccelerator.util;

import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mindtree.migrationaccelerator.dto.LoggedInUserDetails;

public class SessionManager extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(SessionManager.class);
	public static Map<String, LoggedInUserDetails<?>> loggedInUserDetails = new HashedMap<>();
	
	private HttpServletRequest getCurrentHttpRequest(){
	    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
	    if (requestAttributes instanceof ServletRequestAttributes) {
	        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
	        return request;
	    }
	    logger.debug("HTTP request in null at this point...");
	    return null;
	}
	
	protected HttpSession getSession() {
		HttpServletRequest request = getCurrentHttpRequest();
		//returns null, if the session is not associated with the request.
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			logger.info("Creating new Session Object.....");
			//returns new session, if the session is not associated with the request
			session = request.getSession(true);
		}
		
		return session;
	}

	public static Object getLoggedInUser(String sessionID) {
		LoggedInUserDetails<?> user = loggedInUserDetails.get(sessionID);
		return user.getUser();
	}
	
}
