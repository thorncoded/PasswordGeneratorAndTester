import java.util.Arrays;

public class PasswordStrengthMeasurement {
	static final int MAX_CHAR = 256;
	int passwordLength;
	String uniqueCharacters = "";
	String initialPassword = "";

	public PasswordStrengthMeasurement(String initialPassword) {
		this.initialPassword = initialPassword;
		this.passwordLength = initialPassword.length();
		this.uniqueCharacters = printDistinct(initialPassword);
		rateEntropy(calculateEntropy());

	}

	static String printDistinct(String str) {
		StringBuilder strB = new StringBuilder();

		int n = str.length();
		int[] count = new int[MAX_CHAR];
		int[] index = new int[MAX_CHAR];

		// Initialize counts of all characters and
		// indexes of distinct characters.
		for (int i = 0; i < MAX_CHAR; i++) {
			count[i] = 0;
			index[i] = n;
		}

		// Traverse the input string
		for (int i = 0; i < n; i++) {

			char x = str.charAt(i);
			++count[x];

			if (count[x] == 1 && x != ' ')
				index[x] = i;

			if (count[x] == 2)
				index[x] = n;
		}

		// constant time
		Arrays.sort(index);

		for (int i = 0; i < MAX_CHAR && index[i] != n; i++) {
			strB.append(str.charAt(index[i]));
		}

		return strB.toString();

	}

	public static double log2(int N) {
		double result = (Math.log(N) / Math.log(2));

		return result;
	}

	// CALCULATES ACCORDING TO PASSWORD ENTROPY FORMULA OF E = L * log2(R)
	// SOURCE - https://www.omnicalculator.com/other/password-entropy

	// R – Size of the pool of unique characters from which we build the password;
	// and
	// L – Password length, i.e., the number of characters in the password.

	public double calculateEntropy() {
		int r = uniqueCharacters.length();
		double l = passwordLength;
		double logResult = log2(r);
		double entropy = l * logResult;
		return entropy;
	}

	public static void rateEntropy(double entropy) {
		if (entropy < 25)
			System.out.println("RATING: Poor password");
		else if (entropy < 50)
			System.out.println("RATING: Weak password");
		else if (entropy < 75)
			System.out.println("RATING: Reasonable password");
		else if (entropy < 100)
			System.out.println("RATING: Very good password");
		else
			System.out.println("RATING: Excellent password");

		System.out.println("Entropy score was " + entropy + ".");
	}
}
