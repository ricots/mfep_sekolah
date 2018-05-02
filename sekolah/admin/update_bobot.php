<?php 

require_once('dbConnect.php');

// if($_SERVER['REQUEST_METHOD']=='POST'){

    $id_pertanyaan = $_POST['id_pertanyaan'];
    $bobot         = (float) $_POST['bobot'];

    // update sukses
    $max_bobot = getMaxBobot($con);

    if(updateBobot($con, $id_pertanyaan, $bobot)){                

        $bobot;
        if(getSisaBobot($con) > 0.00){
            $bobot = $max_bobot['bobot'] - abs(getSisaBobot($con));
        }else{
            $bobot = $max_bobot['bobot'] + abs(getSisaBobot($con));
        }
        $res = updateBobot($con, $max_bobot['id_pertanyaan'],$bobot);
    }

        echo "sisa bobot : ".getSisaBobot($con);
        echo "<br>";
        echo "all bobot : ".allBobot($con);
    

    


// }

function updateBobot($con, $id_pertanyaan, $bobot){
    return mysqli_query($con,"UPDATE tbl_pertanyaan SET bobot='$bobot' where id_pertanyaan ='$id_pertanyaan'");
}

function getSisaBobot($con){
    $sisa_bobot = mysqli_query($con, "SELECT ROUND(SUM(bobot),2) - 1 as nilai FROM `tbl_pertanyaan`");
    $bobot;
    while ($row = mysqli_fetch_assoc($sisa_bobot)){
        $bobot = $row['nilai'];
    }
    return $bobot;
}

function getMaxBobot($con){
    $max_bobot = mysqli_query($con, "SELECT id_pertanyaan, bobot FROM tbl_pertanyaan ORDER BY bobot DESC LIMIT 1");
    $bobot = array();
    while ($row = mysqli_fetch_assoc($max_bobot)){
        $bobot = array(
                    'id_pertanyaan' => $row['id_pertanyaan'],
                    'bobot'         => $row['bobot']
                );
    }
    return $bobot;
}

function allBobot($con){
    $sisa_bobot = mysqli_query($con, "SELECT ROUND(SUM(bobot),2) as nilai FROM `tbl_pertanyaan`");
    $bobot;
    while ($row = mysqli_fetch_assoc($sisa_bobot)){
        $bobot = $row['nilai'];
    }
    return $bobot;
}


?>