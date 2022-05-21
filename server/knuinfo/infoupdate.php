<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['username']) && isset($_POST['email']) && isset($_POST['phone'])) {
    if ($db->dbConnect()) {
        if ($db->infoupdate("users", $_POST['username'], $_POST['email'], $_POST['phone'])) {
            echo "update Success";
        } else echo "update Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
