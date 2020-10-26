package com.cubicit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubicit.controller.vo.CustomerVO;
import com.cubicit.controller.vo.RoleVO;
import com.cubicit.dao.RoleRepository;
import com.cubicit.entity.CustomerEntity;
import com.cubicit.entity.RoleEntity;

@Service
public class RolesServiceImpl {
	
	@Autowired	
	private RoleRepository roleDaoRepository;
	
	public List<RoleVO> findRoles() {
		List<RoleEntity> listEntity = roleDaoRepository.findAll();
		List<RoleVO> roleVOs = new ArrayList<RoleVO>();
		for (RoleEntity entity : listEntity) {
			RoleVO roleVO = new RoleVO();
			BeanUtils.copyProperties(entity, roleVO);
			roleVOs.add(roleVO);
		}
		return roleVOs;
	}

	public int createRole(RoleVO roleVO){
		Optional<RoleEntity> orole = roleDaoRepository.findByName(roleVO.getName().toUpperCase());
		
		if(!orole.isPresent()) {
			RoleEntity roleEntity=new RoleEntity();
			//What this line is doing?
			BeanUtils.copyProperties(roleVO, roleEntity);
			RoleEntity entity2 = roleDaoRepository.save(roleEntity);
			return entity2.getRid(); 
		}else {
			return 0;
		}
	}

	public RoleVO findById(int rid) {
		RoleEntity roleEntity=roleDaoRepository.findById(rid).get();
		RoleVO roleVO=new RoleVO();
		BeanUtils.copyProperties(roleEntity, roleVO);
		return roleVO;
	}

	public void deleteRole(RoleVO roleVO) {		
		roleDaoRepository.deleteById(roleVO.getRid());
	}

	@Transactional
	public String editRolesDes(RoleVO roleVO) {
		RoleEntity roleEntity=roleDaoRepository.findById(roleVO.getRid()).get();
		roleEntity.setName(roleVO.getName());
		roleEntity.setDescription(roleVO.getDescription());
		return "roles has been edited";
		
	}
	
}
