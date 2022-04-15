<?php
require "DataBase.php";
$db = new DataBase();
#classid	studentid	classname	classtime	classlocation
if (isset($_POST['classid']) && isset($_POST['studentid']) && isset($_POST['classname']) && isset($_POST['classtime'])&& isset($_POST['classlocation'])) {
    if ($db->dbConnect()) {
        if ($db->addtimetable("timetable", $_POST['classid'], $_POST['studentid'], $_POST['classname'], $_POST['classtime'],$_POST['classlocation'] )) {
            echo "add timetable Success";
        } else echo "add timetable Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
