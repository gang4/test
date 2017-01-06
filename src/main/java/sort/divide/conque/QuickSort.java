package sort.divide.conque;

import java.util.Comparator;

public class QuickSort<T> {
	final int pivot;
	final Comparator<T> c;
	public QuickSort(int pivot, Comparator<T> c) {
		this.pivot = pivot;
		this.c = c;
	}
	
	public void partition(T [] array, int start, int end) {
		int p = (end - start) / pivot;
		int pivotIndex = start + p;
		//System.out.println("Pivot point: " + array[pivotIndex] + ", at: " + pivotIndex);
		// swap pivot point to start for ease of implement
		T tmp = array[start];
		array[start] = array[pivotIndex];
		array[pivotIndex] = tmp;
		//dump(array, start, end);
		
		// Start partition O(n)
		int indexPivotPosition = start;
		T pivotValue = array[start];
		for (int i = indexPivotPosition; i < end; i ++) {
			if (c.compare(pivotValue, array[i]) > 0) {
				indexPivotPosition ++;
				tmp = array[indexPivotPosition];
				array[indexPivotPosition] = array[i];
				array[i] = tmp;
			}
			//dump(array, start, end);
		}
		
		// Restore pivot point position
		array[start] = array[indexPivotPosition];
		array[indexPivotPosition] = pivotValue;
		
		//dump(array, start, end);

		// Recursive
		if (indexPivotPosition - start > 1) {
			partition(array, start, indexPivotPosition);
		}
		if (end - indexPivotPosition > 1) {
			partition(array, indexPivotPosition + 1, end);
		}
	}
	
	public void dump(T[] array, int start, int end) {
		for (int i = start; i < end; i ++) {
			System.out.print(" " + array[i]);
		}
		System.out.println("\n");
	}
}
