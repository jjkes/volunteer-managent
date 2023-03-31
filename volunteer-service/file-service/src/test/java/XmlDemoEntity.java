import com.zj.file.annotation.XmlValue;
import lombok.Data;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/10 14:13
 */
@Data
public class XmlDemoEntity {
    @XmlValue(path = "user/username")
    private String username;
    @XmlValue(path = "user/password")
    private String password;
}
