import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Calculator extends JFrame{
	private final int defaultWindowWidth = 500;
	private final int defaultWindowHeight = 700;

	private JPanel mainPanel, display, keyboard;
	private JButton[] buttons;
	private JTextField viewer;
	private final byte totalButtons = 20;

	public Calculator(){
		super("Java swing calculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(this.defaultWindowWidth, this.defaultWindowHeight);
		this.setMinimumSize(new Dimension(220, 230));

		this.mainPanel = new JPanel(new BorderLayout(4, 4));

		this.createDisplay();
		this.createKeyboard();

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				resizeDisplay();
				resizeButtonsFont();
			}
		});

		this.mainPanel.add(this.display,  BorderLayout.NORTH);
		this.mainPanel.add(this.keyboard, BorderLayout.CENTER);
		this.add(this.mainPanel);
		this.setVisible(true);
	}

	private void createDisplay(){
		this.display = new JPanel();
		this.display.setPreferredSize(new Dimension(this.defaultWindowWidth, (int)(this.defaultWindowHeight / 5)));

		this.viewer = new JTextField("0");
		this.viewer.setPreferredSize(this.display.getPreferredSize());
		this.viewer.setFont(this.viewer.getFont().deriveFont((float)(this.display.getPreferredSize().height * 0.75)));
		this.viewer.setEditable(false);
		this.viewer.setHorizontalAlignment(JTextField.RIGHT);
		this.viewer.setBackground(Color.GRAY);

		this.display.add(this.viewer);
	}

	private void resizeDisplay(){
		Dimension windowSize = getSize();

		this.display.setPreferredSize(new Dimension(windowSize.width, (int)(windowSize.height / 5)));
		this.display.revalidate();
		this.display.repaint();

		this.viewer.setPreferredSize(this.display.getPreferredSize());
		this.viewer.setFont(this.viewer.getFont().deriveFont((float)(this.display.getPreferredSize().height * 0.75)));
		this.viewer.revalidate();
		this.viewer.repaint();
	}

	private void createKeyboard(){
		this.keyboard = new JPanel(new GridLayout(5, 4));

		// "\u232B" icon of the delete key
		String[] text = {
			"C", "^", "%", "/",
			"7", "8", "9", "*",
			"4", "5", "6", "-",
			"1", "2", "3", "+",
			".", "0", "\u232B", "=",
		};

		this.buttons = new JButton[ this.totalButtons ];

		for(byte i = 0; i < this.totalButtons; i++){
			this.buttons[i] = new JButton(text[i]);
			this.buttons[i].setBackground(Color.GRAY);
			this.keyboard.add(this.buttons[i]);
		}

		resizeButtonsFont();
	}

	private void resizeButtonsFont(){
		Dimension dim;
		int width, height;

		for(byte i = 0; i < this.totalButtons; i++){
			dim = this.buttons[i].getSize();

			width  = (int)(dim.width  * 0.25);
			height = (int)(dim.height * 0.25);

			this.buttons[i].setFont(this.buttons[i].getFont().deriveFont((float)((width < height) ? width : height)));
		}
	}
}
