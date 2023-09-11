package com.powerlogix.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.powerlogix.models.Role;


public interface RoleRepository extends JpaRepository<Role, Long>
{
	
    public	Role findByRoleName(String roleName);

}
