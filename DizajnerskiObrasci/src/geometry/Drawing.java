package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Drawing extends JPanel {
	public Drawing() {
		setBackground(Color.WHITE);
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Drawing");
		frame.setSize(800, 600);
		Drawing drawing = new Drawing();
		//drawing.setBackground(Color.WHITE);
		frame.getContentPane().add(drawing);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void paint(Graphics g) {
		
		Point p = new Point(50, 60, false, Color.RED);
		// p.draw(g);
		
		Point p1 = new Point(80, 80, true);
		// p1.draw(g);
		
		Line l1 = new Line(new Point(300, 250), new Point(240, 210), true, Color.MAGENTA);
		// l1.draw(g);
		
		Circle c = new Circle(new Point(450, 120), 40, true, Color.RED, Color.BLUE);
		// c.draw(g);
		
		Rectangle r = new Rectangle(new Point(300, 300), 120, 170, true, Color.RED, Color.YELLOW);
		// r.draw(g);
		
		Donut d = new Donut(new Point(600, 400), 50, 20, true, Color.RED, Color.BLUE);
		// d.draw(g);
		
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		shapes.add(p);
		shapes.add(l1);
		shapes.add(c);
		shapes.add(r);
		shapes.add(d);
		
		Iterator<Shape> it = shapes.iterator();
		while(it.hasNext()) {
			System.out.println("Selected: " + it.next().isSelected()); 
		}
		
		// iscrtati treci element iz liste shapes
		shapes.get(2).draw(g);
		
		// iscrtati poslednji element iz liste
		shapes.get(shapes.size() - 1).draw(g);

		// iscrtati element sa indeksom 3
		shapes.get(3).draw(g);

		// dodati i iscrtati novu liniju u listu tako da ona bude na 4.
		// poziciji u listi
		Line newLine = new Line(new Point(150, 150), new Point(300, 300));
	
		shapes.add(3, newLine);
		// shapes.get(3).draw(g);
	
		// ukloniti drugi element liste
		shapes.remove(1);
	
		// setovati svaki oblik iz liste shapes kao selektovan pomocu iteratora it
		while (it.hasNext()) {
			it.next().setSelected(true);
		}
	 
		// Iscrtati svaki oblik iz shapes liste pomocu iteratora it
		while (it.hasNext()) {
			it.next().draw(g);
		}

		// Iscrtati samo povrsinske oblike iz liste shapes
		while (it.hasNext()) {
			if (it.next() instanceof SurfaceShape) {
				it.next().draw(g);
			}
		}

	}
	
}
