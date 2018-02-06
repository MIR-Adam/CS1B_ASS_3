
class Automaton {
	// class constants
	public final static int MAX_DISPLAY_WIDTH = 121;

	// private members
	private boolean rules[]; // allocate rules[8] in constructor!
	private String rulesTemplate[] = new String[8];
	private String thisGen; // same here
	String extremeBit; // bit, "*" or " ", implied everywhere "outside"
	int displayWidth; // an odd number so it can be perfectly centered

	// public constructors, mutators, etc. (need to be written)

	// default constructor
	public Automaton(int new_rule) {

		// rules boolean constructor
		rules = new boolean[8];
		resetFirstGen();
		setDisplayWidth(MAX_DISPLAY_WIDTH);
		setRule(new_rule);
		setRulesTemplate();
	}

	// constructor overloader
	public Automaton() {
		// defaults to rule 0
		this(0);
	}

	public void resetFirstGen() {

		extremeBit = " ";
		thisGen = "*";
	}

	public boolean setRule(int new_rule) {

		if (new_rule > 255 || 0 > new_rule) {

			for (int i = 0; i < rules.length; i++) {
				rules[i] = false;
			}
			return false;

		} else {

			for (int i = 0; i < rules.length; i++) {

				if (new_rule % 2 == 1) {
					rules[i] = true;
				} else {
					rules[i] = false;
				}
				new_rule = new_rule / 2;
			}
		}
		return true;
	}

	public void setRulesTemplate() {

		for (int i = 0; i < 8; i++)
			rulesTemplate[i] = "";

		for (int k = 0; k < 8; k++) {

			int current = k;

			for (int i = 0; i < 3; i++) {
				if (current % 2 == 0) {

					rulesTemplate[k] = " " + rulesTemplate[k];
					current = current / 2;
				} else {
					rulesTemplate[k] = "*" + rulesTemplate[k];
					current = (current - 1) / 2;
				}
			}
		}
	}

	public boolean setDisplayWidth(int width) {

		if (width % 2 == 0 || width > MAX_DISPLAY_WIDTH) {
			return false;
		}
		displayWidth = width;
		return true;
	}

	public String toStringCurrentGen() {

		String autoString = thisGen;
		String emptyString = "";
		int emptySpaces = (displayWidth - thisGen.length() / 2);

		// if there is one or more empty spaces
		if (emptySpaces > 0) {

			// for each empty space greater than 0
			for (int i = 0; i < emptySpaces; i++) {

				emptyString = emptyString + extremeBit;
			}

			autoString = emptyString + thisGen + emptyString;
		}
		// if there are less than 0 empty spaces
		else if (emptySpaces < 0) {
			// adjusts and centers displayWidth
			autoString = autoString.substring(-emptySpaces, thisGen.length() + emptySpaces);
		}
		return autoString;
	}

	public void propagateNewGeneration() {

		String nextGenerationString = "";
		String bitCompareString = "";
		String currentGenerationString = thisGen;

		for (int i = 0; i < 3 - 1; i++)
			currentGenerationString = extremeBit + currentGenerationString + extremeBit;
		
		  thisGen = extremeBit + extremeBit + thisGen + extremeBit + extremeBit;

		// propagate nextGen

		for (int k = 0; k < currentGenerationString.length() + 1 - 3; k++) {

			bitCompareString = currentGenerationString.substring(k, k + 3); // set the range for comparison

			for (int c = 0; c < 8; c++) {

				if (bitCompareString.equals(rulesTemplate[c])) {
					nextGenerationString += rules[c] ? "*" : " ";
				}
			}

			// transfer the generation
			thisGen = nextGenerationString;

			// extreme bit rule check
			if (extremeBit == " " && rules[0]) {
				extremeBit = "*";
			}
			if (extremeBit == "*" && !rules[7]) {
				extremeBit = " ";
			}

		}
	}
}