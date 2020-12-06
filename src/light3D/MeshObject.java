package light3D;

/**
 * 
 * @author Timo Lehnertz
 *
 */

public class MeshObject extends MyObject {

	Mesh mesh;
	
	public MeshObject(double x, double y, double z) {
		this(x, y, z, new Mesh());
	}
	
	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param mesh
	 */
	public MeshObject(double x, double y, double z, Mesh mesh) {
		super(x, y, z);
		setMesh(mesh);
	}

	protected Mesh getMesh() {
		return mesh;
	}

	protected void setMesh(Mesh mesh) {
		if(this.mesh != null) {
			this.mesh.setObject(null);
		}
		this.mesh = mesh;
		mesh.setObject(this);
	}

	@Override
	public void render(BasicRenderer r) {
		mesh.render(r);
	}
}