package light3D;

public class DummyObjects {

	public static MeshObject getCube(double x, double y, double z) {
		Mesh mesh = new Mesh();
		
		Vertex bottomLeftFront = new Vertex(new Vector3D(-1,-1,-1), mesh);
		Vertex bottomLeftBack = new Vertex(new Vector3D(-1,1,-1), mesh);
		Vertex bottomRightBack = new Vertex(new Vector3D(1,1,-1), mesh);
		Vertex bottomRightFront = new Vertex(new Vector3D(1,-1,-1), mesh);
		Vertex topLeftFront = new Vertex(new Vector3D(-1,-1,1), mesh);
		Vertex topLeftBack = new Vertex(new Vector3D(-1,1,1), mesh);
		Vertex topRightBack = new Vertex(new Vector3D(1,1,1), mesh);
		Vertex topRightFront = new Vertex(new Vector3D(1,-1,1), mesh);
		
		mesh.addVertex(bottomLeftFront);
		mesh.addVertex(bottomLeftBack);
		mesh.addVertex(bottomRightBack);
		mesh.addVertex(bottomRightFront);
		mesh.addVertex(topLeftFront);
		mesh.addVertex(topLeftBack);
		mesh.addVertex(topRightBack);
		mesh.addVertex(topRightFront);
		
		/**
		 * Left
		 */
		mesh.fill(bottomLeftFront, bottomLeftBack, topLeftBack);
		mesh.fill(topLeftFront, bottomLeftFront, topLeftBack);
		
		/**
		 * Front
		 */
		mesh.fill(bottomLeftFront, bottomRightFront, topRightFront);
		mesh.fill(bottomLeftFront, topLeftFront, topRightFront);
		
		/**
		 * right
		 */
		mesh.fill(bottomRightFront, bottomRightBack, topRightBack);
		mesh.fill(bottomRightFront, topRightFront, topRightBack);
		
		/**
		 * backside
		 */
		mesh.fill(bottomLeftBack, bottomRightBack, topRightBack);
		mesh.fill(bottomLeftBack, topLeftBack, topRightBack);
		
		/**
		 * Bottom
		 */
		mesh.fill(bottomLeftFront, bottomRightFront, bottomRightBack);
		mesh.fill(bottomLeftFront, bottomLeftBack, bottomRightBack);
		
		/**
		 * Top
		 */
		mesh.fill(topLeftFront, topRightFront, topRightBack);
		mesh.fill(topLeftFront, topLeftBack, topRightBack);
		
		MeshObject cube = new MeshObject(x, y, z, mesh);
		return cube;
	}
	
	public static MeshObject getPlane(double x, double y, double z) {
		Mesh mesh = new Mesh();
		
		Vertex leftFront = new Vertex(new Vector3D(-1,-1,0), mesh);
		Vertex leftBack = new Vertex(new Vector3D(-1,1,0), mesh);
		Vertex rightBack = new Vertex(new Vector3D(1,1,0), mesh);
		Vertex rightFront = new Vertex(new Vector3D(1,-1,0), mesh);
		
		mesh.addVertex(leftFront);
		mesh.addVertex(leftBack);
		mesh.addVertex(rightBack);
		mesh.addVertex(rightFront);
		/**
		 * Bottom
		 */
		mesh.fill(leftFront, rightFront, rightBack);
		mesh.fill(leftFront, leftBack, rightBack);
		
		MeshObject plane = new MeshObject(x, y, z, mesh);
		return plane;
	}
}