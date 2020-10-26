package com.cubicit.controller.json;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cubicit.controller.vo.ApplicationResponseVO;
import com.cubicit.controller.vo.CustomerVO;
import com.cubicit.controller.vo.RoleVO;
import com.cubicit.controller.vo.UpdateRoles;
import com.cubicit.service.RolesServiceImpl;

@RestController
//this annotation ensures all the method inside will generate
//raw data ->>JSON,XML etc
@RequestMapping("/v3")
public class RolesController {
	
	@Autowired
	private RolesServiceImpl rolesServiceImpl;
	
	@GetMapping("/roles")
	public List<RoleVO> getRoles(){
		List<RoleVO> roleVOs = rolesServiceImpl.findRoles();
		return roleVOs;
	}
	
	@PostMapping("/roles")
	public ApplicationResponseVO createRole(@RequestBody RoleVO roleVO){
		int roleid=rolesServiceImpl.createRole(roleVO);
		ApplicationResponseVO applicationResponseVO=new ApplicationResponseVO();
		if(roleid==0){
			applicationResponseVO.setMessage("Role already exists!!!!");
		}else{
			applicationResponseVO.setMessage("Role is created successfully with id = "+roleid);	
		}
		applicationResponseVO.setStatus("success");
		return applicationResponseVO;
	}
	
	@GetMapping(value="/roles",params={"rid"})
	public RoleVO getRoleById(@RequestParam int  rid){
		RoleVO roleVO= rolesServiceImpl.findById(rid);
		return roleVO;
	}
	
	
	@DeleteMapping("/roles/delete")
	public ApplicationResponseVO deleteRole(@RequestBody RoleVO roleVO){
		System.out.println(roleVO);
		rolesServiceImpl.deleteRole(roleVO);
		//We will save it into database
		ApplicationResponseVO applicationResponseVO=new ApplicationResponseVO();
		applicationResponseVO.setStatus("200");
		applicationResponseVO.setMessage("role deleted with name : "+ roleVO.getName());
		return applicationResponseVO;
	} 
	
	@PutMapping("roles/edit")
	public String editRoles(@RequestBody RoleVO roleVO){
		rolesServiceImpl.editRolesDes(roleVO);
		return "roles name and description updated";
	}
	
}
