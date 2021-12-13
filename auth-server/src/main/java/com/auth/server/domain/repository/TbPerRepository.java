package com.auth.server.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.auth.server.domain.entity.TbPermission;

@Repository
public interface TbPerRepository extends JpaRepository<TbPermission, String> {

	@Query("Select tbPer From TbPermission tbPer " 
			+ "Left Join TbRolePermission tbRolePer on tbPer.id = tbRolePer.permissionId "
			+ "Left Join TbUserRole tbUserRole on tbRolePer.roleId = tbUserRole.roleId "
			+ "Where tbUserRole.userId = :userId ")
	public Optional<List<TbPermission>> findByUserId(String userId);
}
