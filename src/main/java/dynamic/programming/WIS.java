package dynamic.programming;

/**
 * From Stanford online courses
 * @author yyu
 *
 */
public class WIS {
	final Integer [] weight;
	private Integer [] cache = null;
	
	public WIS(Integer [] weight) {
		this.weight = weight;
	}
	/**
	 * max(a[i], a[i - 1] + Wi)
	 * @return
	 */
	public int valueOfSet() {
		this.cache = new Integer[this.weight.length];
		cache[0] = this.weight[0];
		cache[1] = this.weight[1];
		for (int i = 2; i < weight.length; i++) {
			cache[i] = this.weight[i] + cache[i -2];
			if (cache[i] < cache[i - 1]) {
				cache[i] = cache[i - 1];
			}
		}
		System.out.println("Cahced:");
		WIS.dump(cache);
		return cache[this.weight.length - 1];
	}
	
	/**
	 * -------- Has bug ------------------------
	 * @return
	 */
	public Integer [] verextInSet() {
		if (cache == null) {
			valueOfSet();
		}
		int i = this.weight.length - 1;
		for (; 2 < i; i -= 2) {
			if (cache[i].intValue() > cache[i - 1].intValue()) {
				this.cache[i] = i;
				this.cache[i -1] = -1;
			}
			else if (cache[i].intValue() == cache[i - 1].intValue()) {
				this.cache[i] = -1;
				i ++;
			}
			else {
				this.cache[i - 1] = i - 1;
				this.cache[i] = -1;
				this.cache[i - 2] = -1;
				i --;
			}
		}
		
		if (i == 1) {
			if (cache[2].intValue() > 1) {
				cache[0] = 0;
				cache[1] = -1;
			}
			else {
				cache[0] = -1;
				cache[1] = 1;
			}
		}
		else {
			if (cache[3].intValue() > 0) {
				setLast1();
			}
			else {
				if (cache[1].intValue() > cache[0].intValue() + cache[0].intValue()) {
					setLast1();
				}
				else {
					setLast2();
				}
			}
		}
		return cache;
	}
	
	private void setLast2() {
		cache[0] = 0;
		cache[1] = -1;
		cache[2] = 2;
	}
	private void setLast1() {
		cache[0] = -1;
		cache[1] = 1;
		cache[2] = -1;
	}
	
	static public void dump(Integer [] data) {
		for (int i = 0; i < data.length; i ++) {
			System.out.print(" " + data[i]);
 		}
		System.out.println();
	}
}
