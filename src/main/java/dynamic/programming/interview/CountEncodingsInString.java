package dynamic.programming.interview;

/**
 * 
 * encode "31257" by A...Z, 1 map to A and 26 map to Z. 
 * Also 26 can be encoded BF
 * So, for a(i), we have
 * a(i) = a(i - 1) + d(i) or
 * a(i) = a(i - 2) + d(i - 1)d(i) if d(i - 1) < 3 and d(i) < 7
 * 
 * We can recursively compute the value of any given digital
 * sequence. 
 */
public class CountEncodingsInString {

}
