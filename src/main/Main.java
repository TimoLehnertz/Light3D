package main;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import light3D.Camera;
import light3D.DummyObjects;
import light3D.Frame;
import light3D.MeshObject;
import light3D.MyObject;
import light3D.ParticleObject;
import light3D.Vector3D;
import light3D.ViewPanel;
import light3D.World;

public class Main {
	
	static double rot = 0;
	
	public static void main(String[] args) {
		Vector3D a = new Vector3D(10, 0, 10);
		System.out.println(a);
		System.out.println(a.rotateX(Math.PI / 2));
		
		List<MyObject> objects  = new ArrayList<>();
		
		objects.add(new ParticleObject(0, 10, 5));
		objects.add(new ParticleObject(0, 10, -5));
		
		objects.add(new ParticleObject(-5, 10, 0));
		objects.add(new ParticleObject(5, 10, 0));
		
		Camera c = new Camera();
		
		objects.add(new ParticleObject(2,4,2));
		
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				for (int z = 0; z < 10; z++) {
					objects.add(new ParticleObject(x,y + 20,z));
				}
			}
		}
		
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				for (int z = 0; z < 3; z++) {
					objects.add(DummyObjects.getCube(x * 3 + 20,y * 3 + 20,z * 3));
				}
			}
		}
		
		MeshObject cube = DummyObjects.getCube(0, 0, 2);
		cube.setScale(1);
		cube.setzRot(0);
		Timer t = new Timer(10, (ActionEvent e) -> {cube.setxRot(cube.getxRot() + 1);cube.setzRot(cube.getzRot() + 1);});
		t.start();
		objects.add(cube);
		MeshObject plane = DummyObjects.getPlane(0, 0, 0);
		plane.setScale(3);
		objects.add(plane);
		
		World world = new World(objects);
		
		ViewPanel vp = new ViewPanel(c, world);
		
		Frame f = new Frame(500,500);
		
		f.viewViewer(vp);
	}
}