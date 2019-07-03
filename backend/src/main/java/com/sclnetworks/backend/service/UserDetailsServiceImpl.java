package com.sclnetworks.backend.service;

import com.sclnetworks.backend.model.UserEntity;
import com.sclnetworks.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        UserEntity user = new UserEntity();//userRepository.findUserByLoginId(loginId);
        if (user == null) throw new UsernameNotFoundException("");
//
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (RoleEntity role : user.getRoles()) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        }

//        return new org.springframework.security.core.userdetails.User(user.getLoginId(), user.getPassword(), grantedAuthorities);
        return new org.springframework.security.core.userdetails.User("", "", grantedAuthorities);
    }
}
