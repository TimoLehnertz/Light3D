package light3D;

public class Edge implements Renderable, Positionable{

	private Vertex a;
	private Vertex b;
	private Mesh mesh;
	
	/**
	 * @param a
	 * @param b
	 */
	protected Edge(Vertex a, Vertex b, Mesh mesh) {
		super();
		this.a = a;
		this.b = b;
		this.mesh = mesh;
	}

	public void remove() {
		a.disconnectFrom(b);
	}
	
	protected Vertex getA() {
		return a;
	}

	protected void setA(Vertex a) {
		this.a = a;
	}

	protected Vertex getB() {
		return b;
	}

	protected void setB(Vertex b) {
		this.b = b;
	}

	@Override
	public void render(BasicRenderer r) {
		r.line(a.getWorldPos(), b.getWorldPos(), Cons.COLOR_EDGE);
	}

	@Override
	public Transform getLocalTransform() {
		return new Transform();
	}

	@Override
	public Transform getWorldTransform() {
		return mesh.getWorldTransform().appendSpace(VMath.getCenter(a, b));
	}
}