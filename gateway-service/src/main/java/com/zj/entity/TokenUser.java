package com.zj.entity;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2022/10/16 9:43
 */

public class TokenUser implements Serializable {
    /** 用户Id */
    private String id;
    /** 用户名 */
    private  String username;
    /** 角色id */
    private String roleId;
    /** 学校Id */
    private String unitId;
    /** 用户登录Ip，防止token盗用 */
    private String ip;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public static TokenUser mapToEntity(Map<String,Object> map){
        TokenUser tokenUser = new TokenUser();
        tokenUser.setId(map.get("id") != null ? map.get("id").toString():null);
        tokenUser.setUsername(map.get("username") != null ? map.get("username").toString():null);
        tokenUser.setRoleId(map.get("roleId") != null ? map.get("roleId").toString():null);
        tokenUser.setUnitId(map.get("unitId") != null ? map.get("unitId").toString():null);
        tokenUser.setIp(map.get("ip") != null ? map.get("ip").toString():null);
        return tokenUser;
    }

    public Map<String,Object> toMap(){
        Map<String, Object> map = new HashMap<>();
        if(this.id != null){
            map.put("id",this.id);
        }
        if(this.username != null){
            map.put("username",this.username);
        }
        if (this.roleId != null) {
            map.put("roleId",this.roleId);
        }
        if(this.unitId != null){
            map.put("unitId",this.unitId);
        }
        if(this.ip != null){
            map.put("ip",this.ip);
        }
        return map;
    }

}
