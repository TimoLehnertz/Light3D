package light3D;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Timo Lehnertz
 *
 */

public class Vertex extends Vector3D implements Renderable, Positionable{
	
	private List<Vertex> connected;
	private Mesh mesh;
	
	public Vertex(Vector3D pos, Mesh mesh) {
		super(pos);
		this.mesh = mesh;
		connected = new ArrayList<>();
	}
	
	public void remove() {
		for (Vertex vertex : connected) {
			vertex.disconnectFrom(this, true);
		}
	}
	
	public void connectTo(Vertex v) {
		connectTo(v, true);
	}
	
	private void connectTo(Vertex v, boolean first) {
		if(connected.add(v) && first) {
			v.connectTo(this, false);
			Edge edge = new Edge(this, v, mesh);
			mesh.addEdge(edge);
		}
	}
	
	public void disconnectFrom(Vertex v) {
		disconnectFrom(v, true);
	}
	
	private void disconnectFrom(Vertex v, boolean first) {
		if(connected.remove(v) && first) {
			v.disconnectFrom(this, false);
			mesh.removeEdge(this, v);
			mesh.removeface(this, v);
		}
	}
	
	public Vertex extrudeBy(Vector3D direction) {
		Vertex v = new Vertex(VMath.add(this, direction), mesh);
		connectTo(v);
		return v;
	}
	
	@Override
	public void render(BasicRenderer r) {
		r.circle(getWorldPos(), Cons.COLOR_VERTEX, 5, true , true);
	}
	
	@Override
	public Transform getLocalTransform() {
		return new Transform(this);
	}
	
	@Override
	public Transform getWorldTransform() {
		return mesh.getWorldTransform().appendSpace(getLocalTransform());
	}
}