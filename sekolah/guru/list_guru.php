<?php 
	require_once('dbConnect.php');
	$nip_guru = $_GET['nip_guru'];
	$sql = "SELECT * from tbl_guru where nip_guru ='$nip_guru'";
	
	$result = mysqli_query($con,$sql);
$number_of_rows = mysqli_num_rows($result);
$temp_array = array();

if($number_of_rows > 0){
while ($row = mysqli_fetch_assoc($result)){
array_push($temp_array,array(
		"nip_guru" => $row['nip_guru'],
		"nama" => $row['nama'],
		"nuptk" => $row['nuptk'],
		"nrg" => $row['nrg'],
		"tempat_lahir" => $row['tempat_lahir'],
		"tanggal_lahir" => $row['tanggal_lahir'],
		"masa_kerja" => $row['masa_kerja'],
		"tgl_mulai_bekerja" => $row['tgl_mulai_bekerja'],
		"pendidikan_terakhir" => $row['pendidikan_terakhir'],
		"spesialisasi" => utf8_encode($row['spesialisasi']),
		"alamat" => $row['alamat'],
		"masa_kerja" => $row['masa_kerja'],
		"tahun_penilaian" => $row['tahun_penilaian'],
		"password_guru" => $row['password_guru'],
		"jenis_kelamin" => $row['jenis_kelamin']
 ));
}}
 echo json_encode(array("list_guru"=>$temp_array));

?>