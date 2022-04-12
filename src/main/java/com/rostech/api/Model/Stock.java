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
public class Stock extends Result {
    private Long id;
    private Long mid;
    private Integer perubahan;
    private Integer total;
    private Date tanggal;
    private String ket;
    private String mnama;
    private Long pembelianId;

    public Stock() {
    }

    public Stock(Long id, Long mid, Integer perubahan, Integer total, Date tanggal, String ket, String mnama, Long pembelianId, Integer isSuccess, String notes, String ip, String token, String nama, String role, String user_id, String group_id) {
        super(isSuccess, notes, ip, token, nama, role, user_id, group_id);
        this.id = id;
        this.mid = mid;
        this.perubahan = perubahan;
        this.total = total;
        this.tanggal = tanggal;
        this.ket = ket;
        this.mnama = mnama;
        this.pembelianId = pembelianId;
    }

    public Stock(Long id, Long mid, Integer perubahan, Integer total, Date tanggal, String ket, String mnama, Long pembelianId) {
        this.id = id;
        this.mid = mid;
        this.perubahan = perubahan;
        this.total = total;
        this.tanggal = tanggal;
        this.ket = ket;
        this.mnama = mnama;
        this.pembelianId = pembelianId;
    }

    public Long getPembelianId() {
        return pembelianId;
    }

    public void setPembelianId(Long pembelianId) {
        this.pembelianId = pembelianId;
    }

    public String getMnama() {
        return mnama;
    }

    public void setMnama(String mnama) {
        this.mnama = mnama;
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

    public Integer getPerubahan() {
        return perubahan;
    }

    public void setPerubahan(Integer perubahan) {
        this.perubahan = perubahan;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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
        return "Stock{" + "id=" + id + ", mid=" + mid + ", perubahan=" + perubahan + ", total=" + total + ", tanggal=" + tanggal + ", ket=" + ket + '}';
    }
    
    
}
