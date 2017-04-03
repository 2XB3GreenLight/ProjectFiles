package cas2xb3.greenlight;

/**
 * Class to implement a function to calculate the point that is closest to a
 * given point that is in the given set of points. NOTE: This implementation is
 * based off equations and information from:
 * http://www.movable-type.co.uk/scripts/latlong.html
 *
 */
public class ClosestPoint {

	/**
	 * Calculate the closest point in the set to the given point.
	 * 
	 * @param pts
	 *            Set of points.
	 * @param lat
	 *            Latitude of given point.
	 * @param lng
	 *            Longitude of given point.
	 * @return Returns the array representing the closest point.
	 */
	public static int closest(Collision[] pts, double lat, double lng) {
		int closest = 0;
		double dist = 99999999999999.0;

		for (int i = 0; i <= pts.length - 1; i++) {

			double r = 6371000;

			double dLat = Math.toRadians(pts[i].getLat() - lat);
			double dLng = Math.toRadians(pts[i].getLng() - lng);

			double t = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians((lat)))
					* Math.cos(Math.toRadians(pts[i].getLat())) * Math.sin(dLng / 2) * Math.sin(dLng / 2);

			double tdist = r * (2 * Math.atan2(Math.sqrt(t), Math.sqrt(1 - t)));
			if (tdist < dist) {
				dist = tdist;
				closest = i;
			}
		}

		return closest;
		//return pts[closest];
	}

}