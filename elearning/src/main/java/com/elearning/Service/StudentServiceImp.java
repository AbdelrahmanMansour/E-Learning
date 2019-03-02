/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.elearning.Service;


import com.elearning.DAO.roleDao;
import com.elearning.DAO.studentDao;
import com.elearning.Model.Student;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Abdelrahman
 */
@Service
public class StudentServiceImp {
    
    @Autowired
    private  studentDao studentdao;
    @Autowired
    private roleDao roledao;
    @Autowired
    private BCryptPasswordEncoder bcryptpass;
   

    public void save(Student student) {
        
        student.setPassword(bcryptpass.encode(student.getPassword()));
        student.setRoles(new HashSet<>(roledao.findAll()));
        studentdao.save(student);
        
    }

   
    
}
