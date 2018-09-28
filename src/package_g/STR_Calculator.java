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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ScrollPaneConstants;

import java.awt.EventQueue;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.Dimension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.Locale;
import javax.swing.JToggleButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

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

	boolean reverseEverything;

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
	private JTextField entryLengthTextField;
	private JTextField answerLengthTextField;

	String converterEntrySpeedType;
	String converterAnswerSpeedType;
	String converterEntryTimeType;
	String converterAnswerTimeType;
	String converterEntryLengthType;
	String converterAnswerLengthType;
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

		// Kalkylator knapp
		JButton calcButton = new JButton("Kalkylator");
		calcButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "calc");
			}
		});
		menuBar.add(calcButton);

		// Info knapp
		JButton infoButton = new JButton("Info");
		infoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "info");
			}
		});
		menuBar.add(infoButton);

		// Logg knapp
		JButton logButton = new JButton("Logg");
		logButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "logScroll");
			}
		});
		menuBar.add(logButton);

		JButton btnKonverterare = new JButton("Konverterare");
		btnKonverterare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c = (CardLayout) (contentPane.getLayout());
				c.show(contentPane, "conv");
			}
		});
		menuBar.add(btnKonverterare);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		JPanel calcPanel = new JPanel();
		contentPane.add(calcPanel, "calc");
		calcPanel.setLayout(null);

		JLabel lineStaticImage = new JLabel("");
		lineStaticImage.setBackground(Color.RED);
		lineStaticImage.setIcon(new ImageIcon(this.getClass().getResource("/trainPicture.png")));
		lineStaticImage.setBounds(340, 211, 325, 50);
		calcPanel.add(lineStaticImage);

		JLabel lineScalableImage = new JLabel((String) null);
		lineScalableImage.setBackground(Color.RED);
		lineScalableImage.setIcon(new ImageIcon(this.getClass().getResource("/trainPicture.png")));
		lineScalableImage.setBounds(340, 272, 325, 50);
		calcPanel.add(lineScalableImage);

		JLabel minLengthLabel = new JLabel("0m");
		minLengthLabel.setBounds(340, 259, 14, 14);
		calcPanel.add(minLengthLabel);

		JLabel maxLengthLabel = new JLabel();
		maxLengthLabel.setBounds(619, 259, 46, 14);
		calcPanel.add(maxLengthLabel);

		JLabel minFLengthLabel = new JLabel("0m");
		minFLengthLabel.setBounds(340, 324, 14, 14);
		calcPanel.add(minFLengthLabel);

		JLabel maxFLengthLabel = new JLabel("");
		maxFLengthLabel.setBounds(619, 324, 46, 14);
		calcPanel.add(maxFLengthLabel);

		/*
		 * Textrutor Ger meddelande om det �r text i rutorna
		 *
		 * L�ngd och tid
		 * 
		 */
		JLabel timeInvalidLabel = new JLabel("");
		timeInvalidLabel.setForeground(Color.RED);
		timeInvalidLabel.setBounds(0, 72, 130, 14);
		calcPanel.add(timeInvalidLabel);

		JLabel lengthInvalidLabel = new JLabel("");
		lengthInvalidLabel.setForeground(Color.RED);
		lengthInvalidLabel.setBounds(180, 72, 130, 14);
		calcPanel.add(lengthInvalidLabel);

		JLabel speedInvalidLabel = new JLabel("");
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

		stillTimeTextField.setToolTipText("Tiden som har passerat på ett stillastående objekt: \"5 år\"");
		stillTimeTextField.setColumns(10);
		stillTimeTextField.setBounds(0, 42, 150, 31);
		calcPanel.add(stillTimeTextField);

		JPanel logPanel = new JPanel();
		logPanel.setAutoscrolls(true);
		// logScrollPane.setViewportView(logPanel);
		logPanel.setPreferredSize(new Dimension(0, 10000));
		logPanel.setLayout(null);

		JTextPane textTime0Pane = new JTextPane();
		textTime0Pane.setEditable(false);
		textTime0Pane.setBounds(30, 25, 96, 10000);
		logPanel.add(textTime0Pane);

		JTextPane textLength0Pane = new JTextPane();
		textLength0Pane.setEditable(false);
		textLength0Pane.setBounds(136, 25, 96, 10000);
		logPanel.add(textLength0Pane);

		JTextPane textSpeedPane = new JTextPane();
		textSpeedPane.setEditable(false);
		textSpeedPane.setBounds(242, 25, 96, 10000);
		logPanel.add(textSpeedPane);

		JTextPane textTimePane = new JTextPane();
		textTimePane.setEditable(false);
		textTimePane.setBounds(348, 25, 96, 10000);
		logPanel.add(textTimePane);

		JTextPane textLengthPane = new JTextPane();
		textLengthPane.setEditable(false);
		textLengthPane.setBounds(454, 25, 96, 10000);
		logPanel.add(textLengthPane);

		JTextPane textGammaPane = new JTextPane();
		textGammaPane.setEditable(false);
		textGammaPane.setBounds(560, 25, 96, 10000);
		logPanel.add(textGammaPane);

		JTextPane textRevPane = new JTextPane();
		textRevPane.setEditable(false);
		textRevPane.setBounds(0, 25, 20, 10000);
		logPanel.add(textRevPane);

		JScrollPane logScrollPane = new JScrollPane(logPanel);
		logScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		logScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		logScrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		logScrollPane.setAutoscrolls(true);
		contentPane.add(logScrollPane, "logScroll");

		percentTextField = new JTextField();
		percentTextField
				.setToolTipText("Visar skillnaden mellan den ursprungliga och den kontrakterade längden i procent");
		percentTextField.setEditable(false);
		percentTextField.setBounds(450, 359, 104, 20);
		calcPanel.add(percentTextField);
		percentTextField.setColumns(10);

		JButton answerButton = new JButton("Räkna ut");
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

				vkms = vms / 1000;

			}

			private void showTime() {
				timeAnswerTextField.setText(t + "");
			}

			private void showLength() {
				lengthAnswerTextField.setText(l + "");
			}

			private void showPics() {
				double scaleFactor = (l / lStill) * 325;
				if ((int) scaleFactor == 0) {
					scaleFactor = 1;
				}
				ImageIcon originalImage = new ImageIcon(this.getClass().getResource("/trainPicture.png"));
				Image newImage = originalImage.getImage().getScaledInstance((int) scaleFactor, 50, Image.SCALE_DEFAULT);
				ImageIcon scaledImageIcon = new ImageIcon(newImage);
				lineScalableImage.setIcon(scaledImageIcon);
				maxLengthLabel.setText((int) lStill + "m");
				maxFLengthLabel.setText((int) l + "m");
				double labelPlacement = (l / lStill) * 265 + 354;
				maxFLengthLabel.setBounds((int) labelPlacement, 324, 46, 14);
			}

			private void showPercent() {
				double percentLengthChange = (l / lStill) * 100;
				BigDecimal bd = new BigDecimal(percentLengthChange);
				bd = bd.setScale(9, RoundingMode.HALF_UP);
				percentLengthChange = bd.doubleValue();
				percentTextField.setText(percentLengthChange + "%");
			}

			/*
			 * R�kna ut: l t gamma
			 * 
			 */
			private void solveLength() {
				if (reverseEverything) {
					l = lStill * gamma;
				} else {
					l = lStill / gamma;
				}

			}

			private void solveTime() {
				if (reverseEverything) {
					t = tStill * gamma;
				} else {
					t = tStill / gamma;
				}

			}

			private void solveGamma() {
				gamma = (1 / (Math.sqrt(1 - (Math.pow(vkms, 2) / Math.pow(c, 2)))));
			}

			private void logEverything() {
				if (reverseEverything) {
					textTime0Pane.setText(textTime0Pane.getText() + sep + t);
					textLength0Pane.setText(textLength0Pane.getText() + sep + l);
					textSpeedPane.setText(textSpeedPane.getText() + sep + vms);
					textTimePane.setText(textTimePane.getText() + sep + tStill);
					textLengthPane.setText(textLengthPane.getText() + sep + lStill);
					textGammaPane.setText(textGammaPane.getText() + sep + gamma);
					textRevPane.setText(textRevPane.getText() + sep + "JA");
				} else {
					textTime0Pane.setText(textTime0Pane.getText() + sep + tStill);
					textLength0Pane.setText(textLength0Pane.getText() + sep + lStill);
					textSpeedPane.setText(textSpeedPane.getText() + sep + vms);
					textTimePane.setText(textTimePane.getText() + sep + t);
					textLengthPane.setText(textLengthPane.getText() + sep + l);
					textGammaPane.setText(textGammaPane.getText() + sep + gamma);
					textRevPane.setText(textRevPane.getText() + sep + "NEJ");
				}

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

		stillLengthTextField
				.setToolTipText("Längden på ett stillastående objekt uppfattad ur objektets perspektiv: \"30 m\"");
		stillLengthTextField.setColumns(10);
		stillLengthTextField.setBounds(180, 42, 150, 31);
		calcPanel.add(stillLengthTextField);

		timeAnswerTextField = new JTextField();
		timeAnswerTextField.setToolTipText("Den dilataterade tiden. Tiden som har passerat på ett objekt i rörelse");
		timeAnswerTextField.setEditable(false);
		timeAnswerTextField.setColumns(10);
		timeAnswerTextField.setBounds(0, 305, 150, 52);
		calcPanel.add(timeAnswerTextField);

		lengthAnswerTextField = new JTextField();
		lengthAnswerTextField.setToolTipText(
				"Den kontrakterade längden. Längden på objektet så som den uppfattas av en stillastående observatör");
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

		JTextArea timeTextArea = new JTextArea();
		timeTextArea.setEditable(false);
		timeTextArea.setText("t0 i år");
		timeTextArea.setOpaque(false);
		timeTextArea.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		timeTextArea.setBounds(0, 15, 130, 22);
		calcPanel.add(timeTextArea);

		JTextArea lengthTextArea = new JTextArea();
		lengthTextArea.setEditable(false);
		lengthTextArea.setText("l0 i meter");
		lengthTextArea.setOpaque(false);
		lengthTextArea.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lengthTextArea.setBounds(180, 15, 130, 26);
		calcPanel.add(lengthTextArea);

		JTextArea speedTextArea = new JTextArea();
		speedTextArea.setEditable(false);
		speedTextArea.setText("Objektets hastighet i m/s");
		speedTextArea.setOpaque(false);
		speedTextArea.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		speedTextArea.setBounds(0, 92, 330, 22);
		calcPanel.add(speedTextArea);

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
		speedTextField.setToolTipText("T.ex 290000000m/s");
		speedTextField.setText("149896229");
		speedTextField.setColumns(15);
		speedTextField.setBounds(125, 156, 80, 25);
		calcPanel.add(speedTextField);

		JButton button_n1k = new JButton("-1k");
		button_n1k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}
				if ((Double.parseDouble(speedTextField.getText()) - 1000) < 0) {
					speedTextField.setText("0");
				} else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText()) - 1000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}

		});
		button_n1k.setMargin(new Insets(0, 0, 0, 0));
		button_n1k.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		button_n1k.setBounds(100, 156, 25, 25);
		calcPanel.add(button_n1k);

		JButton button_n10k = new JButton("-10k");
		button_n10k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}
				if ((Double.parseDouble(speedTextField.getText()) - 10000) < 0) {
					speedTextField.setText("0");
				} else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText()) - 10000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		button_n10k.setMargin(new Insets(0, 0, 0, 0));
		button_n10k.setFont(new Font("Cambria Math", Font.PLAIN, 8));
		button_n10k.setBounds(75, 156, 25, 25);
		calcPanel.add(button_n10k);

		JButton button_n100k = new JButton("-100k");
		button_n100k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}
				if ((Double.parseDouble(speedTextField.getText()) - 100000) < 0) {
					speedTextField.setText("0");
				} else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText()) - 100000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		button_n100k.setMargin(new Insets(0, 0, 0, 0));
		button_n100k.setFont(new Font("Cambria Math", Font.PLAIN, 7));
		button_n100k.setBounds(50, 156, 25, 25);
		calcPanel.add(button_n100k);

		JButton button_n1m = new JButton("-1m");
		button_n1m.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}
				if ((Double.parseDouble(speedTextField.getText()) - 1000000) < 0) {
					speedTextField.setText("0");
				} else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText()) - 1000000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		button_n1m.setMargin(new Insets(0, 0, 0, 0));
		button_n1m.setFont(new Font("Cambria Math", Font.PLAIN, 10));
		button_n1m.setBounds(25, 156, 25, 25);
		calcPanel.add(button_n1m);

		JButton button_n10m = new JButton("-10m");
		button_n10m.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}
				if ((Double.parseDouble(speedTextField.getText()) - 10000000) < 0) {
					speedTextField.setText("0");
				} else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText()) - 10000000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		button_n10m.setMargin(new Insets(0, 0, 0, 0));
		button_n10m.setFont(new Font("Cambria Math", Font.PLAIN, 8));
		button_n10m.setBounds(0, 156, 25, 25);
		calcPanel.add(button_n10m);

		JButton button_p10m = new JButton("+10m");
		button_p10m.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}
				if ((Double.parseDouble(speedTextField.getText()) + 10000000) > c * 1000) {
					speedTextField.setText("299792458");
				} else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText()) + 10000000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		button_p10m.setMargin(new Insets(0, 0, 0, 0));
		button_p10m.setFont(new Font("Cambria Math", Font.PLAIN, 8));
		button_p10m.setBounds(305, 156, 25, 25);
		calcPanel.add(button_p10m);

		JButton button_p1m = new JButton("+1m");
		button_p1m.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}
				if ((Double.parseDouble(speedTextField.getText()) + 1000000) > c * 1000) {
					speedTextField.setText("299792458");
				} else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText()) + 1000000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		button_p1m.setMargin(new Insets(0, 0, 0, 0));
		button_p1m.setFont(new Font("Cambria Math", Font.PLAIN, 10));
		button_p1m.setBounds(280, 156, 25, 25);
		calcPanel.add(button_p1m);

		JButton button_p100k = new JButton("+100k");
		button_p100k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}
				if ((Double.parseDouble(speedTextField.getText()) + 100000) > c * 1000) {
					speedTextField.setText("299792458");
				} else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText()) + 100000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		button_p100k.setMargin(new Insets(0, 0, 0, 0));
		button_p100k.setFont(new Font("Cambria Math", Font.PLAIN, 7));
		button_p100k.setBounds(255, 156, 25, 25);
		calcPanel.add(button_p100k);

		JButton button_p10k = new JButton("+10k");
		button_p10k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}
				if ((Double.parseDouble(speedTextField.getText()) + 10000) > c * 1000) {
					speedTextField.setText("299792458");
				} else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText()) + 10000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		button_p10k.setMargin(new Insets(0, 0, 0, 0));
		button_p10k.setFont(new Font("Cambria Math", Font.PLAIN, 8));
		button_p10k.setBounds(230, 156, 25, 25);
		calcPanel.add(button_p10k);

		JButton button_p1k = new JButton("+1k");
		button_p1k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Double.parseDouble(speedTextField.getText());
					speedInvalidLabel.setText("");
				} catch (NumberFormatException e1) {
					speedInvalidLabel.setText("Endast siffror och punkt");

				}
				if ((Double.parseDouble(speedTextField.getText()) + 1000) > c * 1000) {
					speedTextField.setText("299792458");
				} else {
					speedTextField.setText(String.valueOf((Double.parseDouble(speedTextField.getText()) + 1000)));
				}
				double value = Double.parseDouble(speedTextField.getText());
				speedSlider.setValue((int) value);
			}
		});
		button_p1k.setMargin(new Insets(0, 0, 0, 0));
		button_p1k.setFont(new Font("Cambria Math", Font.PLAIN, 11));
		button_p1k.setBounds(205, 156, 25, 25);
		calcPanel.add(button_p1k);

		JToggleButton reverseButton = new JToggleButton("Reversera");
		reverseButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (ev.getStateChange() == ItemEvent.SELECTED) {
					reverseEverything = true;

					timeTextArea.setText("t i år");
					lengthTextArea.setText("l i meter");
					stillTimeTextField.setToolTipText("Tiden som har passerat på ett objekt i rörelse: \"5 år\"");
					stillLengthTextField.setToolTipText(
							"Längden på ett objektet så som den uppfattas av en stillastående observatör: \"30 m\"");
					timeAnswerTextField.setToolTipText(
							"Den kontrakterade tiden. Tiden som har passerat på ett stillastående objekt");
					lengthAnswerTextField.setToolTipText(
							"Den dilataterade längden. Längden på ett stillastående objekt uppfattad ur objektets perspektiv");
				} else if (ev.getStateChange() == ItemEvent.DESELECTED) {
					reverseEverything = false;

					timeTextArea.setText("t0 i år");
					lengthTextArea.setText("l0 i meter");
					stillTimeTextField.setToolTipText("Tiden som har passerat på ett stillastående objekt: \"5 år\"");
					stillLengthTextField.setToolTipText(
							"Längden på ett stillastående objekt uppfattad ur objektets perspektiv: \"30 m\"");
					timeAnswerTextField
							.setToolTipText("Den dilataterade tiden. Tiden som har passerat på ett objekt i rörelse");
					lengthAnswerTextField.setToolTipText(
							"Den kontrakterade längden. Längden på objektet så som den uppfattas av en stillastående observatör");
				}
			}
		});
		reverseButton.setBounds(444, 46, 121, 23);
		calcPanel.add(reverseButton);

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
		txtrLoremIpsumDolor_1.setText(
				"Tidsdilatation är en kraft Bla. bla.bla Lorem ipsum dolor set amet, vidare läsning och så vidare...");
		txtrLoremIpsumDolor_1.setBounds(10, 54, 335, 360);
		infoPanel.add(txtrLoremIpsumDolor_1);

		JTextArea txtrLngdkontraktionFungerarS = new JTextArea();
		txtrLngdkontraktionFungerarS.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtrLngdkontraktionFungerarS.setWrapStyleWord(true);
		txtrLngdkontraktionFungerarS.setLineWrap(true);
		txtrLngdkontraktionFungerarS.setText(
				"Längdkontraktion fungerar så här blblblblb Lorem ipsum dolor set ametLorem ipsum dolor set ametLorem ipsum dolor set amet");
		txtrLngdkontraktionFungerarS.setBounds(361, 52, 266, 362);
		infoPanel.add(txtrLngdkontraktionFungerarS);

		JLabel logTime0Label = new JLabel("t0");
		logTime0Label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		logTime0Label.setBounds(66, 0, 20, 25);
		logPanel.add(logTime0Label);

		JLabel logLength0Label = new JLabel("l0");
		logLength0Label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		logLength0Label.setBounds(173, 0, 20, 25);
		logPanel.add(logLength0Label);

		JLabel logSpeedLabel = new JLabel("Objektets hastighet");
		logSpeedLabel.setBounds(242, 0, 96, 25);
		logPanel.add(logSpeedLabel);

		JLabel logTimeLabel = new JLabel("t");
		logTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		logTimeLabel.setBounds(396, 0, 7, 25);
		logPanel.add(logTimeLabel);

		JLabel logLengthLabel = new JLabel("l");
		logLengthLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		logLengthLabel.setBounds(502, 0, 7, 25);
		logPanel.add(logLengthLabel);
		JLabel gammaLabel = new JLabel("Gamma");
		gammaLabel.setBounds(560, 0, 90, 25);
		logPanel.add(gammaLabel);

		JLabel logRevLabel = new JLabel("Rev?");
		logRevLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		logRevLabel.setBounds(0, 0, 24, 25);
		logPanel.add(logRevLabel);

		JPanel convPanel = new JPanel();
		contentPane.add(convPanel, "conv");
		convPanel.setLayout(null);

		String[] timeConverterOptions = { "Nanosekunder", "Mikrosekunder", "Millisekunder", "Sekunder", "Minuter",
				"Timmar", "Dagar", "År - grundenhet" };
		String[] speedConverterOptions = { "m/s - grundenhet", "km/s", "km/h" };
		String[] lengthConverterOptions = { "Millimeter", "Centimeter", "Decimeter", "Meter - grundenhet",
				"Kilometer" };

		JComboBox<String> entryTimeBox = new JComboBox<>();
		entryTimeBox.setMaximumRowCount(10);
		entryTimeBox.setModel(new DefaultComboBoxModel<>(timeConverterOptions));
		entryTimeBox.setBounds(515, 10, 150, 40);
		convPanel.add(entryTimeBox);

		JComboBox<String> answerTimeBox = new JComboBox<>();
		answerTimeBox.setModel(new DefaultComboBoxModel<>(timeConverterOptions));
		answerTimeBox.setSelectedIndex(7);
		answerTimeBox.setMaximumRowCount(10);
		answerTimeBox.setBounds(515, 92, 150, 40);
		convPanel.add(answerTimeBox);

		JLabel warningTimeBoxLabel = new JLabel("");
		warningTimeBoxLabel.setForeground(Color.RED);
		warningTimeBoxLabel.setBounds(515, 61, 150, 20);
		convPanel.add(warningTimeBoxLabel);

		JLabel warningSpeedBoxLabel = new JLabel("");
		warningSpeedBoxLabel.setForeground(Color.RED);
		warningSpeedBoxLabel.setBounds(515, 371, 150, 20);
		convPanel.add(warningSpeedBoxLabel);

		answerTimeTextField = new JTextField();
		answerTimeTextField.setEditable(false);
		answerTimeTextField.setColumns(10);
		answerTimeTextField.setBounds(10, 92, 495, 40);
		convPanel.add(answerTimeTextField);

		entryTimeTextField = new JTextField();
		entryTimeTextField.setBounds(10, 10, 495, 40);
		convPanel.add(entryTimeTextField);
		entryTimeTextField.setColumns(10);

		entrySpeedTextField = new JTextField();
		entrySpeedTextField.setColumns(10);
		entrySpeedTextField.setBounds(10, 320, 495, 40);
		convPanel.add(entrySpeedTextField);

		answerSpeedTextField = new JTextField();
		answerSpeedTextField.setEditable(false);
		answerSpeedTextField.setColumns(10);
		answerSpeedTextField.setBounds(10, 402, 495, 40);
		convPanel.add(answerSpeedTextField);

		JComboBox<String> entrySpeedBox = new JComboBox<String>();
		entrySpeedBox.setMaximumRowCount(10);
		entrySpeedBox.setModel(new DefaultComboBoxModel<String>(speedConverterOptions));
		entrySpeedBox.setBounds(515, 320, 150, 40);
		convPanel.add(entrySpeedBox);

		JComboBox<String> answerSpeedBox = new JComboBox<String>();
		answerSpeedBox.setMaximumRowCount(10);
		answerSpeedBox.setModel(new DefaultComboBoxModel<String>(speedConverterOptions));
		answerSpeedBox.setSelectedIndex(0);
		answerSpeedBox.setBounds(515, 402, 150, 40);
		convPanel.add(answerSpeedBox);

		entryLengthTextField = new JTextField();
		entryLengthTextField.setColumns(10);
		entryLengthTextField.setBounds(10, 165, 495, 40);
		convPanel.add(entryLengthTextField);

		answerLengthTextField = new JTextField();
		answerLengthTextField.setEditable(false);
		answerLengthTextField.setColumns(10);
		answerLengthTextField.setBounds(10, 247, 495, 40);
		convPanel.add(answerLengthTextField);

		JComboBox<String> entryLengthBox = new JComboBox<String>();
		entryLengthBox.setMaximumRowCount(10);
		entryLengthBox.setModel(new DefaultComboBoxModel<String>(lengthConverterOptions));
		entryLengthBox.setBounds(515, 165, 150, 40);
		convPanel.add(entryLengthBox);

		JComboBox<String> answerLengthBox = new JComboBox<String>();
		answerLengthBox.setMaximumRowCount(10);
		answerLengthBox.setModel(new DefaultComboBoxModel<String>(lengthConverterOptions));
		answerLengthBox.setSelectedIndex(3);
		answerLengthBox.setBounds(515, 247, 150, 40);
		convPanel.add(answerLengthBox);

		JLabel warningLengthBoxLabel = new JLabel("");
		warningLengthBoxLabel.setForeground(Color.RED);
		warningLengthBoxLabel.setBounds(515, 216, 150, 20);
		convPanel.add(warningLengthBoxLabel);

		JButton timeConverterButton = new JButton("Konvertera");
		timeConverterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				warningTimeBoxLabel.setText("");

				if (entryTimeTextField.getText().equals("")) {
					answerTimeTextField.setText("");
				} else {
					converterEntryTimeType = entryTimeBox.getSelectedItem().toString();
					converterAnswerTimeType = answerTimeBox.getSelectedItem().toString();

					switch (converterEntryTimeType) {
					case "Nanosekunder":
						switch (converterAnswerTimeType) {
						case "Nanosekunder":
							warningTimeBoxLabel.setText("Välj olika värdetyper!");
							break;
						case "Mikrosekunder":
							answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText()) / 1000) + "");
							break;
						case "Millisekunder":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) / 1000000) + "");
							break;
						case "Sekunder":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) / 1000000000) + "");
							break;
						case "Minuter":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) / 1000000000 / 60) + "");
							break;
						case "Timmar":
							answerTimeTextField.setText(
									(Double.parseDouble(entryTimeTextField.getText()) / 1000000000 / 3600) + "");
							break;
						case "Dagar":
							answerTimeTextField.setText(
									(Double.parseDouble(entryTimeTextField.getText()) / 1000000000 / 3600 / 24) + "");
							break;
						case "År - grundenhet":
							answerTimeTextField.setText(
									(Double.parseDouble(entryTimeTextField.getText()) / 1000000000 / 3600 / 24 / 365)
											+ "");
							break;
						}
						break;
					case "Mikrosekunder":
						switch (converterAnswerTimeType) {
						case "Nanosekunder":
							answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText()) * 1000) + "");
							break;
						case "Mikrosekunder":
							warningTimeBoxLabel.setText("Välj olika värdetyper!");
							break;
						case "Millisekunder":
							answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText()) / 1000) + "");
							break;
						case "Sekunder":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) / 1000000) + "");
							break;
						case "Minuter":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) / 1000000 / 60) + "");
							break;
						case "Timmar":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) / 1000000 / 3600) + "");
							break;
						case "Dagar":
							answerTimeTextField.setText(
									(Double.parseDouble(entryTimeTextField.getText()) / 1000000 / 3600 / 24) + "");
							break;
						case "År - grundenhet":
							answerTimeTextField.setText(
									(Double.parseDouble(entryTimeTextField.getText()) / 1000000 / 3600 / 24 / 365)
											+ "");
							break;
						}
						break;
					case "Millisekunder":
						switch (converterAnswerTimeType) {
						case "Nanosekunder":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) * 1000000) + "");
							break;
						case "Mikrosekunder":
							answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText()) * 1000) + "");
							break;
						case "Millisekunder":
							warningTimeBoxLabel.setText("Välj olika värdetyper!");
							break;
						case "Sekunder":
							answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText()) / 1000) + "");
							break;
						case "Minuter":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) / 1000 / 60) + "");
							break;
						case "Timmar":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) / 1000 / 3600) + "");
							break;
						case "Dagar":
							answerTimeTextField.setText(
									(Double.parseDouble(entryTimeTextField.getText()) / 1000 / 3600 / 24) + "");
							break;
						case "År - grundenhet":
							answerTimeTextField.setText(
									(Double.parseDouble(entryTimeTextField.getText()) / 1000 / 3600 / 24 / 365) + "");
							break;
						}
						break;
					case "Sekunder":
						switch (converterAnswerTimeType) {
						case "Nanosekunder":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) * 1000000000) + "");
							break;
						case "Mikrosekunder":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) * 1000000) + "");
							break;
						case "Millisekunder":
							answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText()) * 1000) + "");
							break;
						case "Sekunder":
							warningTimeBoxLabel.setText("Välj olika värdetyper!");
							break;
						case "Minuter":
							answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText()) / 60) + "");
							break;
						case "Timmar":
							answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText()) / 3600) + "");
							break;
						case "Dagar":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) / 3600 / 24) + "");
							break;
						case "År - grundenhet":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) / 3600 / 24 / 365) + "");
							break;
						}
						break;
					case "Minuter":
						switch (converterAnswerTimeType) {
						case "Nanosekunder":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) * 1000000000 * 60) + "");
							break;
						case "Mikrosekunder":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) * 1000000 * 60) + "");
							break;
						case "Millisekunder":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) * 1000 * 60) + "");
							break;
						case "Sekunder":
							answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText()) * 60) + "");
							break;
						case "Minuter":
							warningTimeBoxLabel.setText("Välj olika värdetyper!");
							break;
						case "Timmar":
							answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText()) / 60) + "");
							break;
						case "Dagar":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) / 60 / 24) + "");
							break;
						case "År - grundenhet":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) / 60 / 24 / 365) + "");
							break;
						}
						break;
					case "Timmar":
						switch (converterAnswerTimeType) {
						case "Nanosekunder":
							answerTimeTextField.setText(
									(Double.parseDouble(entryTimeTextField.getText()) * 1000000000 * 3600) + "");
							break;
						case "Mikrosekunder":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) * 1000000 * 3600) + "");
							break;
						case "Millisekunder":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) * 1000 * 3600) + "");
							break;
						case "Sekunder":
							answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText()) * 3600) + "");
							break;
						case "Minuter":
							answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText()) * 60) + "");
							break;
						case "Timmar":
							warningTimeBoxLabel.setText("Välj olika värdetyper!");
							break;
						case "Dagar":
							answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText()) / 24) + "");
							break;
						case "År - grundenhet":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) / 24 / 365) + "");
							break;
						}
						break;
					case "Dagar":
						switch (converterAnswerTimeType) {
						case "Nanosekunder":
							answerTimeTextField.setText(
									(Double.parseDouble(entryTimeTextField.getText()) * 1000000000 * 3600 * 24) + "");
							break;
						case "Mikrosekunder":
							answerTimeTextField.setText(
									(Double.parseDouble(entryTimeTextField.getText()) * 1000000 * 3600 * 24) + "");
							break;
						case "Millisekunder":
							answerTimeTextField.setText(
									(Double.parseDouble(entryTimeTextField.getText()) * 1000 * 3600 * 24) + "");
							break;
						case "Sekunder":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) * 3600 * 24) + "");
							break;
						case "Minuter":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) * 60 * 24) + "");
							break;
						case "Timmar":
							answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText()) * 24) + "");
							break;
						case "Dagar":
							warningTimeBoxLabel.setText("Välj olika värdetyper!");
							break;
						case "År - grundenhet":
							answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText()) / 365) + "");
							break;
						}
						break;
					case "År - grundenhet":
						switch (converterAnswerTimeType) {
						case "Nanosekunder":
							answerTimeTextField.setText(
									(Double.parseDouble(entryTimeTextField.getText()) * 1000000000 * 3600 * 24 * 365)
											+ "");
							break;
						case "Mikrosekunder":
							answerTimeTextField.setText(
									(Double.parseDouble(entryTimeTextField.getText()) * 1000000 * 3600 * 24 * 365)
											+ "");
							break;
						case "Millisekunder":
							answerTimeTextField.setText(
									(Double.parseDouble(entryTimeTextField.getText()) * 1000 * 3600 * 24 * 365) + "");
							break;
						case "Sekunder":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) * 3600 * 24 * 365) + "");
							break;
						case "Minuter":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) * 60 * 24 * 365) + "");
							break;
						case "Timmar":
							answerTimeTextField
									.setText((Double.parseDouble(entryTimeTextField.getText()) * 24 * 365) + "");
							break;
						case "Dagar":
							answerTimeTextField.setText((Double.parseDouble(entryTimeTextField.getText()) * 365) + "");
							break;
						case "År - grundenhet":
							warningTimeBoxLabel.setText("Välj olika värdetyper!");
							break;
						}
						break;
					}
				}
			}
		});
		timeConverterButton.setBounds(200, 61, 115, 20);
		convPanel.add(timeConverterButton);

		JButton speedConverterButton = new JButton("Konvertera");
		speedConverterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				warningSpeedBoxLabel.setText("");

				if (entrySpeedTextField.getText().equals("")) {
					answerSpeedTextField.setText("");
				} else {

					converterEntrySpeedType = entrySpeedBox.getSelectedItem().toString();
					converterAnswerSpeedType = answerSpeedBox.getSelectedItem().toString();

					switch (converterEntrySpeedType) {
					case "m/s - grundenhet":
						switch (converterAnswerSpeedType) {
						case "m/s - grundenhet":
							warningSpeedBoxLabel.setText("Välj olika värdetyper!");
							break;
						case "km/s":
							answerSpeedTextField
									.setText((Double.parseDouble(entrySpeedTextField.getText()) / 1000) + "");
							break;
						case "km/h":
							answerSpeedTextField
									.setText((Double.parseDouble(entrySpeedTextField.getText()) * 3.6) + "");
							break;
						}
						break;
					case "km/s":
						switch (converterAnswerSpeedType) {
						case "m/s - grundenhet":
							answerSpeedTextField
									.setText((Double.parseDouble(entrySpeedTextField.getText()) * 1000) + "");
							break;
						case "km/s":
							warningSpeedBoxLabel.setText("Välj olika värdetyper!");
							break;
						case "km/h":
							answerSpeedTextField
									.setText((Double.parseDouble(entrySpeedTextField.getText()) * 3600) + "");
							break;
						}
						break;
					case "km/h":
						switch (converterAnswerSpeedType) {
						case "m/s - grundenhet":
							answerSpeedTextField
									.setText((Double.parseDouble(entrySpeedTextField.getText()) / 3.6) + "");
							break;
						case "km/s":
							answerSpeedTextField
									.setText((Double.parseDouble(entrySpeedTextField.getText()) / 3600) + "");
							break;
						case "km/h":
							warningSpeedBoxLabel.setText("Välj olika värdetyper!");
							break;
						}
						break;

					}

				}
			}
		});
		speedConverterButton.setBounds(200, 371, 115, 20);
		convPanel.add(speedConverterButton);

		JButton lengthConverterButton = new JButton("Konvertera");
		lengthConverterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				warningLengthBoxLabel.setText("");

				if (entryLengthTextField.getText().equals("")) {
					answerLengthTextField.setText("");
				} else {

					converterEntryLengthType = entryLengthBox.getSelectedItem().toString();
					converterAnswerLengthType = answerLengthBox.getSelectedItem().toString();

					switch (converterEntryLengthType) {
					case "Millimeter":
						switch (converterAnswerLengthType) {
						case "Millimeter":
							warningLengthBoxLabel.setText("Välj olika värdetyper!");
							break;
						case "Centimeter":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) / 10) + "");
							break;
						case "Decimeter":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) / 100) + "");
							break;
						case "Meter - grundenhet":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) / 1000) + "");
							break;
						case "Kilometer":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) / 1000000) + "");
							break;
						}
						break;
					case "Centimeter":
						switch (converterAnswerLengthType) {
						case "Millimeter":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) * 10) + "");
							break;
						case "Centimeter":
							warningLengthBoxLabel.setText("Välj olika värdetyper!");
							break;
						case "Decimeter":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) / 10) + "");
							break;
						case "Meter - grundenhet":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) / 100) + "");
							break;
						case "Kilometer":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) / 100000) + "");
							break;
						}
						break;
					case "Decimeter":
						switch (converterAnswerLengthType) {
						case "Millimeter":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) * 100) + "");
							break;
						case "Centimeter":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) * 10) + "");
							break;
						case "Decimeter":
							warningLengthBoxLabel.setText("Välj olika värdetyper!");
							break;
						case "Meter - grundenhet":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) / 10) + "");
							break;
						case "Kilometer":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) / 10000) + "");
							break;
						}
						break;
					case "Meter - grundenhet":
						switch (converterAnswerLengthType) {
						case "Millimeter":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) * 1000) + "");
							break;
						case "Centimeter":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) * 100) + "");
							break;
						case "Decimeter":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) * 10) + "");
							break;
						case "Meter - grundenhet":
							warningLengthBoxLabel.setText("Välj olika värdetyper!");
							break;
						case "Kilometer":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) / 1000) + "");
							break;
						}
						break;
					case "Kilometer":
						switch (converterAnswerLengthType) {
						case "Millimeter":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) * 1000000) + "");
							break;
						case "Centimeter":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) * 100000) + "");
							break;
						case "Decimeter":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) * 10000) + "");
							break;
						case "Meter - grundenhet":
							answerLengthTextField
									.setText((Double.parseDouble(entryLengthTextField.getText()) * 1000) + "");
							break;
						case "Kilometer":
							warningLengthBoxLabel.setText("Välj olika värdetyper!");
							break;
						}
						break;
					}
				}
			}
		});
		lengthConverterButton.setBounds(200, 216, 115, 20);
		convPanel.add(lengthConverterButton);

	}
}
