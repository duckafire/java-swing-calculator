package calculator.algorithm;

import java.util.LinkedList;

import java.util.Locale;

public class FormatValues{
	public FormatValues(){
	}

	public String dataToViewer(LinkedList<?>[] data){
		if(data[0].size() == 0)
			return "";

		String operation = "";

		LinkedList<?> values    = data[0];
		LinkedList<?> operators = data[1];

		Value    val = (Value)values.pollFirst();
		Operator ope = (Operator)operators.pollFirst();

		while(val != null){
			operation += val.getContent(true);

			if(ope != null)
				operation += Character.toString(ope.getId());

			val = (Value)values.pollFirst();
			ope = (Operator)operators.pollFirst();
		}

		return operation;
	}

	public String adjustDecimalLength(Double result, String floatPointLength){
		if(result.doubleValue() % 1 == 0)
			return Integer.toString(result.intValue());

		String format = "%." + floatPointLength + "f";
		String string = String.format(Locale.US, format, result);
		int i = string.length() - 1;

		for(; i > -1; i--)
			if(string.charAt(i) != '0')
				break;

		return string.substring(0, i + 1);
	}
}
