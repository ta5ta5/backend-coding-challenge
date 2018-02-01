package config;

import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

	public MvcConfig() {
		super();
	}

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}

	/**
	 * Register dispatcherServlet programmatically
	 * 
	 * @return ServletRegistrationBean
	 */
	@Bean
	public ServletRegistrationBean dispatcherServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet(), "/app/*");
		registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
		return registration;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}