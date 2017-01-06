package sort.divide.conque;

import java.awt.Point;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

import sort.divide.conque.SearchMinDistance;

public class MinDistance {
	@Test
	public void test() throws Exception {
		for (int i = 5; i < 101; i ++) {
			minDistance(i);
		}
	}

	private void minDistance(int vetice) throws Exception {
		Point [] points = generatePoints(vetice);
		//points = generatePoints1();
		SearchMinDistance s = new SearchMinDistance(points);
		double d = s.search();
		double d1 = brutalForceMinDistance(points);
		System.out.println("Min distance: " + d + "; " + d1);
		if (Math.abs(d - d1) > 0.00000001) {
			throw new Exception("Fatal");
		}
	}
	
	private Point[] generatePoints(int vetice) {
		Point [] points = new Point[vetice];
		Random r = new Random(new Date().getTime());
		int bound = vetice * 5;
		for (int i = 0; i < points.length; i ++) {
			points[i] = new Point();
			points[i].x = r.nextInt(bound);
			points[i].y = r.nextInt(bound);
		}
		return points;
	}

	private double brutalForceMinDistance(Point[] points) {
		double min = Double.MAX_VALUE;
		for (int i = 0; i < points.length; i ++) {
			Point p = points[i];
			for (int j = i; j < points.length; j ++) {
				if (i == j) {
					continue;
				}
				Point p1 = points[j];
				double d = Point.distance(p.x, p.y, p1.x, p1.y);
				//System.out.println("+++++ distance: " + d);
				min = Math.min(min, d);
			}
		}
		return min;
	}
}
