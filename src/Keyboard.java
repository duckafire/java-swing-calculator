import java.util.List;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.Color;

public class Keyboard extends JPanel{
	private List<Object> buttons;

	Keyboard(JTextField _viewer){
		super(new GridLayout(5, 4));

		// "\u232B" icon of the delete key
		String[] text = {
			"C", "^", "%", "/",
			"7", "8", "9", "*",
			"4", "5", "6", "-",
			"1", "2", "3", "+",
			".", "0", "\u232B", "=",
		};

		buttons = new ArrayList<Object>();

		for(byte i = 0; i < 20; i++){
			if(i == 0)
				this.buttons.add(new ClearButton(text[i], Color.GRAY, _viewer));

			else if(i == 16)
				this.buttons.add(new FloatPointButton(text[i], Color.GRAY, _viewer));

			else if(i == 18)
				this.buttons.add(new DeleteButton(text[i], Color.GRAY, _viewer));

			else if(i == 19)
				this.buttons.add(new ResultButton(text[i], Color.GRAY, _viewer));

			else if((i > 0 && i < 4) || (i < 16 && (i + 1) % 4 == 0))
				this.buttons.add(new OperatorButton(text[i], Color.GRAY, _viewer));

			else
				this.buttons.add(new NumberButton(text[i], Color.GRAY, _viewer));
			
			this.add((JButton)this.buttons.get(i));
		}
	}

	public void updateButtonsFontSize(){
		for(Object item : this.buttons)
			((AdaptableButtonFont)item).updateButtonFontSize();
	}
}
