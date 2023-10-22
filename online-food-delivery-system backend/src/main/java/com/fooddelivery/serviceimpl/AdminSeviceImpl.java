package com.fooddelivery.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.entity.Admin;
import com.fooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.repository.AdminRepository;
import com.fooddelivery.services.AdminService;

@Service
public class AdminSeviceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin registerAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public Admin loginAdmin(Admin admin) {
		return adminRepository.findByAdminEmailIdAndAdminPassword(admin.getAdminEmailId(), admin.getAdminPassword()).orElseThrow(()->new ResourceNotFoundException("admin", "Id", "and Password"));
		
	}

}
