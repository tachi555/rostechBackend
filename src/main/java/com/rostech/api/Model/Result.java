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

    public Result(Integer isSuccess, String notes, String ip, String token) {
        this.isSuccess = isSuccess;
        this.notes = notes;
        this.ip = ip;
        this.token = token;
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
