package sort.heap;

import java.util.Comparator;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

public class TestHeap {

	@Test
	public void test() {
		Random r = new Random(new Date().getTime());
		int len = 0;
		while (len < 5) {
			len = Math.abs(r.nextInt(64));
		}
		heapBuild(len);
	}
	
	private void heapBuild(int length) {
		Heap<Long> heap = new Heap<>(Long.class, new Comparator<Long>() {
			public int compare(Long o1, Long o2) {
				if (o1 > o2) {
					return 1;
				}
				else if (o1 < o2) {
					return -1;
				}
				else {
					return 0;
				}
			}
		});
//		Long [] array = {0L, 0L, 2L, 4L, 2L, 6L, 5L, 7L, 7L, 7L};
//		for (int i = 0; i < array.length; i ++) {
//			heap.insert(array[i]);
//		}
		Random r = new Random(new Date().getTime());
		Long [] array = new Long[length];
		for (int i = 0; i < array.length; i ++) {
			array[i] = new Long(Math.abs(r.nextLong()) % length);
			heap.insert(array[i]);
		}
		
		for (int i = 0; i < array.length; i ++) {
			System.out.print(" " + heap.getKeys()[i]);
		}
		System.out.println("");
		for (int i = 0; i < array.length; i ++) {
			System.out.print(" " + heap.extract());
		}
		System.out.println("\n done!");		
	}
}
