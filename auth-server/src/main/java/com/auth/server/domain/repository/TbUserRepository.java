package com.auth.server.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.auth.server.domain.entity.TbUser;

@Repository
public interface TbUserRepository extends JpaRepository<TbUser, String> {
	
	@Query("Select tbUser From TbUser tbUser " 
			+ "Where tbUser.userName = :userName ")	
	public Optional<TbUser> findByUserName(String userName);
	
	/**
	 * 修改參考範例，不建議此更新資料方式。
	 * 
	 * */
	@Modifying
	@Query("Update TbUser Set email = :email " 
			+ "Where username = :userName ")	
	public void updateEmailByUserName(String email, String userName);
}
