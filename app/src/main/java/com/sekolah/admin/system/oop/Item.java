package com.sekolah.admin.system.oop;

/**
 * Created by acer on 1/20/2018.
 */

public class Item {
    String id_kategori;
    String kategori;
    String id_pertanyaan;
    String pertanyaan;
    String bobot;
    String total;
    String nip_guru;
    String nama_guru;
    String semester,total_skor;

    public String getTotal_skor() {
        return total_skor;
    }

    public void setTotal_skor(String total_skor) {
        this.total_skor = total_skor;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    String keterangan;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getNip_guru() {
        return nip_guru;
    }

    public void setNip_guru(String nip_guru) {
        this.nip_guru = nip_guru;
    }

    public String getNama_guru() {
        return nama_guru;
    }

    public void setNama_guru(String nama_guru) {
        this.nama_guru = nama_guru;
    }

    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getId_pertanyaan() {
        return id_pertanyaan;
    }

    public void setId_pertanyaan(String id_pertanyaan) {
        this.id_pertanyaan = id_pertanyaan;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getBobot() {
        return bobot;
    }

    public void setBobot(String bobot) {
        this.bobot = bobot;
    }
}
