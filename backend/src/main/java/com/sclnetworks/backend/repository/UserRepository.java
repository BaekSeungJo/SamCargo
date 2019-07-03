package com.sclnetworks.backend.repository;

import com.sclnetworks.backend.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
