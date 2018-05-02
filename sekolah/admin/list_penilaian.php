<?php 
	require_once('dbConnect.php');
	$nip_guru = $_GET['nip_guru'];
	$sql = "SELECT SUM(tbl_penilaian.total_skor) as total, tbl_penilaian.nip_guru, tbl_penilaian.id_pertanyaan,tbl_penilaian.semester,tbl_penilaian.tanggal,tbl_penilaian.keterangan,tbl_penilaian.skor,tbl_penilaian.total_skor, tbl_guru.nama, tbl_pertanyaan.pertanyaan FROM tbl_penilaian,tbl_guru,tbl_pertanyaan WHERE tbl_penilaian.nip_guru = tbl_guru.nip_guru and tbl_penilaian.id_pertanyaan = tbl_pertanyaan.id_pertanyaan group by tbl_penilaian.nip_guru";
	
	$result = mysqli_query($con,$sql);
$number_of_rows = mysqli_num_rows($result);
$temp_array = array();

if($number_of_rows > 0){
while ($row = mysqli_fetch_assoc($result)){
array_push($temp_array,array(
	"nip_guru"=>$row['nip_guru'],
	"nama"=>$row['nama'],
	"total"=>number_format($row['total'])
 ));
}}
 echo json_encode(array("list_penilaian"=>$temp_array));

?>