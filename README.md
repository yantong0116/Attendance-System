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

|File name |description|
|-----|--------|
|ImageControl.java|顯示請假申請的請假證明圖片|
|information.java|請假申請寫入資料庫，並檢查相同請假需求不能重複申請|
|LessModelTable.java|表格資料取得設定|
|login.java|登入功能|
|Main.java|執行 project，進入登入畫面|
|ManagerControl.java|管理者選擇csv檔，匯入資料|
|ModelTable.java|表格資料取得設定|
|ShowRecord.java|學生顯示請假紀錄，以及取消請假申請驗證|
|StudentControl.java|學生進行請假申請|
|TeacherControl.java|教師查看學生的請假申請，以及進行審核的動作|
|TeaLessControl.java|教師查看課程學生的出勤狀況|
|application.css||
|ImageFX.fxml|顯示請假證明圖片|
|loginFX.fxml|登入畫面|
|ManagerFX.fxml||
|ShowRecordFX.fxml||
|StudentFX.fxml||
|StuHamburger.fxml||
|TeacherFX.fxml||
|TeaHamburger.fxml||
|TeaLessonsFX.fxml||

## UI

![image](https://user-images.githubusercontent.com/51469882/179345116-1b416929-7ba5-4f76-acf4-637abe58cdc8.png)

### Student
- Leave application screen

![image](https://user-images.githubusercontent.com/51469882/179345131-6a342b6c-f4d6-40d2-be97-c3566a1ca0ab.png)

- View all your leave application

![image](https://user-images.githubusercontent.com/51469882/179345143-cbfcfbf8-936b-4a78-ac42-c64911dd14e5.png)
![image](https://user-images.githubusercontent.com/51469882/179345147-41636e7f-60b7-4f55-9aa9-69a4e0511db4.png)
![image](https://user-images.githubusercontent.com/51469882/179345149-10b41a63-7c03-43ed-ae90-45269f5df262.png)

### Teacher
- View all leave requests

![image](https://user-images.githubusercontent.com/51469882/179345160-5a97b12c-d5d9-4183-9d00-c2bd5f85048b.png)
![image](https://user-images.githubusercontent.com/51469882/179345167-a3d2941a-6f9c-4c56-9077-7d840ae12b03.png)
![image](https://user-images.githubusercontent.com/51469882/179345172-b090591e-55a8-4ae7-9f6a-a25877c51b0c.png)

- View attendance for courses

![image](https://user-images.githubusercontent.com/51469882/179346711-88aec1df-2eac-4da1-948d-0814e0d6b2f9.png)

### Manager
- upload .csv file to 

![image](https://user-images.githubusercontent.com/51469882/179345179-3d2dbc65-d8a7-464f-91b1-3a0e311f42cd.png)
