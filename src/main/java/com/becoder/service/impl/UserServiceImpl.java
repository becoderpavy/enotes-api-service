package com.becoder.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.becoder.dto.EmailRequest;
import com.becoder.dto.UserDto;
import com.becoder.entity.Role;
import com.becoder.entity.User;
import com.becoder.repository.RoleRepository;
import com.becoder.repository.UserRepository;
import com.becoder.service.UserService;
import com.becoder.util.Validation;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private Validation validation;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private EmailService emailService;

	@Override
	public Boolean register(UserDto userDto) throws Exception {

		validation.userValidation(userDto);
		User user = mapper.map(userDto, User.class);

		setRole(userDto, user);

		User saveUser = userRepo.save(user);
		if (!ObjectUtils.isEmpty(saveUser)) {
			// send email
			emailSend(saveUser);
			return true;
		}
		return false;
	}

	private void emailSend(User saveUser) throws Exception {
		
		String message="Hi,<b>"+saveUser.getFirstName()+"</b> "
				+ "<br> Your account register sucessfully.<br>"
				+"<br> Click the below link verify & Active your account <br>"
				+"<a href='#'>Click Here</a> <br><br>"
				+"Thanks,<br>Enotes.com"
				;
		
		EmailRequest emailRequest = EmailRequest.builder()
				.to(saveUser.getEmail())
				.title("Account Creating Confirmation")
				.subject("Account Created Success")
				.message(message)
				.build();
		emailService.sendEmail(emailRequest);
	}

	private void setRole(UserDto userDto, User user) {
		List<Integer> reqRoleId = userDto.getRoles().stream().map(r -> r.getId()).toList();
		List<Role> roles = roleRepo.findAllById(reqRoleId);
		user.setRoles(roles);
	}

}
