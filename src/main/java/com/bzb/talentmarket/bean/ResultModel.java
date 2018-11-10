package com.bzb.talentmarket.bean;

public class ResultModel {
	private boolean success=true;
	private String message;
	private String code;
	private Object o;
	public ResultModel(){
		
	}
	public ResultModel(String message){
		this.message=message;
	}
	public ResultModel(boolean success,String message){
		this.success=success;
		this.message=message;
	}
	
	public ResultModel(boolean success, String message, Object o) {
		super();
		this.success = success;
		this.message = message;
		this.o = o;
	}
	public ResultModel(Object o){
		this.o=o;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public ResultModel setSuccess(boolean success) {
		this.success = success;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public ResultModel setMessage(String message) {
		this.message = message;
		return this;
	}
	
	public Object getO() {
		return o;
	}
	public void setO(Object o) {
		this.o = o;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public ResultModel setCode(String code) {
		this.code = code;
		return this;
	}
}
