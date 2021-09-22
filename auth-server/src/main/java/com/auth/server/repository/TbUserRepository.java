package com.auth.server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.auth.server.entity.TbUser;

@Repository
public interface TbUserRepository extends JpaRepository<TbUser, String> {
	
	@Query("Select tbUser From TbUser tbUser " 
			+ "Where tbUser.userName = :userName ")	
	public Optional<TbUser> findByUserName(String userName);
}
