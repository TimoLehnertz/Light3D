package light3D;

import javax.swing.JFrame;

/**
 * 
 * @author Timo Lehnertz
 *
 */

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;

	public Frame(int width, int height) {
		super("test frame");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, width, height);
	}
	
	public void viewViewer(ViewPanel p) {
		getContentPane().removeAll();
		getContentPane().add(p);
		p.setFocusable(true);
		p.requestFocus();
	}
}
