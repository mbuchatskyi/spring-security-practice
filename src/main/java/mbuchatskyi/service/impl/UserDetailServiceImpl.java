package mbuchatskyi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mbuchatskyi.model.User;
import mbuchatskyi.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Finding in DB user with " + username + " email.");
	
		User user = userRepository.findByEmail(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("Username wasn't found");
		}
		
		return userRepository.findByEmail(username);
	}
}
