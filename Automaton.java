
class Automaton {
	// class constants
	public final static int MAX_DISPLAY_WIDTH = 121;

	// private members
	private boolean rules[]; // allocate rules[8] in constructor!
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
	}

	//constructor overloader
	public Automaton()
	{
		//defaults to rule 0
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

	public boolean setDisplayWidth(int width) {

		if (width > MAX_DISPLAY_WIDTH) {
			return false;
		}
		displayWidth = width;
		return true;
	}

	public String toStringCurrentGen() {

		String autoString = thisGen;
		String emptyString = " ";
		int emptySpaces = (displayWidth - thisGen.length()/2);

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

		String newGenerationString = " ";
		int binaryIndex = 0;

		thisGen = extremeBit + extremeBit + thisGen + extremeBit + extremeBit;

		// propagate nextGen
		for (int i = 1; i < thisGen.length() - 1; i++) {

			binaryIndex = 0;
			int base = 4;

			for (int k = 0; k < 3; k++) {

				if (thisGen.charAt(i - 1 + k) == '*') {
					binaryIndex += base;
					base = base / 2;
				}
				if (rules[binaryIndex]) {
					newGenerationString = newGenerationString + '*';

				} else {
					newGenerationString = newGenerationString + " ";
				}
			}
			// extreme bit rule check
			if (extremeBit == " " && rules[0]) {
				extremeBit = "*";
			}
			if (extremeBit == "*" && !rules[7]) {
				extremeBit = " ";
			}
			// transfer the generation
			thisGen = newGenerationString;
		}
	}
}