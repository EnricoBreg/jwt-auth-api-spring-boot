package com.terricabrel.authapi.repositories;

import com.terricabrel.authapi.entities.Role;
import com.terricabrel.authapi.entities.enums.RoleEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(RoleEnum name);
}
