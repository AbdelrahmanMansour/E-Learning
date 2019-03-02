/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearning.Validator;


import com.elearning.DAO.studentDao;
import com.elearning.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Abdelrahman
 */
public class StudentValidator implements Validator{
    
      @Autowired
    private studentDao studentdao;

    public boolean supports(Class<?> aClass) {
        return Student.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        Student st = (Student) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (st.getName().length() < 6 || st.getName().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (studentdao.findByname(st.getName()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (st.getPassword().length() < 8 || st.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!st.getPasswordConfirm().equals(st.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }

    
}
