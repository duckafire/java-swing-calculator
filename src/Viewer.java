import java.util.Stack;
import java.util.EmptyStackException;

import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Color;

public class Viewer extends JTextField{
	private MathParser parser;
	private Stack<NumberGroup> operations;

	Viewer(){
		super("");

		this.setBackground(Color.GRAY);
		this.setEditable(false);
		this.setPreferredSize(this.getPreferredSize());
		this.setHorizontalAlignment(JTextField.RIGHT);

		this.parser = new MathParser();
		this.operations = new Stack<NumberGroup>();
	}

	public void updateViewerSize(Display display){
		float fontSize = (float)(display.getPreferredSize().height * 0.75);
		Font updatedFont = this.getFont().deriveFont(fontSize);

		this.setFont(updatedFont);
	}

	private void incrementViewerText(String content, boolean fillWithZero, boolean newGroup){
		if(fillWithZero)
			content = "0" + content;
		else if(newGroup && content.equals("-"))
			content = "(" + content;

		String newText = this.getText() + content;
		this.setText(newText);
	}

	public void incrementTextField(String content, boolean isOperator){
		NumberGroup last;

		try{
			last = this.operations.peek();

			if(this.operations.size() == 1 && last.getContent().equals("0")){
				this.decrementTextField();
				throw new EmptyStackException();
			}

			if(last.isFinished())
				throw new EmptyStackException();

		}catch(EmptyStackException e){
			if((isOperator && !content.equals("-")) || content.equals("0"))
				return;

			this.operations.push(new NumberGroup(content, (content.equals("-"))));
			this.incrementViewerText(content, (content.equals(".")), true);
			return;
		}

		if(content.equals("0") && last.getContent().equals("0"))
			return;

		if(last.getContent().matches("[\\(\\-]*0"))
			this.decrementTextField();

		// before increment
		final char lastChar = last.getLastChar();
		final boolean fillWithZero = (isOperator && lastChar == '.');

		boolean isNegativeFloat = false;

		if(isOperator){
			if(lastChar == '-')
				return;

			byte returnedValue = last.addEndParentesis();
			isNegativeFloat = (returnedValue == 1);

			if(returnedValue != -1)
				this.incrementViewerText(")", (lastChar == '.'), false);
		}

		if(last.increment(content, (!isNegativeFloat && fillWithZero)))
			this.incrementViewerText(content, ((lastChar == '-' && content.equals(".")) || fillWithZero), false);

		if(isOperator)
			last.setFinished(true);
	}

	public void decrementTextField(){
		if(this.getText().isEmpty())
			return;

		if(this.getText().equals("(-") || this.getText().length() == 1){
			this.setText("");
			this.operations.clear();
			return;
		}

		int length = this.getText().length();
		String newText = this.getText().substring(0, length - 1);

		if(newText.charAt(length - 2) == '(')
			newText = newText.substring(0, length - 2);

		this.setText(newText);

		if(this.operations.peek().decrement())
			this.operations.pop();

		if(this.operations.peek().getLastChar() == ')')
			this.decrementTextField();
	}

	public void operationResult(){
		if(this.getText().isEmpty())
			return;

		String result = this.parser.run(this.operations);

		this.operations.clear();
		this.setText("");

		if(result.equals("0")){ // gambiarra
			this.incrementTextField(".", false);
			this.decrementTextField();
			return;
		}

		for(char c : result.toCharArray())
			this.incrementTextField(Character.toString(c), false);
	}

	public void clearAll(){
		this.operations.clear();
		this.setText("");
	}
}
