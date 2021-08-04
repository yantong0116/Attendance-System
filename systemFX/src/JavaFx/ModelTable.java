package JavaFx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelTable {
	
	/*
	String ID, name, clas, semester, subject, teacher, reason, time, Result, createTime;
	Button btnProve,btnDelete;

	public ModelTable(String ID, String name, String clas, String semester, String subject, String teacher,
			String reason, String time, String Result, String createTime,Button prove,Button delete) {
		this.ID = ID;
		this.name = name;
		this.clas = clas;
		this.semester = semester;
		this.subject = subject;
		this.teacher = teacher;
		this.reason = reason;
		this.time = time;
		this.Result = Result;
		this.createTime = createTime;
		this.btnProve = new Button("Prove");
		this.btnDelete = new Button("Delete Apply");
	}
	*/

	private final StringProperty ID = new SimpleStringProperty();
	private final StringProperty name = new SimpleStringProperty();
	private final StringProperty clas = new SimpleStringProperty();
	private final StringProperty semester = new SimpleStringProperty();
	private final StringProperty subject = new SimpleStringProperty();
	private final StringProperty teacher = new SimpleStringProperty();
	private final StringProperty category = new SimpleStringProperty();
	private final StringProperty reason = new SimpleStringProperty();
	private final StringProperty time = new SimpleStringProperty();
	private final StringProperty Result = new SimpleStringProperty();
	private final StringProperty createTime = new SimpleStringProperty();
	
	public String getID() {
		return ID.get();
	}
	
	public void setID(String value) {
		ID.set(value);
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setName(String value) {
		name.set(value);
	}
	
	public String getClas() {
		return clas.get();
	}
	
	public void setClas(String value) {
		clas.set(value);
	}
	
	public String getSemester() {
		return semester.get();
	}
	
	public void setSemester(String value) {
		semester.set(value);
	}
	
	public String getSubject() {
		return subject.get();
	}
	
	public void setSubject(String value) {
		subject.set(value);
	}
	
	public String getTeacher() {
		return teacher.get();
	}
	
	public void setTeacher(String value) {
		teacher.set(value);
	}
	
	public String getCategory() {
		return category.get();
	}
	
	public void setCategory(String value) {
		category.set(value);
	}
	
	public String getReason() {
		return reason.get();
	}
	
	public void setReason(String value) {
		reason.set(value);
	}
	
	public String getTime() {
		return time.get();
	}
	
	public void setTime(String value) {
		time.set(value);
	}
	
	public String getResult() {
		return Result.get();
	}
	
	public void setResult(String value) {
		Result.set(value);
	}
	
	public String getCreateTime() {
		return createTime.get();
	}
	
	public void setCreateTime(String value) {
		createTime.set(value);
	}
	
	/*
	public void setBtnProve(Button button) {
		this.btnProve = button;
	}
	
	public Button getBtnProve() {
		return btnProve;
	}
	
	public void setBtnDelete(Button button) {
		this.btnDelete = button;
	}
	
	public Button getBtnDelete() {
		return btnDelete;
	}
	*/
}
