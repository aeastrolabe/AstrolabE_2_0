package dev.utils;

import java.awt.BasicStroke;

public class AstrolabeStroke extends BasicStroke {
	
	public final static int ULTRA_THIN = 0;
	public final static int THIN = 1;
	public final static int MEDIUM = 2;
	public final static int LARGE = 3;
	
	public final static int SOLID = 0;
	public final static int DASHED = 1;

	
	
	public static BasicStroke newStroke(int diam, int lineType) {
		switch (diam+4*lineType) {
			case 0:
				return new BasicStroke(0.5f, CAP_ROUND, JOIN_MITER, 10.0f);
			case 1:
				return new BasicStroke(1.0f, CAP_ROUND, JOIN_MITER, 10.0f);
			case 2:
				return new BasicStroke(1.25f, CAP_ROUND, JOIN_MITER, 10.0f);
			case 3:
				return new BasicStroke(1.5f, CAP_ROUND, JOIN_MITER, 10.0f);
			case 4:
				return new BasicStroke(0.5f, CAP_BUTT, JOIN_MITER, 10.0f, new float[] {10.0f, 10.0f}, 0.0f);
			case 5:
				return new BasicStroke(1.0f, CAP_BUTT, JOIN_MITER, 10.0f, new float[] {10.0f, 10.0f}, 0.0f);
			case 6:
				return new BasicStroke(1.25f, CAP_BUTT, JOIN_MITER, 10.0f, new float[] {10.0f, 10.0f}, 0.0f);
			case 7:
				return new BasicStroke(1.5f, CAP_BUTT, JOIN_MITER, 10.0f, new float[] {10.0f, 10.0f}, 0.0f);
			default:
				return new BasicStroke();
		}
	}
}
