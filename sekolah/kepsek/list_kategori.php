<?php 
	require_once('dbConnect.php');
	$sql = "SELECT * from tbl_kategori";
	
	$result = mysqli_query($con,$sql);
$number_of_rows = mysqli_num_rows($result);
$temp_array = array();

if($number_of_rows > 0){
while ($row = mysqli_fetch_assoc($result)){
array_push($temp_array,array(
	"id_kategori"=>$row['id_kategori'],
	"kategori"=>$row['kategori']
 ));
}}
 echo json_encode(array("list_kategori"=>$temp_array));

?>