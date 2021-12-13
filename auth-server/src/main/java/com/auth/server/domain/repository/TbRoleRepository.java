package com.auth.server.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.auth.server.domain.entity.TbRole;

@Repository
public interface TbRoleRepository extends JpaRepository<TbRole, String> {
	
	@Query("Select tbRole From TbRole tbRole " 
			+ "Left Join TbUserRole tbUserRole on tbRole.id = tbUserRole.roleId "
			+ "Where tbUserRole.userId = :userId ")
	public Optional<TbRole> findByUserId(String userId);
}
