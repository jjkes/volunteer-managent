package com.zj.sys.config;

import com.alibaba.fastjson.JSONObject;
import com.zj.common.constant.StateEnum;
import lombok.Data;

@Data
public class Result<T> {

    private StateEnum resultEnum;

    private T data;

    private Integer count;

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        if (resultEnum != null) {
            jsonObject.put("code", resultEnum.getCode());
            jsonObject.put("message", resultEnum.getMessage());
        }
        if (data != null) {
            jsonObject.put("data", data);
        }
        if (count != null) {
            jsonObject.put("count", count);
        }
        return jsonObject;
    }

    public Result<T> setResultEnum(StateEnum stateEnum) {
        this.resultEnum = stateEnum;
        return this;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

}
