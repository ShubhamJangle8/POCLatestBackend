package com.ikea.statistics.constants;

public enum ColorsEnum {
//	CYAN_AZURE("#528AAE"),
//	MOONSTONE_BLUE("#73A5C6"),
//	DARK_SKY_BLUE("#91BAD6"),
//	BEAU_BLUE("#BCD2E8");
	
	BlUE("#167793"),
//	SANDAL("#F6C85F"),
	CORAL_REEF("#F8776A"),
	SMITTER("#BB447F"),
	MOUNBATTEN_PINK("#987A8C"),
	VIOLET("#6F4E7C"),
	MORNING_BLUE("#83A8A1"),
	GREEN("#9DD866"),
	RED("#CA472F"),
	PEACH("#FFA056"),
	LIGHT_BLUE("#8DDDD0"),
	
	RAINBOW_INDIGO("#1E3F66"), 
	METALIC_BLUE("#2E5984");
	
//	BAYERN_BLUE("#0066b2"), 
//	DALLAS_COWBOYS_BLUE("#041E42"),
//	AIR_FORCE_BLUE("#00308F"), 
//	LOWES_BLUE("#004792"), 
//	MAVERICKS_BLUE("#00538C"), 
//	WARRIORS_BLUE("#1D428A"), 
//	DUKE_BLUE("#012169"),
//	CUBS_BLUE("#0E3386"), 
//	CAROLINA_BLUE("#4B9CD3");

	public final String hexCode;

	private ColorsEnum(String hexCode) {
		this.hexCode = hexCode;
	}
}
