package com.airtribe.learntrack.service;

import java.util.ArrayList;
import java.util.List;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;

public class CourseService {
    private final List<Course> courses = new ArrayList<>();
    // Add new course

    public Course addCourse(String name, String description, int durationInWeeks) {
        if (durationInWeeks < 0) {
            throw new InvalidInputException("Course duration cannot be negative");
        }

        Course course = new Course(name, description, durationInWeeks, true);
        courses.add(course);
        return course;

    }

    // View all courses
    public List<Course> listCourses() {
        return new ArrayList<>(courses);
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
            if (course.getId() == courseId && course.isActive()) {
                return true;
            }
        }
        return false;
    }
}
