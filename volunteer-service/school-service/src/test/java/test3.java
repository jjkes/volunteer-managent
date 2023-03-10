import com.zj.school.entity.Course;
import com.zj.school.entity.Sc;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/2/11 11:17
 */

public class test3 {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Sc sc = new Sc();
        sc.setCid("01");
        sc.setSid("01");
        Sc a = session.get(Sc.class,sc);
        System.err.println(a);
    }
}
