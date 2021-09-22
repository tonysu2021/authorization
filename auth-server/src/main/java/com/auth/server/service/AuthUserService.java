package com.auth.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth.server.entity.TbPermission;
import com.auth.server.entity.TbUser;

@Service
public class AuthUserService implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthUserService.class);
	
	@Autowired
	private TbUserService tbUserService;
	
	@Autowired
	private TbPerService tbPerService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<TbUser> tbUserOpt = tbUserService.findByUserName(username);

		if (!tbUserOpt.isPresent()) {
			logger.info("{} not found",username);
			throw new UsernameNotFoundException(username + " not found");
		}
		TbUser tbUser = tbUserOpt.get();
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		
		Optional<List<TbPermission>> tbPersOpt = tbPerService.findByUserId(tbUser.getId());
		if (tbPersOpt.isPresent()) {
			grantedAuthorities = tbPersOpt.get().stream().map((TbPermission thPer) -> 
				new SimpleGrantedAuthority(thPer.getEnname())
			).collect(Collectors.toList());
		}
		logger.info("{}'s authentication success",username);
		return User.builder()
				.username(tbUser.getUserName())
				.password(tbUser.getPassword())
				.authorities(grantedAuthorities)
				.build();
	}

}
