package com.racegofer.api.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Created by Miles on 11/9/2014.
 */

@RepositoryRestResource(collectionResourceRel = "User", path = "Users")
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUserName(@Param("userName") String userName);
}
