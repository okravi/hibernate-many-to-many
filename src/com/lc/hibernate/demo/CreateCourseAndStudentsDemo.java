package com.lc.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lc.hibernate.demo.entity.Course;
import com.lc.hibernate.demo.entity.Instructor;
import com.lc.hibernate.demo.entity.InstructorDetail;
import com.lc.hibernate.demo.entity.Review;
import com.lc.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {		
			
			
			//start a transaction
			session.beginTransaction();
			
			//create a Course
			Course tempCourse = new Course("How to play golf");
			
			//save course
			session.save(tempCourse);
			
			//create students
			Student tempStudent0 = new Student("John", "Barringer", "john@johnny.com");
			Student tempStudent1 = new Student("Bun", "Larringer", "bun@bunny.com");

			//add students to the course
			tempCourse.addStudent(tempStudent0);
			tempCourse.addStudent(tempStudent1);
			
			//save students
			session.save(tempStudent1);
			session.save(tempStudent0);
			
			//save all
			session.save(tempCourse);

			//commit transaction
			session.getTransaction().commit();;
			System.out.println("Done");
		} finally {
			session.close();
			factory.close();
		}
	}

}
