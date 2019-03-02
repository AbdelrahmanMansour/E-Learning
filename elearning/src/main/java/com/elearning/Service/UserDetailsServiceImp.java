/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearning.Service;

import com.elearning.DAO.studentDao;
import com.elearning.Model.Role;
import com.elearning.Model.Student;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Abdelrahman
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService{
    
    @Autowired
    private studentDao userrepo;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           Student st = userrepo.findByname(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : st.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User
                                       (st.getName(), st.getPassword(), grantedAuthorities);
        
   
    }
    
}
