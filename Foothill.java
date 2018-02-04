import java.util.Scanner;

//Adam Creeger
public class Foothill {

	public static void main(String[] args) {

		int rule;
		String userRuleInputString;

		Automaton autoReference;

		Scanner ruleScanner = new Scanner(System.in);

		do {
			System.out.println("Please input a rule between 0-255! ");
			// have the scanner read the string input
			userRuleInputString = ruleScanner.nextLine();
			// convert the string to integer
			rule = Integer.parseInt(userRuleInputString);
		} while (rule < 0 || rule > 255);
		// construct automaton
		autoReference = new Automaton(rule);
		// set display width
		autoReference.setDisplayWidth(79);

		// starting point of printed rule:
		System.out.println("Start:");

		// display rule for 100 generations
		for (int i = 0; i < 100; i++) {
			System.out.println(autoReference.toStringCurrentGen());
			autoReference.propagateNewGeneration();
		}
		System.out.println("End:");
		ruleScanner.close();
	}
}
