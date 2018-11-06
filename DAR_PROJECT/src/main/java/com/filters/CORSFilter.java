package com.filters;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(asyncSupported = true, urlPatterns = { "/*" })
public class CORSFilter implements Filter {
    private static final Logger log = Logger.getAnonymousLogger();

    /**
     * Default constructor.
     */
    public CORSFilter() {
        // TODO Auto-generated constructor stub
    	System.out.println("cors");
    }
 
    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }
 
    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        System.out.println("CORSFilter HTTP Request: " + request.getMethod());
// 
//        // Authorize (allow) all domains to consume the content
//        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
//        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
// 
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
// 
//        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
//        if (request.getMethod().equals("OPTIONS")) {
//            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
//            return;
//        }
// 
//        // pass the request along the filter chain
//        chain.doFilter(request, servletResponse);
    	
    	   log.info("Adding Access Control Response Headers");
           HttpServletResponse response = (HttpServletResponse) servletResponse;
           response.setHeader("Access-Control-Allow-Origin", "*");
           response.setHeader("Access-Control-Allow-Credentials", "true");
           response.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, OPTIONS");
           response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
           filterChain.doFilter(servletRequest, servletResponse);
    }
 
    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }
}
