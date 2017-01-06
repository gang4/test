package sort.divide.conque;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SearchMinDistance {
	static public class IndexedPoint {
		final long p;
		IndexedPoint paired;
		final int index;
		boolean isLeft = false;
		public IndexedPoint(long p, int i) {
			this.p = p;
			this.index = i;
		}
	}
	IndexedPoint [] xPoints;
	IndexedPoint [] yPoints;
	
	public SearchMinDistance(Point [] points) {
		//
		// Sort by x
		//
		this.xPoints = new IndexedPoint[points.length];
		this.yPoints = new IndexedPoint[points.length];
		// O(n)
		for (int i = 0 ; i < points.length; i ++) {
			this.xPoints[i] = new IndexedPoint(points[i].x, i);
			this.yPoints[i] = new IndexedPoint(points[i].y, i);
			this.xPoints[i].paired = this.yPoints[i];
			this.yPoints[i].paired = this.xPoints[i];
		}
		System.out.println("input");
		dump(this.xPoints);
		dump(this.yPoints);
		// O(n (lng n))
		this.xPoints = new MergeSort<IndexedPoint>().sort(this.xPoints, new Comparator<IndexedPoint>(){
			public int compare(IndexedPoint p0, IndexedPoint p1) {
				if (p0.p - p1.p > 0) {
					return -1;
				}
				else if (p0.p - p1.p < 0) {
					return 1;
				}
				else {
					return 0;
				}
			}});
		System.out.println("sorted x");
		dump(this.xPoints);
		// 
		// Sort by y
		//
		// O(n)
		// O(n (log n))
		this.yPoints = new MergeSort<IndexedPoint>().sort(this.yPoints, new Comparator<IndexedPoint>(){
			public int compare(IndexedPoint p0, IndexedPoint p1) {
				if (p0.p - p1.p > 0) {
					return -1;
				}
				else if (p0.p - p1.p < 0) {
					return 1;
				}
				else {
					return 0;
				}
			}});
		System.out.println("sorted y");
		dump(this.yPoints);
	}
	
	private void dump(Object[] xPoints2) {
		for (int i = 0; i < xPoints2.length; i ++) {
			System.out.print("	" + ((IndexedPoint)xPoints2[i]).index);
		}
		System.out.println();
		for (int i = 0; i < xPoints2.length; i ++) {
			System.out.print("	" + ((IndexedPoint)xPoints2[i]).p);
		}
		System.out.println();
	}

	public double search() {
		return divide(this.xPoints, this.yPoints);
	}

	private double divide(IndexedPoint[] xyPoints, IndexedPoint[] yPoints) {
		//
		//  Divide sorted X point.
		//
		int half = xyPoints.length / 2;
		IndexedPoint [] leftX = Arrays.copyOfRange(xyPoints, 0, half);
		IndexedPoint [] rightX = Arrays.copyOfRange(xyPoints, half, xyPoints.length);
		IndexedPoint [] leftY = new IndexedPoint[leftX.length];
		IndexedPoint [] rightY = new IndexedPoint[rightX.length];
		//
		// Divide sorted Y points
		//
		double lMin = Double.MAX_VALUE;
		// recursive O(log n)
		for (IndexedPoint p : leftX) {
			p.paired.isLeft = true;
		}
		int lIndex = 0;
		int rIndxex = 0;
		for (int i = 0; i < yPoints.length; i ++) {
			if (yPoints[i].isLeft) {
				leftY[lIndex] = yPoints[i];
				yPoints[i].isLeft = false;
				lIndex ++;
			}
			else {
				rightY[rIndxex] = yPoints[i];
				rIndxex ++;
			}
		}
		
		if (half > 1) {
			lMin = divide(leftX, leftY);
		}
		
		double rMin = Double.MAX_VALUE;
		// recursive O(log n)
		if ((xyPoints.length - half) > 1) {
			rMin = divide(rightX, rightY);
		}
		// conque
		return searchInSplit(leftX, rightX, yPoints, Math.min(lMin,  rMin));
	}

	private double searchInSplit(IndexedPoint[] xLeft, IndexedPoint[] xRight, IndexedPoint[] yPoint, double d) {
		// (1) have all left vertices
		// (2) have all right vertices
		// (3) have min distance of vertices either in left or right
		assert (xLeft != null && xRight != null);
		List<IndexedPoint> merged = new ArrayList<>();
		// get middle point by knownn that left and right are sored by x
		double middle = (xLeft[xLeft.length - 1].p  + xRight[0].p) / 2;
		// Merge all vertices in left and right if their |x| <= d
		// O(n / 2 ^ (deep -1))
		for (IndexedPoint p : yPoint) {
			if (Math.abs(middle - p.paired.p) <= d) {
				merged.add(p);
			}
		}
		Object[] points = merged.toArray();
		System.out.println("-------- sorted by y");
		dump(points);
		
		// Search
		// 7 * O(n / 2 ^ (deep -1))
		for (int i = 0; i < points.length; i ++) {
			int len = i + 7;
			if (len > points.length) {
				len = points.length;
			}
			IndexedPoint pi = (IndexedPoint)points[i];
			long yi = pi.p;
			long xi = pi.paired.p;
			for (int j = i + 1; j < len; j ++) {
				IndexedPoint pj = (IndexedPoint)points[j];
				long yj = pj.p;
				long xj = pj.paired.p;
				d = Math.min(d,  Point.distance(xi, yi, xj, yj));
			}
		}
		System.out.println("-------- d " + d);
		return d;
	}
}
