package sort.divide.conque;

import java.util.Comparator;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

import sort.divide.conque.MergeSort;

public class TestMergeSort {

	@Test
	public void test() {
		Random r = new Random(new Date().getTime());
		mergeSort(Math.abs(r.nextInt(1000)));
	}
	
	private void mergeSort(int length) {
		Random r = new Random(new Date().getTime());
		Long [] array = new Long[length];
		for (int i = 0; i < array.length; i ++) {
			array[i] = new Long(r.nextLong() % (length * 2));
		}
		array = new MergeSort<Long>().sort(array, new Comparator<Long>() {
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
		
		for (int i = 0; i < array.length; i ++) {
			System.out.print(" " + array[i]);
		}
		System.out.println("\n done!");		
	}
}
