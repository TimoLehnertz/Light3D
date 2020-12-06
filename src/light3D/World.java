package light3D;

import java.util.List;

/**
 * 
 * @author Timo Lehnertz
 *
 */ 

public class World {

	private List<MyObject> objects;
	
	public World(List<MyObject> objects) {
		super();
		this.objects = objects;
	}

	public boolean addObject(MyObject o) {
		return objects.add(o);
	}
	
	protected List<MyObject> getObjects() {
		return objects;
	}
}