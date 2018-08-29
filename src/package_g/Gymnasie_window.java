package package_g;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Canvas;

public class Gymnasie_window {

	private JFrame frame;
	private JTextField earthTimeTextField;
	private JTextField lengthTextField;
	private JTextField speedTextField;
	private JTextField timeAnswerTextField;
	private JTextField lengthAnswerTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gymnasie_window window = new Gymnasie_window();
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
	public Gymnasie_window() {
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
		earthTimeTextField.setBounds(10, 21, 130, 52);
		frame.getContentPane().add(earthTimeTextField);
		earthTimeTextField.setColumns(10);
		
		JButton answerButton = new JButton("Räkna ut");
		answerButton.setBounds(10, 84, 130, 52);
		frame.getContentPane().add(answerButton);
		
		Canvas canvas = new Canvas();
		canvas.setBounds(332, 184, 342, 267);
		frame.getContentPane().add(canvas);
		
		JButton backButton = new JButton("Tillbaka");
		backButton.setBounds(595, 11, 79, 52);
		frame.getContentPane().add(backButton);
		
		lengthTextField = new JTextField();
		lengthTextField.setColumns(10);
		lengthTextField.setBounds(153, 21, 130, 52);
		frame.getContentPane().add(lengthTextField);
		
		speedTextField = new JTextField();
		speedTextField.setColumns(10);
		speedTextField.setBounds(153, 84, 130, 52);
		frame.getContentPane().add(speedTextField);
		
		timeAnswerTextField = new JTextField();
		timeAnswerTextField.setColumns(10);
		timeAnswerTextField.setBounds(10, 184, 130, 52);
		frame.getContentPane().add(timeAnswerTextField);
		
		lengthAnswerTextField = new JTextField();
		lengthAnswerTextField.setColumns(10);
		lengthAnswerTextField.setBounds(10, 247, 130, 52);
		frame.getContentPane().add(lengthAnswerTextField);
	}
}
