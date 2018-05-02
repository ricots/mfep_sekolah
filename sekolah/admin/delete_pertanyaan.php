<?php 
 $id_pertanyaan = $_GET['id_pertanyaan'];
 
 require_once('dbConnect.php');

 $sql = "DELETE FROM tbl_pertanyaan WHERE id_pertanyaan='$id_pertanyaan'";
 
 if(mysqli_query($con,$sql)){
 echo 'Deleted Successfully';
 }else{
 echo 'Try Again';
 }
 ?>