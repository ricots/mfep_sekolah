<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
		$id_kategori = $_POST['id_kategori'];
		$id_pertanyaan = $_POST['id_pertanyaan'];
		$nip_kepsek = $_POST['nip_kepsek'];
		$nip_guru = $_POST['nip_guru'];
		//$semester = $_POST['semester'];
		$id_pertanyaan = $_POST['id_pertanyaan'];
		$nip_kepsek = $_POST['nip_kepsek'];
		$keterangan = $_POST['keterangan'];
		$skor = $_POST['skor'];
		$total_skor = $_POST['total_skor'];
	
		require_once('dbConnect.php');
	
		$no = mysqli_query($con,"select * from tbl_penilaian order by id_penilaian desc limit 0,1");
		$no_excute = mysqli_fetch_array($no);
		$kodeawal=substr($no_excute['id_penilaian'],3,4)+1;
		 if($kodeawal<10){
		  $kode='PNO000'.$kodeawal;
		 }elseif($kodeawal > 9 && $kodeawal <=99){
		  $kode='PNO00'.$kodeawal;
		 }else{
		  $kode='PNO00'.$kodeawal;
		 }
		 $tgl=date('Y-m-d');
		 if ((date('m') >= 01) && (date('m') <= 06)){
		 	$semester = "GENAP";
		 }else{
		 	$semester = "GANJIL";
		 }

		$skor_konf = $skor / 10;
		$sql = "INSERT INTO tbl_penilaian VALUES ('$kode','$id_pertanyaan','$nip_kepsek','$nip_guru','$semester','$tgl','$keterangan','$skor','$skor_konf','$total_skor')";
		
		
		if(mysqli_query($con,$sql)){
			echo "Successfully";
		}else{
			echo "failed";

		}
	}else{
echo 'error';
}

?>