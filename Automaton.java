
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

		// set displayWidth integer for new rule
		if (setRule(new_rule)) {
			displayWidth = MAX_DISPLAY_WIDTH;
		}
		resetFirstGen();
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
		} else {

		}
		for (int i = 0; i < rules.length; i++) {

			if (new_rule % 2 == 1) {
				rules[i] = true;
			} else {
				rules[i] = false;
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
		int emptySpaces = (displayWidth - thisGen.length());

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
			//adjusts and centers displayWidth
			autoString.substring(-emptySpaces, thisGen.length() + emptySpaces);
		}
			return autoString;
	}

	public void propagateNewGeneration() {
		String nextGen;
		String storageString;
		int binaryIndex = 0;
		
		thisGen = extremeBit + extremeBit + thisGen + extremeBit + extremeBit;
		
		//propagate nextGen
		for (int i = 1; i < thisGen.length() - 1; i++)
		{
			binaryIndex = 0;
			int k = 4;
			
		}
		
	}
}