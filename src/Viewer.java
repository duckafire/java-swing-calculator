import java.util.Stack;
import java.util.Queue;
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

			if(last.isFinished())
				throw new EmptyStackException();

		}catch(EmptyStackException e){
			if((isOperator && !content.equals("-")) || content.equals("0"))
				return;

			this.operations.push(new NumberGroup(content, (content.equals("-"))));
			this.incrementViewerText(content, (content.equals(".")), true);
			this.operations.peek().debug(); // DEBUG
			return;
		}

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

		last.debug(); // DEBUG
	}

	public void decrementTextField(){
		if(this.getText().isEmpty())
			return;

		if(this.getText().equals("(-") || this.getText().length() == 1){
			this.setText("");
			this.operations.clear();
			return;
		}

		int lastId = this.getText().length() - 1;
		String newText = this.getText().substring(0, lastId);
		this.setText(newText);

		if(this.operations.peek().decrement())
			this.operations.pop();

		this.operations.peek().debug();
	}

	public void operationResult(){
		Queue returned = this.parser.run(this.operations);
		Boolean isNegative = (Boolean)returned.poll();
		String result = (String)returned.poll();

		this.operations.clear();
		this.setText("");

		if(result.equals("0")){ // gambiarra
			this.incrementTextField(".", false);
			this.decrementTextField();
			return;
		}

		if(isNegative)
			this.incrementTextField("-", true);

		for(char c : result.toCharArray())
			this.incrementTextField(Character.toString(c), false);
	}

	public void clearAll(){
		System.out.println("[DEBUG] Removed operations:");
		for(NumberGroup item : this.operations)
			item.debug();
		System.out.println();

		this.operations.clear();
		this.setText("");
	}
}
