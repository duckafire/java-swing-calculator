// [-] <n> [operator]
public class NumberGroup{
	private boolean negativeValue = false;
	private boolean floatNumber = false;
	private boolean finished = false;

	private String content = "";
	private char lastChar = '\u0000';

	NumberGroup(String _content, boolean _isNegative){
		this.negativeValue = _isNegative;
		
		if(_content.equals("."))
			this.increment("0", false);

		else if(_content.equals("-"))
			this.increment("(", false);

		this.increment(_content, false);
	}

	public void debug(){
		System.out.printf("[\"%s\"\t'%c'\t%b\t%b\t%b]\n",
					this.content,
					this.lastChar,
					this.negativeValue,
					this.floatNumber,
					this.finished
				);
	}

	public char getLastChar(){
		return this.lastChar;
	}

	public boolean isNegative(){
		return this.negativeValue;
	}

	public boolean isFloatNumber(){
		return this.floatNumber;
	}

	public boolean isFinished(){
		return this.finished;
	}

	public byte addEndParentesis(){
		if(!this.negativeValue)
			return -1;

		byte returnValue = 0;

		if(this.lastChar == '.'){
			this.increment("0", false);
			returnValue = 1;
		}

		this.increment(")", false);
		return returnValue;
	}

	public void setFinished(boolean enable){
		if(enable)
			this.finished = true;
	}

	public boolean increment(String _content, boolean fillOnRight){
		if(fillOnRight || (_content.equals(".") && this.content.equals("(-")))
			this.increment("0", false);

		if(_content.equals(".")){
			if(this.floatNumber)
				return false;
			else
				this.floatNumber = true;
		}


		this.lastChar = _content.charAt(0);
		this.content += _content;

		return true;
	}

	public boolean decrement(){
		int newLastId = this.content.length() - 2;
		String newContent = this.content.substring(0, newLastId);

		this.content  = newContent;
		this.lastChar = newContent.charAt(newLastId);

		if(this.finished)
			this.finished = false;

		else if(this.lastChar == '.')
			this.floatNumber = false;

		// if `true`, remove it
		return this.content.isEmpty();
	}
}
