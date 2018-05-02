<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
		$nip_guru = $_POST['nip_guru'];
		$nama = $_POST['nama'];
		$nuptk = $_POST['nuptk'];
		$nrg = $_POST['nrg'];
		$tempat_lahir = $_POST['tempat_lahir'];
		$tanggal_lahir = $_POST['tanggal_lahir'];
		$masa_kerja = $_POST['masa_kerja'];
		$tgl_mulai_bekerja = $_POST['tgl_mulai_bekerja'];
		$pendidikan_terakhir = $_POST['pendidikan_terakhir'];
		$spesialisasi = $_POST['spesialisasi'];
		$alamat = $_POST['alamat'];
		$masa_kerja = $_POST['masa_kerja'];
		$tahun_penilaian = $_POST['tahun_penilaian'];
		$password_guru = $_POST['password_guru'];
		$jenis_kelamin = $_POST['jenis_kelamin'];

		require_once('dbConnect.php');
		
		$sql = "update tbl_guru set nama = '$nama', nuptk = '$nuptk', nrg = '$nrg', tempat_lahir = '$tempat_lahir',tanggal_lahir = '$tanggal_lahir',masa_kerja = '$masa_kerja', tgl_mulai_bekerja = '$tgl_mulai_bekerja',pendidikan_terakhir = '$pendidikan_terakhir', spesialisasi = '$spesialisasi',jenis_kelamin = '$jenis_kelamin',alamat = '$alamat',tahun_penilaian = '$tahun_penilaian',password_guru = '$password_guru' where nip_guru = '$nip_guru'";
		
		
		if(mysqli_query($con,$sql)){
			echo "Successfully update";
		}else{
			echo "Could not update";

		}
	}else{
echo 'error';
}

?>