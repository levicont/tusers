package com.lvg.tusers.repositories;

import org.springframework.data.repository.CrudRepository;

import com.lvg.tusers.models.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
