package com.airtribe.learntrack.service;

import java.util.ArrayList;
import java.util.List;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.exception.EntityNotFoundException;

public class CourseService {
    private final List<Course> courses = new ArrayList<>();
    // Add new course

    public Course addCourse(String name, String description) {

        int courseId = IdGenerator.getNextCourseId();
        Course course = new Course(courseId, name, description, 0, true); 
                                // Assuming durationInWeeks is 0 and active is
                               // true for new courses
        courses.add(course);
        return course;

    }

    // View all courses 
    public void listCourses() {
        for (Course course : courses) {
            System.out.println("============================================");
            System.out.println("ID: " + course.getId());
            System.out.println("Name: " + course.getCourseName());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Duration (weeks): " + course.getDurationInWeeks());
            System.out.println("Active: " + course.isActive());
            System.out.println("============================================");
        }
    }

    // Activate/Deactivate a course
    public void setCourseActiveStatus(int courseId, boolean isActive) {
        for (Course course : courses) {
            if (course.getId() == courseId) {
                course.setActive(isActive);
                return;
            }
        }
         throw new EntityNotFoundException(
                "Course not found with ID: " + courseId);
    }

    public boolean courseExists(int courseId) {
        for (Course course : courses) {
            if (course.getId() == courseId) {
                return true;
            }
        }
        return false;
    }
}
