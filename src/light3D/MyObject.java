package light3D;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Timo Lehnertz
 *
 */

public abstract class MyObject extends Transform implements Renderable, Positionable{

	private List<MyObject> children;
	/**
	 * Can be null
	 */
	private MyObject parent;
	
	public MyObject (double x, double y, double z) {
		this(new Vector3D(x,y,z));
	}
	
	public MyObject() {
		this(new Vector3D());
	}
	
	public MyObject(Vector3D pos) {
		this(pos, Vector3D.SCALE_DEFAULT, 0, 0);
	}
	
	/**
	 * Constructor
	 * 
	 * @param pos
	 * @param scale
	 * @param zRot
	 * @param xRot
	 */
	protected MyObject(Vector3D pos, Vector3D scale, double zRot, double xRot) {
		super(pos, scale, zRot, xRot);
		this.scale = new Vector3D(1, 1, 1);
		children = new ArrayList<>();
	}
	
	/**
	 * Meant to be overriden
	 * 
	 * @param g Graphics to paint on
	 * @param cam Camera to reference
	 */
	public abstract void render(BasicRenderer r);
	
	public final void initRender(BasicRenderer r, boolean firstLayer) {
		if((firstLayer && !isChild()) || (!firstLayer && isChild())) {// only render when neccessary
			renderChildren(r);
			render(r);
		}
	}
	
	/**
	 * Renders all children recursivly
	 * @param g
	 * @param cam
	 */
	private final void renderChildren(BasicRenderer r) {
		for (MyObject myObject : children) {
			myObject.initRender(r, false);
		}
	}
	
	public boolean addChild(MyObject o) {
		o.parent = this;
		return children.add(o);
	}

	public boolean removeChild(MyObject o) {
		o.parent = null;
		return children.remove(o);
	}
	
	public void removeAllChildren() {
		for (MyObject myObject : children) {
			removeChild(myObject);
		}
	}
	
	public void addAllChildren(List<MyObject> children) {
		for (MyObject myObject : children) {
			addChild(myObject);
		}
	}
	
	/**
	 * getters and setters
	 */
	
	
	public void setLocalPos(Vector3D pos) {
		this.pos = pos;
	}
	
	public Vector3D getLocalPos() {
		return pos;
	}

	public Vector3D getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = new Vector3D(scale);
	}
	
	public void setScale(Vector3D scale) {
		this.scale = scale;
	}
	
	public int getChildrenCount() {
		return children.size();
	}
	
	public MyObject getChildren(int index) {
		return children.get(index);
	}
	
	public boolean isChild() {
		return parent != null;
	}
	
	public Transform getLocalTransform() {
		return this; 
	}
	
	public Transform getWorldTransform() {
		if(!isChild()) {
			return getTransform();
		} else {
			return parent.getWorldTransform().appendSpace(getTransform());
		}
	}
}