package service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.cubicit.controller.vo.CustomerVO;
import com.cubicit.dao.AddressDaoRepository;
import com.cubicit.dao.CustomerDaoRepository;
import com.cubicit.entity.CustomerEntity;
import com.cubicit.service.CustomerServiceImpl;

//JUNIT RUNNER from JUNIT
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {
	
	@Mock
    private CustomerDaoRepository customerDaoRepository;
		
	@Mock	
	private AddressDaoRepository addressDaoRepository;
	
	@InjectMocks
	// creating instance of RolesServiceImpl and inject mock roleRepository
	// inside
	private CustomerServiceImpl customerServiceImpl;
	

	@Before
	public void beforeEveryTestCase() {
		MockitoAnnotations.initMocks(this); // Initializing mocking for each
		// test cases
	}
	
	public void testUpdateCustomer(){
		
		CustomerEntity customerEntity=new CustomerEntity();
		customerEntity.setCid(200);
		customerEntity.setEmail("nagendra@gmail.com");
//		customerEntity.setMobile("9872525234");
		customerEntity.setFirstName("Nagendra");
		customerEntity.setLastName("Kumar");
		
		//When we passing object as parameter
		Optional<CustomerEntity> optional=Optional.of(customerEntity);
		when(customerDaoRepository.findById(200)).thenReturn(optional);

		CustomerVO customerVO=new CustomerVO();
		customerVO.setCid(200);
		customerVO.setEmail("nagendra@gmail.com");
		customerVO.setFirstName("Nagendra");
		customerVO.setLastName("Kumar");
		String result=customerServiceImpl.updateCustomer(customerVO);
		assertEquals("updated", result);

	}
	
	@Test
	public void testSaveCustomer(){
		
		CustomerEntity customerEntity=new CustomerEntity();
		customerEntity.setCid(200);
		customerEntity.setEmail("nagendra@gmail.com");
		customerEntity.setMobile("9872525234");
		customerEntity.setFirstName("Nagendra");
		customerEntity.setLastName("Kumar");
		//When we passing object as parameter
		when(customerDaoRepository.save(any(CustomerEntity.class))).thenReturn(customerEntity);
		
		CustomerVO customerVO=new CustomerVO();
		customerVO.setCid(200);
		customerVO.setEmail("nagendra@gmail.com");
		customerVO.setMobile("9872525234");
		customerVO.setFirstName("Nagendra");
		customerVO.setLastName("Kumar");
		
		String result=customerServiceImpl.save(customerVO);
		assertEquals("saved", result);
	}
	

	@Test
	public void testFindById() {
		CustomerEntity customerEntity=new CustomerEntity();
		customerEntity.setCid(200);
		customerEntity.setEmail("nagendra@gmail.com");
		customerEntity.setMobile("9872525234");
		customerEntity.setFirstName("Nagendra");
		customerEntity.setLastName("Kumar");
		Optional<CustomerEntity> optional=Optional.of(customerEntity);
		when(customerDaoRepository.findById(200)).thenReturn(optional);
		
		CustomerVO customerVO=customerServiceImpl.findById(200);
		assertEquals("Nagendra", customerVO.getFirstName());
		assertEquals("Kumar", customerVO.getLastName());
		assertEquals("nagendra@gmail.com", customerVO.getEmail());
		assertEquals("9872525234", customerVO.getMobile());
		verify(customerDaoRepository, times(1)).findById(200);
		verifyNoMoreInteractions(customerDaoRepository);
		
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testFindByIdWhenNotExist() {
		Optional<CustomerEntity> optional=Optional.empty();
		when(customerDaoRepository.findById(200)).thenReturn(optional);
	    customerServiceImpl.findById(200);
	    verify(customerDaoRepository, times(1)).findById(200);
	    verifyNoMoreInteractions(customerDaoRepository);
	}


	/*@Test
	public void testFindAllCustomers() {
		fail("Not yet implemented");
	}
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}
	@Test
	public void testSave() {
		fail("Not yet implemented");
	}
	@Test
	public void testFindByEmail() {
		fail("Not yet implemented");
	}
	@Test
	public void testFindRolesForCustomer() {
		fail("Not yet implemented");
	}
	@Test
	public void testUpdateRolesForCustomer() {
		fail("Not yet implemented");
	}
*/
}