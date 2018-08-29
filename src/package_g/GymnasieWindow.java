package package_g;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GymnasieWindow {

	private JFrame frame;
	private JTextField earthTimeTextField;
	private JTextField lengthTextField;
	private JTextField timeAnswerTextField;
	private JTextField lengthAnswerTextField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GymnasieWindow window = new GymnasieWindow();
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
	public GymnasieWindow() {
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
		earthTimeTextField.setToolTipText("T.ex 5 \u00E5r");
		earthTimeTextField.setBounds(10, 53, 130, 31);
		frame.getContentPane().add(earthTimeTextField);
		earthTimeTextField.setColumns(10);
		
		JButton answerButton = new JButton("Räkna ut");
		answerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    System.out.println("Vi har en fungerande knapp");
			}
		});
		answerButton.setBounds(80, 240, 130, 52);
		frame.getContentPane().add(answerButton);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(Color.ORANGE);
		canvas.setBounds(332, 184, 342, 267);
		frame.getContentPane().add(canvas);
		
		JButton backButton = new JButton("Tillbaka");
		backButton.setBounds(595, 11, 79, 52);
		frame.getContentPane().add(backButton);
		
		lengthTextField = new JTextField();
		lengthTextField.setToolTipText("T.ex 30 m");
		lengthTextField.setColumns(10);
		lengthTextField.setBounds(152, 53, 131, 31);
		frame.getContentPane().add(lengthTextField);
		
		timeAnswerTextField = new JTextField();
		timeAnswerTextField.setEditable(false);
		timeAnswerTextField.setColumns(10);
		timeAnswerTextField.setBounds(10, 305, 130, 52);
		frame.getContentPane().add(timeAnswerTextField);
		
		lengthAnswerTextField = new JTextField();
		lengthAnswerTextField.setEditable(false);
		lengthAnswerTextField.setColumns(10);
		lengthAnswerTextField.setBounds(153, 305, 130, 52);
		frame.getContentPane().add(lengthAnswerTextField);
		
		JSlider slider = new JSlider();
		slider.setValue(150000);
		slider.setMaximum(300000);
		slider.setBounds(10, 119, 273, 26);
		frame.getContentPane().add(slider);
		
		JTextArea txtrTidSomHar = new JTextArea();
		txtrTidSomHar.setText("Tid som har\r\ng\u00E5tt p\u00E5 jorden");
		txtrTidSomHar.setBounds(10, 11, 124, 40);
		frame.getContentPane().add(txtrTidSomHar);
		
		JTextArea txtrObjektetsLngd = new JTextArea();
		txtrObjektetsLngd.setText("Objektets l\u00E4ngd");
		txtrObjektetsLngd.setBounds(152, 26, 124, 26);
		frame.getContentPane().add(txtrObjektetsLngd);
		
		JTextArea txtrObjektetsHastighetI = new JTextArea();
		txtrObjektetsHastighetI.setText("Objektets hastighet i km/s");
		txtrObjektetsHastighetI.setBounds(44, 92, 220, 22);
		frame.getContentPane().add(txtrObjektetsHastighetI);
		
		textField = new JTextField();
		textField.setText("150000");
		textField.setToolTipText("T.ex 290000 km/s");
		textField.setBounds(10, 158, 273, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
