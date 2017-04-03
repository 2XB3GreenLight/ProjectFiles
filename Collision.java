package cas2xb3.greenlight;

public class Collision implements Comparable {

	private int index;
	private double latitude;
	private double longitude;
	private int severity;
	private int weather;
	private int paved;

	public Collision(int index, double lat, double lng, int CSEV, int weather, int paved) {
		this.index = index;
		latitude = lat;
		longitude = lng;
		severity = CSEV;
		this.weather = weather;
		this.paved = paved;
	}

	public int getIndex() {
		return index;
	}

	public double getLat() {
		return latitude;
	}

	public double getLng() {
		return longitude;
	}

	public int getSeverity() {
		return severity;
	}

	public int getWeather() {
		return weather;
	}

	public int getPaved() {
		return paved;
	}

	public double getEquation() {
		return (1.0 / (2 * getSeverity()) + 1 / (5 * getWeather())) * (0.9 + (getPaved() / 10));
	}

	public double distance(Collision that) {
		return Math.sqrt(Math.pow(this.getLat() - that.getLat(), 2) + Math.pow(this.getLng() - that.getLng(), 2));
	}

	@Override
	public int compareTo(Object o) {
		Collision c = (Collision) o;
		if (this.latitude == c.latitude)
			return 0;
		else
			return this.latitude > c.latitude ? 1 : -1;
	}
}
