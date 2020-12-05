package light3D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ViewPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private Camera camera;
	private World world;
	private Color background = Color.DARK_GRAY;
	
	/**
	 *  Repaint with 60 FPS
	 */
	Timer t = new Timer(1000/60, (ActionEvent e) -> {repaint();});
			
	/**
	 * @param camera
	 * @param world
	 */
	public ViewPanel(Camera camera, World world) {
		super();
		setCamera(camera);
		this.world = world;
		this.t.start();
	}
	
	private int getMaxWidth() {
		return Math.max(getWidth(), getHeight());
	}
	
	private int getMinWidth() {
		return Math.min(getWidth(), getHeight());
	}
	
	@Override
	protected void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2;
		/**
		 * background
		 */
		g.setColor(background);
		g.fillRect(0, 0, getWidth(), getHeight());
		camera.handleMovement(getWidth(), getHeight());
		
		/**
		 * Rendering
		 */
		AffineTransform tbackup = g.getTransform();
		for (MyObject object : world.getObjects()) {
			Point2D.Double screenPos = camera.getScreenPos(object.getPos(), getMinWidth());
			if(screenPos != null) {
				g.setColor(Color.gray);
				g.fillOval((int) screenPos.x, (int) screenPos.y, 5, 5);
//				g.getTransform().translate(screenPos.x, screenPos.y);
//				object.paint(g);
//				g.setTransform(tbackup);
			}
		}
	}

	protected Camera getCamera() {
		return camera;
	}

	protected void setCamera(Camera camera) {
		if(this.camera != null) {
			removeKeyListener(this.camera);
			removeMouseListener(this.camera);
			removeMouseListener(this.camera);
		}
		this.camera = camera;
		addKeyListener(camera);
		addMouseListener(camera);
		addMouseMotionListener(camera);
	}
}