<?php
 header("Content-Type:text/html;charset=utf-8"); 
require "DataBase.php";
$db = new DataBase();
    if ($db->dbConnect()) {
      $db->timetable("lecture");      
    } else echo "Error: Database connection";
?>
