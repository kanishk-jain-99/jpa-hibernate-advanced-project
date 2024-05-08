package com.kjcodes.jpaadvanced.dao;

import com.kjcodes.jpaadvanced.entity.Course;
import com.kjcodes.jpaadvanced.entity.Instructor;
import com.kjcodes.jpaadvanced.entity.InstructorDetail;
import com.kjcodes.jpaadvanced.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao{

    private final EntityManager entityManager;

    @Autowired
    public AppDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course getCourseAndReviews(int id) {

        TypedQuery<Course> query = entityManager.createQuery("select c from Course c JOIN FETCH c.reviews where c.id =:data", Course.class);

        query.setParameter("data", id);

        return query.getResultList().getFirst();

    }

    @Override
    @Transactional
    public void deleteCourseAndReviewsById(int id) {
        Course course = entityManager.find(Course.class, id);

        entityManager.remove(course);
    }

    @Override
    public Course getCourseAndStudentsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course as c JOIN FETCH c.students where c.id =:data", Course.class);

        query.setParameter("data", id);

        return query.getResultList().getFirst();

    }

    @Override
    public Student getCoursesAndStudentByStudentId(int id) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student as s JOIN FETCH s.courses where s.id =:data", Student.class);

        query.setParameter("data", id);

        return query.getResultList().getFirst();
    }

    @Override
    @Transactional
    public void update(Student student) {

        entityManager.merge(student);

    }

    @Override
    public Instructor findById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);

        List<Course> courses = instructor.getCourses();

        for(Course course: courses){
            course.setInstructor(null);
        }

        entityManager.remove(instructor);

    }

    @Override
    public InstructorDetail findDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteDetailById(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);

        //remove the bi directional link since we do not need link when not cascading

        instructorDetail.getInstructor().setInstructorDetail(null);


        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> fetCourseByInsId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id =:data", Course.class);

        query.setParameter("data", id);

        return query.getResultList();

    }

    @Override
    public Instructor findInstructorWithCourseById(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i " + "JOIN fetch i.courses " + "where i.id =:data", Instructor.class);

        query.setParameter("data", id);

        return query.getResultList().getFirst();

    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
       return entityManager.find(Course.class, id);
    }
}
