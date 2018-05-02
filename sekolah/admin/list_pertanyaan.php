<?php 
	require_once('dbConnect.php');
	$id_kategori = $_GET['id_kategori'];
	$sql = "SELECT * FROM tbl_pertanyaan where id_kategori ='$id_kategori'";
	
	$result = mysqli_query($con,$sql);
$number_of_rows = mysqli_num_rows($result);
$temp_array = array();

if($number_of_rows > 0){
while ($row = mysqli_fetch_assoc($result)){
array_push($temp_array,array(
	"id_kategori"=>$row['id_kategori'],
	"id_pertanyaan"=>$row['id_pertanyaan'],
	"pertanyaan"=>$row['pertanyaan'],
	"bobot"=>$row['bobot']
 ));
}}
 echo json_encode(array("list_pertanyaan"=>$temp_array));

?>