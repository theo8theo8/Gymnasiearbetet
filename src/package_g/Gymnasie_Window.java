package package_g;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Insets;
import java.awt.Font;

public class Gymnasie_Window {

	private JFrame frame;
	private JTextField earthTimeTextField;
	private JTextField lengthTextField;
	private JTextField timeAnswerTextField;
	private JTextField lengthAnswerTextField;
	private JTextField speedTextField;
	
	double tStill;
	double t;
	double lStill;
	double l;
	double gamma;
	double v; 
	double c = 299792.458;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Gymnasie_Window window = new Gymnasie_Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gymnasie_Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		earthTimeTextField = new JTextField();
		earthTimeTextField.setToolTipText(Strings.getString("Gymnasie_Window.earthTimeTextField.toolTipText")); //$NON-NLS-1$
		earthTimeTextField.setBounds(10, 53, 130, 31);
		frame.getContentPane().add(earthTimeTextField);
		earthTimeTextField.setColumns(10);
		
		JButton answerButton = new JButton(Strings.getString("Gymnasie_Window.answerButton.text"));
		answerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    System.out.println("Vi har en fungerande knapp");
			    setParameters();
			    solveGamma();
			    solveTime();
			    solveLength();
			    showTime();
			    showLength();
			}

			private void setParameters() {
				if (earthTimeTextField.getText().equals("")) {tStill = 0;}else{tStill = Double.parseDouble(earthTimeTextField.getText());}
				if (lengthTextField.getText().equals("")) {lStill = 0;}else{lStill = Double.parseDouble(lengthTextField.getText());}
				if (speedTextField.getText().equals("")) {v = 0;}else{v = Double.parseDouble(speedTextField.getText());}
				
			}

			private void showTime() {
				timeAnswerTextField.setText(t+"");
			}

			private void showLength() {
				lengthAnswerTextField.setText(l+"");
			}

			private void solveLength() {
				if (lengthTextField.getText().equals("")) {
					
				}else {
					t = tStill / gamma;
				}
				l = lStill / gamma;
				System.out.println("l" + l);
			}

			private void solveTime() {
				if (earthTimeTextField.getText().equals("")) {
	
				}else {
					t = tStill / gamma;
				}
				System.out.println("t" + t);
			}

			private void solveGamma() {
				gamma = (1/(Math.sqrt(1-(Math.pow(v, 2)/Math.pow(c, 2)))));
				System.out.println("gamma" + gamma);
			}
		});
		answerButton.setBounds(93, 240, 130, 52);
		frame.getContentPane().add(answerButton);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(Color.ORANGE);
		canvas.setBounds(332, 184, 342, 267);
		frame.getContentPane().add(canvas);
		
		//Bakåtknappen
		JButton backButton = new JButton(Strings.getString("Gymnasie_Window.backButton.text")); //$NON-NLS-1$
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		backButton.setBounds(595, 11, 79, 52);
		frame.getContentPane().add(backButton);
		
		lengthTextField = new JTextField();
		lengthTextField.setToolTipText(Strings.getString("Gymnasie_Window.lengthTextField.toolTipText")); //$NON-NLS-1$
		lengthTextField.setColumns(10);
		lengthTextField.setBounds(175, 53, 130, 31);
		frame.getContentPane().add(lengthTextField);
		
		timeAnswerTextField = new JTextField();
		timeAnswerTextField.setEditable(false);
		timeAnswerTextField.setColumns(10);
		timeAnswerTextField.setBounds(10, 305, 130, 52);
		frame.getContentPane().add(timeAnswerTextField);
		
		lengthAnswerTextField = new JTextField();
		lengthAnswerTextField.setEditable(false);
		lengthAnswerTextField.setColumns(10);
		lengthAnswerTextField.setBounds(175, 305, 130, 52);
		frame.getContentPane().add(lengthAnswerTextField);
		
		JSlider speedSlider = new JSlider(0,299793,150000);
		speedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				speedTextField.setText(String.valueOf(speedSlider.getValue()));
			}
		});
		speedSlider.setValue(150000);
		speedSlider.setMaximum(299793);
		speedSlider.setBounds(10, 119, 295, 26);
		frame.getContentPane().add(speedSlider);
		
		JTextArea txtrTidSomHar = new JTextArea();
		txtrTidSomHar.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		txtrTidSomHar.setOpaque(false);
		txtrTidSomHar.setText(Strings.getString("Gymnasie_Window.txtrTidSomHar.text")); //$NON-NLS-1$
		txtrTidSomHar.setBounds(10, 11, 130, 40);
		frame.getContentPane().add(txtrTidSomHar);
		
		JTextArea txtrObjektetsLngd = new JTextArea();
		txtrObjektetsLngd.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		txtrObjektetsLngd.setOpaque(false);
		txtrObjektetsLngd.setText(Strings.getString("Gymnasie_Window.txtrObjektetsLngd.text")); //$NON-NLS-1$
		txtrObjektetsLngd.setBounds(175, 26, 130, 26);
		frame.getContentPane().add(txtrObjektetsLngd);
		
		JTextArea txtrObjektetsHastighetI = new JTextArea();
		txtrObjektetsHastighetI.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		txtrObjektetsHastighetI.setOpaque(false);
		txtrObjektetsHastighetI.setText(Strings.getString("Gymnasie_Window.txtrObjektetsHastighetI.text")); //$NON-NLS-1$
		txtrObjektetsHastighetI.setBounds(10, 92, 295, 22);
		frame.getContentPane().add(txtrObjektetsHastighetI);
		
		speedTextField = new JTextField();
		speedTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent ke) {
				String typed = speedTextField.getText();
				speedSlider.setValue(0);
				if(!typed.matches("\\d+") || typed.length() > 3) {
					return;
				}
				double value = Double.parseDouble(typed);
				speedSlider.setValue((int)value);
			}
		});
		speedTextField.setText(Strings.getString("Gymnasie_Window.textField.text")); //$NON-NLS-1$
		speedTextField.setToolTipText(Strings.getString("Gymnasie_Window.textField.toolTipText")); //$NON-NLS-1$
		speedTextField.setBounds(135, 156, 45, 25);
		frame.getContentPane().add(speedTextField);
		speedTextField.setColumns(10);
		
		JButton button_n1 = new JButton(Strings.getString("Gymnasie_Window.button_4.text")); //$NON-NLS-1$
		button_n1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				speedSlider.setValue(speedSlider.getValue()-1);
			}
		});
		button_n1.setMargin(new Insets(0, 0, 0, 0));
		button_n1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_n1.setBounds(110, 156, 25, 25);
		frame.getContentPane().add(button_n1);
		
		JButton button_n10 = new JButton(Strings.getString("Gymnasie_Window.button.text")); //$NON-NLS-1$
		button_n10.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				speedSlider.setValue(speedSlider.getValue()-10);
			}
		});
		button_n10.setMargin(new Insets(0, 0, 0, 0));
		button_n10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_n10.setBounds(85, 156, 25, 25);
		frame.getContentPane().add(button_n10);
		
		JButton button_n100 = new JButton(Strings.getString("Gymnasie_Window.button_1.text_1")); //$NON-NLS-1$
		button_n100.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				speedSlider.setValue(speedSlider.getValue()-100);
			}
		});
		button_n100.setMargin(new Insets(0, 0, 0, 0));
		button_n100.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_n100.setBounds(60, 156, 25, 25);
		frame.getContentPane().add(button_n100);
		
		JButton button_n1000 = new JButton(Strings.getString("Gymnasie_Window.button_2.text")); //$NON-NLS-1$
		button_n1000.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				speedSlider.setValue(speedSlider.getValue()-1000);
			}
		});
		button_n1000.setMargin(new Insets(0, 0, 0, 0));
		button_n1000.setFont(new Font("Tahoma", Font.PLAIN, 7));
		button_n1000.setBounds(35, 156, 25, 25);
		frame.getContentPane().add(button_n1000);
		
		JButton button_n10000 = new JButton(Strings.getString("Gymnasie_Window.button_3.text")); //$NON-NLS-1$
		button_n10000.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				speedSlider.setValue(speedSlider.getValue()-10000);
				speedTextField.setText(String.valueOf(speedSlider.getValue()));
			}
		});
		button_n10000.setMargin(new Insets(0, 0, 0, 0));
		button_n10000.setFont(new Font("Tahoma", Font.PLAIN, 6));
		button_n10000.setBounds(10, 156, 25, 25);
		frame.getContentPane().add(button_n10000);
		
		JButton button_p10000 = new JButton(Strings.getString("Gymnasie_Window.button_7.text")); //$NON-NLS-1$
		button_p10000.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				speedSlider.setValue(speedSlider.getValue()+10000);
			}
		});
		button_p10000.setMargin(new Insets(0, 0, 0, 0));
		button_p10000.setFont(new Font("Tahoma", Font.PLAIN, 6));
		button_p10000.setBounds(280, 156, 25, 25);
		frame.getContentPane().add(button_p10000);
		
		JButton button_p1000 = new JButton(Strings.getString("Gymnasie_Window.button_8.text")); //$NON-NLS-1$
		button_p1000.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				speedSlider.setValue(speedSlider.getValue()+1000);
			}
		});
		button_p1000.setMargin(new Insets(0, 0, 0, 0));
		button_p1000.setFont(new Font("Tahoma", Font.PLAIN, 6));
		button_p1000.setBounds(255, 156, 25, 25);
		frame.getContentPane().add(button_p1000);
		
		JButton button_p100 = new JButton(Strings.getString("Gymnasie_Window.button_9.text")); //$NON-NLS-1$
		button_p100.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				speedSlider.setValue(speedSlider.getValue()+100);
			}
		});
		button_p100.setMargin(new Insets(0, 0, 0, 0));
		button_p100.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button_p100.setBounds(230, 156, 25, 25);
		frame.getContentPane().add(button_p100);
		
		JButton button_p10 = new JButton(Strings.getString("Gymnasie_Window.button_6.text")); //$NON-NLS-1$
		button_p10.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				speedSlider.setValue(speedSlider.getValue()+10);
			}
		});
		button_p10.setMargin(new Insets(0, 0, 0, 0));
		button_p10.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_p10.setBounds(205, 156, 25, 25);
		frame.getContentPane().add(button_p10);
		
		JButton button_p1 = new JButton("+1");
		button_p1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				speedSlider.setValue(speedSlider.getValue()+1);
			}
		});
		button_p1.setMargin(new Insets(0, 0, 0, 0));
		button_p1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_p1.setBounds(180, 156, 25, 25);
		frame.getContentPane().add(button_p1);
	}
}
