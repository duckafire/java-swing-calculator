import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FloatPointButton extends AdaptableButtonFont{
	FloatPointButton(String text, Color color, JTextField _viewer){
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
		System.out.println("Work in progress");
		//String newText = this.viewer.getText() + ".";
		//this.viewer.setText(newText);
	}
}
