package dev.astrolabe;

public class AstrolabeLocalisationModel {

	/*
	 * hemisphere == 1 in the N and -1 in the S
	 */
	private int hemisphere = 1;
	
	/*
	 *  latitude >= 0
	 */
	private double latitude = 48.53;
	
	private double longitude = 0;

	/**
	 * @return the hemisphere
	 */
	public int getHemisphere() {
		return hemisphere;
	}

//	/**
//	 * @param hemisphere the hemisphere to set
//	 */
//	public void setHemisphere(int hemisphere) {
//		this.hemisphere = hemisphere;
//	}
//
	/**
	 * @return the latitude
	 */
	
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
		hemisphere = (int) Math.signum(latitude);
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param latitude the longitude to set
	 */
	public void setLongitude(double longtitude) {
		this.longitude = longtitude;
	}
}
