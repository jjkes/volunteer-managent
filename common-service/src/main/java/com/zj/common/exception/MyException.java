package com.zj.common.exception;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/11 21:12
 */

public abstract class MyException extends RuntimeException{
    static final long serialVersionUID = -3387516993124229489L;
    public MyException() {
        super("System error,please contact staff");
    }

    public MyException(String message) {
        super(message);
    }
}
