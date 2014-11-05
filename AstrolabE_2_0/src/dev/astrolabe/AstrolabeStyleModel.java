package dev.astrolabe;

import java.awt.Color;

public enum AstrolabeStyleModel {
	
	//Objets directement construits
	GREEK(
			"Greek",
			Color.magenta,
			new Color[] {new Color(255, 254, 198),new Color(40,90,172),new Color(40,72,150)},
			Color.red,
			Color.red,
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
	
	
	
	private static AstrolabeStyleModel selected = MODERN;

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