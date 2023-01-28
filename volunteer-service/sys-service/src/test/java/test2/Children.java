package test2;

import test1.Mother;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/28 16:59
 */

public class Children extends Mother{
    static int motherAge;
//    public void getMotherAge(){
//        motherAge=super.age;
//    }
    public static void main(String[] args) {
        Mother mother = new Mother();
        Children children = new Children();
//        children.getMotherAge();
        System.err.println(children.motherAge);
    }
}
