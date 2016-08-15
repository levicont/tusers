package com.lvg.tusers.dao.repository;

import org.springframework.data.repository.CrudRepository;

import com.lvg.tusers.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
