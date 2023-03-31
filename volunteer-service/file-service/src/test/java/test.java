import com.zj.file.util.XMLUtil;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/10 14:04
 */

public class test {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException, SAXException {
        DocumentFactory documentFactory = new DocumentFactory();
        Document document = documentFactory.createDocument();
        Element element = document.addElement("user");
        element.addElement("username").setText("admin");
        element.addElement("password").setText("123123");
        XmlDemoEntity x = (XmlDemoEntity) XMLUtil.parseXML(document.getRootElement(), XmlDemoEntity.class);
        System.err.println(x);

        File file = new File("D:\\test.xml");
        if(!file.exists()){
            file.createNewFile();
        }
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter xmlWriter = new XMLWriter(new FileWriter(file),format);
        xmlWriter.write(document);
        xmlWriter.flush();
        xmlWriter.close();
    }
}
