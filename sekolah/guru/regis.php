<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
		$nip_guru = $_POST['nip_guru'];
		$nama = $_POST['nama'];
		$nuptk = $_POST['nuptk'];
		$nrg = $_POST['nrg'];
		$tempat_lahir = $_POST['tempat_lahir'];
		$tanggal_lahir = $_POST['tanggal_lahir'];
		$masa_kerja = $_POST['masa_kerja'];
		$tgl_mulai_kerja = $_POST['tgl_mulai_kerja'];
		$pendidikan_terakhir = $_POST['pendidikan_terakhir'];
		$spesialis = $_POST['spesialis'];
		$alamat = $_POST['alamat'];
		$masa_kerja = $_POST['masa_kerja'];
		$tahun_penilaian = $_POST['tahun_penilaian'];
		$password_guru = $_POST['password_guru'];
		$jenis_kelamin = $_POST['jenis_kelamin'];

		require_once('dbConnect.php');
		
		$sql = "INSERT INTO tbl_guru VALUES ('$nip_guru','$nama','$nuptk','$nrg','$tempat_lahir','$tanggal_lahir','$masa_kerja','$tgl_mulai_kerja','$pendidikan_terakhir','$spesialis','$jenis_kelamin','$alamat','$tahun_penilaian','$password_guru')";
		
		
		if(mysqli_query($con,$sql)){
			echo "Successfully Registered";
		}else{
			echo "Could not register";

		}
	}else{
echo 'error';
}

?>