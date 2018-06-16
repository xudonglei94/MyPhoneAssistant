package org.crazyit.myphoneassistant.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/11.
 */

public class BaseBean<T> extends BaseEntity {

    public  static  final  int  SUCCESS=1;
    private  int  status;
    private String message;
    //第三个数据类型我们是不确定的所以我们用泛型
    private T data;

    public  boolean success(){
        return (status==SUCCESS);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }



}
