package com.sekolah.admin.system.koneksi;

/**
 * Created by ACER on 2/28/2017.
 */

public class config {
    public static final String LIST_KEPSEK = "http://mydeveloper.id/sekolah/admin/list_kepsek.php";
    public static final String LOGIN_KEPSEK = "http://mydeveloper.id/sekolah/kepsek/login.php";
    public static final String UPDATE_KEPSEK = "http://mydeveloper.id/sekolah/admin/update_kepsek.php";
    public static final String LIST_KATEGORI = "http://mydeveloper.id/sekolah/admin/list_kategori.php";
    public static final String LIST_PERTANYAAN = "http://mydeveloper.id/sekolah/admin/list_pertanyaan.php?id_kategori=";
    public static final String ADD_TANYA = "http://mydeveloper.id/sekolah/admin/add_pertanyaan.php";
    public static final String LIST_PENIALIAN = "http://mydeveloper.id/sekolah/admin/list_penilaian.php";
    public static final String LIST_DETAIL_PENILAIAN = "http://mydeveloper.id/sekolah/admin/list_detail_penialaian.php?nip_guru=";
    public static final String ADD_KATEGORI = "http://mydeveloper.id/sekolah/admin/add_kategori.php";
    public static final String DELETE_KATEGORI = "http://mydeveloper.id/sekolah/admin/delete_kategori.php?id_kategori=";
    public static final String DELETE_pertanyaan = "http://mydeveloper.id/sekolah/admin/delete_pertanyaan.php?id_pertanyaan=";
    public static final String LIST_GURU = "http://mydeveloper.id/sekolah/kepsek/list_guru.php";
    public static final String REGIS = "http://mydeveloper.id/sekolah/guru/regis.php";
    public static final String LOGIN_GURU = "http://mydeveloper.id/sekolah/guru/login.php";
    public static final String DATA_GURU = "http://mydeveloper.id/sekolah/guru/list_guru.php?nip_guru=";
    public static final String UPDDATE_DATA_GURU = "http://mydeveloper.id/sekolah/guru/update_data_guru.php";
    public static final String PENILAIAN_GURU = "http://mydeveloper.id/sekolah/kepsek/penilaian.php";
    public static final String DETAIL_GURU = "http://mydeveloper.id/sekolah/guru/detail_guru.php?nip_guru=";
    public static final String UPDATE_BOBOT = "http://mydeveloper.id/sekolah/admin/update_bobot.php";

    public static final String KEY_nip_kepsek = "nip_kepsek";
    public static final String KEY_NAMA = "nama";

    public static final String KEY_id_kategori = "id_kategori";
    public static final String KEY_id_penilaian = "id_penilaian";
    public static final String KEY_kategori = "kategori";

    public static final String KEY_alamat = "alamat";
    public static final String KEY_telp = "tlpn";
    public static final String KEY_pass = "password";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_id_pertanyaan = "id_pertanyaan";
    public static final String KEY_pertanyaan = "pertanyaan";
    public static final String KEY_bobot = "bobot";
    public static final String KEY_nip_guru = "nip_guru";
    public static final String KEY_nama_guru = "nama";
    public static final String KEY_total = "total";
    public static final String KEY_SEMSETER = "semester";
    public static final String KEY_KET = "keterangan";
    public static final String KEY_nuptk = "nuptk";
    public static final String KEY_nrg = "nrg";
    public static final String KEY_tmpt_lahir = "tempat_lahir";
    public static final String KEY_tgl_lahir = "tanggal_lahir";
    public static final String KEY_masa_kerja = "masa_kerja";
    public static final String KEY_mulai_kerja = "tgl_mulai_bekerja";
    public static final String KEY_pendidikan_akhir = "pendidikan_terakhir";
    public static final String KEY_spesialis = "spesialisasi";
    public static final String KEY_jk = "jenis_kelamin";
    public static final String KEY_thn_penialian = "tahun_penilaian";
    public static final String KEY_pass_guru = "password_guru";
    public static final String KEY_skor = "skor";
    public static final String KEY_skor_konf = "skor_konf";
    public static final String KEY_toatal_skor = "total_skor";


    public static final String LOGIN_SUCCESS = "success";
    public static final String SHARED_PREF_NAME = "myloginapp";
    public static final String EMAIL_SHARED_PREF = "key";
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

    public static final String SHARED_PREF_kepsek= "mykepsek";
    public static final String nipkepsek_SHARED_PREF = "nipkepsek";
    public static final String LOGGEDIN_SHARED_kepsek = "loggedinkepsek";
}
