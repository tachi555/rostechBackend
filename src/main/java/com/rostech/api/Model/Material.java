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
public class Material extends Result {
    private Long mid;
    private String mnama;
    private String type;
    private String ukuran;
    private Date tanggal;
    private Integer jumlah;
    private Integer total;
    private String ket;

    public Material() {
    }

    public Material(Long mid, String mnama, String type, String ukuran, Date tanggal, Integer jumlah, Integer total, String ket, Integer isSuccess, String notes, String ip, String token, String nama, String role, String user_id, String group_id) {
        super(isSuccess, notes, ip, token, nama, role, user_id, group_id);
        this.mid = mid;
        this.mnama = mnama;
        this.type = type;
        this.ukuran = ukuran;
        this.tanggal = tanggal;
        this.jumlah = jumlah;
        this.total = total;
        this.ket = ket;
    }

    public String getMnama() {
        return mnama;
    }

    public void setMnama(String mnama) {
        this.mnama = mnama;
    }
    
    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    @Override
    public String toString() {
        return "Material{" + "mid=" + mid + ", nama=" + nama + ", type=" + type + ", ukuran=" + ukuran + ", tanggal=" + tanggal + ", jumlah=" + jumlah + ", total=" + total + ", ket=" + ket + '}';
    }
    
    
    
}
