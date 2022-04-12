package com.rostech.api.Model;

import java.util.Date;

public class Daily extends Result {
    private Long id;
    private Date tanggal;
    private Integer mobilBesar;
    private Integer bodyBesar;
    private Integer mobilKecil;
    private Integer bodyKecil;
    private Integer karpet;
    private Integer upahCuci;
    private Integer upahKarpet;
    private Integer beliNasi;
    private Integer jasaAlex;
    private Integer jasaRos;
    private Integer sparepart;
    private Integer minuman;
    private Integer sewa;
    private Integer cicilan;
    private Integer lain;
    private Integer penambahan;
    private String ket;
    private Integer pendapatan;
    private Integer pendapatanCucian;
    private Integer pengeluaran;
    private Integer pengeluaranCucian;
    private Integer total;
    private Integer pendapatanTekuk;
    private Integer pendapatanPlasma;
    private Integer pendapatanLas;
    private Integer pendapatanBubut;
    private Date from;
    private Date to;
	
    public Daily() {}

    public Daily(Long id, Date tanggal, Integer mobilBesar, Integer bodyBesar, Integer mobilKecil, Integer bodyKecil, Integer karpet, Integer upahCuci, Integer upahKarpet, Integer beliNasi, Integer jasaAlex, Integer jasaRos, Integer sparepart, Integer minuman, Integer sewa, Integer cicilan, Integer lain, Integer penambahan, String ket, Integer pendapatan, Integer pendapatanCucian, Integer pengeluaran, Integer pengeluaranCucian, Integer total, Integer pendapatanTekuk, Integer pendapatanPlasma, Integer pendapatanLas, Integer pendapatanBubut, Date from, Date to, Integer isSuccess, String notes, String ip, String token, String nama, String role, String user_id, String group_id) {
        super(isSuccess, notes, ip, token, nama, role, user_id, group_id);
        this.id = id;
        this.tanggal = tanggal;
        this.mobilBesar = mobilBesar;
        this.bodyBesar = bodyBesar;
        this.mobilKecil = mobilKecil;
        this.bodyKecil = bodyKecil;
        this.karpet = karpet;
        this.upahCuci = upahCuci;
        this.upahKarpet = upahKarpet;
        this.beliNasi = beliNasi;
        this.jasaAlex = jasaAlex;
        this.jasaRos = jasaRos;
        this.sparepart = sparepart;
        this.minuman = minuman;
        this.sewa = sewa;
        this.cicilan = cicilan;
        this.lain = lain;
        this.penambahan = penambahan;
        this.ket = ket;
        this.pendapatan = pendapatan;
        this.pendapatanCucian = pendapatanCucian;
        this.pengeluaran = pengeluaran;
        this.pengeluaranCucian = pengeluaranCucian;
        this.total = total;
        this.pendapatanTekuk = pendapatanTekuk;
        this.pendapatanPlasma = pendapatanPlasma;
        this.pendapatanLas = pendapatanLas;
        this.pendapatanBubut = pendapatanBubut;
        this.from = from;
        this.to = to;
    }

	

    public Integer getPenambahan() {
        return penambahan;
    }

    public void setPenambahan(Integer penambahan) {
        this.penambahan = penambahan;
    }

    public String getKet() {
            return ket;
    }

    public void setKet(String ket) {
            this.ket = ket;
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

    public Date getTanggal() {
            return tanggal;
    }

    public void setTanggal(Date tanggal) {
            this.tanggal = tanggal;
    }

    public Integer getMobilBesar() {
            return mobilBesar;
    }

    public void setMobilBesar(Integer mobilBesar) {
            this.mobilBesar = mobilBesar;
    }

    public Integer getBodyBesar() {
            return bodyBesar;
    }

    public void setBodyBesar(Integer bodyBesar) {
            this.bodyBesar = bodyBesar;
    }

    public Integer getMobilKecil() {
            return mobilKecil;
    }

    public void setMobilKecil(Integer mobilKecil) {
            this.mobilKecil = mobilKecil;
    }

    public Integer getBodyKecil() {
            return bodyKecil;
    }

    public void setBodyKecil(Integer bodyKecil) {
            this.bodyKecil = bodyKecil;
    }

    public Integer getKarpet() {
            return karpet;
    }

    public void setKarpet(Integer karpet) {
            this.karpet = karpet;
    }

    public Integer getUpahCuci() {
            return upahCuci;
    }

    public void setUpahCuci(Integer upahCuci) {
            this.upahCuci = upahCuci;
    }

    public Integer getUpahKarpet() {
            return upahKarpet;
    }

    public void setUpahKarpet(Integer upahKarpet) {
            this.upahKarpet = upahKarpet;
    }

    public Integer getBeliNasi() {
            return beliNasi;
    }

    public void setBeliNasi(Integer beliNasi) {
            this.beliNasi = beliNasi;
    }

    public Integer getJasaAlex() {
            return jasaAlex;
    }

    public void setJasaAlex(Integer jasaAlex) {
            this.jasaAlex = jasaAlex;
    }

    public Integer getJasaRos() {
            return jasaRos;
    }

    public void setJasaRos(Integer jasaRos) {
            this.jasaRos = jasaRos;
    }

    public Integer getSparepart() {
            return sparepart;
    }

    public void setSparepart(Integer sparepart) {
            this.sparepart = sparepart;
    }

    public Integer getMinuman() {
            return minuman;
    }

    public void setMinuman(Integer minuman) {
            this.minuman = minuman;
    }

    public Integer getSewa() {
            return sewa;
    }

    public void setSewa(Integer sewa) {
            this.sewa = sewa;
    }

    public Integer getCicilan() {
            return cicilan;
    }

    public void setCicilan(Integer cicilan) {
            this.cicilan = cicilan;
    }

    public Integer getLain() {
            return lain;
    }

    public void setLain(Integer lain) {
            this.lain = lain;
    }

    public Integer getPendapatan() {
            return pendapatan;
    }

    public void setPendapatan(Integer pendapatan) {
            this.pendapatan = pendapatan;
    }

    public Integer getPendapatanCucian() {
            return pendapatanCucian;
    }

    public void setPendapatanCucian(Integer pendapatanCucian) {
            this.pendapatanCucian = pendapatanCucian;
    }

    public Integer getPengeluaran() {
            return pengeluaran;
    }

    public void setPengeluaran(Integer pengeluaran) {
            this.pengeluaran = pengeluaran;
    }

    public Integer getPengeluaranCucian() {
            return pengeluaranCucian;
    }

    public void setPengeluaranCucian(Integer pengeluaranCucian) {
            this.pengeluaranCucian = pengeluaranCucian;
    }

    public Integer getTotal() {
            return total;
    }

    public void setTotal(Integer total) {
            this.total = total;
    }

    public Integer getPendapatanTekuk() {
            return pendapatanTekuk;
    }

    public void setPendapatanTekuk(Integer pendapatanTekuk) {
            this.pendapatanTekuk = pendapatanTekuk;
    }

    public Integer getPendapatanPlasma() {
            return pendapatanPlasma;
    }

    public void setPendapatanPlasma(Integer pendapatanPlasma) {
            this.pendapatanPlasma = pendapatanPlasma;
    }

    public Integer getPendapatanLas() {
            return pendapatanLas;
    }

    public void setPendapatanLas(Integer pendapatanLas) {
            this.pendapatanLas = pendapatanLas;
    }

    public Integer getPendapatanBubut() {
            return pendapatanBubut;
    }

    public void setPendapatanBubut(Integer pendapatanBubut) {
            this.pendapatanBubut = pendapatanBubut;
    }
	
	
	
}
