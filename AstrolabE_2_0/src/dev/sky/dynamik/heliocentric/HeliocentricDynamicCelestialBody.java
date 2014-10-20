package dev.sky.dynamik.heliocentric;

import java.awt.geom.Point2D;

import dev.sky.dynamik.DynamicCelestialBody;
import dev.sky.dynamik.geocentric.Sun;



public abstract class HeliocentricDynamicCelestialBody extends DynamicCelestialBody {

	protected double Na, Nb;
	protected double ia, ib;
	protected double wa, wb;
	protected double aa, ab;
	protected double ea, eb;
	protected double Ma, Mb;
	
	public HeliocentricDynamicCelestialBody(String name) {
		super(name);
	}

	public double N(double d) {
		return rev(Na*d + Nb);
	}
	
	public double i(double d) {
		return rev(ia*d + ib);
	}
	
	public double w(double d) {
		return rev(wa*d + wb);
	}
	
	public double a(double d) {
		return rev(aa*d + ab);
	}
	
	public double e(double d) {
		return rev(ea*d + eb);
	}
	
	public double M(double d) {
		return rev(Ma*d + Mb);
	}
	
	private double EccentricAnomalyIter(double in, double d) {
		double out = in - (in - rad2deg(e(d)*Math.sin(deg2rad(in)))-M(d))/(1-e(d)*Math.cos(deg2rad(in)));
		return out;
	}
	
	public double EccentricAnomaly(double d) {
		double E0 = 1 + e(d) * Math.cos(deg2rad(M(d)));
		E0 =  M(d) + rad2deg(e(d)*Math.sin(deg2rad(E0)));
				
		double E1 = EccentricAnomalyIter(E0, d);
		
		while (Math.abs(E0-E1) > 0.005) {
			E0 = E1;
			E1 = EccentricAnomalyIter(E0, d);
		}
		return E1;
	}
	
	public Point2D.Double HeliocentricPosition(double d) {
		Point2D.Double h = new Point2D.Double();
		h.x = a(d) * ( Math.cos(deg2rad(EccentricAnomaly(d))) - e(d));
		h.y = a(d) * Math.sqrt(1 - e(d)*e(d)) * Math.sin(deg2rad(EccentricAnomaly(d)));
		return h;
	}
	
	public double r(double d) {
		Point2D.Double p = HeliocentricPosition(d);
		return Math.sqrt(p.x*p.x + p.y*p.y);
	}
	
	public double v(double d) {
		Point2D.Double p = HeliocentricPosition(d);
		return rad2deg(Math.atan2(p.y, p.x));
	}
	
	public double[] heliocentricRectangularCoordinates(double d) {
		double r = r(d);
		double N = deg2rad(N(d));
		double v = deg2rad(v(d));
		double w = deg2rad(w(d));
		double i = deg2rad(i(d));
		double xeclip = r * ( Math.cos(N) * Math.cos(v+w) - Math.sin(N) * Math.sin(v+w) * Math.cos(i) );
		double yeclip = r * ( Math.sin(N) * Math.cos(v+w) + Math.cos(N) * Math.sin(v+w) * Math.cos(i) );
		double zeclip = r * Math.sin(v+w) * Math.sin(i);
		return new double[] {xeclip, yeclip, zeclip};
	}
	
	public double[] heliocentricSphericalCoordinates(double d) {
		double[] z = heliocentricRectangularCoordinates(d);
		double[] s = new double[3];
		
		s[0] = Math.sqrt(z[0]*z[0]+z[1]*z[1]+z[2]*z[2]);
		s[1] = rad2deg(Math.atan2(z[1], z[0]));
		s[2] = rad2deg(Math.atan2(z[2],Math.sqrt(z[0]*z[0]+z[1]*z[1])));
		
		return s;
	}
	
	public double[] heliocentricToGeocentricRectangularCoordinates(double d) {
		double[] xsun = Sun.sunRectangularCoordinates(d);
		double[] xplan = heliocentricRectangularCoordinates(d);
		
		double[] xgeoc = new double[3];
		for (int i=0; i<3; i++) {
			xgeoc[i] = xsun[i] + xplan[i];
		}
		return xgeoc;
	}
	
	public double[] geocentricRectangularToSphericalCoordinates(double d) {
		double[] s = heliocentricToGeocentricRectangularCoordinates(d);
		double[] t = new double[3];
		double oblecl = deg2rad(23.4393 - 3.563E-7 * d);
		
		t[0] = s[0];
		t[1] = s[1] * Math.cos(oblecl) - s[2] * Math.sin(oblecl);
		t[2]= s[1] * Math.sin(oblecl) + s[2] * Math.cos(oblecl);

		double[] z = new double[3];
		z[0] = Math.sqrt(t[0]*t[0]+t[1]*t[1]+t[2]*t[2]);
		z[1] = rad2deg(Math.atan2(t[1], t[0]));
		z[2] = rad2deg(Math.atan2(t[2],Math.sqrt(t[0]*t[0]+t[1]*t[1])));
		return z;
	}
	
	public static double[] parseDoubleTab(String[] strings) {
		double[] result = new double[strings.length];
		for (int i = 0; i<strings.length; i++) {
			result[i] = Double.parseDouble(strings[i]);
		}
		return result;
	}
}
