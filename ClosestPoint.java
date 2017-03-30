package src;

/** 
 * Class to implement a function to calculate the point that is closest to
 * a given point that is in the given set of points.
 * NOTE: This implementation is based off equations and information from:
 * http://www.movable-type.co.uk/scripts/latlong.html
 *
 */
public class ClosestPoint {
	
	/**
	 * Calculate the closest point in the set to the given point.
	 * @param pts Set of points.
	 * @param lat Latitude of given point.
	 * @param lng Longitude of given point.
	 * @return Returns the array representing the closest point.
	 */
	public static Double[] closest(Double[][] pts, double lat, double lng){
		int closest=0;
		double dist=999999999999999999.0;
		for(int i = 0 ; i <= pts.length-1;i++){
		
			double r = 6371000;
			
			double dLat = Math.toRadians((pts[i][1])-lat);
			double dLng = Math.toRadians((pts[i][2])-lng);
			
			double t = 
				    Math.sin(dLat/2) * Math.sin(dLat/2) +
				    Math.cos(Math.toRadians((lat))) * Math.cos(Math.toRadians((pts[i][1]))) * 
				    Math.sin(dLng/2) * Math.sin(dLng/2); 
			
			double tdist = r * (2 * Math.atan2(Math.sqrt(t), Math.sqrt(1-t)));
			if (tdist < dist){
				dist = tdist;
				closest = i;
			}
			System.out.println(tdist);
			
		}
		
		return pts[closest];
	}
	
	

}
