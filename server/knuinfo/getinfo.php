<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['username'])) {
    if ($db->dbConnect()) {
        if ($db->getinfo("users",$_POST['username'])) {
            echo "Get infomation Success";
        } else echo "Get infomation Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
