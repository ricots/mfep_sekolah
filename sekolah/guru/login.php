<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values 
 $nip_guru = $_POST['nip_guru'];
 $password_guru = $_POST['password_guru'];
 
 //Creating sql query
 $sql = "select * from tbl_guru where nip_guru='$nip_guru' and password_guru='$password_guru'";
 
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