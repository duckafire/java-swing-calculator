package calculator.presentation;

import calculator.algorithm.OperatorsIcon;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.Color;

@SuppressWarnings("serial")
public class Keyboard extends JPanel{
	private List<Key> buttons;

	public Keyboard(){
		super(new GridLayout(5, 4));

		String[] texts = {
			"C", "\u00B1", OperatorsIcon.PER.getString(), OperatorsIcon.DIV.getString(),
			"7", "8",      "9",                           OperatorsIcon.MUL.getString(),
			"4", "5",      "6",                           OperatorsIcon.SUB.getString(),
			"1", "2",      "3",                           OperatorsIcon.ADD.getString(),
			".", "0",      "\u232B",                      "=",
		};

		KeyIndex[] ids = {
			KeyIndex.CLEAR_ALL,         KeyIndex.INCREMENT_SPECIAL, KeyIndex.INCREMENT_OPERATOR, KeyIndex.INCREMENT_OPERATOR,
			KeyIndex.INCREMENT_NUMBER,  KeyIndex.INCREMENT_NUMBER,  KeyIndex.INCREMENT_NUMBER,   KeyIndex.INCREMENT_OPERATOR,
			KeyIndex.INCREMENT_NUMBER,  KeyIndex.INCREMENT_NUMBER,  KeyIndex.INCREMENT_NUMBER,   KeyIndex.INCREMENT_OPERATOR,
			KeyIndex.INCREMENT_NUMBER,  KeyIndex.INCREMENT_NUMBER,  KeyIndex.INCREMENT_NUMBER,   KeyIndex.INCREMENT_OPERATOR,
			KeyIndex.INCREMENT_SPECIAL, KeyIndex.INCREMENT_NUMBER,  KeyIndex.DECREMENT_ONE,      KeyIndex.GET_RESULT,
		};

		this.buttons = new ArrayList<Key>();
		Key newItem;

		// top-left to bottom-right
		for(byte i = 0; i < 20; i++){
			newItem = new Key(texts[i], ids[i]);
			this.buttons.add(newItem);
			this.add((JButton)newItem);
		}
	}

	public void updateButtonsFontSize(){
		for(Key item : this.buttons)
			item.updateButtonFontSize();
	}

	public List<Key> getButtons(){
		return Collections.unmodifiableList(this.buttons);
	}
}
