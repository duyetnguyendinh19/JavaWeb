package shop.newshop.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import shop.newshop.Interceptor.ServiceInterceptor;

@SuppressWarnings("deprecation")
@Component
public class InterceptorAppConfig extends WebMvcConfigurerAdapter{

	@Autowired
	ServiceInterceptor ServiceInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(ServiceInterceptor);
	}
}
