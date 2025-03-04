import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OperatorButton extends AdaptableButtonFont{
	OperatorButton(String text, Color color, JTextField _viewer){
		super(text, color, _viewer);

		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				action(e);
			}
		});
	}

	@Override
	protected void action(ActionEvent e){
		if(this.viewer.getText().isEmpty() || !isMathOperator(e.getActionCommand().charAt(0)) || isMathOperator(this.getViewerLastChar()))
			return;

		String newText = this.viewer.getText() + e.getActionCommand();
		this.viewer.setText(newText);
	}

	private boolean isMathOperator(char c){
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^';
	}
}
