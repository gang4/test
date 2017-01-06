package sort.divide.conque;

import java.util.Comparator;
import java.util.Date;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class TestSearchXthElement {
	static QuickSort<Integer> sort = new QuickSort<>(2, new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
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
	
	@Test
	public void test() {
		Random r = new Random(new Date().getTime());
		int length = 0;
		while (length < 2) {
			length = Math.abs(r.nextInt(32));
		}
		search(length);
	}
	
	private void search(int length) {
		Random r = new Random(new Date().getTime());
		Integer [] array = new Integer[length];
		for (int i = 0; i < array.length; i ++) {
			array[i] = new Integer(r.nextInt() % (length * 2));
		}
		int xth = Math.abs(r.nextInt(length - 1));
		System.out.println("Input:");
		dump(array);
		System.out.println("Xth: " + xth);
		SearchXthElement<Integer> search = new SearchXthElement<>(xth, 2, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
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
		
		int x = search.partition(array, 0, array.length);
		System.out.println("---------------- sorted ---------------------");
		sort.partition(array, 0, array.length);
		System.out.println("Output sorted");
		dump(array);
		System.out.println("Direct search,	Xth: " + xth + ", value = " + x);
		System.out.println("After sorted, 	Xth: " + xth + ", value = " + array[xth]);
		Assert.assertTrue(x == array[xth]);
		System.out.println("done!");		
	}

	private void dump(Integer[] array) {
		for (int i = 0; i < array.length; i ++) {
			System.out.print(" " + array[i]);
		}
		System.out.println("\n");
	}
}
