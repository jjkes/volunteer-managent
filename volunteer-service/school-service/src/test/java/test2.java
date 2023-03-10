import com.zj.school.entity.Course;
import com.zj.school.entity.Sc;
import com.zj.school.entity.Student;
import com.zj.school.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDateTime;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/2/11 10:39
 */

public class test2 {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        // teacher
        Teacher teacher = new Teacher();
        teacher.setId("05");
        teacher.setName("王力宏");
        // course
        Course course = new Course();
        course.setId("05");
        course.setName("音乐");
        course.setTeacher(teacher);
        // Student
        Student student = new Student();
        student.setId("14");
        student.setName("李健");
        student.setAge(LocalDateTime.now());
        student.setSex("男");
        //sc
        Sc sc= new Sc();
        sc.setScore(100);
        sc.setCourse(course);
        sc.setStudent(student);
        session.save(teacher);
        session.save(course);
        session.save(student);
        session.save(sc);
        session.beginTransaction().commit();
        session.close();
    }
}
