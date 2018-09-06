package package_g;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

import java.awt.EventQueue;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.Locale;

//Test kommentar
public class STR_Calculator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField earthTimeTextField;
	private JTextField lengthTextField;
	private JTextField timeAnswerTextField;
	private JTextField lengthAnswerTextField;
	private JTextField speedTextField;
	
	String sep = System.getProperty("line.separator");
	
	double tStill;
	double t;
	double lStill;
	double l;
	double gamma;
	double v;
	double c = 299792.458;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					STR_Calculator frame = new STR_Calculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public STR_Calculator() {
		setIconImage(new ImageIcon(this.getClass().getResource("/icon_Newton.jpg")).getImage());

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 516);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton menuButton = new JButton(Language.getString("STR_Calculator.menuButton.text", "Menu")); //$NON-NLS-1$ //$NON-NLS-2$
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane, "menu");
			}
		});
		menuBar.add(menuButton);
		
		JButton calcButton = new JButton(Language.getString("STR_Calculator.calcButton.text", "Calculator")); //$NON-NLS-1$ //$NON-NLS-2$
		calcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane, "calc");
			}
		});
		menuBar.add(calcButton);
		
		JButton infoButton = new JButton(Language.getString("STR_Calculator.infoButton.text", "Info")); //$NON-NLS-1$ //$NON-NLS-2$
		infoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane, "info");
			}
		});
		menuBar.add(infoButton);
		
		JButton logButton = new JButton(Language.getString("STR_Calculator.btnLogg.text", "Logg")); //$NON-NLS-1$ //$NON-NLS-2$
		logButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane, "logScroll");
			}
		});
		menuBar.add(logButton);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel menuPanel = new JPanel();
		contentPane.add(menuPanel, "menu");
		
		JButton btnNewButton = new JButton(Language.getString("STR_Calculator.btnNewButton.text", "New button")); //$NON-NLS-1$ //$NON-NLS-2$
		menuPanel.add(btnNewButton);
		
		JPanel calcPanel = new JPanel();
		contentPane.add(calcPanel, "calc");
		calcPanel.setLayout(null);
		
		JLabel timeInvalidLabel = new JLabel(Language.getString("STR_Calculator.timeInvalidLabel.text", "")); //$NON-NLS-1$ //$NON-NLS-2$
		timeInvalidLabel.setForeground(Color.RED);
		timeInvalidLabel.setBounds(0, 72, 130, 14);
		calcPanel.add(timeInvalidLabel);
		
		JLabel lengthInvalidLabel = new JLabel(Language.getString("STR_Calculator.lengthInvalidLabel.text", "")); //$NON-NLS-1$ //$NON-NLS-2$
		lengthInvalidLabel.setForeground(Color.RED);
		lengthInvalidLabel.setBounds(165, 72, 130, 14);
		calcPanel.add(lengthInvalidLabel);
		
		earthTimeTextField = new JTextField();
		earthTimeTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
			Double.parseDouble(earthTimeTextField.getText());
			timeInvalidLabel.setText("");
		} catch (NumberFormatException e1) {
			timeInvalidLabel.setText("Endast siffror och punkt");
		}
			}
		});
		
		JLabel speedInvalidLabel = new JLabel("");
		speedInvalidLabel.setForeground(Color.RED);
		speedInvalidLabel.setBounds(83, 184, 130, 14);
		calcPanel.add(speedInvalidLabel);
		
		earthTimeTextField.setToolTipText(Language.getString("STR_Calculator.earthTimeTextField.toolTipText", "T.ex 5 år")); //$NON-NLS-1$ //$NON-NLS-2$
		earthTimeTextField.setColumns(10);
		earthTimeTextField.setBounds(0, 42, 150, 31);
		calcPanel.add(earthTimeTextField);
		
		JScrollPane logScrollPane = new JScrollPane();
		contentPane.add(logScrollPane, "logScroll");
		
		JPanel logPanel = new JPanel();
		logScrollPane.setViewportView(logPanel);
		logPanel.setLayout(null);
		
		JTextPane textTime0Pane = new JTextPane();
		textTime0Pane.setBounds(0, 25, 126, 425);
		logPanel.add(textTime0Pane);
		
		JTextPane textLength0Pane = new JTextPane();
		textLength0Pane.setBounds(136, 25, 126, 425);
		logPanel.add(textLength0Pane);
		
		JTextPane textSpeedPane = new JTextPane();
		textSpeedPane.setBounds(272, 25, 126, 425);
		logPanel.add(textSpeedPane);
		
		JTextPane textTimePane = new JTextPane();
		textTimePane.setBounds(408, 25, 126, 425);
		logPanel.add(textTimePane);
		
		JTextPane textLengthPane = new JTextPane();
		textLengthPane.setBounds(544, 25, 126, 425);
		logPanel.add(textLengthPane);
		
		
		JButton answerButton = new JButton(Language.getString("STR_Calculator.answerButton.text", "Räkna ut")); //$NON-NLS-1$ //$NON-NLS-2$
		answerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Vi har en fungerande knapp");
				setParameters();
				solveGamma();
				solveTime();
				solveLength();
				showTime();
				showLength();
				logEverything();
			}

			private void setParameters() {
				if (earthTimeTextField.getText().equals("")) {
					tStill = 0;
				} else {
					tStill = Double.parseDouble(earthTimeTextField.getText());
				}
				if (lengthTextField.getText().equals("")) {
					lStill = 0;
				} else {
					lStill = Double.parseDouble(lengthTextField.getText());
				}
				if (speedTextField.getText().equals("")) {
					v = 0;
				} else {
					v = Double.parseDouble(speedTextField.getText());
				}

			}

			private void showTime() {
				timeAnswerTextField.setText(t + "");
			}

			private void showLength() {
				lengthAnswerTextField.setText(l + "");
			}

			private void solveLength() {
				if (lengthTextField.getText().equals("")) {

				} else {
					t = tStill / gamma;
				}
				l = lStill / gamma;
			}

			private void solveTime() {
				if (earthTimeTextField.getText().equals("")) {

				} else {
					t = tStill / gamma;
				}
			}

			private void solveGamma() {
				gamma = (1 / (Math.sqrt(1 - (Math.pow(v, 2) / Math.pow(c, 2)))));
			}
			
			private void logEverything() {
			    textTime0Pane.setText(textTime0Pane.getText()+sep+tStill);
			    textLength0Pane.setText(textLength0Pane.getText()+sep+lStill);
			    textSpeedPane.setText(textSpeedPane.getText()+sep+v);
			    textTimePane.setText(textTimePane.getText()+sep+t);
			    textLengthPane.setText(textLengthPane.getText()+sep+l);
			    
			}
		});
		answerButton.setBounds(100, 240, 130, 52);
		calcPanel.add(answerButton);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(Color.ORANGE);
		canvas.setBounds(332, 184, 342, 267);
		calcPanel.add(canvas);
		
		lengthTextField = new JTextField();
		lengthTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
			Double.parseDouble(lengthTextField.getText());
			lengthInvalidLabel.setText("");
		} catch (NumberFormatException e1) {
			lengthInvalidLabel.setText("Endast siffror och punkt");
			
		}
			}
		});
		lengthTextField.setToolTipText(Language.getString("STR_Calculator.lengthTextField.toolTipText", "T.ex 30 m")); //$NON-NLS-1$ //$NON-NLS-2$
		lengthTextField.setColumns(10);
		lengthTextField.setBounds(180, 42, 150, 31);
		calcPanel.add(lengthTextField);
		
		timeAnswerTextField = new JTextField();
		timeAnswerTextField.setEditable(false);
		timeAnswerTextField.setColumns(10);
		timeAnswerTextField.setBounds(0, 305, 150, 52);
		calcPanel.add(timeAnswerTextField);
		
		lengthAnswerTextField = new JTextField();
		lengthAnswerTextField.setEditable(false);
		lengthAnswerTextField.setColumns(10);
		lengthAnswerTextField.setBounds(180, 305, 150, 52);
		calcPanel.add(lengthAnswerTextField);
		
		JSlider speedSlider = new JSlider(0, 299793, 150000);
		speedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");
					
				}
				speedTextField.setText(String.valueOf(speedSlider.getValue()));
			}
		});
		speedSlider.setValue(150000);
		speedSlider.setMaximum(299793);
		speedSlider.setBounds(0, 119, 330, 26);
		calcPanel.add(speedSlider);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText(Language.getString("STR_Calculator.textArea.text", "Tid som har\r\ngått på jorden")); //$NON-NLS-1$ //$NON-NLS-2$
		textArea.setOpaque(false);
		textArea.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		textArea.setBounds(0, 0, 130, 40);
		calcPanel.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setText(Language.getString("STR_Calculator.textArea_1.text", "Objektets längd")); //$NON-NLS-1$ //$NON-NLS-2$
		textArea_1.setOpaque(false);
		textArea_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		textArea_1.setBounds(180, 15, 130, 26);
		calcPanel.add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setText(Language.getString("STR_Calculator.textArea_2.text", "Objektets hastighet i km/s")); //$NON-NLS-1$ //$NON-NLS-2$
		textArea_2.setOpaque(false);
		textArea_2.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		textArea_2.setBounds(0, 92, 330, 22);
		calcPanel.add(textArea_2);
		
		speedTextField = new JTextField();
		speedTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent ke) {
				
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");
				}
			
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		speedTextField.setToolTipText(Language.getString("STR_Calculator.speedTextField.toolTipText", "T.ex 290000 km/s")); //$NON-NLS-1$ //$NON-NLS-2$
		speedTextField.setText(Language.getString("STR_Calculator.speedTextField.text", "150000")); //$NON-NLS-1$ //$NON-NLS-2$
		speedTextField.setColumns(10);
		speedTextField.setBounds(125, 156, 80, 25);
		calcPanel.add(speedTextField);
		
		JButton button_n1 = new JButton(Language.getString("STR_Calculator.button_n1.text", "-1")); //$NON-NLS-1$ //$NON-NLS-2$
		button_n1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())-1)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
			
		});
		button_n1.setMargin(new Insets(0, 0, 0, 0));
		button_n1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_n1.setBounds(100, 156, 25, 25);
		calcPanel.add(button_n1);
		
		JButton button_n10 = new JButton(Language.getString("STR_Calculator.button_n10.text", "-10")); //$NON-NLS-1$ //$NON-NLS-2$
		button_n10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())-10)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
		});
		button_n10.setMargin(new Insets(0, 0, 0, 0));
		button_n10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_n10.setBounds(75, 156, 25, 25);
		calcPanel.add(button_n10);
		
		JButton button_n100 = new JButton(Language.getString("STR_Calculator.button_n100.text", "-100")); //$NON-NLS-1$ //$NON-NLS-2$
		button_n100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())-100)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
		});
		button_n100.setMargin(new Insets(0, 0, 0, 0));
		button_n100.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_n100.setBounds(50, 156, 25, 25);
		calcPanel.add(button_n100);
		
		JButton button_n1000 = new JButton(Language.getString("STR_Calculator.button_n1000.text", "-1000")); //$NON-NLS-1$ //$NON-NLS-2$
		button_n1000.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())-1000)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
		});
		button_n1000.setMargin(new Insets(0, 0, 0, 0));
		button_n1000.setFont(new Font("Tahoma", Font.PLAIN, 7));
		button_n1000.setBounds(25, 156, 25, 25);
		calcPanel.add(button_n1000);
		
		JButton button_n10000 = new JButton(Language.getString("STR_Calculator.button_n10000.text", "-10000")); //$NON-NLS-1$ //$NON-NLS-2$
		button_n10000.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())-10000)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
		});
		button_n10000.setMargin(new Insets(0, 0, 0, 0));
		button_n10000.setFont(new Font("Tahoma", Font.PLAIN, 6));
		button_n10000.setBounds(0, 156, 25, 25);
		calcPanel.add(button_n10000);
		
		JButton button_p10000 = new JButton(Language.getString("STR_Calculator.button_p10000.text", "+10000")); //$NON-NLS-1$ //$NON-NLS-2$
		button_p10000.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())+10000)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
		});
		button_p10000.setMargin(new Insets(0, 0, 0, 0));
		button_p10000.setFont(new Font("Tahoma", Font.PLAIN, 6));
		button_p10000.setBounds(305, 156, 25, 25);
		calcPanel.add(button_p10000);
		
		JButton button_p1000 = new JButton(Language.getString("STR_Calculator.button_p1000.text", "+1000")); //$NON-NLS-1$ //$NON-NLS-2$
		button_p1000.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())+1000)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
		});
		button_p1000.setMargin(new Insets(0, 0, 0, 0));
		button_p1000.setFont(new Font("Tahoma", Font.PLAIN, 6));
		button_p1000.setBounds(280, 156, 25, 25);
		calcPanel.add(button_p1000);
		
		JButton button_p100 = new JButton(Language.getString("STR_Calculator.button_p100.text", "+100")); //$NON-NLS-1$ //$NON-NLS-2$
		button_p100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())+100)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
		});
		button_p100.setMargin(new Insets(0, 0, 0, 0));
		button_p100.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_p100.setBounds(255, 156, 25, 25);
		calcPanel.add(button_p100);
		
		JButton button_p10 = new JButton(Language.getString("STR_Calculator.button_p10.text", "+10")); //$NON-NLS-1$ //$NON-NLS-2$
		button_p10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())+10)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
		});
		button_p10.setMargin(new Insets(0, 0, 0, 0));
		button_p10.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_p10.setBounds(230, 156, 25, 25);
		calcPanel.add(button_p10);
		
		JButton button_p1 = new JButton(Language.getString("STR_Calculator.button_p1.text", "+1")); //$NON-NLS-1$ //$NON-NLS-2$
		button_p1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    try {
				Double.parseDouble(speedTextField.getText());
				speedInvalidLabel.setText("");
			} catch (NumberFormatException e1) {
				speedInvalidLabel.setText("Endast siffror och punkt");
				
			}
			speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())+1)));
			double value = Double.parseDouble(speedTextField.getText());
			speedSlider.setValue((int) value);
			}
		});
		button_p1.setMargin(new Insets(0, 0, 0, 0));
		button_p1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_p1.setBounds(205, 156, 25, 25);
		calcPanel.add(button_p1);
		
		
		
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
		DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
		decimalFormat.setGroupingUsed(false);
		
		JPanel infoPanel = new JPanel();
		contentPane.add(infoPanel, "info");
		infoPanel.setLayout(null);
		
		JTextArea txtrLoremIpsumDolor_1 = new JTextArea();
		txtrLoremIpsumDolor_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtrLoremIpsumDolor_1.setWrapStyleWord(true);
		txtrLoremIpsumDolor_1.setLineWrap(true);
		txtrLoremIpsumDolor_1.setText(Language.getString("STR_Calculator.txtrLoremIpsumDolor_1.text", (String) null)); //$NON-NLS-1$
		txtrLoremIpsumDolor_1.setBounds(10, 54, 335, 360);
		infoPanel.add(txtrLoremIpsumDolor_1);
		
		JTextArea txtrLngdkontraktionFungerarS = new JTextArea();
		txtrLngdkontraktionFungerarS.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtrLngdkontraktionFungerarS.setWrapStyleWord(true);
		txtrLngdkontraktionFungerarS.setLineWrap(true);
		txtrLngdkontraktionFungerarS.setText(Language.getString("STR_Calculator.txtrLngdkontraktionFungerarS.text", "Längdkontraktion fungerar så här blblblblb Lorem ipsum dolor set ametLorem ipsum dolor set ametLorem ipsum dolor set amet")); //$NON-NLS-1$ //$NON-NLS-2$
		txtrLngdkontraktionFungerarS.setBounds(361, 52, 266, 362);
		infoPanel.add(txtrLngdkontraktionFungerarS);
		
		
		
		
		
		JLabel logTime0Label = new JLabel(Language.getString("STR_Calculator.lblNewLabel.text", (String) null)); //$NON-NLS-1$
		logTime0Label.setFont(new Font("Tahoma", Font.PLAIN, 10));
		logTime0Label.setBounds(0, 0, 126, 25);
		logPanel.add(logTime0Label);
		
		JLabel logLength0Label = new JLabel(Language.getString("STR_Calculator.logLength0Label.text", "Objektets l\u00E4ngd")); //$NON-NLS-1$ //$NON-NLS-2$
		logLength0Label.setBounds(136, 0, 126, 25);
		logPanel.add(logLength0Label);
		
		JLabel logSpeedLabel = new JLabel(Language.getString("STR_Calculator.logSpeedLabel.text", "Objektets hastighet")); //$NON-NLS-1$ //$NON-NLS-2$
		logSpeedLabel.setBounds(272, 0, 126, 25);
		logPanel.add(logSpeedLabel);
		
		JLabel logTimeLabel = new JLabel(Language.getString("STR_Calculator.logTimeLabel.text", "Resultat - tid")); //$NON-NLS-1$ //$NON-NLS-2$
		logTimeLabel.setBounds(408, 0, 126, 25);
		logPanel.add(logTimeLabel);
		
		JLabel logLengthLabel = new JLabel(Language.getString("STR_Calculator.logLengthLabel.text", "Resultat - l\u00E4ngd")); //$NON-NLS-1$ //$NON-NLS-2$
		logLengthLabel.setBounds(544, 0, 126, 25);
		logPanel.add(logLengthLabel);
	}
}
