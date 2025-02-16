<?php 
header("Content-Type:text/html;charset=utf-8");
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function logIn($table, $username, $password)
    {
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $this->sql = "select * from " . $table . " where username = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbusername = $row['username'];
            $dbpassword = $row['password'];
            if ($dbusername == $username && password_verify($password, $dbpassword)) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table, $fullname, $email, $username, $password,$phone)
    {
        $fullname = $this->prepareData($fullname);
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $email = $this->prepareData($email);
        $password = password_hash($password, PASSWORD_DEFAULT);
        $this->sql =
            "INSERT INTO " . $table . " (fullname, username, password, phone, email) VALUES ('" . $fullname . "','" . $username . "','" . $password . "','" . $phone . "','" . $email . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function timetable($table)
    {
        $this->sql = "select * from " . $table . "";
        $result = mysqli_query($this->connect, $this->sql);

	    while ($row = mysqli_fetch_array($result, MYSQLI_NUM)) {
        	foreach ($row as $r) {
	            echo $r;
         	    echo "|";
	        }
		    echo "$";
        }   
        return false;
    }
    
    function addtimetable($table, $classid, $studentid, $classname, $classtime, $classlocation, $professor, $actTime, $diffCheck)
    {
        $classid = $this->prepareData($classid);
        $studentid = $this->prepareData($studentid);
        $classname = $this->prepareData($classname);
        $classtime = $this->prepareData($classtime);
        $classlocation = $this->prepareData($classlocation);
        $professor = $this->prepareData($professor);
        $actTime = $this->prepareData($actTime);
        $diffCheck = $this->prepareData($diffCheck);
        $this->sql =
            "INSERT INTO " . $table . " (classid, studentid, classname, classtime, classlocation, professor, actTime, diffCheck) VALUES ('" . $classid . "','" . $studentid . "','" . $classname . "','" . $classtime . "','" . $classlocation . "','" . $professor . "','" . $actTime . "','" . $diffCheck . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function gettimetable($table, $studentid )
    {
        $this->sql = "select * from " . $table . " where studentid = '" . $studentid . "'";
        $result = mysqli_query($this->connect, $this->sql);

	    while ($row = mysqli_fetch_array($result, MYSQLI_NUM)) {
        	foreach ($row as $r) {
	            echo $r;
         	    echo "|";
	        }
		    echo "$";
        }   
        return false;
    }
     function gettimetable_json($table)
    {
	$this->sql = "select * from " . $table . "";
	$result = mysqli_query($this->connect, $this->sql);
	$rowCnt = mysqli_num_rows($result);
	$arr = array();
	for($i=0;$i<$rowCnt;$i++){
        		$row= mysqli_fetch_array($result, MYSQLI_ASSOC);
        		//각 각의 row를 $arr에 추가
        		$arr[$i]= $row;
        
    	}
 
    	//배열을 json으로 변환하는 함수가 있음.
        	$jsonData=json_encode($arr,JSON_UNESCAPED_UNICODE); //json배열로 만들어짐.
        	echo "$jsonData";
    
	    
    }
    function gettimetable_($table, $studentid, $actTime, $diffCheck)
    {
    
        $this->sql = "select diffCheck from " . $table . " where studentid = '" . $studentid . "'";
        $result = mysqli_query($this->connect, $this->sql);

	    while ($row = mysqli_fetch_array($result, MYSQLI_NUM)) {
        	#Check1 : 추가하려는 강의 시간
            #diffCheck1 : 추가되어있는 강의 시간
            foreach ($row as $r) {
	            $Check1 = explode("\n",$diffCheck);
                $diffCheck1 = explode("\n",$r);
                foreach($Check1 as $value1){
                    $Check2 = explode("#",$value1);
                    $week1=$Check2[0];
                    $Check3 = explode("|",$Check2[1]);
                    $firstTime1 = $Check3[0];
                    $firstTime2 = $Check3[1];
                    foreach($diffCheck1 as $diffvalue1){
                        $diffCheck2 = explode("#",$diffvalue1);
                        if($week1==$diffCheck2[0]){
                            $diffCheck3 = explode("|",$diffCheck2[1]);
                            if($diffCheck3[0]<=$firstTime1&&$firstTime1<$diffCheck3[1]){                            
                                return true;                            
                            }
                        }
                    }
                }
            }
            
        }   
        return false;
    }


    function deletetimetable($table, $classid, $studentid)
    {
        $classid = $this->prepareData($classid);
        $studentid = $this->prepareData($studentid);
        $this->sql = "delete from " . $table . " where studentid = '" . $studentid . "' and classid = '" . $classid . "'";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function passcheck($table, $password)
    {
        $password = $this->prepareData($password);
        $password = password_hash($password, PASSWORD_DEFAULT);
        $this->sql = "select * from " . $table . " where password = '" . $password . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbpassword = $row['password'];
            if (password_verify($password, $dbpassword)) {
                $passcheck = true;
            } else $passcheck = false;
        } else $passcheck = false;
    
        return $passcheck;
    }
    
    function infoupdate($table,$username, $email, $phone)
    {
        $username = $this->prepareData($username);
        $phone = $this->prepareData($phone);
        $email = $this->prepareData($email);
        $this->sql = "update " . $table . " set email = '" . $email . "' , phone= '" . $phone. "' where username= '" . $username . "'";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function getinfo($table, $username)
    {
        $username = $this->prepareData($username);

        $this->sql = "select * from " . $table ." where username = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
        while ($row = mysqli_fetch_array($result, MYSQLI_NUM)) {
        	foreach ($row as $r) {
	            echo $r;
         	    echo "|";
	        }
		    return true;
        }   
        // if (mysqli_query($this->connect, $this->sql)) {
        //     return true;
        // } else return false;
    }

    function gettimetable_alarm($table, $studentid )
    {
        $this->sql = "select classname, actTime from " . $table . " where studentid = '" . $studentid . "'";
        $result = mysqli_query($this->connect, $this->sql);

	    while ($row = mysqli_fetch_array($result, MYSQLI_NUM)) {
        	foreach ($row as $r) {
	            echo $r;
         	    echo "|";
	        }
		    echo "$";
        }   
        return false;
    }
}
?>