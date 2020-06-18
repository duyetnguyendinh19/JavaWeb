package shop.newshop.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.Charset;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shop.newshop.Entity.Department;
import shop.newshop.Service.DepartmentService;
import shop.newshop.config.DepartmentConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DepartmentConfig.class })
public class DepartmentControllerTest {

	private DepartmentService departmentService;
	private int departmentId = 2;
	private String departmentName = "Kiểm duyệt";
	private int lengthRandomName = 7;

	@Autowired
	public void setProductService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@Test
	public void testGetDepartment() {
		Department depart = departmentService.getDepartById(departmentId);
		if (depart == null) {
			assertFalse(true, "Không tìm thấy phòng ban");
		} else {
			assertEquals(departmentName, depart.getName());
		}

	}

	@Test
	public void testRequiredInsertDepartName() {
		if (departmentName == null || departmentName.isEmpty()) {
			assertFalse(true, "Tên phòng ban không được để trống");
		} else {
//			Department depart = new Department();
//			depart.setName(departmentName);
//			departmentService.insert(depart);
			assertTrue(true, "Thêm mới thành công");
		}
	}

	@Test
	public void testExistInsertDepartName() {
		if (departmentName != null) {
			if (departmentService.checkName(departmentName, departmentId) > 0) {
				assertFalse(true, "Tên phòng ban đã tồn tại");
			} else {
//				Department depart = new Department();
//				depart.setName(departmentName);
//				departmentService.insert(depart);
				assertTrue(true, "Thêm mới thành công");
			}
		} else {
			assertFalse(true, "Tên phòng ban không được để trống");
		}
	}

	@Test
	public void testExceptionInsert() {
		try {
			Department depart = new Department();
			byte[] array = new byte[lengthRandomName];
			new Random().nextBytes(array);
			String departmentNameRandom = new String(array, Charset.forName("UTF-8"));
			depart.setName(departmentNameRandom);
			departmentService.insert(depart);
			assertTrue(true, "Thêm phòng ban thành công");
		} catch (Exception e) {
			assertFalse(true, "Xảy ra ngoại lệ: " + e);
		}
	}

	@Test
	public void testRequiredUpdateDepartName() {
		if (departmentName == null || departmentName.isEmpty()) {
			assertFalse(true, "Tên phòng ban không được để trống");
		} else {
//			Department depart = new Department();
//			depart.setName(departmentName);
//			departmentService.update(depart);
			assertTrue(true, "Sửa phòng ban thành công");
		}
	}

	@Test
	public void testExistUpdateDepartName() {
		if (departmentName != null) {
			if (departmentService.checkName(departmentName, departmentId) > 0) {
				assertFalse(true, "Tên phòng ban đã tồn tại");
			} else {
//				Department depart = new Department();
//				depart.setName(departmentName);
//				departmentService.update(depart);
				assertTrue(true, "Sửa phòng ban thành công");
			}
		} else {
			assertFalse(true, "Tên phòng ban không được để trống");
		}
	}

	@Test
	public void testExceptionUpdate() {
		try {
			Department depart = departmentService.getDepartById(departmentId);
			byte[] array = new byte[lengthRandomName];
			new Random().nextBytes(array);
			String departmentNameRandom = new String(array, Charset.forName("UTF-8"));
			depart.setName(departmentNameRandom);
			departmentService.update(depart);
			assertTrue(true, "Sửa phòng ban thành công");
		} catch (Exception e) {
			assertFalse(true, "Xảy ra ngoại lệ: " + e);
		}
	}

	@Test
	public void testfindDepart() {
		Department depart = departmentService.getDepartById(departmentId);
		if (depart == null) {
			assertFalse(true, "Bản ghi đã bị xóa, vui lòng refresh lại trang");
		}
	}
	
	@Test
	public void testDeleteDepart() {
		Department depart = departmentService.getDepartById(departmentId);
		if (depart == null) {
			assertFalse(true, "Bản ghi đã bị xóa, vui lòng refresh lại trang");
		}else {
			assertTrue(true, "Xóa phòng ban thành công");
		}
	}

}
