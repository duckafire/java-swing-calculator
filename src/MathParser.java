import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

import java.util.Locale;

public class MathParser{
	private Queue<Double> values;
	private Queue<Character> operators;
	private Double resultBuffer;
	private String result;

	MathParser(){
		this.values = new LinkedList<Double>();
		this.operators = new LinkedList<Character>();
	}

	public String run(Stack<NumberGroup> textOnViewer){
		this.analyzeAndSeparate(textOnViewer);
		this.executeCalculation();
		this.buildResult();
		return this.result;
	}

	private void analyzeAndSeparate(Stack<NumberGroup> textOnViewer){
		int length;
		Double douVal;
		char lastBeforeLastChar;
		String value;
		char operator;

		for(NumberGroup item : textOnViewer){
			length = item.getContent().length();
			lastBeforeLastChar = item.getContent().charAt(length - 1);

			if(lastBeforeLastChar < '0' || lastBeforeLastChar > '9'){
				value    = item.getContent().substring(0, length - 1);
				operator = lastBeforeLastChar;
			}else{
				value    = item.getContent();
				operator = '\u0000'; // top of stack
			}

			value  = value.replaceAll("[()]", "");
			douVal = Double.parseDouble(value);

			this.values.add(douVal);
			this.operators.add(operator);
		}
	}

	private void executeCalculation(){
		if(this.values.size() == 1)
			this.resultBuffer = this.values.poll();

		else if(ordenatedOperations('%', '^', Operations.POR_POW))
			this.resultBuffer = this.values.poll();

		else if(ordenatedOperations('*', '/', Operations.MUL_DIV))
			this.resultBuffer = this.values.poll();

		else if(ordenatedOperations('+', '-', Operations.ADD_SUB))
			this.resultBuffer = this.values.poll();
	}

	private boolean ordenatedOperations(char op0, char op1, Operations core){
		Queue<Double> newValues = new LinkedList<Double>();
		Queue<Character> newOperators = new LinkedList<Character>();
		
		Double    n0 = this.values.poll();
		Character op = this.operators.poll();
		Double    n1 = this.values.poll();

		if(n1 == null){
			this.resultBuffer = n0;
			return true;
		}

		while(true){
			if(op == op0 || op == op1){
				n0 = core.calculate(n0, n1, (op == op0));

			}else{
				newValues.add(n0);
				newOperators.add(op);
				n0 = n1;
			}

			op = this.operators.poll();
			n1 = this.values.poll();

			if(n1 == null){
				newValues.add(n0);
				break;
			}
		}

		this.values    = newValues;
		this.operators = newOperators;

		if(newOperators.isEmpty()){
			this.resultBuffer = newValues.peek();
			return true;
		}

		return false;
	}

	private void buildResult(){
		if(this.resultBuffer % 1 == 0){
			Integer integer = this.resultBuffer.intValue();
			this.result = integer.toString();

			return;
		}

		String string = String.format(Locale.US, "%.6f", this.resultBuffer);
		int i = string.length() - 1;

		for(; i > -1; i--)
			if(string.charAt(i) != '0')
				break;

		this.result = string.substring(0, i + 1);
	}
}
