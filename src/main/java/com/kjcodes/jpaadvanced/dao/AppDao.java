package com.kjcodes.jpaadvanced.dao;

import com.kjcodes.jpaadvanced.entity.Course;
import com.kjcodes.jpaadvanced.entity.Instructor;
import com.kjcodes.jpaadvanced.entity.InstructorDetail;
import com.kjcodes.jpaadvanced.entity.Student;

import java.util.List;

public interface AppDao {
    public void save(Instructor theInstructor);

    public Instructor findById(int id);

    public void deleteById(int id);

    public InstructorDetail findDetailById(int id);

    public void deleteDetailById(int id);

    public List<Course> fetCourseByInsId(int id);

    public Instructor findInstructorWithCourseById(int id);

    public void update(Instructor instructor);

    public void update(Course course);

    public Course findCourseById(int id);

    public void save(Course course);

    public Course getCourseAndReviews(int id);

    public void deleteCourseAndReviewsById(int id);

    public Course getCourseAndStudentsByCourseId(int id);

    public Student getCoursesAndStudentByStudentId(int id);

    public void update(Student student);
}
