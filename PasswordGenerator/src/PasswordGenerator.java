import java.util.Random;

public class PasswordGenerator {
	boolean includeLower = false;
	boolean includeUpper = false;
	boolean includeNumbers = false;
	boolean includeSymbols = false;

	int length;

	String lowercase = "abcdefghijklmnopqrstuvwxyz";
	String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String numbers = "0123456789";
	String symbols = "~`!@#$%^&*()_-+={[}]|\\:;\"'<,>.?/";

	String characterList;

	public PasswordGenerator(boolean includeLower, boolean includeUpper, boolean includeNumbers, boolean includeSymbols,
			int length) {
		this.includeLower = includeLower;
		this.includeUpper = includeUpper;
		this.includeNumbers = includeNumbers;
		this.includeSymbols = includeSymbols;
		this.length = length;
		buildList();
		buildPassword();

	}

	public void buildList() {
		StringBuilder str = new StringBuilder();
		if (includeLower)
			str.append(lowercase);
		if (includeUpper)
			str.append(uppercase);
		if (includeNumbers)
			str.append(numbers);
		if (includeSymbols)
			str.append(symbols);

		characterList = str.toString();
	}

	public char selectACharacter(String s) {
		Random rand = new Random();
		int index = rand.nextInt(s.length());
		char result = s.charAt(index);
		return result;
	}

	public void buildPassword() {
		StringBuilder passBuilder = new StringBuilder();
		for (int i = 1; i <= length; i++) {
			passBuilder.append(selectACharacter(characterList));
		}
		System.out.println("GENERATED PASSWORD IS: ");
		System.out.println(passBuilder.toString());

	}

}
