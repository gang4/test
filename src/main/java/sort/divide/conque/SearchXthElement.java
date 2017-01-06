package sort.divide.conque;

import java.util.Comparator;

public class SearchXthElement<T> {
	final int pivot;
	final Comparator<T> c;
	private int xth;
	public SearchXthElement(int xth, int pivot, Comparator<T> c) {
		this.xth = xth;
		this.pivot = pivot;
		this.c = c;
	}
	
	public T partition(T [] array, int start, int end) {
		int p = (end - start) / pivot;
		int pivotIndex = start + p;
	
		// Start partition O(n)
		int swapIndex = start;
		for (int i = start; i < end; i ++) {
			if (this.c.compare(array[i], array[pivotIndex]) < 0) {
				T tmp = array[swapIndex];
				array[swapIndex] = array[i];
				array[i] = tmp;
				if (swapIndex == pivotIndex) {
					pivotIndex = i;
				}
				swapIndex ++;
			}
			//dump(array, start, end);
		}
		T tmp = array[pivotIndex];
		array[pivotIndex] = array[swapIndex];
		array[swapIndex] = tmp;
		pivotIndex = swapIndex;
		//dump(array, start, end);
		
		if (this.xth > pivotIndex) {
			//this.xth -= pivotIndex;
			if (end - pivotIndex > 1) {
				return partition(array, pivotIndex + 1, end);
			}
			else {
				return array[pivotIndex];
			}
		}
		else if (this.xth < pivotIndex) {
			if (pivotIndex - start > 1) {
				return partition(array, start, pivotIndex);
			}
			else {
				return array[start];
			}
		}
		else {
			return array[pivotIndex];
		}
	}
	
	public void dump(T[] array, int start, int end) {
		for (int i = start; i < end; i ++) {
			System.out.print(" " + array[i]);
		}
		System.out.println("\n");
	}
}
