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
    private String status;
    private Date tanggal;
    private Date tanggal_out;
           
    public Iip() {
    }

    public Iip(String status, Date tanggal, Date tanggal_out, Integer isSuccess, String notes, String ip, String token, String nama, String role, String user_id, String group_id) {
        super(isSuccess, notes, ip, token, nama, role, user_id, group_id);
        this.status = status;
        this.tanggal = tanggal;
        this.tanggal_out = tanggal_out;
    }

    public Iip(String ip, String status, Date tanggal, Date tanggal_out) {
        this.ip = ip;
        this.status = status;
        this.tanggal = tanggal;
        this.tanggal_out = tanggal_out;
    }

    public Date getTanggal_out() {
        return tanggal_out;
    }

    public void setTanggal_out(Date tanggal_out) {
        this.tanggal_out = tanggal_out;
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

    @Override
    public String toString() {
        return "Iip{" + "ip=" + ip + ", status=" + status + ", tanggal=" + tanggal + ", token=" + token + '}';
    }
    
    
}
