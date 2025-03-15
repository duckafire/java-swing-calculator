package calculator.algorithm;

import java.util.LinkedList;

public class DataManager{
	private LinkedList<Value> values;
	private LinkedList<Operator> operators;

	private boolean lastIsOperator;

	public DataManager(){
		this.lastIsOperator = false;
		this.values    = new LinkedList<Value>();
		this.operators = new LinkedList<Operator>();
	}

	public LinkedList<?>[] getData(){
		LinkedList<?>[] data = new LinkedList<?>[2];

		data[0] = (LinkedList<?>)this.values.clone();
		data[1] = (LinkedList<?>)this.operators.clone();

		return data;
	}

	public boolean increment(Double value){
		if(value.doubleValue() % 1 == 0)
			return this.increment(value.intValue());

		String stringValue = value.toString();
		final int floatPointPosition = stringValue.indexOf('.');
		int   scientificNotationChar = stringValue.indexOf('E');

		if(scientificNotationChar == -1)
			scientificNotationChar = stringValue.length();

		Integer prePoint = Integer.parseInt(stringValue.substring(0, floatPointPosition));
		Integer posPoint = Integer.parseInt(stringValue.substring(floatPointPosition + 1, scientificNotationChar));

		return (this.increment(prePoint) && this.increment(".") && this.increment(posPoint));
	}
	
	public boolean increment(Integer value){
		if(!this.lastIsOperator && this.values.size() > 0 && this.values.getLast().isEqualZero() && value.equals(0))
			return false;

		final boolean negativeValue = (value < 0);
		if(negativeValue)
			value *= -1;

		if(this.lastIsOperator || this.values.size() == 0)
			this.values.add(new Value(value));
		else
			this.values.getLast().increment(value);

		if(negativeValue)
			this.increment("\u00B1");

		this.lastIsOperator = false;
		return true;
	}

	public boolean increment(Operator operator){
		if(this.values.size() == 0)
			return false;

		if(this.values.getLast().hasDecimalContent() == 0)
			this.increment(0);

		if(this.lastIsOperator)
			this.operators.pollLast();

		this.operators.add(operator);
		this.lastIsOperator = true;

		return true;
	}

	public boolean increment(String special){
		if(this.values.size() == 0 || this.lastIsOperator)
			return false;

		if(special.equals(".") && !this.values.getLast().isFloatValue()){
			this.values.getLast().setFloatValue();
			return true;
		}

		if(special.equals("\u00B1")){
			this.values.getLast().invertValueSignal();
			return true;
		}

		return false;
	}

	public boolean decrement(){
		if(this.values.isEmpty())
			return false;

		if(!this.lastIsOperator){
			if(this.values.getLast().decrement()){
				this.values.pollLast();

				if(!this.values.isEmpty())
					this.lastIsOperator = true;
			}
		
		}else if(!this.operators.isEmpty()){
			this.operators.pollLast();

			this.lastIsOperator = false;
		}

		return true;
	}

	public void clearAll(){
		this.values.clear();
		this.operators.clear();
		this.lastIsOperator = false;
	}
}
