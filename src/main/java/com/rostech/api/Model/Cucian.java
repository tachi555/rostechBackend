/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rostech.api.Model;

import java.util.Date;

/**
 *
 * @author PC-Rumah
 */
public class Cucian extends Result{
    private Integer row;
    private Long id;
    private Date tanggal;
    private String noPlat;
    private String pencuci;
    private String typeCucian;
    private Integer harga;
    private String status;
    private String ket;
    private Date from;
    private Date to;

    public Cucian() {
    }

    public Cucian(Integer row, Long id, Date tanggal, String noPlat, String pencuci, String typeCucian, Integer harga, String status, String ket, Date from, Date to, Integer isSuccess, String notes, String ip, String token, String nama, String role, String user_id, String group_id) {
        super(isSuccess, notes, ip, token, nama, role, user_id, group_id);
        this.row = row;
        this.id = id;
        this.tanggal = tanggal;
        this.noPlat = noPlat;
        this.pencuci = pencuci;
        this.typeCucian = typeCucian;
        this.harga = harga;
        this.status = status;
        this.ket = ket;
        this.from = from;
        this.to = to;
    }

    public Cucian(Integer row, Long id, Date tanggal, String noPlat, String pencuci, String typeCucian, Integer harga, String status, String ket, Date from, Date to) {
        this.row = row;
        this.id = id;
        this.tanggal = tanggal;
        this.noPlat = noPlat;
        this.pencuci = pencuci;
        this.typeCucian = typeCucian;
        this.harga = harga;
        this.status = status;
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

    public String getNoPlat() {
        return noPlat;
    }

    public void setNoPlat(String noPlat) {
        this.noPlat = noPlat;
    }

    public String getPencuci() {
        return pencuci;
    }

    public void setPencuci(String pencuci) {
        this.pencuci = pencuci;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getTypeCucian() {
        return typeCucian;
    }

    public void setTypeCucian(String typeCucian) {
        this.typeCucian = typeCucian;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }
    
    
}
