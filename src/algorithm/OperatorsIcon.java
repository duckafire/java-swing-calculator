package calculator.algorithm;

public enum OperatorsIcon{
	ADD('+', "+"),
	SUB('\u2212', "\u2212"),
	MUL('\u00D7', "\u00D7"),
	DIV('\u00F7', "\u00F7"),
	POR('%', "%");

	private char itselfChar;
	private String itselfString;

	OperatorsIcon(char _char, String _string){
		this.itselfChar   = _char;
		this.itselfString = _string;
	}

	public char getChar(){
		return this.itselfChar;
	}

	public String getString(){
		return this.itselfString;
	}
}
