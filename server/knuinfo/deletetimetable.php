<?php
header("Content-Type:text/html;charset=utf-8");
require "DataBase.php";

$db = new DataBase();
if (isset($_POST['classid']) && isset($_POST['studentid'])) {

    if ($db->dbConnect()){
         if($db->deletetimetable("timetable",$_POST['classid'], $_POST['studentid'])==true){
            echo "timetable delete success";
         } else {
            echo "timetable delete fail";
         }
    }
    else{
        echo "timetable delete required";
    }
} 
else echo "Error: Database deletetimetable connection";

?>
