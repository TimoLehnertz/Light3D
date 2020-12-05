package light3D;

public class VMath {

	public static Vector3D add(Vector3D a, Vector3D b) {
		return new Vector3D(a.x + b.x, a.y + b.y, a.z + b.z);
	}
	
	public static Vector3D add(Vector3D a, double b) {
		return new Vector3D(a.x + b, a.y + b, a.z + b);
	}
	
	public static Vector3D subtract(Vector3D a, Vector3D b) {
		return new Vector3D(a.x - b.x, a.y - b.y, a.z - b.z);
	}
	
	public static Vector3D subtract(Vector3D a, double b) {
		return new Vector3D(a.x - b, a.y - b, a.z - b);
	}
	
	public static Vector3D multiply(Vector3D a, Vector3D b) {
		return new Vector3D(a.x * b.x, a.y * b.y, a.z * b.z);
	}
	
	public static Vector3D multiply(Vector3D a, double b) {
		return new Vector3D(a.x * b, a.y * b, a.z * b);
	}
	
	public static Vector3D divide(Vector3D a, Vector3D b) {
		return new Vector3D(a.x / b.x, a.y / b.y, a.z / b.z);
	}
	
	public static Vector3D divide(Vector3D a, double b) {
		return new Vector3D(a.x / b, a.y / b, a.z / b);
	}
	
	public static double getLength(Vector3D a) {
		return Math.sqrt(a.x*a.x + a.y*a.y + a.z*a.z);
	}
	
	public static double getDistance(Vector3D a, Vector3D b) {
		return getLength(subtract(b, a));
	}
	
	public static Vector3D getUnitLength(Vector3D a) {
		return divide(a, getLength(a));
	}
	
	/**
	 * in Radians
	 * @param a
	 * @param rot
	 * @return
	 */
	public static Vector3D rotateZ(Vector3D a, double rot) {
		double x = (a.x * Math.cos(rot)) - (a.y * Math.sin(rot));
		double y = (a.x * Math.sin(rot)) + (a.y * Math.cos(rot));
		return new Vector3D(x, y, a.z);
	}
	
	/**
	 * in Radians
	 * @param a
	 * @param rot
	 * @return
	 */
	public static Vector3D rotateX(Vector3D a, double rot) {
		double y = (a.y * Math.cos(rot)) - (a.z * Math.sin(rot));
		double z = (a.y * Math.sin(rot)) + (a.z * Math.cos(rot));
		return new Vector3D(a.x, y, z);
	}
}
