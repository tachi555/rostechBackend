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
public class Toko extends Result {
    private Long id;
    private String nama;
    private String contact;
    private String telpon;
    private String alamat;
    private Date tanggal;
    private String ket;

    public Toko() {
    }

    public Toko(Long id, String nama, String contact, String telpon, String alamat, Date tanggal, String ket) {
        this.id = id;
        this.nama = nama;
        this.contact = contact;
        this.telpon = telpon;
        this.alamat = alamat;
        this.tanggal = tanggal;
        this.ket = ket;
    }

    public Toko(Long id, String nama, String contact, String telpon, String alamat, Date tanggal, String ket, Integer isSuccess, String notes, String ip, String token) {
        super(isSuccess, notes, ip, token);
        this.id = id;
        this.nama = nama;
        this.contact = contact;
        this.telpon = telpon;
        this.alamat = alamat;
        this.tanggal = tanggal;
        this.ket = ket;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTelpon() {
        return telpon;
    }

    public void setTelpon(String telpon) {
        this.telpon = telpon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }
    
    

    @Override
    public String toString() {
        return "Toko{" + "id=" + id + ", nama=" + nama + ", contact=" + contact + ", telpon=" + telpon + ", alamat=" + alamat + ", tanggal=" + tanggal + ", ket=" + ket + '}';
    }
       
}
