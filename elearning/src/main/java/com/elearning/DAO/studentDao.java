/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearning.DAO;

import com.elearning.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Abdelrahman
 */
@Repository
public interface studentDao extends JpaRepository<Student, Long> {
    
    Student findByname(String name);
    
}
