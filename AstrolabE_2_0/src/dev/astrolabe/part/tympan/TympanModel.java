package dev.astrolabe.part.tympan;

public class TympanModel {
	
	/**Tympan Attributes*/
	private double[] R_h;
	private double[] C_h;
	private double[] R_a;
	private double[] C_a;
	private double pi;
	
	protected final int N_ALTITUDE = 18;
	protected final int N_AZIMUTH = 18;
	/**Methodes*/
	
	public void set_h(double latitude, double equatorRadius) {
		int dh = 90/N_ALTITUDE;
		R_h = new double[N_ALTITUDE+2];
		C_h = new double[N_ALTITUDE+2];
		for(int i=0;i<=N_ALTITUDE;i++) {
			double h = i*dh;
			double p_h1   = equatorRadius*Math.tan(Math.toRadians((h - latitude)/2));
			double p_h2   = equatorRadius/Math.tan(Math.toRadians((h + latitude)/2));
			R_h[i] = (p_h2 - p_h1)/2;
			C_h[i] = (p_h2 + p_h1)/2;
		}
		int h = -18;
		double p_h1     = equatorRadius*Math.tan(Math.toRadians((h - latitude)/2));
		double p_h2     = equatorRadius/Math.tan(Math.toRadians((h + latitude)/2));
		R_h[N_ALTITUDE+1] = (p_h2 - p_h1)/2;
		C_h[N_ALTITUDE+1] = (p_h2 + p_h1)/2;
	}
	
	public void set_pi_a(double latitude, double equatorRadius) {
		double pz = equatorRadius*Math.tan(Math.toRadians(( 90. - latitude)/2.));
		double pn = equatorRadius*Math.tan(Math.toRadians((-90. - latitude)/2.));
		pi =  (pz + pn)/2;
		double iz = - pi + pz;

		double da = 180./N_AZIMUTH;
		C_a = new double[N_AZIMUTH+1];
		R_a = new double[N_AZIMUTH+1];
		double E_a=0;
		for (int i=0;i<=N_AZIMUTH;i++) {
			C_a[i] = - iz/Math.tan(Math.toRadians(i*da));
			E_a    = + iz/Math.tan(Math.toRadians(i*da/2));
			R_a[i] = E_a + C_a[i];
		}
	}
	
	public double[] getR_h() {
		return R_h;
	}

	public double[] getC_h() {
		return C_h;
	}

	public double[] getR_a() {
		return R_a;
	}

	public double[] getC_a() {
		return C_a;
	}
	
	public double getPi() {
		return pi;
	}
}
