package com.auth.server.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.server.domain.entity.TbRolePermission;

@Repository
public interface TbRolePerRepository extends JpaRepository<TbRolePermission, String> {

}
