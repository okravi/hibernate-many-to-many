package com.lc.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lc.hibernate.demo.entity.Course;
import com.lc.hibernate.demo.entity.Instructor;
import com.lc.hibernate.demo.entity.InstructorDetail;
import com.lc.hibernate.demo.entity.Review;
import com.lc.hibernate.demo.entity.Student;

public class GetCoursesDemo {

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
			
			//get a student from db
			int theId = 1;
			Student tempStudent = session.get(Student.class, theId);
			
			//create courses
			Course newCourse = new Course("Beekeping course");
			
			//add student to courses
			newCourse.addStudent(tempStudent);
			
			//save
			session.save(newCourse);

			//commit transaction
			session.getTransaction().commit();;
			System.out.println("Done");
		} finally {
			session.close();
			factory.close();
		}
	}

}
