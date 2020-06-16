package shop.newshop.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import shop.newshop.Service.DepartmentService;
import shop.newshop.ServiceImpl.DepartmentServiceImpl;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages={"shop.newshop"})
@ComponentScan(basePackages={"shop.newshop.Service","shop.newshop.ServiceImpl","shop.newshop.DAO","shop.newshop.DaoImpl"})
public class DepartmentConfig {
	
	@Bean
    DepartmentService departmentService(){
        return new DepartmentServiceImpl();
    }
	
}
