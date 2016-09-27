package com.assistclass.analyzecase;

public class CaseItem {
	private int case_image_id;
	private String case_description;
	public int getCase_image_id() {
		return case_image_id;
	}
	public void setCase_image_id(int case_image_id) {
		this.case_image_id = case_image_id;
	}
	public String getCase_description() {
		return case_description;
	}
	public void setCase_description(String case_description) {
		this.case_description = case_description;
	}
	public CaseItem(int image_id,String description){
		case_image_id=image_id;
		case_description=description;
	}

}
