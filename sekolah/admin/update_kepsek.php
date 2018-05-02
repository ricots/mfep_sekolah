<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
		//Getting values 
		$tlpn = $_POST['tlpn'];
		$nip_kepsek = $_POST['nip_kepsek'];
		$nama = $_POST['nama'];
		$alamat = $_POST['alamat'];
		$password = $_POST['password'];
		
		//importing database connection script 
		require_once('dbConnect.php');
		
		$sql1 = "update tbl_kepsek set tlpn='$tlpn',nama = '$nama',alamat='$alamat',password = '$password',nip_kepsek ='$nip_kepsek'";
		
		//Updating database table 
		if(mysqli_query($con,$sql1)){
			echo 'sukses';
		}else{
			echo 'coba lagi';
		}
		
		//closing connection 
		mysqli_close($con);
}
?>