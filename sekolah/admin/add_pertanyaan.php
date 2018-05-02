<?php
require_once('dbConnect.php');
if($_SERVER['REQUEST_METHOD']=='POST'){
		$id_kategori = $_POST['id_kategori'];
		$id_pertanyaan = $_POST['id_pertanyaan'];
		$pertanyaan = $_POST['pertanyaan'];
		$bobot = $_POST['bobot'];
		
		

		$no = mysqli_query($con,"select * from tbl_pertanyaan order by id_pertanyaan desc limit 0,1");
		$no_excute = mysqli_fetch_array($no);
		$kodeawal=substr($no_excute['id_pertanyaan'],3,4)+1;
		 if($kodeawal<10){
		  $kode='PTO000'.$kodeawal;
		 }elseif($kodeawal > 9 && $kodeawal <=99){
		  $kode='PTO00'.$kodeawal;
		 }else{
		  $kode='PTO00'.$kodeawal;
		 }
		 		
		$sql = "insert into tbl_pertanyaan values('$id_kategori','$kode','$pertanyaan','$bobot')";
		
		if(mysqli_query($con,$sql)){
			echo "Successfully save";
			
		}else{
			echo "Could not save";
		}

		}
	else{
echo 'error';
}

			$kurang_bobot = mysqli_query($con, "select * from tbl_pertanyaan order by bobot DESC limit 1");

			//$kurang = mysqli_fetch_array($kurang_bobot);
			$get_bobot = [];
			while ($row = mysqli_fetch_assoc($kurang_bobot)){
				$get_bobot[$row['id_pertanyaan']] = $row['bobot'];

			}

			$id_pertanyaan_kurang;
			$bobot_besar;
			foreach ($get_bobot as $key => $value) {
				$id_pertanyaan_kurang = $key;
				$bobot_besar = $value;
			}
			//print_r($id_pertanyaan_kurang . $bobot_besar);
			$bobot_kurang = $bobot_besar - $bobot; 
			
			$update ="update tbl_pertanyaan set bobot='$bobot_kurang' where id_pertanyaan ='$id_pertanyaan_kurang'";
			
			if(mysqli_query($con,$update)){
			echo "Successfully update";
			
		}else{
			echo "Could not update";
		}
?>