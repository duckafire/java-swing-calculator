package calculator.algorithm;

import java.util.LinkedList;

public class MathParser{
	private LinkedList<Double> values;
	private LinkedList<Operator> operators;

	private Double resultBuffer;

	public MathParser(){
	}

	public String runAlgorithm(LinkedList<?>[] data){
		if(data[0].size() == 0)
			return null;

		this.extractData(data);
		this.executeCalculation();

		return this.resultBuffer.toString();
	}

	private void extractData(LinkedList<?>[] data){
		this.values    = new LinkedList<Double>();
		this.operators = new LinkedList<Operator>();

		for(Object item : data[0])
			this.values.add(Double.parseDouble( ((Value)item).getContent(false) ));

		for(Object item : data[1])
			this.operators.add((Operator)item);
	}

	private void executeCalculation(){
		if(this.ordenatedOperations('%', '%'))
			this.resultBuffer = this.values.pollFirst();

		else if(this.ordenatedOperations('*', '/'))
			this.resultBuffer = this.values.pollFirst();

		else if(this.ordenatedOperations('+', '-'))
			this.resultBuffer = this.values.pollFirst();
	}

	private boolean ordenatedOperations(char op0, char op1){
		LinkedList<Double>   newValues    = new LinkedList<Double>();
		LinkedList<Operator> newOperators = new LinkedList<Operator>();

		Double   n0 = this.values.pollFirst();
		Operator op = this.operators.pollFirst();
		Double   n1 = this.values.pollFirst();

		if(n1 == null){
			this.resultBuffer = n0;
			return true;
		}

		while(true){
			if(op.getId() == op0 || op.getId() == op1){
				n0 = op.calculate(n0, n1);
			
			}else{
				newValues.add(n0);
				newOperators.add(op);
				n0 = n1;
			}

			op = this.operators.pollFirst();
			n1 = this.values.pollFirst();

			if(n1 == null){
				newValues.add(n0);
				break;
			}
		}

		this.values    = newValues;
		this.operators = newOperators;

		if(newOperators.isEmpty()){
			this.resultBuffer = this.values.peek();
			return true;
		}

		return false;
	}
}
