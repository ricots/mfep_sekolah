<?php 
	$id_ketua_rt = $_GET['id_ketua_rt'];
	require_once('dbConnect.php');
	$sql = "SELECT * FROM tbl_kepsek";
	
	$result = mysqli_query($con,$sql);
$number_of_rows = mysqli_num_rows($result);
$temp_array = array();

if($number_of_rows > 0){
while ($row = mysqli_fetch_assoc($result)){
array_push($temp_array,array(
	"nip_kepsek"=>$row['nip_kepsek'],
	"nama"=>$row['nama'],
	"alamat"=>$row['alamat'],
	"tlpn"=>$row['tlpn'],
	"password"=>$row['password']
 ));
}}
 echo json_encode(array("list_kepsek"=>$temp_array));

?>