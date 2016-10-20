/*
 * Created on 16/02/2005 05:57:42 PM
 *
 * biz.belcorp.ssicc.util.Mod11Ck
 */
package biz.belcorp.ssicc.service.util;

/**
 * TODO Include class description here.
 * <p>
 * <a href="Mod11Ck.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class Mod11Ck {
	public static String calc(String digStr) {
		int[] factors = new int[] {2, 3, 4, 5, 6, 7};
		int len = digStr.length();
		int sum = 0, rem = 0;
		
		for(int k = 0; k < len; k++) {
			// compute weighted sum
			sum += factors[k % 6] * Character.getNumericValue(digStr.charAt(len - k - 1));
		}

		if ((rem = sum % 11) == 0)
			return "0";
		else if (rem == 1)
			return "X";
		return (new Integer(11 - rem)).toString();
	}

	public static void main(String[] args) {
		String code = "100001709";
		System.out.println(code + "-" + calc(code));
	}
}