/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rostech.api.Model;

import java.util.Date;

/**
 *
 * @author PC-Rumah
 */
public class Ilogin extends Result {
    private Long id;
    private String pass;
    private Date tanggal;
    private String status;
    private Date tanggal_out;

    public Ilogin() {
    }

    public Ilogin(Long id, String pass, Date tanggal, String status, Date tanggal_out) {
        this.id = id;
        this.pass = pass;
        this.tanggal = tanggal;
        this.status = status;
        this.tanggal_out = tanggal_out;
    }

    public Ilogin(Long id, String pass, Date tanggal, String status, Date tanggal_out, Integer isSuccess, String notes, String ip, String token, String nama, String role, String user_id, String group_id) {
        super(isSuccess, notes, ip, token, nama, role, user_id, group_id);
        this.id = id;
        this.pass = pass;
        this.tanggal = tanggal;
        this.status = status;
        this.tanggal_out = tanggal_out;
    }

    public Date getTanggal_out() {
        return tanggal_out;
    }

    public void setTanggal_out(Date tanggal_out) {
        this.tanggal_out = tanggal_out;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ilogin{" + "id=" + id + ", nama=" + nama + ", pass=" + pass + ", tanggal=" + tanggal + ", status=" + status + '}';
    }
    
    
}
