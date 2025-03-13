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
	
	public boolean increment(Integer value){
		if(this.values.size() > 0 && this.values.getLast().isEqualZero() && value.equals(0))
			return false;

		if(this.lastIsOperator || this.values.size() == 0)
			this.values.add(new Value(value, null));
		else
			this.values.getLast().increment(value);

		this.lastIsOperator = false;
		return true;
	}

	public boolean increment(Operator operator){
		if(this.values.size() == 0 ||
			(this.values.size() == 1 && this.values.getLast().isEqualZero()))
			return false;

		if(this.lastIsOperator)
			this.operators.pollLast();

		this.operators.add(operator);
		this.lastIsOperator = true;

		return true;
	}

	public boolean increment(String special){
		if(this.values.size() == 0)
			return false;

		if(special.equals(".") && !this.values.getLast().isFloatValue()){
			this.values.getLast().setFloatValue();
			return true;
		}

		if(special.equals("-")){
			this.values.getLast().invertValueSignal();
			return true;
		}

		return false;
	}

	public boolean decrement(){
		if(this.values.isEmpty())
			return false;

		if(!this.lastIsOperator){
			if(this.values.getLast().decrement())
				this.values.pollLast();
		
		}else if(!this.operators.isEmpty()){
			this.operators.pollLast();
		}

		return true;
	}
}
