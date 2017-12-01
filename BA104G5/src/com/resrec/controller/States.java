package com.resrec.controller;

public enum States {
	ESTABLISH("ESTABLISH"),
	CONFIRM("CONFIRM"),
	CANCEL("CANCEL");
	
	@SuppressWarnings("unused")
	private String states;
	
	States(String states){
		this.states=states;
	}
	
	public String getString(){
		return this.name();
	}
}
