<?php 
	require_once('dbConnect.php');
	$nip_guru = $_GET['nip_guru'];
	$sql = "SELECT tbl_kategori.id_kategori, tbl_pertanyaan.id_pertanyaan, tbl_pertanyaan.pertanyaan, tbl_pertanyaan.bobot, tbl_penilaian.semester, tbl_penilaian.tanggal, tbl_penilaian.keterangan,tbl_penilaian.total_skor FROM tbl_penilaian, tbl_pertanyaan, tbl_kategori where tbl_penilaian.id_pertanyaan = tbl_pertanyaan.id_pertanyaan and tbl_kategori.id_kategori = tbl_pertanyaan.id_kategori and tbl_penilaian.nip_guru ='$nip_guru'";
	
	$result = mysqli_query($con,$sql);
$number_of_rows = mysqli_num_rows($result);
$temp_array = array();

if($number_of_rows > 0){
while ($row = mysqli_fetch_assoc($result)){
array_push($temp_array,array(
	"id_pertanyaan"=>$row['id_pertanyaan'],
	"pertanyaan"=>$row['pertanyaan'],
	"semester"=>$row['semester'],
	"keterangan"=>$row['keterangan'],
	"total_skor"=>$row['total_skor']
 ));
}}
 echo json_encode(array("list_detail_penilaian"=>$temp_array));

?>