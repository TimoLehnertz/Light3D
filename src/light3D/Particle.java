package light3D;

import java.awt.Color;

public class Particle extends Vector3D implements Positionable{

	Color color;
	double size;
	boolean unitSize;
	
	MyObject opject;
	
	@Override
	public Transform getLocalTransform() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Transform getWorldTransform() {
		// TODO Auto-generated method stub
		return null;
	}
}
