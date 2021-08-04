package JavaFx;

import javafx.beans.property.SimpleStringProperty;

import javafx.beans.property.StringProperty;

public class LessModelTable {
	private final StringProperty ID = new SimpleStringProperty();
	private final StringProperty name = new SimpleStringProperty();
	private final StringProperty grade = new SimpleStringProperty();
	private final StringProperty times = new SimpleStringProperty();
	
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
	
	public String getGrade() {
		return grade.get();
	}
	
	public void setGrade(String value) {
		grade.set(value);
	}
	
	public String getTimes() {
		return times.get();
	}
	
	public void setTimes(String value) {
		times.set(value);
	}
}
