package sort.heap;

import java.lang.reflect.Array;
import java.util.Comparator;

/**
 * (1) each node has a key
 * (2) The key in parent is smaller than one in its children.
 * (3) It is a complete (or fully balanced) binary tree
 * @author yyu
 *
 */
public class Heap<T> {
	private T [] keys;
	public T[] getKeys() {
		return keys;
	}

	final int increment = 2;
	private int length = 0;
	final Class<T> clazz;
	final Comparator<T> com;
	
	@SuppressWarnings("unchecked")
	public Heap(Class<T> clazz, Comparator<T> com) {
        // Use Array native method to create array
        // of a type only known at run time
    	this.clazz = clazz;
    	this.com = com;
        this.keys = (T[]) Array.newInstance(clazz, 20);
    }
	
	public void insert(T key) {
		if (length == this.keys.length) {
		    @SuppressWarnings("unchecked")
			T [] newArray = (T[]) Array.newInstance(clazz, this.keys.length * 2);
		    System.arraycopy(this.keys, 0, newArray, 0, this.keys.length);
		    this.keys = newArray;
		}
		this.keys[length] = key;
		this.length ++;
		bubbleUp();
	}
	
	private void bubbleUp() {
		if (length == 1) {
			return;
		}
		int current = this.length - 1;
		int parent = getParent(current);
		while (com.compare(this.keys[parent], this.keys[current]) > 0) {
			T tmp = this.keys[parent];
			this.keys[parent] = this.keys[current];
			this.keys[current] = tmp;
			current = parent;
			parent = getParent(current);
			if (parent < 0) {
				break;
			}
		}
	}

	private int getParent(int current) {
		if ((current & 1) == 0) {
			return (current / 2) - 1;
		}
		else {
			return (current / 2);
		}
	}

	public T extract() {
		if (this.length == 0) {
			return null;
		}
		T key = this.keys[0];
		this.keys[0] = this.keys[this.length - 1];
		this.length --;
		bubbleDown();
		return key;
	}

	private void bubbleDown() {
		if (length == 1) {
			return;
		}
		int current = 0;
		do {
			int childLeft = current * 2 + 1;
			int childRight = (current + 1) * 2;
			if (childLeft >= this.length) {
				break;
			}
			T key = this.keys[childLeft];
			int next = childLeft;
			if (childRight < this.length && com.compare(this.keys[childLeft], this.keys[childRight]) > 0) {
				key = this.keys[childRight];
				next = childRight;				
			}
			if (com.compare(key, this.keys[current]) < 0) {
				T tmp = this.keys[next];
				this.keys[next] = this.keys[current];
				this.keys[current] = tmp;
				current = next;
				continue;
			}
			break;
		} while (true);
	}
}
