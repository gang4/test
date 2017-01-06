package sort.divide.conque;

import java.util.Comparator;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

import sort.divide.conque.QuickSort;

public class TestQuickSort {

	@Test
	public void test() {
		Random r = new Random(new Date().getTime());
		QuickSort(Math.abs(r.nextInt(1000)));
	}
	
	private void QuickSort(int length) {
		Random r = new Random(new Date().getTime());
		Long [] array = new Long[length];
		for (int i = 0; i < array.length; i ++) {
			array[i] = new Long(r.nextLong() % (length * 2));
		}
		System.out.println("Input:");
		dump(array);
		QuickSort<Long> sort = new QuickSort<>(2, new Comparator<Long>() {
			@Override
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
		
		sort.partition(array, 0, array.length);
		
		System.out.println("Output: " + array.length);
		dump(array);
		System.out.println("done!");		
	}

	private void dump(Long[] array) {
		for (int i = 0; i < array.length; i ++) {
			System.out.print(" " + array[i]);
		}
		System.out.println("\n");
	}
}
