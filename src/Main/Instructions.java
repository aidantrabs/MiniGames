import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;

public class Instructions extends JFrame implements ActionListener
{
	//Declare Global Variables

	//Declare and initialize 2 JPanel objects 
	private JPanel mainPanel = new JPanel();
	private JPanel optPanel = new JPanel();	

	//Declare and initialize ImageIcon object
	private ImageIcon img = new ImageIcon("instructions\\info.png");

	//Declare and initialize 2 JLabel objects for the title and image used 
	private JLabel titleLabel = new JLabel();
	private JLabel imgLabel = new JLabel();

	//Declare and initialize JLabel object for 'or'
	private JLabel orLabel = new JLabel("OR");

	//Declare and initialize ButtonGroup object
	private ButtonGroup group = new ButtonGroup();

	//Declare and initialize JTextArea object
	private JTextArea txtOutput = new JTextArea(12, 43);

	//Declare and initialize 2 JRadioButtons for selection choices
	private JRadioButton rbTic = new JRadioButton();
	private JRadioButton rbTaxi = new JRadioButton();

	//Declare and initialize Border object
	private Border lineBorder = BorderFactory.createLineBorder(new Color(0, 0, 102), 2);

	//Declare and initialize TitledBorder object 
	private TitledBorder titledBorder = BorderFactory.createTitledBorder(lineBorder, "Select A Game: ");

	public Instructions() 
	{
		//Set icon of image label
		imgLabel.setIcon(img);

		//Set properties of or JLabel 
		orLabel.setFont(new Font("Impact", Font.PLAIN, 25));
		orLabel.setHorizontalAlignment(JLabel.CENTER);

		//Set properties of titled border object
		titledBorder.setTitleFont(new Font("Impact", Font.ITALIC, 20));
		titledBorder.setTitleJustification(TitledBorder.CENTER);

		//Set properties of title label object
		titleLabel.setFont(new Font("Impact", Font.PLAIN, 50));
		titleLabel.setText("Instructions");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setForeground(new Color(0, 0, 102));

		//Set properties of JTextArea
		txtOutput.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 102), 1));
		txtOutput.setEditable(false);
		txtOutput.setFont(new Font("", Font.ITALIC, 17));
		txtOutput.setText("\n\n\n\n\t                Please Select a Game Option");

		//Set properties of the JRadioButton for Snake
		rbTic.setFont(new Font("Impact", Font.PLAIN, 25));
		rbTic.setText("Tic-Tac-Toe");
		rbTic.addActionListener(this);
		rbTic.setBackground(new Color(204, 255, 204));

		//Set properties of the JRadioButton for Crazy Taxi
		rbTaxi.setFont(new Font("Impact", Font.PLAIN, 25));
		rbTaxi.setText("Crazy Taxi");
		rbTaxi.addActionListener(this);
		rbTaxi.setBackground(new Color(204, 255, 204));

		//Add 2 JRadioButtons to group
		group.add(rbTic);
		group.add(rbTaxi);

		//Set properties of option mainPanel and add components
		optPanel.setLayout(new GridLayout(3, 1, 2, 2));
		optPanel.setBackground(new Color(204, 255, 204));
		optPanel.setBorder(titledBorder);
		optPanel.add(rbTic);
		optPanel.add(orLabel);
		optPanel.add(rbTaxi);

		//Set properties of mainPanel object and add components
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
		mainPanel.setBackground(new Color(204, 255, 204));
		mainPanel.add(titleLabel);
		mainPanel.add(imgLabel);
		mainPanel.add(optPanel);
		mainPanel.add(new JScrollPane(txtOutput, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

		//Set properties of JFrame
		setContentPane(mainPanel);
		setSize(650, 550);
		setLocationRelativeTo(null);
		setTitle("Instructions");
		setVisible(true);
		setResizable(false);
	}

	public void actionPerformed(ActionEvent e)
	{
		//Change font of JTextArea
		txtOutput.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		//If the user clicks the tic-tac-toe option
		if(e.getSource() == rbTic)
		{
			//If it is selected
			if(rbTic.isSelected())
			{
				//Set the text of JTextArea
				txtOutput.setText("\n\t\t   OBJECTIVE:\n\t- The players have to try to get 3 of their letter in a row,"
						+ "\n\t   either diagonally or horizontally"
						+ "\n\n\t\t       RULES:\n\t1. Only 2 players are allowed to play at a time"
						+ "\n\t2. Players must alternate turns each move, with X starting"
						+ "\n\t3. If no player gets 3 in a row when all squares are filled,"
						+ "\n\t   the result is a tie");
			}
		}

		//If the user clicks the taxi option
		if(e.getSource() == rbTaxi)
		{
			//If it is selected
			if(rbTaxi.isSelected())
			{
				//Set the text of the JTextArea
				txtOutput.setText("\n\t\t   OBJECTIVE:\n\t- The player is a taxi and you have to avoid the oncoming"
						+ "\n\t   traffic by moving vertically; either up or down and getting "
						+ "\n\t   the highest possible score"
						+ "\n\n\t\t       RULES:\n\t1. Use the arrow keys to move up or down"
						+ "\n\t2. Do not collide with oncoming traffic"
						+ "\n\t3. The score increases for every car avoided"
						+ "\n\t4. The game ends when a collision occurs");
			}
		}
	}

	public static void main(String[] args)
	{
		new Instructions();
	}
}