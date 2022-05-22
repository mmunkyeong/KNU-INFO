<?php
header("Content-Type:text/html;charset=utf-8");
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['studentid'])) {
    if ($db->dbConnect()) {
         $db->gettimetable_alarm("timetable",$_POST['studentid']);
    } 
    else echo "Error: Database connection";
} 
else echo "All fields are required";

?>
