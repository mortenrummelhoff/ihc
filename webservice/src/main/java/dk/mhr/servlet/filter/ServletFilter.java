package dk.mhr.servlet.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class ServletFilter extends BasicAuthenticationFilter {

	private Logger aLog = Logger.getLogger(getClass());

	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub

		aLog.info("doFilter called for MHR Servlet");
		
		super.doFilter(arg0, arg1, arg2);
	}

}
