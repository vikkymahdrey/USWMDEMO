package com.team.app.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team.app.domain.Role;

public interface RoleDao extends JpaRepository<Role, Serializable> {

	@Query("SELECT r FROM Role r WHERE r.type=:type")
	Role getRoleByUserType(@Param("type") String type);

	@Query("SELECT r FROM Role r WHERE r.id=:roleId")
	Role getRoleByRoleId(@Param("roleId") String roleId);

	@Query("FROM Role")
	List<Role> getRoles();

}
