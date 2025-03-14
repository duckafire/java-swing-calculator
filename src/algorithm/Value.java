package calculator.algorithm;

public class Value{
	private boolean negativeValue;
	private boolean floatValue;
	private String preComma;
	private String posComma;

	public Value(Integer value){
		this.negativeValue = false;
		this.floatValue    = false;

		this.preComma = "";
		this.posComma = "";

		this.increment(value);
	}

	public boolean isEqualZero(){
		return (this.preComma.equals("0") && !this.floatValue);
	}

	public void setFloatValue(){
		this.floatValue = true;
	}

	public boolean isFloatValue(){
		return this.floatValue;
	}

	public void invertValueSignal(){
		this.negativeValue = !this.negativeValue;
	}

	public byte hasDecimalContent(){
		if(!this.floatValue)
			return -1;

		if(this.posComma.isEmpty())
			return 0;

		return 1;
	}

	public String getContent(boolean negativeParentesis){
		String value = "";

		if(negativeParentesis && this.negativeValue)
			value += "(";

		if(this.negativeValue)
			value += "-";

		if(!this.preComma.isEmpty())
			value += this.preComma;

		if(this.floatValue)
			value += ".";

		if(!this.posComma.isEmpty())
			value += this.posComma;

		if(value.isEmpty() || value.equals("-") || value.equals("(-"))
			return null;

		if(negativeParentesis && this.negativeValue)
			value += ")";

		return value;
	}

	public void increment(Integer value){
		if(!this.floatValue){
			if(this.preComma.equals("0"))
				this.preComma = "";

			this.preComma += Integer.toString(value);

		}else{
			this.posComma += Integer.toString(value);
		}
	}

	public boolean decrement(){
		if(!this.floatValue){
			this.preComma = this.preComma.substring(0, this.preComma.length() - 1);

		}else{
			if(this.posComma.isEmpty())
				this.floatValue = false;
			else
				this.posComma = this.posComma.substring(0, this.posComma.length() - 1);
		}

		// if true, remove it
		return this.preComma.isEmpty();
	}
}
