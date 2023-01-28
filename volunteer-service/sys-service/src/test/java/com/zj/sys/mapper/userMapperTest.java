package com.zj.sys.mapper;

import com.zj.sys.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class userMapperTest {
    int adf;

    @Autowired
    public UserMapper userMapper;
    @Test
    public void getUserById(){
        User user = userMapper.getUserById("ee3d464e-f087-4632-adc2-22579ff1c4c4");
        System.err.println(user);
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
//        System.err.println(adf);
//        System.err.println(a.age+b.age);
    }


}
class  A{
    protected int age = 1;
    protected void getAge(){
        System.err.println(this.age);
    }
}

class B {
    public static void main(String[] args) {
        A a = new A();
        a.getAge();
    }
}