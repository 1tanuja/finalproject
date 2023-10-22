package com.fooddelivery.services;

import com.fooddelivery.entity.Admin;

public interface AdminService {

	Admin registerAdmin(Admin admin);

	Admin loginAdmin(Admin admin);

}
