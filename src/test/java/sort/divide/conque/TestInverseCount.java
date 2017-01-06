package sort.divide.conque;

import java.util.Comparator;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

import sort.divide.conque.InverseCount;

public class TestInverseCount {

	@Test
	public void test() {
		Random r = new Random(new Date().getTime());
		mergeSort(Math.abs(r.nextInt(300)));
	}
	
	private void mergeSort(int length) {
		Random r = new Random(new Date().getTime());
		Integer [] array = new Integer[length];
		System.out.println("Input array:");
		for (int i = 0; i < array.length; i ++) {
			array[i] = new Integer(r.nextInt() % (length * 2));
			System.out.print(" " + array[i]);
		}
		System.out.print("\n");
		
		int count = 0;
		for (int i = 0; i < array.length; i ++) {
			for (int j = i + 1; j < array.length; j ++) {
				if (array[i] > array[j]) {
					count ++;
				}
			}
		}
		
		InverseCount<Integer> inverse = new InverseCount<>();
		array = inverse.sort(array, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) {
					return -1;
				}
				else if (o1 < o2) {
					return 1;
				}
				else {
					return 0;
				}
			}
		});
		
		System.out.println("Output array:");
		for (int i = 0; i < array.length; i ++) {
			System.out.print(" " + array[i]);
		}
		System.out.println("\nbrutal force: " + count + "; sort :" + inverse.getCount());
		System.out.println("\n done!");	
	}
}
