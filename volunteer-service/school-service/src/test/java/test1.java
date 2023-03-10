import com.zj.school.entity.Course;
import com.zj.school.entity.Student;
import com.zj.school.entity.Teacher;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/2/10 17:34
 */

public class test1 {
    public static void main(String[] args) throws  HibernateException {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Teacher teacher = new Teacher();
        teacher.setId("04");
        teacher.setName("赵健");
        Course course = new Course();
        course.setId("04");
        course.setName("科学");
        course.setTeacher(teacher);
        Session session = sessionFactory.openSession();
        session.save(teacher);
        session.save(course);
        session.beginTransaction().commit();
        session.close();
    }
}
