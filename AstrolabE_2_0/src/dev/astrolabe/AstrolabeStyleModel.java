package dev.astrolabe;

import java.awt.Color;

public enum AstrolabeStyleModel {
	
	//Objets directement construits
	GREEK(
			"Greek",
			Color.magenta,
			Color.orange,
			Color.red,
			Color.red,
			Color.black
			),
	ARAB(
			"Arab",
			Color.red,
			Color.orange,
			Color.red,
			Color.red,
			Color.black
			),
	RENAISSANCE(
			"Renaissance",
			Color.pink,
			Color.orange,
			Color.red,
			Color.red,
			Color.black
			),
	MODERN(
			"Modern",
			Color.white,
			Color.orange,
			Color.red,
			Color.red,
			Color.black
			);

	private String name = "";
	private Color backgroundColor = Color.white;
	private Color tympanColor = Color.orange;
	
	private Color equatorColor = Color.red;
	private Color tropicsColor = Color.red;
	private Color eclipticColor = Color.black;
	
	private static AstrolabeStyleModel selected = MODERN;

	//Constructeur
	AstrolabeStyleModel(
			String name,
			Color backgroundColor,
			Color tympanColor,
			Color equatorColor,
			Color tropicsColor,
			Color eclipticColor){
		this.name = name;
		this.backgroundColor = backgroundColor;
		this.tympanColor = tympanColor;
		this.equatorColor = equatorColor;
		this.tropicsColor = tropicsColor;
		this.eclipticColor = eclipticColor;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString() {
		return getName();
	}
	
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	
	public static AstrolabeStyleModel get(int i) {
		switch (i) {
		case 0:
			return GREEK;
		case 1:
			return ARAB;
		case 2:
			return RENAISSANCE;
		case 3:
			return MODERN;
		default:
			return MODERN;
		}
	}
	
	public static AstrolabeStyleModel getSelected() {
		return selected;
	}

	public static void setSelected(AstrolabeStyleModel select) {
		selected = select;
	}
	
	public static void setSelected(String select) {
		switch (select) {
		case "Greek":
			selected = GREEK;
			break;
		case "Arab":
			selected = ARAB;
			break;
		case "Renaissance":
			selected = RENAISSANCE;
			break;
		case "Modern":
			selected = MODERN;
			break;
		default:
			selected = MODERN;
			break;
		}
	}

	public Color getTympanColor() {
		return tympanColor;
	}
	
	public Color getEquatorColor() {
		return equatorColor;
	}
	
	public Color getTropicsColor() {
		return tropicsColor;
	}
	
	public Color getEclipticColor() {
		return eclipticColor;
	}


}