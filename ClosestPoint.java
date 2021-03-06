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
	public static int closest(Collision[] pts, double lat, double lng){
		double delta = 0.081699697; //5% range
		int floorIndex; //lower index of sub array
		int ceilingIndex; //higher index of sub array
		
		
		//search latitudes.
		Collision dummy; //dummy object to compare latitudes with
		//loop until at least one point is in the given range
		do{
			
			dummy  = new Collision(0, lat-delta, 0, 0, 0, 0);
			
			floorIndex = BinarySearch.indexOf(pts, dummy, 0, pts.length);
			
			dummy = new Collision(0, lat+delta, 0, 0, 0, 0);
			ceilingIndex = BinarySearch.indexOf(pts, dummy, 0, pts.length);
			delta += 0.081699697; //increase the range by 5%
		}while(floorIndex == ceilingIndex);

		
		int closest=0; //index of the closest point
		double dist=9999999999.0;
		//search from floor to ceiling
		for(int i = floorIndex ; i < ceilingIndex ;i++){

		
			//radius of earth in meters
			double r = 6371000;
			
			
			
			double dLat = Math.toRadians(pts[i].getLat()-lat);
			double dLng = Math.toRadians(pts[i].getLng()-lng);
			
			double t = Math.sin(dLat/2) * Math.sin(dLat/2) +
					   Math.cos(Math.toRadians((lat))) * Math.cos(Math.toRadians(pts[i].getLat())) * 
					   Math.sin(dLng/2) * Math.sin(dLng/2); 
			
			double tdist = r * (2 * Math.atan2(Math.sqrt(t), Math.sqrt(1-t)));
			if (tdist < dist){
				dist = tdist;
				closest = i;
			}
			
			
		}
		return closest;
	}


	
	/**
	 * TESTING
	 */
	public static void main(String[] args){
		Collision c1 = new Collision(1, 50, 62.9, 0, 0, 0);
		Collision c2 = new Collision(2, 44.3, 77.6, 0, 0, 0);
		Collision c3 = new Collision(3, 25.6, 88.8, 0, 0, 0);
		
		Collision[] x = {c1, c2, c3};
		
		
		int r = closest(x, 30.4, 83);
		
		System.out.println(r);
		
	}

}
