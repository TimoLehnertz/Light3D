package light3D;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import javax.swing.SwingUtilities;

public class Camera implements KeyListener, MouseListener, MouseMotionListener{

	Vector3D pos = new Vector3D();
	private double zRot = 0;
	private double xRot = 0;
	
	/**
	 * Mouse stuff
	 */
	private boolean middlemouseDown = false;
	private Point stearEnterPoint;
	private double stearEnterZRot;
	private double stearEnterXRot;
	private Point mouse;
	/**
	 * Field of view in degrees
	 * not implemented yet
	 */
	public double fov = 90;
	
	/**
	 * Key listener helper variables
	 */
	private boolean forewards = false;
	private boolean backwards = false;
	private boolean left = false;
	private boolean right = false;
	
	private boolean up = false;
	private boolean down = false;
	
	private boolean rotLeft = false;
	private boolean rotRight = false;
	
	private static final Vector3D V_FOREWARDS = new Vector3D(0,1,0);
	private static final Vector3D V_BACKWARDS = new Vector3D(0,-1,0);
	private static final Vector3D V_LEFT = 		new Vector3D(-1,0,0);
	private static final Vector3D V_RIGHT = 	new Vector3D(1,0,0);
	private static final Vector3D V_UP = 		new Vector3D(0,0,1);
	private static final Vector3D V_DOWN = 		new Vector3D(0,0,-1);
	
	/**
	 * degrees
	 */
	private static final double MOUSE_SENSITIVITY = 0.5;
	
	private double speed = 0.2;
	
	private Vector3D velocity = new Vector3D();
	private double velocityRotZ = 0;
	private double velocityRotX = 0;
	private int width;
	private int height;
	
	public Camera() {
		super();
	}
	
	/**
	 * 
	 * @param v
	 * @param width
	 * @param height
	 * @return
	 */
	public Point2D.Double getScreenPos(Vector3D v, int minWidth) {
		Vector3D local = toSpace(v);
		if(local.y < 0) {//behind camera
			return null;
		}
		/**
		 * x = -0.5 = screen width * 0
		 * x = 0.5 = screen width
		 */
		double x = local.x / local.y;
		double y = -local.z / local.y;//minus as screen is inverted
		
		x = (x * minWidth / 2) + (minWidth / 2);
		y = (y * minWidth / 2) + (minWidth / 2);
		
		return new Point2D.Double(x, y);
	}
	
	/**
	 * Turn a vector into this camera space
	 * @param b
	 * @return
	 */
	public Vector3D toSpace(Vector3D b) {
		Vector3D a = b.clone();
		a.subtract(pos);
		a.rotateZ(-Math.toRadians(zRot));
		a.rotateX(-Math.toRadians(xRot));
		return a;
	}
	
	public void handleMovement(int width, int height) {
		this.width = width;
		this.height = height;
		/**
		 * incrementing
		 */
		if(forewards) {
			velocity.add(VMath.rotateZ(VMath.multiply(V_FOREWARDS, speed), Math.toRadians(zRot)));
		}
		if(backwards) {
			velocity.add(VMath.rotateZ(VMath.multiply(V_BACKWARDS, speed), Math.toRadians(zRot)));
		}
		if(left) {
			velocity.add(VMath.rotateZ(VMath.multiply(V_LEFT, speed), Math.toRadians(zRot)));
		}
		if(right) {
			velocity.add(VMath.rotateZ(VMath.multiply(V_RIGHT, speed), Math.toRadians(zRot)));
		}
		if(up) {
			velocity.add(VMath.multiply(V_UP, speed));
		}
		if(down) {
			velocity.add(VMath.multiply(V_DOWN, speed));
		}
		
		if(rotLeft) {
			velocityRotZ += MOUSE_SENSITIVITY;
		}
		if(rotRight) {
			velocityRotZ -= MOUSE_SENSITIVITY;
		}
		/**
		 * decrementing
		 */
		velocity.divide(1.3);
		if(velocity.getLength() < 0.1) {
			velocity.setVector(new Vector3D());
		} else {
			/**
			 * Moving
			 */
			pos.add(velocity);
		}
		
		velocityRotZ /= 1.3;
		zRot += velocityRotZ;
		/**
		 * Mouse movement
		 */
		if(middlemouseDown) {
			zRot = stearEnterZRot - (mouse.x - stearEnterPoint.x) * MOUSE_SENSITIVITY;
			xRot = stearEnterXRot - (mouse.y - stearEnterPoint.y) * MOUSE_SENSITIVITY;
		}
	}
	
