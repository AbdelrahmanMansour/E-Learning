/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearning.Controller;

import com.elearning.DAO.courseDao;
import com.elearning.Model.Course;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Abdelrahman
 */
@RestController
public class courseController {
    
    private final String getcourses = "elearning/courses";
    private final String addcourse = "elearning/addcourse";
    private final String updatecourse = "course/{id}";
    private final String deletcourse = "course/{id}";
    
    @Autowired
    courseDao coursedao;
    
    @RequestMapping(
            path = addcourse,
            method = POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    
    public void addcourse(@Valid @RequestBody Course course) {
        coursedao.save(course);
    }
    
    @RequestMapping(
            path = getcourses,
            method = GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Set<Course> getcourses() {
        List<Course> courses = coursedao.findAll();
        Set<Course> courses_set = new HashSet<>(courses);
        return courses_set;
    }
    
    @RequestMapping(
            path = updatecourse,
            method = PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    
    public void updateCourse(@RequestBody Course course) {
        Course course_record = coursedao.findById(course.getId()).get();
        if (course_record != null) {
            course_record.setDescription(course.getDescription());
            course_record.setInstructor(course.getInstructor());
            course_record.setLastdate(course.getLastdate());
            course_record.setPublishdate(course.getPublishdate());
            course_record.setTotalhours(course.getTotalhours());
            
            coursedao.save(course_record);
        }
    }
    
    @RequestMapping(
            path = deletcourse,
            method = DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void deletcourse(@PathVariable Long id) {
        Course course_rec = coursedao.findById(id).get();
        coursedao.delete(course_rec);
        
    }
    
}
