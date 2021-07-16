package com.iktpreobuka.elektronski_dnevnik.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik.entities.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Integer>{
	
	@Query("SELECT * FROM roles r, user_roles u  WHERE r.role_id = u.role_id AND u.user_id = ?1")
	public ArrayList<RoleEntity> findAllByUserId(Integer userId);

}
