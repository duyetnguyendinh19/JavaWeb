package shop.newshop.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shop.newshop.Entity.Department;
import shop.newshop.Service.DepartmentService;
import shop.newshop.config.DepartmentConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DepartmentConfig.class})
public class DepartmentControllerTest {

	private DepartmentService departmentService;
	
	@Autowired
    public void setProductService(DepartmentService departmentService){
        this.departmentService = departmentService;
    }
	
	@Test
    public void testGetDepartment(){
        Department depart = departmentService.getDepartById(1);
        assertEquals("Nhân sự", depart.getName());
    }
	
}
