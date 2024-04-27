package vn.gvt.ENote.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.gvt.ENote.Models.User;
import vn.gvt.ENote.Repositories.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findUserByEmail(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		} else {
			return new CustomUser(user);
		}
		
	}

}