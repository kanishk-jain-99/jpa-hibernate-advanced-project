package com.kjcodes.jpaadvanced;

import com.kjcodes.jpaadvanced.dao.AppDao;
import com.kjcodes.jpaadvanced.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JpaadvancedApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaadvancedApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao){
		return runner-> {
//			createInstructor(appDao);

//			findInstructorById(appDao);

//			deleteById(appDao);

//			getInstructorDetailById(appDao);

//			deleteDetailsById(appDao);

//			createInstructorWithCourses(appDao);

//			findCoursesForInstructor(appDao);

//			updateInstructor(appDao);

//			updateCourse(appDao);

//			createCourseAndReviews(appDao);

//			getCourseAndReviews(appDao);

//			deleteCourseAndReviewsById(appDao);

//			createCourseAndStudents(appDao);

//			findCourseAndStudentById(appDao);

			findStudentAndTheirCoursesById(appDao);

//			addCoursesToStudent(appDao);
		};


	}

	private void addCoursesToStudent(AppDao appDao) {

		Student student = appDao.getCoursesAndStudentByStudentId(1);

		Course course1 = new Course("Test Course");

		Course course2 = new Course("Course woohoo");

		student.addCourse(course1);
		student.addCourse(course2);

		appDao.update(student);

	}

	private void findStudentAndTheirCoursesById(AppDao appDao) {
		Student student = appDao.getCoursesAndStudentByStudentId(1);

		System.out.println("Student: " + student);
		System.out.println("Courses" + student.getCourses());
	}

	private void findCourseAndStudentById(AppDao appDao) {

		Course course = appDao.getCourseAndStudentsByCourseId(10);

		System.out.println("Course: " + course);
		System.out.println("Students" + course.getStudents());

	}

	private void createCourseAndStudents(AppDao appDao) {

		Course course = new Course("Popular Course");

		Student student1 = new Student("Kanishk", "jain", "kj.99cute@gmail.com");

		Student student2 = new Student("Ksdfnishk", "jadsfsin", "kj.99999cute@gmail.com");

		course.addStudent(student1);

		course.addStudent(student2);

		System.out.println("Course: " + course);
		System.out.println("Students" + course.getStudents());
		appDao.save(course);

	}

	private void deleteCourseAndReviewsById(AppDao appDao) {

		appDao.deleteCourseAndReviewsById(10);

	}

	private void getCourseAndReviews(AppDao appDao) {

		Course course = appDao.getCourseAndReviews(10);

		System.out.println("The Course " + course);
		System.out.println("Reviews: " + course.getReviews());

	}

	private void createCourseAndReviews(AppDao appDao) {

		Course course = new Course("Best Course");

		Review review1 = new Review("Best Comment");

		Review review2 = new Review("Second Best Comment");

		course.addReview(review1);

		course.addReview(review2);

		System.out.println(course);

		System.out.println(course.getReviews());

		appDao.save(course);


	}

	private void updateCourse(AppDao appDao) {
		Course course = appDao.findCourseById(10);

		course.setTitle("Fifty Shades");

		appDao.update(course);
	}

	private void updateInstructor(AppDao appDao) {

		Instructor instructor = appDao.findById(2);

		instructor.setFirstName("Kansihk");

		appDao.update(instructor);

	}

	private void findCoursesForInstructor(AppDao appDao) {

		Instructor theInstructor = appDao.findInstructorWithCourseById(2);

		System.out.println(theInstructor);

		System.out.println(theInstructor.getCourses());

	}

	private void createInstructorWithCourses(AppDao appDao) {

		Instructor theInstructor = new Instructor("Piyu", "Jain", "piyu.98jain@gmail.com");

		InstructorDetail instructorDetail = new InstructorDetail("Trading", "piyuearns");

		Course springCourse = new Course("Spring Boot");

		Course reactCourse = new Course("React");

		theInstructor.add(springCourse);

		theInstructor.add(reactCourse);

		theInstructor.setInstructorDetail(instructorDetail);

		System.out.println(theInstructor);

		//This will also create a new instructor detail in the ins det table
		appDao.save(theInstructor);

		System.out.println("Done!!");
	}

	private void deleteDetailsById(AppDao appDao) {

		appDao.deleteDetailById(3);

	}

	private void getInstructorDetailById(AppDao appDao) {
		InstructorDetail instructorDetail = appDao.findDetailById(2);

		System.out.println(instructorDetail);
		System.out.println(instructorDetail.getInstructor());

	}

	private void deleteById(AppDao appDao) {
		appDao.deleteById(1);
	}

	private void findInstructorById(AppDao appDao) {
		Instructor instructor = appDao.findById(2);

		System.out.println("the instructor" + instructor);
		System.out.println("the course" + instructor.getCourses());
	}

	private void createInstructor(AppDao appDao) {

		Instructor theInstructor = new Instructor("Piyu", "Jain", "piyu.98jain@gmail.com");

		InstructorDetail instructorDetail = new InstructorDetail("Trading", "piyuearns");

		theInstructor.setInstructorDetail(instructorDetail);

		System.out.println(theInstructor);

		//This will also create a new instructor detail in the ins det table
		appDao.save(theInstructor);

		System.out.println("Done!!");

	}

}
