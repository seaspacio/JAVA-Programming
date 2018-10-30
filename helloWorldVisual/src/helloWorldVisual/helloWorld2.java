package helloWorldVisual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class helloWorld2 {

	private JFrame frame;
	private JTextField txtHelloWorld;
	private JButton btnShow;
	private JButton btnHide;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					helloWorld2 window = new helloWorld2();
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
	public helloWorld2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtHelloWorld = new JTextField();
		txtHelloWorld.setEditable(false);
		txtHelloWorld.setHorizontalAlignment(SwingConstants.CENTER);
		txtHelloWorld.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtHelloWorld.setBounds(32, 61, 367, 78);
		frame.getContentPane().add(txtHelloWorld);
		txtHelloWorld.setColumns(10);
		
		btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				
				txtHelloWorld.setText("Hello World Visual" );
			}
		});
		btnShow.setBounds(31, 174, 115, 29);
		frame.getContentPane().add(btnShow);
		
		btnHide = new JButton("Hide");
		btnHide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtHelloWorld.setText("");				
			}
		});
		btnHide.setBounds(284, 174, 115, 29);
		frame.getContentPane().add(btnHide);
	}
}
