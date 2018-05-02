<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values 
 $nip_kepsek = $_POST['nip_kepsek'];
 $password = $_POST['password'];
 
 //Creating sql query
 $sql = "select * from tbl_kepsek where nip_kepsek='$nip_kepsek' and password='$password'";
 
require_once('dbConnect.php');

 
 //executing query
 $result = mysqli_query($con,$sql);
 
 //fetching result
 $check = mysqli_fetch_array($result);
 
 //if we got some result 
 if(isset($check)){
 //displaying success 
 echo "success";
 }else{
 //displaying failure
 echo "failure";
 }
// mysqli_close($con);
 }
 ?>