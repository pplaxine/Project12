package com.biocycle.staffCRUD;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.biocycle.staffCRUD.dao.StaffDao;
import com.biocycle.staffCRUD.model.Staff;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StaffCrudApplicationTests {

	@Autowired
	private StaffDao staffDao;
	
	@Test
	public void contextLoads() {
	
		Staff staff = staffDao.findById(1).get();
		staff.setName("BLOPY BLOOP");
		
		staffDao.save(staff);
		
		System.out.println(staff);
	}

}
