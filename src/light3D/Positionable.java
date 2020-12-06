package light3D;

/**
 * 
 * @author Timo Lehnertz
 *
 */

public interface Positionable {

	public default Vector3D getLocalPos() {
		return getLocalTransform().pos;
	}
	
	public default Vector3D getWorldPos() {
		return getWorldTransform().pos;
	}
	
	public Transform getLocalTransform();
	
	public Transform getWorldTransform();
}
