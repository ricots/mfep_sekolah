<?php 
 $id_kategori = $_GET['id_kategori'];
 
 require_once('dbConnect.php');

 $sql = "DELETE FROM tbl_kategori WHERE id_kategori='$id_kategori'";
 
 if(mysqli_query($con,$sql)){
 echo 'Deleted Successfully';
 }else{
 echo 'Try Again';
 }
 ?>