# JAVA Attendance System

## Introduce
Develop an attendance system.
Store courses, instructors, and student-selected courses in the database.
Students can ask for leave and view attendance records on the system.
Teachers can audit students' leave requests and check class attendance.

## Development environment
- Java Developers : Eclipse IDE
- Database : phpMySQL
- UI : JavaFX Scene Builder

![image](https://user-images.githubusercontent.com/51469882/151664538-7b6a0b48-78d6-43c5-8d22-a9e589301749.png)

## Connect the project to database

```
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
public Connection connection;
    public Connection getConnection(){

        String dbName="systemfx";
        String userName="root";
        String password="";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.
            1:3306/"+dbName,userName,password);
        } catch (Exception e) {
            e.printStackTrace();
        }  
            return connection;
    }
}
```

## Table
### login

Record login account, password and permissions.

|Name |Type|Description|
|-----|--------|--------|
|ID|varchar(225)|登入帳號(學號/編號)|
|name|varchar(225)|學生/教師姓名|
|Grade|varchar(225)|系級/單位|
|password|int(11)|登入密碼|
|KeyValue|int(10)|登入權限|

### courseinf

Record information about courses.

|Name |Type|Description|
|-----|--------|--------|
|semester|varchar(225)|學期|
|CourseID|varchar(225)|課程代碼|
|CourseName|varchar(225)|課程名稱|
|TeacherID|varchar(225)|授課老師代碼|

### studentinf

Record the student's courses selection information.

|Name |Type|Description|
|-----|--------|--------|
|semester|varchar(225)|學期|
|ID|varchar(225)|學號|
|CourseID|int(10)|課程代碼|

### information

Complete leave information for the student.

|Name |Type|Description|
|-----|--------|--------|
|ID|varchar(225)|登入帳號(學號/編號)|
|name|varchar(225)|學生姓名|
|class|varchar(225)|系級|
|semester|varchar(225)|學期|
|subject|varchar(225)|請假課程(代碼)|
|teacher|varchar(225)|教師代碼|
|category|varchar(225)|請假類別|
|reason|varchar(225)|請假原因|
|time|varchar(225)|請假日期與節次|
|confirm|varchar(225)|審核進度|
|createTime|datetime|申請時間|

## System UI
### Student
#### Login

#### Apply

### Teacher
#### Login

### Manager
#### Login






