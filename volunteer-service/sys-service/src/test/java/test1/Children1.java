package test1;

import org.apache.xmlbeans.impl.store.CharUtil;
import org.junit.jupiter.api.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/28 17:02
 */

public class Children1 {
    public static void main(String[] args) throws IOException {
//        Mother mother = new Mother();
//        System.err.println(mother.age);
//        writeFile();
//        readFile();
//        createObjectFile();
//        readObjectFile();
//        writer();
        Read();
//        Reader reader = new StringReader("adsfadsf");
//        int a;
////            System.err.println((char) reader.read());
//        while ((a= reader.read())!=-1){
//            char f = (char)a;
//            System.err.print(f);
//        }
    }

    public static void Read(){
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream("I:\\迅雷下载\\reader.txt"),"UTF-8");
            System.err.println(reader.read());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*File file = new File("I:\\迅雷下载\\reader.txt");
        try (Reader reader = new FileReader(file)) {
//            char[] charArrays = new char[1024];
            int a;
//            System.err.println((char) reader.read());
            while ((a= reader.read())!=-1){
                char f = (char)a;
                System.err.print(f);
            }
//            int charNum = reader.read(charArrays);
//            System.err.println(charNum);
//            for(int i=0;i<charNum;i++){
//                System.err.print(charArrays[i]);
//            }
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }
    public static void writer(){
        File file = new File("I:\\迅雷下载\\reader.txt");
        try (Writer writer = new FileWriter(file)){
            writer.write("123123123");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void writeFile(){
        File file = new File("I:\\迅雷下载\\test1.txt");
        if(!file.exists()){
            file.mkdir();
        }
        try(OutputStream os = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(os);
        ) {
            dos.writeInt(123123);
            dos.writeInt(456456);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readFile(){
        File file = new File("I:\\迅雷下载\\test1.txt");
        try(FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);
        ) {
            int i;
            while ((i=dis.read()) != -1){
                System.err.println(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void createObjectFile(){
        TestObject t = new TestObject(1,"zhao");
        try(FileOutputStream fis = new FileOutputStream("I:\\迅雷下载\\objectTest.txt");
            ObjectOutputStream dis = new ObjectOutputStream(fis);
            ){
            dis.writeObject(t);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void readObjectFile(){
        try(FileInputStream fis = new FileInputStream("I:\\迅雷下载\\objectTest.txt");
        ObjectInputStream dis = new ObjectInputStream(fis)){
            TestObject testObject = (TestObject) dis.readObject();

            System.err.println(testObject.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
class TestObject implements Serializable {
    public int a;
    public String name;

    public TestObject(int a, String name) {
        this.a = a;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "a=" + a +
                ", name='" + name + '\'' +
                '}';
    }
}

class TestObject1 implements Serializable {
    public int a;
    public String name;

    public TestObject1(int a, String name) {
        this.a = a;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestObject1{" +
                "a=" + a +
                ", name='" + name + '\'' +
                '}';
    }
}