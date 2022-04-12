/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rostech.api.Model;

/**
 *
 * @author PC-Rumah
 */
public class Result {
    Integer isSuccess;
    String notes;
    String ip;
    String token;
    String nama;
    String role;
    String user_id;
    String group_id;

    public Result(Integer isSuccess, String notes, String ip, String token, String nama, String role, String user_id, String group_id) {
        this.isSuccess = isSuccess;
        this.notes = notes;
        this.ip = ip;
        this.token = token;
        this.nama = nama;
        this.role = role;
        this.user_id = user_id;
        this.group_id = group_id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
    
    public Result() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
  
    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    
}
