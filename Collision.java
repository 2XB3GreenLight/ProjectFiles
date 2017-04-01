//package cas2xb3.greenlight;
package src;

/**
 * This class implements a collision object which
 * represents each collision as a point with 
 * multiple characteristics.
 * @author 
 *
 */
public class Collision implements Comparable {

	private int index;
	private double latitude;
	private double longitude;
	private int severity;
	private int weather;
	private int paved;

	/**
	 * Constructor class to initialize variables.
	 * @param index Key of the collision (id).
	 * @param lat Latitude of the point (collision).
	 * @param lng Longitude of the point (collision).
	 * @param CSEV Crash severity (1-5) 1 = worst.
	 * @param weather Weather conditions.
	 * @param paved Paved road? (1 = paved)(2 = not paved)
	 */
	public Collision(int index, double lat, double lng, int CSEV, int weather, int paved) {
		this.index = index;
		latitude = lat;
		longitude = lng;
		severity = CSEV;
		this.weather = weather;
		this.paved = paved;
	}

	/**
	 * Getter function of the index.
	 * @return Returns the index.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Getter function of the latitude.
	 * @return Returns the latitude.
	 */
	public double getLat() {
		return latitude;
	}

	/**
	 * Getter function of the longitude.
	 * @return Returns the longitude.
	 */
	public double getLng() {
		return longitude;
	}

	/**
	 * Getter function of the severity.
	 * @return Returns the severity.
	 */
	public int getSeverity() {
		return severity;
	}

	/**
	 * Getter function of the weather.
	 * @return Returns the weather.
	 */
	public int getWeather() {
		return weather;
	}

	/**
	 * Getter function of the paved state.
	 * @return Returns the paved state.
	 */
	public int getPaved() {
		return paved;
	}

	/**
	 * Calculates the value of the safety equation.
	 * @return Returns the value of the safety equation.
	 */
	public double getEquation() {
		return (1.0 / (2 * getSeverity()) + 1 / (5 * getWeather())) * (0.9 + (getPaved() / 10));
	}

	public double distance(Collision that) {
		double r = 6371000;

		double dLat = Math.toRadians(this.getLat() - that.getLat());
		double dLng = Math.toRadians(this.getLng() - that.getLng());

		double t = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians((that.getLat())))
				* Math.cos(Math.toRadians(this.getLat())) * Math.sin(dLng / 2) * Math.sin(dLng / 2);

		double dist = r * (2 * Math.atan2(Math.sqrt(t), Math.sqrt(1 - t)));
		
		return Math.abs(dist);
	}

	/**
	 * CompareTo function as implemented by Comparable interface.
	 */
	@Override
	public int compareTo(Object o) {
		Collision c = (Collision) o;
		if (this.latitude == c.latitude)
			return 0;
		else
			return this.latitude > c.latitude ? 1 : -1;
	}
}
