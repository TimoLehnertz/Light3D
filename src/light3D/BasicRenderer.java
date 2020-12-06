package light3D;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BasicRenderer {
	
	private Camera camera;
	
	private List<RenderObject> renderObjects;
	
	public BasicRenderer(Camera camera) {
		super();
		this.camera = camera;
		renderObjects = new ArrayList<>();
	}
	
	/**
	 * Sort renderObjects by their distance to the camera
	 */
	private void sortRenderObjects(){
		renderObjects.sort(new Comparator<Positionable>() {
			@Override
			public int compare(Positionable a, Positionable b) {
				double lengthA = a.getWorldPos().distanceFrom(camera);
				double lengthB = b.getWorldPos().distanceFrom(camera);
				return lengthA > lengthB ? -1 : (lengthA < lengthB) ? 1 : 0;
			}
		});
	}
	
	public void render(Graphics2D g, World world) {
		/**
		 * initializing
		 */
		for (MyObject myObject : world.getObjects()) {
			myObject.initRender(this, true);
		}
		/**
		 * sorting by distanc from camera
		 */
		sortRenderObjects();
		/**
		 * Rendering
		 */
		for (RenderObject renderObject : renderObjects) {
			renderObject.render(g);
		}
		/**
		 * Clearing and awaiting a new call to render
		 */
		renderObjects.clear();
	}
	
	public void circle(Vector3D center, Color color, double size) {
			circle(center, color, size, false, true);
	}
	
	public void circle(Vector3D center, Color color, double size, boolean unitSize, boolean fill) {
		renderObjects.add(new Circle(center, size, unitSize, fill, color));
	}
	
	public void line(Vector3D from, Vector3D to, Color color) {
		renderObjects.add(new Line(from, to, color));
	}
	
	public void triangle(Vector3D a, Vector3D b, Vector3D c, Color color) {
		renderObjects.add(new Triangle(a, b, c, color));
	}
	
	private class Circle implements RenderObject{
		/**
		 * Everything in world pos
		 */
		Vector3D center;
		double size;
		boolean unitSize;
		boolean fill;
		Color color;
		
		/**
		 * @param center
		 * @param size
		 * @param unitSize
		 * @param fill
		 * @param color
		 */
		protected Circle(Vector3D center, double size, boolean unitSize, boolean fill, Color color) {
			super();
			this.center = center;
			this.size = size;
			this.unitSize = unitSize;
			this.fill = fill;
			this.color = color;
		}
		
		public void render(Graphics2D g) {
			Vector3D inCamSpace = camera.toSpace(center);
			Point2D.Double screenPos = camera.getScreenPos(inCamSpace);
			if(screenPos == null) {
				return;
			}
			int size = (int) (unitSize ? this.size : this.size / Math.max(inCamSpace.y, 0.001));
			g.setColor(color);
			if(fill) {
				g.fillOval((int) (screenPos.x - size / 2), (int) (screenPos.y - size / 2), size, size);
			} else {
				g.drawOval((int) (screenPos.x - size / 2), (int) (screenPos.y - size / 2), size, size);
			}
		}

		@Override
		public Transform getLocalTransform() {
			return new Transform();
		}

		@Override
		public Transform getWorldTransform() {
			return new Transform(center);
		}
	}
	
	private class Line implements RenderObject{
		
		/**
		 * Everything in world pos
		 */
		Vector3D from;
		Vector3D to;
		Color color;
		
		/**
		 * @param from
		 * @param to
		 * @param color
		 */
		protected Line(Vector3D from, Vector3D to, Color color) {
			super();
			this.from = from;
			this.to = to;
			this.color = color;
		}
		
		public void render(Graphics2D g) {
			Vector3D from = camera.toSpace(this.from);
			Vector3D to = camera.toSpace(this.to);
			Point2D.Double fromPos = camera.getScreenPos(from);
			Point2D.Double toPos = camera.getScreenPos(to);
			if(fromPos == null || toPos == null) {
				return;
			}
			g.setColor(color);
			g.drawLine((int) fromPos.x, (int) fromPos.y, (int) toPos.x, (int) toPos.y);
		}
		
		@Override
		public Transform getLocalTransform() {
			return new Transform();
		}

		@Override
		public Transform getWorldTransform() {
			return new Transform(VMath.getCenter(from, to));
		}
	}
	
	private class Triangle implements RenderObject{

		Vector3D a;
		Vector3D b;
		Vector3D c;
		Color color;
		
		/**
		 * @param a
		 * @param b
		 * @param c
		 * @param color
		 */
		protected Triangle(Vector3D a, Vector3D b, Vector3D c, Color color) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
			this.color = color;
		}

		@Override
		public Transform getLocalTransform() {
			return new Transform();
		}

		@Override
		public Transform getWorldTransform() {
			return new Transform(VMath.getCenter(a, b, c));
		}

		@Override
		public void render(Graphics2D g) {
			Point2D.Double pa = camera.getScreenPos(camera.toSpace(a));
			Point2D.Double pb = camera.getScreenPos(camera.toSpace(b));
			Point2D.Double pc = camera.getScreenPos(camera.toSpace(c));
			if(pa == null || pb == null|| pc == null) {
				return;
			}
			g.setColor(color);
			g.fillPolygon(new int[] {(int) pa.x, (int) pb.x, (int) pc.x}, new int[] {(int) pa.y, (int) pb.y, (int) pc.y}, 3);
		}
		
	}
}