	public void moveMouse(Point p) {
	    GraphicsEnvironment ge = 
	        GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] gs = ge.getScreenDevices();

	    // Search the devices for the one that draws the specified point.
	    for (GraphicsDevice device: gs) { 
	        GraphicsConfiguration[] configurations =
	            device.getConfigurations();
	        for (GraphicsConfiguration config: configurations) {
	            Rectangle bounds = config.getBounds();
	            if(bounds.contains(p)) {
	                // Set point to screen coordinates.
	                Point b = bounds.getLocation(); 
	                Point s = new Point(p.x - b.x, p.y - b.y);

	                try {
	                    Robot r = new Robot(device);
	                    r.mouseMove(s.x, s.y);
	                } catch (AWTException e) {
	                    e.printStackTrace();
	                }

	                return;
	            }
	        }
	    }
	    // Couldn't move to the point, it may be off screen.
	    return;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {/*garbage*/}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 87) {//w
			forewards = true;
		}
		if(e.getKeyCode() == 65) {//a
			left = true;
		}
		if(e.getKeyCode() == 83) {//s
			backwards = true;
		}
		if(e.getKeyCode() == 68) {//d
			right = true;
		}
		
		if(e.getKeyCode() == 38) {//arrow up
			forewards = true;
		}
		if(e.getKeyCode() == 37) {//arrow left
			left = true;
		}
		if(e.getKeyCode() == 40) {//arrow down
			backwards = true;
		}
		if(e.getKeyCode() == 39) {//arrow right
			right = true;
		}
		
		if(e.getKeyCode() == 16) {//shift
			down = true;
		}
		if(e.getKeyCode() == 32) {//space
			up = true;
		}
		
		if(e.getKeyCode() == 81) {
			rotLeft = true;
		}
		if(e.getKeyCode() == 69) {
			rotRight = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 87) {//w
			forewards = false;
		}
		if(e.getKeyCode() == 65) {//a
			left = false;
		}
		if(e.getKeyCode() == 83) {//s
			backwards = false;
		}
		if(e.getKeyCode() == 68) {//d
			right = false;
		}
		
		if(e.getKeyCode() == 38) {//arrow up
			forewards = false;
		}
		if(e.getKeyCode() == 37) {//arrow left
			left = false;
		}
		if(e.getKeyCode() == 40) {//arrow down
			backwards = false;
		}
		if(e.getKeyCode() == 39) {//arrow right
			right = false;
		}
		
		if(e.getKeyCode() == 16) {//shift
			down = false;
		}
		if(e.getKeyCode() == 32) {//space
			up = false;
		}
		
		if(e.getKeyCode() == 81) {
			rotLeft = false;
		}
		if(e.getKeyCode() == 69) {
			rotRight = false;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isMiddleMouseButton(e)) {
			stearEnterPoint = e.getPoint();
			middlemouseDown = true;
			stearEnterZRot = zRot;
			stearEnterXRot = xRot;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(SwingUtilities.isMiddleMouseButton(e)) {
			middlemouseDown = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(middlemouseDown) {
			if(mouse.x > width * 0.9) {
				stearEnterZRot = zRot;
				stearEnterPoint.x = 0;
				moveMouse(new Point(0, mouse.y));
				
			} else if(mouse.x < width / 10) {
				stearEnterZRot = zRot;
				stearEnterPoint.x = (int) width;
				moveMouse(new Point(width, mouse.y));
				
			} else if(mouse.y > height * 0.9) {
				System.out.println("top");
				stearEnterXRot = xRot;
				stearEnterPoint.y = 0;
				moveMouse(new Point(mouse.x, 0));
				
			} else if(mouse.y < height / 10) {
				System.out.println("bottom");
				stearEnterXRot = xRot;
				stearEnterPoint.y = (int) height;
				moveMouse(new Point(mouse.x, height));
			}
		} else if(SwingUtilities.isMiddleMouseButton(e)) {
			middlemouseDown = false;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.mouse = e.getPoint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.mouse = e.getPoint();
	}
}