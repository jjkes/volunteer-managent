import com.zj.common.config.SysProperties;
import com.zj.file.XmlApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/10 15:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {XmlApplication.class})
public class BaseTest {
    @Autowired
    private SysProperties sysProperties;

    @Test
    public void test() {
        String baseFilePath = sysProperties.getBaseFilePath();
        System.err.println(baseFilePath);
    }
}
