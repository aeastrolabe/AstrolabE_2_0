package dev.astrolabe;

import java.awt.Color;

public enum AstrolabeStyleModel {
	
	//Objets directement construits
	GREEK(
			"Greek",
			Color.magenta,
			new Color[] {new Color(212, 187, 80),new Color(113,102,40),new Color(100,94,40)},
			new Color(40,40,40),
			new Color(40,40,40),
			Color.black
			),
	ARAB(
			"Arab",
			Color.red,
			new Color[] {new Color(90, 150, 240),new Color(40,90,172),new Color(40,72,150)},
			Color.red,
			Color.red,
			Color.black
			),
	RENAISSANCE(
			"Renaissance",
			Color.pink,
			new Color[] {new Color(90, 150, 240),new Color(40,90,172),new Color(40,72,150)},
			Color.red,
			Color.red,
			Color.black
			),
	MODERN(
			"Modern",
			Color.white,
			new Color[] {new Color(90, 150, 240),new Color(40,90,172),new Color(40,72,150)},
			Color.red,
			Color.red,
			Color.black
			);

	private String name = "";
	private Color backgroundColor = Color.white;
	
	private float[] tympanFractions = new float[] {0.0f, 0.8f, 1.0f};
	private Color[] tympanColors = new Color[] {new Color(90, 150, 240),new Color(40,90,172),new Color(40,72,150)};
	
	private Color equatorColor = Color.red;
	private Color tropicsColor = Color.red;
	private Color eclipticColor = Color.black;

	//Constructeur
	AstrolabeStyleModel(
			String name,
			Color backgroundColor,
			Color[] tympanColors,
			Color equatorColor,
			Color tropicsColor,
			Color eclipticColor){
		this.name = name;
		this.backgroundColor = backgroundColor;
		this.tympanColors = tympanColors;
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

	public static AstrolabeStyleModel get(String select) {
		switch (select) {
		case "Greek":
			return GREEK;
		case "Arab":
			return ARAB;
		case "Renaissance":
			return RENAISSANCE;
		case "Modern":
			return MODERN;
		default:
			return MODERN;
		}
	}

	public Color[] getTympanColors() {
		return tympanColors;
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

	/**
	 * @return the tympanFractions
	 */
	public float[] getTympanFractions() {
		return tympanFractions;
	}

	/**
	 * @param tympanFractions the tympanFractions to set
	 */
	public void setTympanFractions(float[] tympanFractions) {
		this.tympanFractions = tympanFractions;
	}


}