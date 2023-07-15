import java.util.Scanner;

public class Menu {
	int choice;
	Scanner input = new Scanner(System.in);

	public Menu() {
		displayAndHandle();
	}

	public void displayAndHandle() {
		int choice = 0;
		while (choice != 3) {
			choice = menuLogic(displayMenu());
		}
	}

	public boolean askBoolean(String question) {
		String userChoiceInput;
		boolean result = false;
		do {
			System.out.println(question);
			userChoiceInput = input.next();
			if (userChoiceInput.equalsIgnoreCase("yes")) {
				result = true;
			} else if (userChoiceInput.equalsIgnoreCase("no")) {
				result = false;
			} else {
				System.out.println("Not a valid option.");
			}

		} while (!userChoiceInput.equalsIgnoreCase("yes") && !userChoiceInput.equalsIgnoreCase("no"));

		return result;
	}

	public int displayMenu() {

		int selection;

		do {
			System.out.println("Choose from these choices");
			System.out.println("-------------------------\n");
			System.out.println("1 - Generate Password");
			System.out.println("2 - Test Password Strength");
			System.out.println("3 - Quit");
			System.out.println("Please enter a selection");
			// input validation
			while (!input.hasNextInt()) {
				System.out.println("That's not a number!");
				input.next();
			}
			selection = input.nextInt();
		} while (selection > 3 || selection < 1);
		return selection;
	}

	public int menuLogic(int selection) {
		switch (selection) {
		case 1:
			System.out.println("GENERATE PASSWORD");
			generatePasswordOption();
			break;
		case 2:
			System.out.println("TEST PASSWORD STRENGTH");
			testPasswordStrength();
			break;
		case 3:
			System.out.println("EXIT");
			break;
		}
		return selection;
	}

	public void generatePasswordOption() {
		boolean includeLower = false;
		boolean includeUpper = false;
		boolean includeNumbers = false;
		boolean includeSymbols = false;

		boolean isBlank = true;

		int passwordLength;

		boolean generateNewPassword = false;

		String userChoiceInput;

		// TAKE USER INPUT - IF THEY DON'T SELECT AN OPTION, DO NOT LET THEM CREATE
		// PASSWORD.

		do {
			do {
				includeLower = askBoolean("INCLUDE LOWERCASE LETTERS? (YES/NO)");
				includeUpper = askBoolean("INCLUDE UPPERCASE LETTERS? (YES/NO)");
				includeNumbers = askBoolean("INCLUDE NUMBERS? (YES/NO)");
				includeSymbols = askBoolean("INCLUDE SYMBOLS? (YES/NO)");

				if (!includeLower && !includeUpper && !includeNumbers && !includeSymbols) {
					System.out.println(
							"You have not included any valid selections. Please try again and answer 'yes' for one of the options.");
					isBlank = true;
				} else {
					isBlank = false;
				}
			} while (isBlank);
			// Generate Password Length
			do {
				System.out.println("Please enter a password length between 1-70");
				while (!input.hasNextInt()) {
					System.out.println("That's not a number!");
					input.next();
				}
				passwordLength = input.nextInt();
				// trying to skip next line
				input.nextLine();
			} while (passwordLength > 70 || passwordLength < 1);
			// Generate and print password
			PasswordGenerator pg = new PasswordGenerator(includeLower, includeUpper, includeNumbers, includeSymbols,
					passwordLength);

			// ASK IF THEY WANT A NEW PASSWORD
			generateNewPassword = askBoolean("WOULD YOU LIKE TO GENERATE ANOTHER PASSWORD? (YES/NO)");

		} while (generateNewPassword);

	}

	public void testPasswordStrength() {
		boolean askTestAnother;

		do {
			System.out.println("Please enter a password to be tested.");
			input.nextLine();
			String userPassword = input.nextLine();
			PasswordStrengthMeasurement psm = new PasswordStrengthMeasurement(userPassword);

			// ASK IF THEY WANT A NEW PASSWORD
			askTestAnother = askBoolean("WOULD YOU LIKE TO TEST ANOTHER PASSWORD? (YES/NO)");

		} while (askTestAnother);

	}

}
