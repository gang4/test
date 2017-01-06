package sort.divide.conque;

import java.util.Arrays;
import java.util.Comparator;

public class InverseCount<T> {
	private int count = 0;
	
	public int getCount() {
		return count;
	}

	public T [] sort(final T [] array, final Comparator<T> comparator) {
		// Divede the input array into half
		return divide(array, comparator);
	}

	private T [] divide(final T [] array, final Comparator<T> comparator) {
		// if length > 2, divide
		int half = array.length / 2;
		T [] left = Arrays.copyOfRange(array, 0, half);
		if (half > 1) {
			left = divide(left, comparator);
		}
		
		T [] right = Arrays.copyOfRange(array, half, array.length);
		if ((array.length - half) > 1) {
			right = divide(right, comparator);
		}
		// conque
		return merge(array, left, right, comparator);
	}

	private T [] merge(final T [] array, T[] left, T[] right, Comparator<T> comparator) {
		int indexRight = 0;
		int indexLeft = 0;
		for (int i = 0; i < (left.length + right.length); i ++) {
			if (indexRight < right.length && indexLeft < left.length) {
				if (comparator.compare(left[indexLeft],  right[indexRight]) >= 0) {
					array[i] = left[indexLeft];
					indexLeft ++;
				}
				else {
					count += (left.length - indexLeft);
					array[i] =  right[indexRight];
					indexRight ++;
				}
			}
			else if (indexRight == right.length) {
				array[i] = left[indexLeft];
				indexLeft ++;
			}
			else {
				array[i] = right[indexRight];
				indexRight ++;
			}
		}
		return array;
	}
}
