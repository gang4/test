package dynamic.programming;

/**
 * From Stanford online courses
 * @author yyu
 *
 */
public class WIS {
	final Integer [] weight;
	private Integer [] a = null;
	
	public WIS(Integer [] weight) {
		this.weight = weight;
	}
	/**
	 * max(a[i], a[i - 1] + Wi)
	 * @return
	 */
	public int valueOfSet() {
		this.a = new Integer[this.weight.length];
		a[0] = this.weight[0];
		a[1] = this.weight[1];
		for (int i = 2; i < weight.length; i++) {
			a[i] = this.weight[i] + a[i -2];
			if (a[i] < a[i - 1]) {
				a[i] = a[i - 1];
			}
		}
		System.out.println("Cahced:");
		WIS.dump(a);
		return a[this.weight.length - 1];
	}
	
	public Integer [] verextInSet() {
		if (a == null) {
			valueOfSet();
		}
		for (int i = this.weight.length - 1; 0 < i; i --) {
			if (a[i] > (a[i -1])) {
				this.a[i] = this.weight[i];
				this.a[i -1] = -1;
			}
			else {
				this.a[i] = -1;
				this.a[i - 1] = this.weight[i - 1];
			}
		}
		return a;
	}
	
	static public void dump(Integer [] data) {
		for (int i = 0; i < data.length; i ++) {
			System.out.print(" " + data[i]);
 		}
		System.out.println();
	}
}
