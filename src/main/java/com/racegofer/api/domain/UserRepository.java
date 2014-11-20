package com.racegofer.api.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Miles on 11/9/2014.
 */

@Repository
public interface UserRepository extends CrudRepository<RaceUser, Long> {
    RaceUser findByUserName(@Param("userName") String userName);
}
