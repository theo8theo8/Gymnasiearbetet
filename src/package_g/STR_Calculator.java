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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.Locale;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ScrollPaneConstants;
import javax.swing.DropMode;
import javax.swing.Icon;

/*
 * STR_Calculator:
 * 
 * 
 * 
 */
public class STR_Calculator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField stillTimeTextField;
	private JTextField stillLengthTextField;
	private JTextField timeAnswerTextField;
	private JTextField lengthAnswerTextField;
	private JTextField speedTextField;

	String sep = System.getProperty("line.separator");

	double tStill;
	double t;
	double lStill;
	double l;
	double gamma;
	double vms;
	double vkms;
	double c = 299792.458;
	private JTextField entryTimeTextField;
	private JTextField answerTimeTextField;
	private JTextField entrySpeedTextField;
	private JTextField answerSpeedTextField;

	String converterEntrySpeedType;
	String converterAnswerSpeedType;
	String converterEntryTimeType;
	String converterAnswerTimeType;
	private JTextField percentTextField;



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
		setIconImage(new ImageIcon(this.getClass().getResource("/icon_Einstein.jpg")).getImage());

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 516);



		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// Menu knapp
		JButton menuButton = new JButton(Language.getString("STR_Calculator.menuButton.text", "Menu")); //$NON-NLS-1$ //$NON-NLS-2$
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane, "menu");
			}
		});
		menuBar.add(menuButton);

		// Kalkylator knapp
		JButton calcButton = new JButton(Language.getString("STR_Calculator.calcButton.text", "Calculator")); //$NON-NLS-1$ //$NON-NLS-2$
		calcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane, "calc");
			}
		});
		menuBar.add(calcButton);

		// Info knapp
		JButton infoButton = new JButton(Language.getString("STR_Calculator.infoButton.text", "Info")); //$NON-NLS-1$ //$NON-NLS-2$
		infoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane, "info");
			}
		});
		menuBar.add(infoButton);

		// Logg knapp
		JButton logButton = new JButton(Language.getString("STR_Calculator.btnLogg.text", "Logg")); //$NON-NLS-1$ //$NON-NLS-2$
		logButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane, "logScroll");
			}
		});
		menuBar.add(logButton);

		JButton btnKonverterare = new JButton(Language.getString("STR_Calculator.btnKonvaterare.text", "Konverterare")); //$NON-NLS-1$ //$NON-NLS-2$
		btnKonverterare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane, "conv");
			}
		});
		menuBar.add(btnKonverterare);


		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		JPanel menuPanel = new JPanel();
		contentPane.add(menuPanel, "menu");

		/*
		 * 
		 * Ska bort
		 */
		JButton btnNewButton = new JButton(Language.getString("STR_Calculator.btnNewButton.text", "New button")); //$NON-NLS-1$ //$NON-NLS-2$
		menuPanel.add(btnNewButton);

		JPanel calcPanel = new JPanel();
		contentPane.add(calcPanel, "calc");
		calcPanel.setLayout(null);

		JLabel lineStaticImage = new JLabel(Language.getString("STR_Calculator.lblLinestaticimage.text", "")); //$NON-NLS-1$ //$NON-NLS-2$
		lineStaticImage.setBackground(Color.RED);
		lineStaticImage.setIcon(new ImageIcon(this.getClass().getResource("/trainPicture.png")));
		lineStaticImage.setBounds(340, 211, 325, 50);
		calcPanel.add(lineStaticImage);

		JLabel lineScalableImage = new JLabel(Language.getString("STR_Calculator.lineScalableImage.text", (String) null)); //$NON-NLS-1$
		lineScalableImage.setBackground(Color.RED);
		lineScalableImage.setIcon(new ImageIcon(this.getClass().getResource("/trainPicture.png")));
		lineScalableImage.setBounds(340, 272, 325, 50);
		calcPanel.add(lineScalableImage);

		JLabel minLengthLabel = new JLabel(Language.getString("STR_Calculator.minLengthLabel.text", (String) null)); //$NON-NLS-1$
		minLengthLabel.setBounds(340, 259, 14, 14);
		calcPanel.add(minLengthLabel);

		JLabel maxLengthLabel = new JLabel();
		maxLengthLabel.setBounds(619, 259, 46, 14);
		calcPanel.add(maxLengthLabel);

		JLabel minFLengthLabel = new JLabel(Language.getString("STR_Calculator.lblm.text", "0m")); //$NON-NLS-1$ //$NON-NLS-2$
		minFLengthLabel.setBounds(340, 324, 14, 14);
		calcPanel.add(minFLengthLabel);

		JLabel maxFLengthLabel = new JLabel(Language.getString("STR_Calculator.lblNewLabel.text", "")); //$NON-NLS-1$ //$NON-NLS-2$
		maxFLengthLabel.setBounds(619, 324, 46, 14);
		calcPanel.add(maxFLengthLabel);

		/*
		 * Textrutor
		 * Ger meddelande om det �r text i rutorna
		 *
		 *L�ngd och tid
		 * 
		 */
		JLabel timeInvalidLabel = new JLabel(Language.getString("STR_Calculator.timeInvalidLabel.text", "")); //$NON-NLS-1$ //$NON-NLS-2$
		timeInvalidLabel.setForeground(Color.RED);
		timeInvalidLabel.setBounds(0, 72, 130, 14);
		calcPanel.add(timeInvalidLabel);

		JLabel lengthInvalidLabel = new JLabel(Language.getString("STR_Calculator.lengthInvalidLabel.text", "")); //$NON-NLS-1$ //$NON-NLS-2$
		lengthInvalidLabel.setForeground(Color.RED);
		lengthInvalidLabel.setBounds(180, 72, 130, 14);
		calcPanel.add(lengthInvalidLabel);

		JLabel speedInvalidLabel = new JLabel(Language.getString("STR_Calculator.speedInvalidLabel.text", "")); //$NON-NLS-1$ //$NON-NLS-2$
		speedInvalidLabel.setForeground(Color.RED);
		speedInvalidLabel.setBounds(100, 183, 130, 14);
		calcPanel.add(speedInvalidLabel);

		stillTimeTextField = new JTextField();
		stillTimeTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					Double.parseDouble(stillTimeTextField.getText());
					timeInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					timeInvalidLabel.setText("Endast siffror och punkt");
				}
			}
		});



		stillTimeTextField.setToolTipText(Language.getString("STR_Calculator.stillTimeTextField.toolTipText", "T.ex tiden ett rymdskepp varit på resa: \"5 år\"")); //$NON-NLS-1$ //$NON-NLS-2$
		stillTimeTextField.setColumns(10);
		stillTimeTextField.setBounds(0, 42, 150, 31);
		calcPanel.add(stillTimeTextField);



		JPanel logPanel = new JPanel();
		logPanel.setAutoscrolls(true);
		//logScrollPane.setViewportView(logPanel);
		logPanel.setPreferredSize(new Dimension(0, 10000));
		logPanel.setLayout(null);

		JTextPane textTime0Pane = new JTextPane();
		textTime0Pane.setEditable(false);
		textTime0Pane.setBounds(0, 25, 100, 10000);
		logPanel.add(textTime0Pane);

		JTextPane textLength0Pane = new JTextPane();
		textLength0Pane.setEditable(false);
		textLength0Pane.setBounds(110, 25, 100, 10000);
		logPanel.add(textLength0Pane);

		JTextPane textSpeedPane = new JTextPane();
		textSpeedPane.setEditable(false);
		textSpeedPane.setBounds(220, 25, 100, 10000);
		logPanel.add(textSpeedPane);

		JTextPane textTimePane = new JTextPane();
		textTimePane.setEditable(false);
		textTimePane.setBounds(330, 25, 100, 10000);
		logPanel.add(textTimePane);

		JTextPane textLengthPane = new JTextPane();
		textLengthPane.setEditable(false);
		textLengthPane.setBounds(440, 25, 100, 10000);
		logPanel.add(textLengthPane);

		JTextPane textGammaPane = new JTextPane();
		textGammaPane.setEditable(false);
		textGammaPane.setBounds(550, 25, 100, 10000);
		logPanel.add(textGammaPane);

		JScrollPane logScrollPane = new JScrollPane(logPanel);
		logScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		logScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		logScrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		logScrollPane.setAutoscrolls(true);
		contentPane.add(logScrollPane, "logScroll");

		percentTextField = new JTextField();
		percentTextField.setToolTipText(Language.getString("STR_Calculator.percentTextField.toolTipText", "Visar skillnaden mellan den ursprungliga och den kontrakterade längden i procent")); //$NON-NLS-1$ //$NON-NLS-2$
		percentTextField.setEditable(false);
		percentTextField.setBounds(450, 359, 104, 20);
		calcPanel.add(percentTextField);
		percentTextField.setColumns(10);

		JButton answerButton = new JButton(Language.getString("STR_Calculator.answerButton.text", "Räkna ut")); //$NON-NLS-1$ //$NON-NLS-2$
		answerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setParameters();
				solveGamma();
				solveTime();
				solveLength();
				showTime();
				showLength();
				logEverything();
				showPics();
				showPercent();
			}

			private void setParameters() {
				if (stillTimeTextField.getText().equals("")) {
					tStill = 0;
				} else {
					tStill = Double.parseDouble(stillTimeTextField.getText());
				}
				if (stillLengthTextField.getText().equals("")) {
					lStill = 0;
				} else {
					lStill = Double.parseDouble(stillLengthTextField.getText());
				}
				if (speedTextField.getText().equals("")) {
					vms = 0;
				} else {
					vms = Double.parseDouble(speedTextField.getText());
				}

				vkms = vms/1000;

			}

			private void showTime() {
				timeAnswerTextField.setText(t + "");
			}

			private void showLength() {
				lengthAnswerTextField.setText(l + "");
			}

			private void showPics() {
				double scaleFactor = (l/lStill)*325;
				ImageIcon originalImage = new ImageIcon(this.getClass().getResource("/trainPicture.png"));
				Image newImage = originalImage.getImage().getScaledInstance((int)scaleFactor, 50, Image.SCALE_DEFAULT);
				ImageIcon scaledImageIcon = new ImageIcon(newImage);
				lineScalableImage.setIcon(scaledImageIcon);
				maxLengthLabel.setText((int)lStill+ "m");
				maxFLengthLabel.setText((int)l + "m");
				double labelPlacement = (l/lStill)*265+354;
				maxFLengthLabel.setBounds((int)labelPlacement, 324, 46, 14);
			}

			private void showPercent() {
				double percentLengthChange = (l/lStill)*100;
				BigDecimal bd = new BigDecimal(percentLengthChange);
				bd = bd.setScale(9, RoundingMode.HALF_UP);
				percentLengthChange = bd.doubleValue();
				percentTextField.setText(percentLengthChange + "%");
			}
			/*
			 * R�kna ut:
			 * l
			 * t
			 * gamma
			 * 
			 */
			private void solveLength() {
				l = lStill / gamma;
			}

			private void solveTime() {
				t = tStill / gamma;
			}

			private void solveGamma() {
				gamma = (1 / (Math.sqrt(1 - (Math.pow(vkms, 2) / Math.pow(c, 2)))));
			}

			private void logEverything() {
				textTime0Pane.setText(textTime0Pane.getText()+sep+tStill);
				textLength0Pane.setText(textLength0Pane.getText()+sep+lStill);
				textSpeedPane.setText(textSpeedPane.getText()+sep+vms);
				textTimePane.setText(textTimePane.getText()+sep+t);
				textLengthPane.setText(textLengthPane.getText()+sep+l);
				textGammaPane.setText(textGammaPane.getText()+sep+gamma);

			}
		});
		answerButton.setBounds(100, 240, 130, 52);
		calcPanel.add(answerButton);


		stillLengthTextField = new JTextField();
		stillLengthTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					Double.parseDouble(stillLengthTextField.getText());
					lengthInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					lengthInvalidLabel.setText("Endast siffror och punkt");

				}
			}
		});
		stillLengthTextField.setToolTipText(Language.getString("STR_Calculator.stillLengthTextField.toolTipText", "T.ex objektets längd: \"30 m\"")); //$NON-NLS-1$ //$NON-NLS-2$
		stillLengthTextField.setColumns(10);
		stillLengthTextField.setBounds(180, 42, 150, 31);
		calcPanel.add(stillLengthTextField);

		timeAnswerTextField = new JTextField();
		timeAnswerTextField.setToolTipText(Language.getString("STR_Calculator.timeAnswerTextField.toolTipText", "Visar den dilataterade tiden")); //$NON-NLS-1$ //$NON-NLS-2$
		timeAnswerTextField.setEditable(false);
		timeAnswerTextField.setColumns(10);
		timeAnswerTextField.setBounds(0, 305, 150, 52);
		calcPanel.add(timeAnswerTextField);

		lengthAnswerTextField = new JTextField();
		lengthAnswerTextField.setToolTipText(Language.getString("STR_Calculator.lengthAnswerTextField.toolTipText", "Visar den kontrakterade längden")); //$NON-NLS-1$ //$NON-NLS-2$
		lengthAnswerTextField.setEditable(false);
		lengthAnswerTextField.setColumns(10);
		lengthAnswerTextField.setBounds(180, 305, 150, 52);
		calcPanel.add(lengthAnswerTextField);

		JSlider speedSlider = new JSlider(0, 299792458, 149896229);
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
		speedSlider.setValue(149896229);
		speedSlider.setMaximum(299792458);
		speedSlider.setBounds(0, 119, 330, 26);
		calcPanel.add(speedSlider);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText(Language.getString("STR_Calculator.textArea.text", "t0 i år")); //$NON-NLS-1$ //$NON-NLS-2$
		textArea.setOpaque(false);
		textArea.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		textArea.setBounds(0, 15, 130, 22);
		calcPanel.add(textArea);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setText(Language.getString("STR_Calculator.textArea_1.text", "l0 i meter")); //$NON-NLS-1$ //$NON-NLS-2$
		textArea_1.setOpaque(false);
		textArea_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		textArea_1.setBounds(180, 15, 130, 26);
		calcPanel.add(textArea_1);

		JTextArea textArea_2 = new JTextArea();
		textArea_2.setEditable(false);
		textArea_2.setText(Language.getString("STR_Calculator.textArea_2.text", "Objektets hastighet i m/s")); //$NON-NLS-1$ //$NON-NLS-2$
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
		speedTextField.setToolTipText(Language.getString("STR_Calculator.speedTextField.toolTipText", "T.ex 290000000m/s")); //$NON-NLS-1$ //$NON-NLS-2$
		speedTextField.setText(Language.getString("STR_Calculator.speedTextField.text", "149896229")); //$NON-NLS-1$ //$NON-NLS-2$
		speedTextField.setColumns(15);
		speedTextField.setBounds(125, 156, 80, 25);
		calcPanel.add(speedTextField);

		JButton button_n1k = new JButton(Language.getString("STR_Calculator.button_n1.text", "-1k")); //$NON-NLS-1$ //$NON-NLS-2$
		button_n1k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}
				if ((Double.parseDouble(speedTextField.getText())-1000)<0) {
					speedTextField.setText("0");
				}else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())-1000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}

		});
		button_n1k.setMargin(new Insets(0, 0, 0, 0));
		button_n1k.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		button_n1k.setBounds(100, 156, 25, 25);
		calcPanel.add(button_n1k);

		JButton button_n10k = new JButton(Language.getString("STR_Calculator.button_n10.text", "-10k")); //$NON-NLS-1$ //$NON-NLS-2$
		button_n10k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}
				if ((Double.parseDouble(speedTextField.getText())-10000)<0) {
					speedTextField.setText("0");
				}else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())-10000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		button_n10k.setMargin(new Insets(0, 0, 0, 0));
		button_n10k.setFont(new Font("Cambria Math", Font.PLAIN, 8));
		button_n10k.setBounds(75, 156, 25, 25);
		calcPanel.add(button_n10k);

		JButton button_n100k = new JButton(Language.getString("STR_Calculator.button_n100.text", "-100k")); //$NON-NLS-1$ //$NON-NLS-2$
		button_n100k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}
				if ((Double.parseDouble(speedTextField.getText())-100000)<0) {
					speedTextField.setText("0");
				}else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())-100000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		button_n100k.setMargin(new Insets(0, 0, 0, 0));
		button_n100k.setFont(new Font("Cambria Math", Font.PLAIN, 7));
		button_n100k.setBounds(50, 156, 25, 25);
		calcPanel.add(button_n100k);

		JButton button_n1m = new JButton(Language.getString("STR_Calculator.button_n1000.text", "-1m")); //$NON-NLS-1$ //$NON-NLS-2$
		button_n1m.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}
				if ((Double.parseDouble(speedTextField.getText())-1000000)<0) {
					speedTextField.setText("0");
				}else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())-1000000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		button_n1m.setMargin(new Insets(0, 0, 0, 0));
		button_n1m.setFont(new Font("Cambria Math", Font.PLAIN, 10));
		button_n1m.setBounds(25, 156, 25, 25);
		calcPanel.add(button_n1m);

		JButton button_n10m = new JButton(Language.getString("STR_Calculator.button_n10000.text", "-10m")); //$NON-NLS-1$ //$NON-NLS-2$
		button_n10m.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}
				if ((Double.parseDouble(speedTextField.getText())-10000000)<0) {
					speedTextField.setText("0");
				}else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())-10000000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		button_n10m.setMargin(new Insets(0, 0, 0, 0));
		button_n10m.setFont(new Font("Cambria Math", Font.PLAIN, 8));
		button_n10m.setBounds(0, 156, 25, 25);
		calcPanel.add(button_n10m);

		JButton button_p10m = new JButton(Language.getString("STR_Calculator.button_p10000.text", "+10m")); //$NON-NLS-1$ //$NON-NLS-2$
		button_p10m.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}
				if ((Double.parseDouble(speedTextField.getText())+10000000)>c*1000) {
					speedTextField.setText("299792458");
				}else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())+10000000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		button_p10m.setMargin(new Insets(0, 0, 0, 0));
		button_p10m.setFont(new Font("Cambria Math", Font.PLAIN, 8));
		button_p10m.setBounds(305, 156, 25, 25);
		calcPanel.add(button_p10m);

		JButton button_p1m = new JButton(Language.getString("STR_Calculator.button_p1000.text", "+1m")); //$NON-NLS-1$ //$NON-NLS-2$
		button_p1m.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}
				if ((Double.parseDouble(speedTextField.getText())+1000000)>c*1000) {
					speedTextField.setText("299792458");
				}else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())+1000000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		button_p1m.setMargin(new Insets(0, 0, 0, 0));
		button_p1m.setFont(new Font("Cambria Math", Font.PLAIN, 10));
		button_p1m.setBounds(280, 156, 25, 25);
		calcPanel.add(button_p1m);

		JButton button_p100k = new JButton(Language.getString("STR_Calculator.button_p100.text", "+100k")); //$NON-NLS-1$ //$NON-NLS-2$
		button_p100k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}
				if ((Double.parseDouble(speedTextField.getText())+100000)>c*1000) {
					speedTextField.setText("299792458");
				}else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())+100000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		button_p100k.setMargin(new Insets(0, 0, 0, 0));
		button_p100k.setFont(new Font("Cambria Math", Font.PLAIN, 7));
		button_p100k.setBounds(255, 156, 25, 25);
		calcPanel.add(button_p100k);

		JButton button_p10k = new JButton(Language.getString("STR_Calculator.button_p10.text", "+10k")); //$NON-NLS-1$ //$NON-NLS-2$
		button_p10k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}if ((Double.parseDouble(speedTextField.getText())+10000)>c*1000) {
					speedTextField.setText("299792458");
				}else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())+10000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		button_p10k.setMargin(new Insets(0, 0, 0, 0));
		button_p10k.setFont(new Font("Cambria Math", Font.PLAIN, 8));
		button_p10k.setBounds(230, 156, 25, 25);
		calcPanel.add(button_p10k);

		JButton button_p1k = new JButton(Language.getString("STR_Calculator.button_p1.text", "+1k")); //$NON-NLS-1$ //$NON-NLS-2$
		button_p1k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}if ((Double.parseDouble(speedTextField.getText())+1000)>c*1000) {
					speedTextField.setText("299792458");
				}else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText())+1000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		button_p1k.setMargin(new Insets(0, 0, 0, 0));
		button_p1k.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		button_p1k.setBounds(205, 156, 25, 25);
		calcPanel.add(button_p1k);



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





		JLabel logTime0Label = new JLabel(Language.getString("STR_Calculator.logTime0Label.text", (String) null)); //$NON-NLS-1$
		logTime0Label.setFont(new Font("Tahoma", Font.PLAIN, 10));
		logTime0Label.setBounds(0, 0, 100, 25);
		logPanel.add(logTime0Label);

		JLabel logLength0Label = new JLabel(Language.getString("STR_Calculator.logLength0Label.text", "Objektets l\u00E4ngd")); //$NON-NLS-1$ //$NON-NLS-2$
		logLength0Label.setBounds(110, 0, 100, 25);
		logPanel.add(logLength0Label);

		JLabel logSpeedLabel = new JLabel(Language.getString("STR_Calculator.logSpeedLabel.text", "Objektets hastighet")); //$NON-NLS-1$ //$NON-NLS-2$
		logSpeedLabel.setBounds(220, 0, 100, 25);
		logPanel.add(logSpeedLabel);

		JLabel logTimeLabel = new JLabel(Language.getString("STR_Calculator.logTimeLabel.text", "Resultat - tid")); //$NON-NLS-1$ //$NON-NLS-2$
		logTimeLabel.setBounds(330, 0, 100, 25);
		logPanel.add(logTimeLabel);

		JLabel logLengthLabel = new JLabel(Language.getString("STR_Calculator.logLengthLabel.text", "Resultat - l\u00E4ngd")); //$NON-NLS-1$ //$NON-NLS-2$
		logLengthLabel.setBounds(440, 0, 100, 25);
		logPanel.add(logLengthLabel);
		JLabel gammaLabel = new JLabel(Language.getString("STR_Calculator.gammaLabel.text", (String) null)); //$NON-NLS-1$
		gammaLabel.setBounds(550, 0, 100, 25);
		logPanel.add(gammaLabel);

		JPanel convPanel = new JPanel();
		contentPane.add(convPanel, "conv");
		convPanel.setLayout(null);

		String[] timeConverterOptions = {"Nanoseconds", "Microseconds", "Milliseconds", "Seconds", "Minutes", "Hours", "Days", "Years"};
		String[] speedConverterOptions = {"m/s", "km/s", "km/h"};

		JComboBox<String> entryTimeBox = new JComboBox<>();
		entryTimeBox.setMaximumRowCount(10);
		entryTimeBox.setModel(new DefaultComboBoxModel<>(timeConverterOptions));
		entryTimeBox.setBounds(515, 10, 150, 60);
		convPanel.add(entryTimeBox);

		JComboBox<String> answerTimeBox = new JComboBox<>();
		answerTimeBox.setModel(new DefaultComboBoxModel<>(timeConverterOptions));
		answerTimeBox.setMaximumRowCount(10);
		answerTimeBox.setBounds(515, 110, 150, 60);
		convPanel.add(answerTimeBox);

		JLabel warningTimeBoxLabel = new JLabel(Language.getString("STR_Calculator.warningTimeBoxLabel.text", "")); //$NON-NLS-1$ //$NON-NLS-2$
		warningTimeBoxLabel.setForeground(Color.RED);
		warningTimeBoxLabel.setBounds(515, 81, 150, 20);
		convPanel.add(warningTimeBoxLabel);

		JLabel warningSpeedBoxLabel = new JLabel(Language.getString("STR_Calculator.warningSpeedBoxLabel.text", "")); //$NON-NLS-1$ //$NON-NLS-2$
		warningSpeedBoxLabel.setForeground(Color.RED);
		warningSpeedBoxLabel.setBounds(515, 351, 150, 20);
		convPanel.add(warningSpeedBoxLabel);

		answerTimeTextField = new JTextField();
		answerTimeTextField.setEditable(false);
		answerTimeTextField.setColumns(10);
		answerTimeTextField.setBounds(10, 110, 495, 60);
		convPanel.add(answerTimeTextField);

		entryTimeTextField = new JTextField();
		entryTimeTextField.setBounds(10, 10, 495, 60);
		convPanel.add(entryTimeTextField);
		entryTimeTextField.setColumns(10);

		entrySpeedTextField = new JTextField();
		entrySpeedTextField.setColumns(10);
		entrySpeedTextField.setBounds(10, 282, 495, 60);
		convPanel.add(entrySpeedTextField);

		answerSpeedTextField = new JTextField();
		answerSpeedTextField.setEditable(false);
		answerSpeedTextField.setColumns(10);
		answerSpeedTextField.setBounds(10, 382, 495, 60);
		convPanel.add(answerSpeedTextField);

		JComboBox<String> entrySpeedBox = new JComboBox<String>();
		entrySpeedBox.setMaximumRowCount(10);
		entrySpeedBox.setModel(new DefaultComboBoxModel<String>(speedConverterOptions));
		entrySpeedBox.setBounds(515, 282, 150, 60);
		convPanel.add(entrySpeedBox);

		JComboBox<String> answerSpeedBox = new JComboBox<String>();
		answerSpeedBox.setMaximumRowCount(10);
		answerSpeedBox.setModel(new DefaultComboBoxModel<String>(speedConverterOptions));
		answerSpeedBox.setBounds(515, 382, 150, 60);
		convPanel.add(answerSpeedBox);

		JButton converterButton = new JButton(Language.getString("STR_Calculator.btnNewButton_1.text", "Convert")); //$NON-NLS-1$ //$NON-NLS-2$
		converterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//TODO fixa null-mekanismen

				if (entrySpeedTextField.getText() == null) {
					answerSpeedTextField.setText("");
				}

				if (entryTimeTextField.getText() == null) {
					answerTimeTextField.setText("");
				}





				warningSpeedBoxLabel.setText("");
				warningTimeBoxLabel.setText("");

				converterEntrySpeedType = entrySpeedBox.getSelectedItem().toString();
				converterAnswerSpeedType = answerSpeedBox.getSelectedItem().toString();
				converterEntryTimeType = entryTimeBox.getSelectedItem().toString();
				converterAnswerTimeType = answerTimeBox.getSelectedItem().toString();

				switch (converterEntrySpeedType) {
				case "m/s":
					switch (converterAnswerSpeedType) {
					case "m/s":
						warningSpeedBoxLabel.setText("Välj olika värdetyper!");
						break;
					case "km/s":
						answerSpeedTextField.setText((Double.parseDouble(entrySpeedTextField.getText())/1000) + "");
						break;
					case "km/h":
						answerSpeedTextField.setText((Double.parseDouble(entrySpeedTextField.getText())*3.6) + "");
						break; 
					}
					break;
				case "km/s":
					switch (converterAnswerSpeedType) {
					case "m/s":
						answerSpeedTextField.setText((Double.parseDouble(entrySpeedTextField.getText())*1000) + "");
						break;
					case "km/s":
						warningSpeedBoxLabel.setText("Välj olika värdetyper!");
						break;
					case "km/h":
						answerSpeedTextField.setText((Double.parseDouble(entrySpeedTextField.getText())*3600) + "");
						break; 
					}
					break;
				case "km/h":
					switch (converterAnswerSpeedType) {
					case "m/s":
						answerSpeedTextField.setText((Double.parseDouble(entrySpeedTextField.getText())/3.6) + "");
						break;
					case "km/s":
						answerSpeedTextField.setText((Double.parseDouble(entrySpeedTextField.getText())/3600) + "");
						break;
					case "km/h":
						warningSpeedBoxLabel.setText("Välj olika värdetyper!");
						break; 
					}
					break; 


				}
				switch (converterEntryTimeType) {
				case "Nanoseconds":
					switch (converterAnswerTimeType) {
					case "Nanoseconds":
						warningTimeBoxLabel.setText("Välj olika värdetyper!");
						break;
					case "Microseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/1000) + "");
						break;
					case "Milliseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/1000000) + "");
						break;
					case "Seconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/1000000000) + "");
						break;
					case "Minutes":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/1000000000/60) + "");
						break;
					case "Hours":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/1000000000/3600) + "");
						break;
					case "Days":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/1000000000/3600/24) + "");
						break;
					case "Years":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/1000000000/3600/24/365) + "");
						break;
					}
					break;
				case "Microseconds":
					switch (converterAnswerTimeType) {
					case "Nanoseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*1000) + "");
						break;
					case "Microseconds":
						warningTimeBoxLabel.setText("Välj olika värdetyper!");
						break;
					case "Milliseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/1000) + "");
						break;
					case "Seconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/1000000) + "");
						break;
					case "Minutes":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/1000000/60) + "");
						break;
					case "Hours":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/1000000/3600) + "");
						break;
					case "Days":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/1000000/3600/24) + "");
						break;
					case "Years":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/1000000/3600/24/365) + "");
						break;
					}
					break;
				case "Milliseconds":
					switch (converterAnswerTimeType) {
					case "Nanoseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*1000000) + "");
						break;
					case "Microseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*1000) + "");
						break;
					case "Milliseconds":
						warningTimeBoxLabel.setText("Välj olika värdetyper!");
						break;
					case "Seconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/1000) + "");
						break;
					case "Minutes":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/1000/60) + "");
						break;
					case "Hours":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/1000/3600) + "");
						break;
					case "Days":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/1000/3600/24) + "");
						break;
					case "Years":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/1000/3600/24/365) + "");
						break;
					}
					break;
				case "Seconds":
					switch (converterAnswerTimeType) {
					case "Nanoseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*1000000000) + "");
						break;
					case "Microseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*1000000) + "");
						break;
					case "Milliseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*1000) + "");
						break;
					case "Seconds":
						warningTimeBoxLabel.setText("Välj olika värdetyper!");
						break;
					case "Minutes":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/60) + "");
						break;
					case "Hours":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/3600) + "");
						break;
					case "Days":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/3600/24) + "");
						break;
					case "Years":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/3600/24/365) + "");
						break;
					}
					break;
				case "Minutes":
					switch (converterAnswerTimeType) {
					case "Nanoseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*1000000000*60) + "");
						break;
					case "Microseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*1000000*60) + "");
						break;
					case "Milliseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*1000*60) + "");
						break;
					case "Seconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*60) + "");
						break;
					case "Minutes":
						warningTimeBoxLabel.setText("Välj olika värdetyper!");
						break;
					case "Hours":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/60) + "");
						break;
					case "Days":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/60/24) + "");
						break;
					case "Years":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/60/24/365) + "");
						break;
					}
					break;
				case "Hours":
					switch (converterAnswerTimeType) {
					case "Nanoseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*1000000000*3600) + "");
						break;
					case "Microseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*1000000*3600) + "");
						break;
					case "Milliseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*1000*3600) + "");
						break;
					case "Seconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*3600) + "");
						break;
					case "Minutes":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*60) + "");
						break;
					case "Hours":
						warningTimeBoxLabel.setText("Välj olika värdetyper!");
						break;
					case "Days":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/24) + "");
						break;
					case "Years":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/24/365) + "");
						break;
					}
					break;
				case "Days":
					switch (converterAnswerTimeType) {
					case "Nanoseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*1000000000*3600*24) + "");
						break;
					case "Microseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*1000000*3600*24) + "");
						break;
					case "Milliseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*1000*3600*24) + "");
						break;
					case "Seconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*3600*24) + "");
						break;
					case "Minutes":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*60*24) + "");
						break;
					case "Hours":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*24) + "");
						break;
					case "Days":
						warningTimeBoxLabel.setText("Välj olika värdetyper!");
						break;
					case "Years":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())/365) + "");
						break;
					}
					break;
				case "Years":
					switch (converterAnswerTimeType) {
					case "Nanoseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*1000000000*3600*24*365) + "");
						break;
					case "Microseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*1000000*3600*24*365) + "");
						break;
					case "Milliseconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*1000*3600*24*365) + "");
						break;
					case "Seconds":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*3600*24*365) + "");
						break;
					case "Minutes":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*60*24*365) + "");
						break;
					case "Hours":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*24*365) + "");
						break;
					case "Days":
						answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText())*365) + "");
						break;
					case "Years":
						warningTimeBoxLabel.setText("Välj olika värdetyper!");
						break;
					}
					break;
				}	
			}
		});
		converterButton.setBounds(200, 210, 115, 32);
		convPanel.add(converterButton); 
	}	
}
