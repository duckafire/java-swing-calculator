import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

import java.util.Locale;

public class MathParser{
	private Queue<Double> values;
	private Queue<Character> operators;
	private Queue<Object> result;

	MathParser(){
		this.values = new LinkedList<Double>();
		this.operators = new LinkedList<Character>();
	}

	public Queue run(Stack<NumberGroup> textOnViewer){
		this.analyze(textOnViewer);
		this.buildResult( this.executeCalculation() );
		return this.result;
	}

	private void analyze(Stack<NumberGroup> textOnViewer){
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

	private Double executeCalculation(){
		Character operator;
		Double resultBuffer;

		resultBuffer = this.values.poll();
		operator = this.operators.poll();

		try{
			while(true){
				if(operator.equals('\u0000'))
					break;

				else if(operator.equals('+'))
					resultBuffer += this.values.poll();

				else if(operator.equals('-'))
					resultBuffer -= this.values.poll();

				else if(operator.equals('*'))
					resultBuffer *= this.values.poll();

				else if(operator.equals('/'))
					resultBuffer /= this.values.poll();

				else if(operator.equals('%'))
					resultBuffer *= ((double)(this.values.poll() / 100));

				else if(operator.equals('^'))
					resultBuffer = Math.pow(resultBuffer, this.values.poll());

				operator = this.operators.poll();
			}
		}catch(NullPointerException e){}

		return resultBuffer;
	}

	private void buildResult(Double resultBuffer){
		this.result = new LinkedList<Object>();

		this.result.add((resultBuffer < 0));

		if(resultBuffer % 1 == 0){
			Integer tmp = resultBuffer.intValue();
			this.result.add(tmp.toString());

			return;
		}

		String string = String.format(Locale.US, "%.6f", resultBuffer);
		int i = string.length() - 1;

		for(; i > -1; i--)
			if(string.charAt(i) != '0')
				break;

		this.result.add(string.substring(0, i + 1));
	}
}
