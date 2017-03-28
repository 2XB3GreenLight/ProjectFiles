package src;
/**
 * Class to implement binary search.
 * 
 * @author Yousaf Shaheen
 * @since   2017-03-09
 */
public class BinarySearch {
	/**
	 * This method implements Binary search.
	 * Takes in high and low index values to search sub arrays in the given array.
	 * Uses comparable interface.
	 * Throws IndexOutOfBoundsException if indexes are invalid.
	 * 
	 * @param x Given array to search
	 * @param item Item to be searched for
	 * @param lo lower index value - INCLUSIVE
	 * @param hi higher index value - EXCLUSIVE
	 * @return True if the value given is in the given array.
	 */
	public static boolean search(Comparable[] x, Comparable item, int lo, int hi) {
		
		if(lo < 0){
			throw new IndexOutOfBoundsException("Lower bound invalid");
		}else if(hi > x.length){
			throw new IndexOutOfBoundsException("Upper bound invalid");
		}
		
		while (hi > lo) {
			int mid = (hi + lo) / 2;
			if (item.compareTo(x[mid]) == -1) {
				hi = mid;
			} else if (item.compareTo(x[mid]) == 1) {
				lo = mid + 1;
			} else if (item == x[mid]) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method takes an array input, an item, and a low index and high index.
	 * It returns the index at which an element exists in the array. If the 
	 * element does not exist in the given array, the method returns the index at
	 * which the element should be inserted.
	 * 
	 * @param x Given array.
	 * @param item Item to be searched for.
	 * @param lo Lower index. - INCLUSIVE
	 * @param hi Higher index. - EXCLUSIVE
	 * @return The index at which the element is at or should be at.
	 */
	public static int indexOf(Comparable[] x, Comparable item, int lo, int hi){
		if(lo < 0){
			throw new IndexOutOfBoundsException("Lower bound invalid");
		}else if(hi > x.length){
			throw new IndexOutOfBoundsException("Upper bound invalid");
		}
		
		while (hi > lo) {
			int mid = (hi + lo) / 2;
			if (item.compareTo(x[mid]) == -1) {
				hi = mid;
			} else if (item.compareTo(x[mid]) == 1) {
				lo = mid + 1;
			} else if (item == x[mid]) {
				return mid;
			}
		}
		return lo;
	}
	
}
