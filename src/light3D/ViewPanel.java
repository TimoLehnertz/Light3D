package light3D;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 * @author Timo Lehnertz
 *
 */

public class ViewPanel extends JPanel implements ComponentListener{

	private static final long serialVersionUID = 1L;

	private Camera activeCamera;
	private World world;
	private BasicRenderer render;
	
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
		this.world = world;
		render = new BasicRenderer(camera);
		t.start();
		setActiveCamera(camera);
		addComponentListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2;
		/**
		 * background
		 */
		g.setColor(Cons.COLOR_VIEW_PANEL_BG);
		g.fillRect(0, 0, getWidth(), getHeight());
		activeCamera.handleMovement();
		/**
		 * Rendering
		 */
		render.render(g, world);
	}

	protected Camera getActiveCamera() {
		return activeCamera;
	}

	protected void setActiveCamera(Camera camera) {
		if(!world.getObjects().contains(camera)) {
			world.addObject(camera);
		}
		if(this.activeCamera != null) {
			removeKeyListener(this.activeCamera);
			removeMouseListener(this.activeCamera);
			removeMouseListener(this.activeCamera);
		}
		addKeyListener(camera);
		addMouseListener(camera);
		addMouseMotionListener(camera);
		this.activeCamera = camera;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		activeCamera.setRenderWidth(getWidth());
		activeCamera.setRenderHeight(getHeight());
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
		activeCamera.setRenderWidth(getWidth());
		activeCamera.setRenderHeight(getHeight());
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}
}