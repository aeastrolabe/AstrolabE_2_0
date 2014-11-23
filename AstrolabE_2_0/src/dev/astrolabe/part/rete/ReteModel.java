package dev.astrolabe.part.rete;

public class ReteModel {
	
	public ReteModel() {
		
	}

	private String[] months = new String[] {
			"January",
			"February",
			"March",
			"April",
			"May",
			"June",
			"July",
			"August",
			"September",
			"October",
			"November",
			"December"};
	
	public String[] getMonths() {
		return months;
	}
	
	public String getMonth(int i) {
		return months[i-1];
	}
}
