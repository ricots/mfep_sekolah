<?php
include 'dbConnect.php';	
$nip_guru = $_GET['nip_guru'];
$sql = "select * from tbl_guru where nip_guru = '$nip_guru'";
$result = mysqli_query($con,$sql);
$number_of_rows = mysqli_num_rows($result);
$temp_array = array();

if($number_of_rows > 0){
while ($row = mysqli_fetch_assoc($result)){
array_push($temp_array,array(
"nip_guru"=>$row['nip_guru'],
"nama"=>$row['nama']
 ));
}}
 echo json_encode(array("guru"=>$temp_array));
 ?>