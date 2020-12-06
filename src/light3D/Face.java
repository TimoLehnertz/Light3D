package light3D;

public class Face implements Renderable, Positionable{

	private Mesh mesh;
	private Vertex a;
	private Vertex b;
	private Vertex c;
	
	/**
	 * @param mesh
	 * @param edges
	 */
	protected Face(Mesh mesh, Vertex a, Vertex b, Vertex c) {
		super();
		this.mesh = mesh;
		this.a = a;
		this.b = b;
		this.c = c;
		a.connectTo(b);
		b.connectTo(c);
		c.connectTo(a);
	}
	
	public boolean containsBoth(Vertex a, Vertex b) {
		return contains(a) && contains(b);
	}
	
	public boolean contains(Vertex v) {
		return a == v || b == v || c == v;
	}

	public void remove() {
		mesh.removeVertex(a);
		mesh.removeVertex(b);
		mesh.removeVertex(c);
	}

	@Override
	public void render(BasicRenderer r) {
		r.triangle(
				mesh.getWorldTransform().appendSpace(a).pos,
				mesh.getWorldTransform().appendSpace(b).pos,
				mesh.getWorldTransform().appendSpace(c).pos,
				Cons.COLOR_FACE);
	}

	@Override
	public Transform getLocalTransform() {
		return new Transform();
	}

	@Override
	public Transform getWorldTransform() {
		return mesh.getWorldTransform().appendSpace(getLocalTransform());
	}
}