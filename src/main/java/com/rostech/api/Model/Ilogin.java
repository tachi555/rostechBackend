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
    private String nama;
    private String pass;
    private Date tanggal;
    private String status;
    private String role;
    private Date tanggal_out;

    public Ilogin() {
    }

    public Ilogin(Long id, String nama, String pass, Date tanggal, String status, String role, Date tanggal_out) {
        this.id = id;
        this.nama = nama;
        this.pass = pass;
        this.tanggal = tanggal;
        this.status = status;
        this.role = role;
        this.tanggal_out = tanggal_out;
    }

    public Date getTanggal_out() {
        return tanggal_out;
    }

    public void setTanggal_out(Date tanggal_out) {
        this.tanggal_out = tanggal_out;
    }

    public Ilogin(Long id, String nama, String pass, Date tanggal, String status, String role, Integer isSuccess, String notes, String ip, String token) {
        super(isSuccess, notes, ip, token);
        this.id = id;
        this.nama = nama;
        this.pass = pass;
        this.tanggal = tanggal;
        this.status = status;
        this.role = role;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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
