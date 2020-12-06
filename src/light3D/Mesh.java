package light3D;

import java.util.ArrayList;
import java.util.List;

public class Mesh implements Renderable, Positionable{
	
	private MyObject object;
	
	private List<Vertex> vertecies;
	private List<Edge> edges;
	private List<Face> faces;
	
	private boolean renderVertecies;
	private boolean renderEdges;
	private boolean renderFaces;
	
	public Mesh() {
		this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
	}
	
	/**
	 * @param object
	 * @param vetecies
	 * @param edges
	 * @param faces
	 */
	public Mesh(List<Vertex> vertecies, List<Edge> edges, List<Face> faces) {
		super();
		this.object = null;
		this.vertecies = vertecies;
		this.edges = edges;
		this.faces = faces;
		
		renderEdges = true;
		renderFaces = true;
		renderVertecies = true;
	}

	public void fill(Vertex a, Vertex b, Vertex c) {
		addVertex(a);
		addVertex(b);
		addVertex(c);
		Face f = new Face(this, a, b, c);
		addFace(f);
	}
	
	public boolean addVertex(Vertex vertex) {
		return vertecies.add(vertex);
	}
	
	public boolean addEdge(Edge edge) {
		return edges.add(edge);
	}
	
	public boolean addFace(Face face) {
		return faces.add(face);
	}

	public boolean removeVertex(Vertex vertex) {
		vertex.remove();
		return vertecies.remove(vertex);
	}
	
	public boolean removeEdge(Edge edge) {
		edge.remove();
		return edges.remove(edge);
	}
	
	public void removeEdge(Vertex a, Vertex b) {
		for (Edge edge : edges) {
			if(edge.getA() == a && edge.getB() == b) {
				removeEdge(edge);
			}
		}
	}
	
	public void removeface(Vertex a, Vertex b) {
		for (Face face : faces) {
			if(face.containsBoth(a, b)) {
				removeFace(face);
			}
		}
	}
	
	public boolean removeFace(Face face) {
		face.remove();
		return faces.remove(face);
	}
	
	@Override
	public void render(BasicRenderer r) {
		if (renderVertecies) {
			for (Vertex vertex : vertecies) {
				vertex.render(r);
			}
		}
		if (renderEdges) {
			for (Edge edge : edges) {
				edge.render(r);
			}
		}
		if (renderFaces) {
			for (Face face : faces) {
				face.render(r);
			}
		}
	}

	/**
	 * @return the object
	 */
	public MyObject getObject() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(MyObject object) {
		this.object = object;
	}

	@Override
	public Transform getLocalTransform() {
		return object.getLocalTransform();
	}

	@Override
	public Transform getWorldTransform() {
		return object.getWorldTransform();
	}
}