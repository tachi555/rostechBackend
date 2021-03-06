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
public class Pembelian extends Result {
    private Long id;
    private Long mid;
    private String mnama;
    private Long tid;
    private String tnama;
    private String tanggal;
    private Integer persatuan;
    private Integer jumlah;
    private Integer total;
    private String ket;
    private Date from;
    private Date to;

    public Pembelian() {
    }

    public Pembelian(Long id, Long mid, String mnama, Long tid, String tnama, String tanggal, Integer persatuan, Integer jumlah, Integer total, String ket, Date from, Date to) {
        this.id = id;
        this.mid = mid;
        this.mnama = mnama;
        this.tid = tid;
        this.tnama = tnama;
        this.tanggal = tanggal;
        this.persatuan = persatuan;
        this.jumlah = jumlah;
        this.total = total;
        this.ket = ket;
        this.from = from;
        this.to = to;
    }

    public Pembelian(Long id, Long mid, String mnama, Long tid, String tnama, String tanggal, Integer persatuan, Integer jumlah, Integer total, String ket, Date from, Date to, Integer isSuccess, String notes, String ip, String token, String nama, String role, String user_id, String group_id) {
        super(isSuccess, notes, ip, token, nama, role, user_id, group_id);
        this.id = id;
        this.mid = mid;
        this.mnama = mnama;
        this.tid = tid;
        this.tnama = tnama;
        this.tanggal = tanggal;
        this.persatuan = persatuan;
        this.jumlah = jumlah;
        this.total = total;
        this.ket = ket;
        this.from = from;
        this.to = to;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getMnama() {
        return mnama;
    }

    public void setMnama(String mnama) {
        this.mnama = mnama;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getTnama() {
        return tnama;
    }

    public void setTnama(String tnama) {
        this.tnama = tnama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getPersatuan() {
        return persatuan;
    }

    public void setPersatuan(Integer persatuan) {
        this.persatuan = persatuan;
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
        return "Harga{" + "id=" + id + ", mid=" + mid + ", tid=" + tid + ", tanggal=" + tanggal + ", persatuan=" + persatuan + ", jumlah=" + jumlah + ", total=" + total + ", ket=" + ket + '}';
    }
    
    
    
}
