package main;

import java.util.ArrayList;
import java.util.List;

import light3D.Camera;
import light3D.Frame;
import light3D.MyObject;
import light3D.Vector3D;
import light3D.ViewPanel;
import light3D.World;

public class Main {

	public static void main(String[] args) {
		Vector3D a = new Vector3D(10, 0, 10);
		System.out.println(a);
		System.out.println(a.rotateX(Math.PI / 2));
		
		List<MyObject> objects  = new ArrayList<>();
		
		objects.add(new MyObject(0, 10, 5));
		objects.add(new MyObject(0, 10, -5));
		
		objects.add(new MyObject(-5, 10, 0));
		objects.add(new MyObject(5, 10, 0));
		
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				for (int z = 0; z < 10; z++) {
					objects.add(new MyObject(x,y + 20,z));
				}
			}
		}
		
		World world = new World(objects);
		Camera c = new Camera();
		
		ViewPanel vp = new ViewPanel(c, world);
		
		Frame f = new Frame(500,500);
		
		f.viewViewer(vp);
	}
}