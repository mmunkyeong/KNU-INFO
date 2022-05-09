<?php
header("Content-Type:text/html;charset=utf-8");
require "DataBase.php";
$db = new DataBase();
#classid	studentid	classname	classtime	classlocation
if (isset($_POST['classid']) && isset($_POST['studentid']) && isset($_POST['classname']) 
&& isset($_POST['classtime'])&& isset($_POST['classlocation'])&&isset($_POST['professor'])
&&isset($_POST['actTime'])&&isset($_POST['diffCheck'])) {
    if ($db->dbConnect()) {
        $duplicate = $db->gettimetable_("timetable", $_POST['studentid'], $_POST['actTime'], $_POST['diffCheck']);
        
        if ($duplicate==false && $db->addtimetable("timetable", $_POST['classid'], $_POST['studentid'], $_POST['classname'],
         $_POST['classtime'],$_POST['classlocation'],$_POST['professor'],$_POST['actTime'] ,$_POST['diffCheck'])) {
            echo "add timetable Success";
        } else { 
            if($duplicate==true)
                echo "add timetable Failed_duplicate";    
            else 
                echo "add timetable Failed";
        }
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
