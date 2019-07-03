package com.sclnetworks.backend.service;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Override
    public void findByLoginId(int id) {

    }
}
