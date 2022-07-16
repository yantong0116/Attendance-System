# Attendance System

## Introduce
Develop an attendance system.
Store courses, instructors, and student-selected courses in the database.
Students can ask for leave and view attendance records on the system.
Teachers can audit students' leave requests and check class attendance.

## Development environment
- Language : Java
- IDE : Eclipse
- Database : phpMySQL
- UI : JavaFX (use SceneBuilder)

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

## Main File
### MySQL/
- courseinf.sql
- information.sql
- login.sql
- studenting.sql
- systemfx.sql

### systemFX/src/Connection
- Connection/ConnectionClass.class
### system/src/JavaFx
- ImageControl.java

## UI

![image](https://user-images.githubusercontent.com/51469882/179345116-1b416929-7ba5-4f76-acf4-637abe58cdc8.png)

### Student
#### Login
![image](https://user-images.githubusercontent.com/51469882/179345131-6a342b6c-f4d6-40d2-be97-c3566a1ca0ab.png)
#### Apply
![image](https://user-images.githubusercontent.com/51469882/179345143-cbfcfbf8-936b-4a78-ac42-c64911dd14e5.png)
![image](https://user-images.githubusercontent.com/51469882/179345147-41636e7f-60b7-4f55-9aa9-69a4e0511db4.png)
![image](https://user-images.githubusercontent.com/51469882/179345149-10b41a63-7c03-43ed-ae90-45269f5df262.png)

### Teacher
#### Login
![image](https://user-images.githubusercontent.com/51469882/179345160-5a97b12c-d5d9-4183-9d00-c2bd5f85048b.png)
![image](https://user-images.githubusercontent.com/51469882/179345167-a3d2941a-6f9c-4c56-9077-7d840ae12b03.png)
![image](https://user-images.githubusercontent.com/51469882/179345172-b090591e-55a8-4ae7-9f6a-a25877c51b0c.png)

### Manager
#### Login
![image](https://user-images.githubusercontent.com/51469882/179345179-3d2dbc65-d8a7-464f-91b1-3a0e311f42cd.png)
