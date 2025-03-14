package calculator;

import calculator.presentation.*;
import calculator.algorithm.DataManager;
import calculator.algorithm.Operator;
import calculator.algorithm.FormatValues;
import calculator.algorithm.MathParser;
import calculator.algorithm.OperatorsIcon;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@SuppressWarnings("serial")
public class Calculator extends JFrame{
	private final int defaultWindowWidth = 500;
	private final int defaultWindowHeight = 700;

	private JPanel mainPanel;
	private Display display;
	private Keyboard keyboard;
	private DataManager data;
	private FormatValues format;
	private MathParser parser;

	public Calculator(){
		super("Java Swing Calculator");
		System.out.println("Java Swing Calculator | v0.0.2 | by DuckAfire | MIT License");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(this.defaultWindowWidth, this.defaultWindowHeight);
		this.setMinimumSize(new Dimension(220, 230));

		this.mainPanel = new JPanel(new BorderLayout(4, 4));

		this.display  = new Display(this.defaultWindowWidth, this.defaultWindowHeight);
		this.keyboard = new Keyboard();
		this.data     = new DataManager();
		this.format   = new FormatValues();
		this.parser   = new MathParser();

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				display.updateSize(getSize());
				keyboard.updateButtonsFontSize();
			}
		});

		for(Key key : this.keyboard.getButtons()){
			if(key.getId() == KeyIndex.INCREMENT_NUMBER){
				key.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						if(data.increment(Integer.parseInt(key.getText())))
							display.updateViewer(format.dataToViewer(data.getData()));
					}
				});
			
			}else if(key.getId() == KeyIndex.INCREMENT_OPERATOR){
				key.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						Operator keyMathOperator = null;

						if(key.getText().equals(OperatorsIcon.ADD.getString()))
							keyMathOperator = Operator.ADD;

						else if(key.getText().equals(OperatorsIcon.SUB.getString()))
							keyMathOperator = Operator.SUB;

						else if(key.getText().equals(OperatorsIcon.MUL.getString()))
							keyMathOperator = Operator.MUL;

						else if(key.getText().equals(OperatorsIcon.DIV.getString()))
							keyMathOperator = Operator.DIV;

						else if(key.getText().equals(OperatorsIcon.PER.getString()))
							keyMathOperator = Operator.PER;

						if(data.increment(keyMathOperator))
							display.updateViewer(format.dataToViewer(data.getData()));
					}
				});

			}else if(key.getId() == KeyIndex.INCREMENT_SPECIAL){
				key.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						if(data.increment(key.getText()))
							display.updateViewer(format.dataToViewer(data.getData()));
					}
				});
			}else if(key.getId() == KeyIndex.DECREMENT_ONE){
				key.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						if(data.decrement())
							display.updateViewer(format.dataToViewer(data.getData()));
					}
				});
			}else if(key.getId() == KeyIndex.CLEAR_ALL){
				key.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						data.clearAll();
						display.updateViewer("");
					}
				});
			}else if(key.getId() == KeyIndex.GET_RESULT){
				key.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						String stringResult = parser.runAlgorithm(data.getData());
						if(stringResult == null)
							return;

						Double doubleResult   = Double.parseDouble(stringResult);
						String formatedResult = format.adjustDecimalLength(doubleResult, "6");

						data.clearAll();

						if(formatedResult.indexOf('.') == -1)
							data.increment(Integer.parseInt(formatedResult));
						else
							data.increment(Double.parseDouble(formatedResult));

						display.updateViewer(format.dataToViewer(data.getData()));
					}
				});
			}
		}

		this.mainPanel.add(this.display,  BorderLayout.NORTH);
		this.mainPanel.add(this.keyboard, BorderLayout.CENTER);
		this.add(this.mainPanel);
		this.setVisible(true);
	}
}
