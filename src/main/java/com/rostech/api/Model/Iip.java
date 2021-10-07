/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rostech.api.Model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author PC-Rumah
 */
public class Iip extends Result {
    private String ip;
    private String status;
    private Date tanggal;
    private String token;
    private String role;
    private Date tanggal_out;
           
    public Iip() {
    }

    public Iip(String ip, String status, Date tanggal, String token, String role, Date tanggal_out) {
        this.ip = ip;
        this.status = status;
        this.tanggal = tanggal;
        this.token = token;
        this.role = role;
        this.tanggal_out = tanggal_out;
    }

    public Date getTanggal_out() {
        return tanggal_out;
    }

    public void setTanggal_out(Date tanggal_out) {
        this.tanggal_out = tanggal_out;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Iip{" + "ip=" + ip + ", status=" + status + ", tanggal=" + tanggal + ", token=" + token + '}';
    }
    
    
}
