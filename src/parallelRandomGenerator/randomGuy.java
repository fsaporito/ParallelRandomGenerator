package parallelRandomGenerator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Choice;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class randomGuy implements ActionListener {

	private JFrame frame;
	private JPanel panel;
	private JLabel rangeLabel;
	private JSpinner spinnerRange;
	private JSpinner spinnerSize;
	private JSeparator separator;
	private JSeparator separator2;
	private JSeparator separator3;
	private JLabel labelNumberSize;
	private Choice choice;
	private Button buttonRun;
	private JTextArea textArea;
	
	private int range;
	private int size;
	
	private int seed = 748;
	private String type;
	

	/**
	 * Launch the application.
	 */
 	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					randomGuy window = new randomGuy();
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
	public randomGuy() {
			
		initialize();
	
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		rangeLabel = new JLabel("Range");
		panel.add(rangeLabel);
		
		spinnerRange = new JSpinner();
		panel.add(spinnerRange);
		
		separator = new JSeparator();
		panel.add(separator);
		
		labelNumberSize = new JLabel("Number Size");
		panel.add(labelNumberSize);
		
		spinnerSize = new JSpinner();
		panel.add(spinnerSize);
		
		JSeparator separator2 = new JSeparator();
		panel.add(separator2);
		
		choice = new Choice();
		choice.add("Integer");
		choice.add("Double");
		choice.add("Character");
		panel.add(choice);
		
		separator3 = new JSeparator();
		panel.add(separator3);
		
		buttonRun = new Button("Run");
		buttonRun.addActionListener(this);
		panel.add(buttonRun);
		
		textArea = new JTextArea();
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.buttonRun) {
			
			this.type = this.choice.getSelectedItem();
			
			this.range = (Integer) this.spinnerRange.getValue();
			
			this.size = (Integer) this.spinnerSize.getValue();
			
			if (this.type.equals("Integer")) {
			
				ParallelRandInt rand = new ParallelRandInt(seed, range, size);
			
				this.textArea.setText(rand.toString());
				
			}
			
			if (this.type.equals("Double")) {
				
				ParallelRandDouble rand = new ParallelRandDouble (seed, range, size);
			
				this.textArea.setText(rand.toString());
				
			}
			
			if (this.type.equals("Character")) {
				
				ParallelRandChar rand = new ParallelRandChar(seed, size);
			
				this.textArea.setText(rand.toString());
				
			}
			
			
		}
		
	}

}
