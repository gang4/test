package dynamic.programming;

import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;

import org.junit.Test;

/**
 * 
 * **** Not verified *****
 *
 */
public class TestSequenceAligment {
	private int index = 0;
	public void buildTable(String v, String w) {
		SequenceAligment k = new SequenceAligment(v, w);
		int [][] table = k.buildTable();
		System.out.println("Table");
		Util.dump(table);
		table = k.traceBack(table);
		System.out.println("Trace Back");
		Util.dump(table);
	}
	
	@Test
	public void test1() {
		String s1 = new String(" GAATTCAGTTA");
		String s2 = new String(" GGATCGA");
		buildTable(s1, s2);
	}
	
	@Test
	public void test2() {
		String s1 = new String(" AGGGCT");
		String s2 = new String(" AGGCA");
		buildTable(s1, s2);
	}
	
	@Test
	public void test() {
		Random r = new Random(new Date().getTime());
		String s1 = autoGen(r);
		String s2 = autoGen(r);
		buildTable(s1, s2);
	}
	
	private String autoGen(Random r) {
		int len = 0;
		while (len < 5) {
			len = Math.abs(r.nextInt(32));
		}
		
		IntStream intStream = r.ints(len, 0, 26);
		final char [] array = new char[len + 1];
		index = 0;
		intStream.forEach(i -> {
			array[index] = (char) (i + 65); // start from A
			System.out.print(array[index]);
			index ++;
		});
		System.out.println();
		return new String(array);
	}
}
