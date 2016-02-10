package dk.mhr.servlet.filter;

import java.util.Hashtable;

import javax.servlet.Filter;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleContext;
import org.springframework.osgi.context.BundleContextAware;
import org.springframework.osgi.context.support.OsgiBundleXmlApplicationContext;
import org.springframework.security.config.BeanIds;
import org.springframework.security.web.FilterChainProxy;


public class SpringSecurityFilterActivator implements BundleContextAware {

	private Logger logger = Logger.getLogger(getClass()); 

	public SpringSecurityFilterActivator() {
		logger.debug("Starting Spring Security Filter");

	}


	private void registerFilter(BundleContext bundleContext) {
		OsgiBundleXmlApplicationContext xmlContext = new OsgiBundleXmlApplicationContext();

		xmlContext.setBundleContext(bundleContext);
		xmlContext.setConfigLocations(new String[] {"classpath:META-INF/security/securityContext.xml"});

		xmlContext.refresh();

		FilterChainProxy springFilter = (FilterChainProxy) 
				xmlContext.getBean(BeanIds.SPRING_SECURITY_FILTER_CHAIN);

		logger.debug("SpringFilter: " + springFilter);	

		Hashtable<String, Object> properties = new Hashtable<String, Object>();
		properties.put("filter-name", new String[] {BeanIds.SPRING_SECURITY_FILTER_CHAIN});
		properties.put("urlPatterns", "/**");
		properties.put("servletNames", new String[] {"home_ihc_servlet"});
		bundleContext.registerService(Filter.class.getName(), springFilter, properties);
	}

	public void setBundleContext(BundleContext bundleContext) {
		registerFilter(bundleContext);


	}
}
