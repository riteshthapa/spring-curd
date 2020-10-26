package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.cubicit.controller.vo.RoleVO;
import com.cubicit.dao.RoleRepository;
import com.cubicit.entity.RoleEntity;
import com.cubicit.service.RolesServiceImpl;

//JUNIT RUNNER from JUNIT
@RunWith(MockitoJUnitRunner.class)
public class RolesServiceImplTest {
	
	@Mock // creates a mock implementation of the class
	private RoleRepository roleRepository;

	@InjectMocks
	// creating instance of RolesServiceImpl and inject mock roleRepository
	// inside
	// RolesServiceImpl is real object
	private RolesServiceImpl rolesServiceImpl;

	@Before
	public void beforeEveryTestCase() {
		MockitoAnnotations.initMocks(this); // Initializing mocking for each
											// test cases
	}
	
//	
//	@Test
//	public void testCreateRoleWhenNotExist(){
//		
//		Optional<RoleEntity> orole =Optional.empty();
//		RoleVO roleVO=new RoleVO();
//		roleVO.setName("ADMIN");
//		roleVO.setRid(100);
//		roleVO.setDescription("NA");
//		when(roleRepository.findByName(roleVO.getName())).thenReturn(orole);
//		
//		
//		RoleEntity entity=new RoleEntity();
//		entity.setName("ADMIN");
//		entity.setRid(100);
//		entity.setDescription("NA");
//		when(roleRepository.save(entity)).thenReturn(entity);
//		
//		int result=rolesServiceImpl.createRole(roleVO);
//		assertEquals(100,result);
//	}
//	
//	@Test
//	public void testCreateRoleWhenExist(){
//		
//		RoleEntity entity=new RoleEntity();
//		entity.setName("ADMIN");
//		entity.setRid(100);
//		entity.setDescription("NA");
//		Optional<RoleEntity> orole =Optional.of(entity);
//		
//		RoleVO roleVO=new RoleVO();
//		roleVO.setName("ADMIN");
//		roleVO.setRid(100);
//		roleVO.setDescription("NA");
//		when(roleRepository.findByName(roleVO.getName())).thenReturn(orole);
//		int result=rolesServiceImpl.createRole(roleVO);
//		assertEquals(0,result);
//	}
//	

	@Test
	public void testFindRolesWhenExist() {
		/// List<RoleEntity> listEntity = roleRepository.findAll();
		RoleEntity role1 = new RoleEntity();
		role1.setName("ADMIN");
		role1.setRid(100);

		RoleEntity role2 = new RoleEntity();
		role2.setName("CUSTOMER");
		role2.setRid(200);

		List<RoleEntity> list = new ArrayList<RoleEntity>();
		list.add(role1);
		list.add(role2);

		// stubbing the behavior
		// mocking the method 
		when(roleRepository.findAll()).thenReturn(list); // returns the list of roleEntity

		List<RoleVO> roleVOs = rolesServiceImpl.findRoles();
		assertNotNull(roleVOs);
		assertEquals(2,roleVOs.size());
		assertEquals("ADMIN",roleVOs.get(0).getName());

		verify(roleRepository, times(1)).findAll();
		verifyNoMoreInteractions(roleRepository);
	}
	

	@Test
	public void testFindRolesWhenNotExist() {
		List<RoleEntity> list = new ArrayList<RoleEntity>();
		// stubbing the behavior
		when(roleRepository.findAll()).thenReturn(list);
		List<RoleVO> roleVOs = rolesServiceImpl.findRoles();
		assertNotNull(roleVOs);
		assertEquals(0, roleVOs.size());
		verify(roleRepository, times(1)).findAll();
		verifyNoMoreInteractions(roleRepository);
	}

}
