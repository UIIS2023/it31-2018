package geometry;

import java.util.Arrays;
import java.util.HashMap;

public class Test {

	public static void main(String[] args) {

		Point p = new Point();
		p.setX(10);
		p.setY(20);
		p.setSelected(true);
		
		System.out.println("X coordinate of point p is: " + p.getX() + '\n' +
										"Y coordinate of point p is: " + p.getY() + '\n' +
										"Point p is selected: " + p.isSelected());
		

		double rez = p.distance(30, 40);
		System.out.println(rez);
		
		Point p1 = new Point();
		p1.setX(15);
		p1.setY(27);
		p1.setSelected(true);
		
		// 1. Inicijalizovati x koordinatu tacke p
		// na vrednost y koordinate tacke p1
		
		p.setX(p1.getY());
		System.out.println("X of p = " + p.getX());
		
		// 2. Postaviti za pocetnu tacku linije l1 tacku p, a
		// za krajnju tacku linije l1 tacku p1
		
		Line l1 = new Line();
		l1.setStartPoint(p);
		l1.setEndPoint(p1);

		// 3. Postaviti y koordinatu krajnje tacke l1 na 23
		
		l1.getEndPoint().setY(23);

		// 4. Inicijalizovati x koordinatu pocetne tacke linije l1
		// na vrednost y koordinate krajnje tacke linije l1
		
		l1.getStartPoint().setX(l1.getEndPoint().getY());

		// 5. Postaviti x koordinatu krajnje tacke l1 na vrednost
		// duzine linije l1 umanjene za vrednost zbira x i y
		// koordinate pocetne tacke linije l1
		
		l1.getEndPoint().setX((int) (l1.length() - (l1.getStartPoint().getX() + l1.getStartPoint().getY())));
		System.out.println("X of end point of l1 = " + l1.getEndPoint().getX());
		
		// 6. Postaviti x koordinatu tacke gore levo pravougaonika
		// r1 na vrednost 10 i y koordinatu na vrednost 15

		Rectangle r1 = new Rectangle();
		r1.setUpperLeftPoint(p);
		r1.getUpperLeftPoint().setX(10);
		r1.getUpperLeftPoint().setY(15);
		
		// 7. Postaviti centar kruga c1 na koordinate tacke
		// gore levo od r1
		
		Circle c1 = new Circle();
		c1.setCenter(r1.getUpperLeftPoint());

		// 8. Postaviti x koordinatu centra kruga c1 na vrednost razlike
		// povrsine pravougaonika r1 i y koordinate pocetne tacke linije l1
		
		r1.setHeight(20);
		r1.setWidth(30);
		c1.getCenter().setX((int) r1.area() - l1.getStartPoint().getY());
		
		Point p2 = new Point(100, 200);
		Point p3 = new Point(100, 200);
		Line l2 = new Line(p, new Point(400, 500));
		
		System.out.println(p2 == p3);
		System.out.println(p2.equals(p3));
		System.out.println(p2.equals(r1));
		System.out.println(p2.equals(p));
		
		System.out.println(p2.toString());
		// System.out.println(p2);
		System.out.println(l2.toString());
		// c1.setRadius(155);
		System.out.println(c1.toString());
		System.out.println(r1.toString());
		
		String s = "Hello";
		String s1 = "Hello";
		
		System.out.println(s == s1);
		
		String s2 = new String("Hello World");
		String s3 = new String("Hello World");
		
		System.out.println(s2 == s3);
		System.out.println(s2.equals(s3));
		
		Donut d = new Donut();
		
		// dinamicko povezivanje
		Circle c2 = new Donut(new Point(10,10), 50, 20);
		
		Point p7 = new Point(5, 5);
		Point p8 = new Point(10, 10);
		Point p9 = new Point(2, 2);
		Point p10 = new Point(15, 15);
		
		// Niz
		Point[] tacke = {p7, p8, p9, p10};
		
		System.out.println("Nesortiran niz tacaka");
		for (int i = 0; i < tacke.length; i++) {
			System.out.println(tacke[i]);
		}
		
		// sortiranje niza
		Arrays.sort(tacke);  
		
		System.out.println("Sortiran niz tacaka");
		for (int i = 0; i < tacke.length; i++) {
			System.out.println(tacke[i]);
		}
		
		// HashMap-e
		HashMap<String, Shape> map = new HashMap<String, Shape>();
		map.put("point", p1);
		map.put("rectangle", r1);
		map.put("Point", p3);
		
		System.out.println("point from map is: " + map.get("point")); 
		System.out.println("point from map is: " + map.get("Point")); 
		
		Point p11 = new Point(200, 200);
		map.put("point", p11);
		System.out.println("point from map is: " + map.get("point")); 
		
		Circle c3 = new Circle(p3, 50);
		
		try {
			c3.setRadius(-50);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Uvek se izvrsava");
		}
		
	}

}
