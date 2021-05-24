package com.com.hibernate.demo;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {

    public static void main(String[] args){

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            session.beginTransaction();

            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);

            Course course1 = new Course("Guitar");
            Course course2 = new Course("The Pinball");

            instructor.addCourse(course1);
            instructor.addCourse(course2);


            session.save(course1);
            session.save(course2);
            session.getTransaction().commit();

        }finally {
            session.close();
            factory.close();
        }
    }
}
