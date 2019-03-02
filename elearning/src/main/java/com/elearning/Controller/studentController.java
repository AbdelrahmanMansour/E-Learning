/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearning.Controller;

import com.elearning.DAO.courseDao;
import com.elearning.DAO.studentDao;
import com.elearning.Model.Course;
import com.elearning.Model.Student;
import com.elearning.Service.SecurityService;
import com.elearning.Validator.StudentValidator;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Abdelrahman
 */
@RestController
public class studentController {

    private final String registercourseUrl = "{courseId}/student/registercourse/{studentId}";
    private final String unregistercourseUrl = "{courseId}/student/unregistercourse/{studentId}";
    private final String getstudentcourses="student/{id}/courses";
    private final String updatestudent="updatestudent/{id}";
    private final String deletstudent="deletestudent/{id}";
    @Autowired
    studentDao studentdao;
    @Autowired
    courseDao coursedao;
    
     @Autowired
    private SecurityService securityService;

    @Autowired
    private StudentValidator studentValidator;

    @RequestMapping(
            path = registercourseUrl,
            method = POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public void registerCourse(@PathVariable Long courseId, @PathVariable Long studentId) {

        Course course_record = coursedao.findById(courseId).get();
        if (course_record != null) {
            Student student_record = studentdao.findById(studentId).get();

            student_record.getCourses().add(course_record);
            studentdao.save(student_record);
        }

    }

    @RequestMapping(
            path = registercourseUrl,
            method = POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public void UnregisterCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
          Course course_record = coursedao.findById(courseId).get();
        if (course_record != null) {
            Student student_record = studentdao.findById(studentId).get();

            student_record.getCourses().remove(course_record);
            studentdao.save(student_record);
        }
       
    }
    
      @RequestMapping(
            path = getstudentcourses,
            method = GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
      public Set<Course> getStudentCourses(@PathVariable Long id)
      {
        Student student = studentdao.findById(id).get();
        Set<Course> studentcourses = student.getCourses();
        
        
          return studentcourses;
      }
      
        @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("studentForm", new Student());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("studentForm") Student studentForm, BindingResult bindingResult, Model model) {
        studentValidator.validate(studentForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        studentdao.save(studentForm);

        securityService.autologin(studentForm.getName(), studentForm.getPasswordConfirm());

        return "redirect:/welcome";
    }
    
     @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
    
       @RequestMapping(
            path = updatestudent,
            method = PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
       public void updatestudent(@RequestBody Student student)
       {
           Student student_rec=studentdao.findById(student.getId()).get();
           if(student_rec!=null)
           {
               student_rec.setName(student.getName());
               student_rec.setEmail(student.getEmail());
               student_rec.setPassword(student.getPassword());
               student_rec.setPasswordConfirm(student.getPasswordConfirm());
               student_rec.setUsername(student.getUsername());
               
               studentdao.save(student_rec);
           }
       }
       
           @RequestMapping(
            path = deletstudent,
            method = DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
           public void deletstudent(@PathVariable Long id)
           {
        Student student_record = studentdao.findById(id).get();
        studentdao.delete(student_record);
           }
    
}
