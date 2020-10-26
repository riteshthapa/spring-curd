package com.cubicit.controller.json;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cubicit.controller.vo.ApplicationResponseVO;
import com.cubicit.controller.vo.CustomerVO;
import com.cubicit.controller.vo.UpdateRoles;
import com.cubicit.service.CustomerServiceImpl;
@RestController
//this annotation ensures all the method inside will generate
//raw data ->>JSON,XML etc
@RequestMapping("/v3")
public class CustomerController {
	
	//Spring data jpa - Spring jdbc
	@Autowired	
	private CustomerServiceImpl customerService;
	
	@PostMapping("/customers")
	public ApplicationResponseVO createCustomer(@RequestBody CustomerVO customerVO){
		System.out.println(customerVO);
		customerService.save(customerVO);
		//We will save it into database
		ApplicationResponseVO applicationResponseVO=new ApplicationResponseVO();
		applicationResponseVO.setStatus("200");
		applicationResponseVO.setMessage("customer is added email = "+customerVO.getEmail());
		return applicationResponseVO;
	} 
	
	@GetMapping(value="/customers",params={"cid"})
	public CustomerVO getCustomerByCid(@RequestParam int  cid){
		CustomerVO  customerVO= customerService.findById(cid);
		
		return customerVO; //view resolver is not coming in pic
	}
	
	@GetMapping(value="/customers",params={"email"})
	public CustomerVO getCustomer(@RequestParam String email){
		CustomerVO  customerVO=customerService.findByEmail(email);
		return customerVO; //view resolver is not coming in pic
	}
	
//	@GetMapping(value="/customers",params={"debitcard"})
//	public CustomerVO verifyCustomer(@RequestParam String debitcard ){
//		CustomerVO  customerVO=customerService.findByDebitCard(debitcard);
//		return customerVO; //view resolver is not coming in pic
//	}
	
	@GetMapping("customers/roles")
	public List<String> findRoles(@RequestParam int  cid){
		return customerService.findRolesForCustomer(cid);
	} 
	
	@GetMapping("/customers")
	public List<CustomerVO> getCustomers(){
		List<CustomerVO> customerVOs = customerService.findAllCustomers();
		return customerVOs;
	}
	
	@PutMapping("customers/roles")
	public String updateCustomerRoles(@RequestBody UpdateRoles updateRoles){//{"cid":19,sroles:[1,4]}//List<Integer> sroles;
		return customerService.updateRolesForCustomer(updateRoles);
	}
	 
	@PutMapping("customers")
	public String updateCustomer(@RequestBody CustomerVO customerVO){
		return customerService.updateCustomer(customerVO);
	}
	
	@PutMapping("changePassword")
	public String updatePassword(@RequestBody CustomerVO customerVO){
		return customerService.changePassword(customerVO);
	}
}
