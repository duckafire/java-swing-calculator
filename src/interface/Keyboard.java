import java.util.List;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.Color;

public class Keyboard extends JPanel{
	private List<Key> buttons;

	Keyboard(){
		super(new GridLayout(5, 4));

		// "\u232B" icon of the delete key
		String[] text = {
			"C", "^", "%", "/",
			"7", "8", "9", "*",
			"4", "5", "6", "-",
			"1", "2", "3", "+",
			".", "0", "\u232B", "=",
		};

		this.buttons = new ArrayList<Key>();

		Key newItem;

		for(byte i = 0; i < 20; i++){
			newItem = new Key(text[i]);
			this.buttons.add(newItem);
			this.add((JButton)newItem);
		}
	}

	public void updateButtonsFontSize(){
		for(Key item : this.buttons)
			item.updateButtonFontSize();
	}
}
