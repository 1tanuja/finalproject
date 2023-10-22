package com.fooddelivery.serviceimpl;




import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.fooddelivery.entity.User;
import com.fooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.repository.UserRepository;
import com.fooddelivery.services.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
	private UserRepository userRepository;

   

    @Override
    public User register(User user) {
    	System.out.println("user registred successfuly"+user);
        return userRepository.save(user);
    }



	@Override
	public User authenticate(User user) {
		return userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword())
		.orElseThrow(()->new ResourceNotFoundException("User ", "Id ",user.getEmail()+"and password "+user.getPassword()));
		
	}



	@Override
	public User findByUserId(long userId) {
		return userRepository.findById(userId).get();
		
	}



	@Override
	public User updateUser(User user,long userId) {
		User existingUser=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Customer","Id",userId));	
		existingUser.setFirstname(user.getFirstname());
		existingUser.setLastname(user.getLastname());
		existingUser.setDateOfBirth(user.getDateOfBirth());
		existingUser.setDistrict(user.getDistrict());
		existingUser.setPhoneNo(user.getPhoneNo());
		existingUser.setState(user.getState());
		existingUser.setZipcode(user.getZipcode());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		userRepository.save(existingUser);
		return existingUser;
		
		
	}



	



	@Override
	public User getUserById(long userId) {
		User user = userRepository.findById(userId).get();
		return user;
	}



	


	@Override
	public User getUserByEmail(User user) {
		return this.userRepository.findByEmail(user.getEmail()).orElseThrow(()->new ResourceNotFoundException("Customer ", "Email",user.getEmail() ));
		
	}



	


	



	



  
	

   
	
}


