
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import javax.swing.border.*;

public class MiniGames extends JFrame implements ActionListener
{
	//Declare and initialize global variables
	
	//Declare and initialize 2 JPanel objects
	private JPanel mainPanel = new JPanel();
	private JPanel selectionPanel = new JPanel();

	//Declare and initialize title JLabel object
	private JLabel titleLabel = new JLabel();

	//Declare and initialize JLabel objects for exit screen
	private JLabel outputLabel = new JLabel("THANK YOU FOR PLAYING!   ");
	private JLabel confirmLabel = new JLabel("ARE YOU SURE YOU WANT TO EXIT?   ");

	//Declare and initialize 2 JLabel objects for flame gifs 
	private JLabel flameLabel = new JLabel();
	private JLabel flameLabel2 = new JLabel();
	
	//Declare and initialize 2 JLabel objects for column image 
	private JLabel columnLabel = new JLabel();
	private JLabel columnLabel2 = new JLabel();
	
	//Declare and initialize JLabel object for name
	private JLabel nameLabel = new JLabel();
	
	//Declare and initialize JLabel object for 'or'
	private JLabel orLabel = new JLabel("OR");

	//Declare and initialize all ImageIcon objects for buttons, exit screen, flames, columns and title label
	private ImageIcon imgTitle = new ImageIcon("/assets/mainmenu/title.png");
	private ImageIcon imgTaxi = new ImageIcon("assets\\mainmenu\\crazytaxi.png");
	private ImageIcon imgTic = new ImageIcon("assets\\mainmenu\\tictactoe.png");
	private ImageIcon imgExit = new ImageIcon("assets\\mainmenu\\exit.png");
	private ImageIcon imgInstructions = new ImageIcon("assets\\mainmenu\\instructions.png");
	private ImageIcon imgSmiley = new ImageIcon("assets\\mainmenu\\smiley.png");
	private ImageIcon imgSadSmiley = new ImageIcon("assets\\mainmenu\\sadSmiley.png");
	private ImageIcon imgFlame = new ImageIcon("assets\\mainmenu\\flame.gif");
	private ImageIcon imgColumn = new ImageIcon("assets\\mainmenu\\column.png");

	//Declare and initialize 2 JButton objects for exit and instructionscd
	private JButton exitBtn = new JButton();
	private JButton instructionsBtn = new JButton();

	//Declare and initialize 2 JButton objects for game selections
	private JButton ticBtn = new JButton();
	private JButton taxiBtn = new JButton();

	//Declare and initialize Dimension object for buttons
	private Dimension d = new Dimension(120, 40);

	//Declare and initialize border for selection panel
	private Border lineBorder = BorderFactory.createLineBorder(Color.MAGENTA, 2);
	private TitledBorder titledBorder = BorderFactory.createTitledBorder(lineBorder, "Select A Game: ");

	public MiniGames()
	{
		//Set icons of title label, flame labels and column labels
		titleLabel.setIcon(imgTitle);
		flameLabel.setIcon(imgFlame);
		flameLabel2.setIcon(imgFlame);
		columnLabel.setIcon(imgColumn);
		columnLabel2.setIcon(imgColumn);

		//Set fonts of exit screen text
		outputLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		confirmLabel.setFont(new Font("Impact", Font.PLAIN, 20));
		
		//Set properties of name label
		nameLabel.setFont(new Font("Impact", Font.ITALIC, 23));
		nameLabel.setText("Created By: Aidan Traboulay");
		nameLabel.setPreferredSize(new Dimension(310, 35));
		nameLabel.setOpaque(true);
		nameLabel.setBackground(new Color(240,248,255));
		nameLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		nameLabel.setHorizontalAlignment(nameLabel.CENTER);
		
		//Set properties of border for selection panel
		titledBorder.setTitleFont(new Font("Impact", Font.ITALIC, 28));
		titledBorder.setTitleJustification(TitledBorder.CENTER);

		//Set properties of instructions button 
		instructionsBtn.addActionListener(this);
		instructionsBtn.setPreferredSize(d);
		instructionsBtn.setContentAreaFilled(false);
		instructionsBtn.setFocusPainted(false);
		instructionsBtn.setBorderPainted(false);
		instructionsBtn.setIcon(imgInstructions);

		//Set properties of exit button
		exitBtn.addActionListener(this);
		exitBtn.setContentAreaFilled(false);
		exitBtn.setFocusPainted(false);
		exitBtn.setBorderPainted(false);
		exitBtn.setIcon(imgExit);
		exitBtn.setPreferredSize(d);

		//Set properties of or JLabel 
		orLabel.setFont(new Font("Impact", Font.PLAIN, 40));
		orLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//Set properties of tic-tac-toe button
		ticBtn.setIcon(imgTic);
		ticBtn.setContentAreaFilled(false);
		ticBtn.setFocusPainted(false);
		ticBtn.setBorderPainted(false);
		ticBtn.addActionListener(this);

		//Set properties of crazy taxi button
		taxiBtn.setIcon(imgTaxi);
		taxiBtn.setContentAreaFilled(false);
		taxiBtn.setFocusPainted(false);
		taxiBtn.setBorderPainted(false);
		taxiBtn.addActionListener(this);

		//Set properties of selection panel and add components
		selectionPanel.setLayout(new GridLayout(3, 1, 2, 2));
		selectionPanel.setBackground(new Color(240,248,255));
		selectionPanel.setBorder(titledBorder);
		selectionPanel.add(ticBtn);
		selectionPanel.add(orLabel);
		selectionPanel.add(taxiBtn);

		//Set properties of main panel
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setBackground(new Color(240,248,255));

		//Declare and initialize new GridBagConstraints object
		GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets(10, 10, 10, 10);
		
		//Set GridBagConstraints properties for the flame label and add to panel
		gc.gridx = 0;
		gc.gridy = 0;
		mainPanel.add(flameLabel, gc);
		
		//Set GridBagConstraints properties for the title label and add to panel
		gc.gridx = 1;
		mainPanel.add(titleLabel, gc);

		//Set GridBagConstraints properties for the second flame label and add to panel
		gc.gridx = 2;
		mainPanel.add(flameLabel2, gc);
		
		//Set GridBagConstraints properties for the column label and add to panel
		gc.gridx = 0;
		gc.gridy = 1;
		mainPanel.add(columnLabel, gc);
		
		//Set GridBagConstraints properties for the second column label and add to panel
		gc.gridx = 2;
		gc.gridy = 1;
		mainPanel.add(columnLabel2, gc);

		//Set GridBagConstraints properties for the selection panel and add to panel
		gc.gridx = 1;
		gc.gridy = 1;
		gc.gridheight = 2;
		mainPanel.add(selectionPanel, gc);

		//Set GridBagConstraints properties for the instructions button and add to panel
		gc.gridy = 3;
		gc.gridheight = 1;
		gc.fill = GridBagConstraints.BOTH;
		mainPanel.add(instructionsBtn, gc);

		//Set GridBagConstraints properties for the exit button and add to panel
		gc.gridx = 0;
		gc.gridy = 3;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.NONE;
		mainPanel.add(exitBtn, gc);
		
		//Set GridBagConstraints properties for the name label and add to panel
		gc.gridx = 1;
		gc.gridy = 4;
		mainPanel.add(nameLabel, gc);

		//Set properties of JFrame
		setContentPane(mainPanel);
		setTitle("Mini Games");
		setSize(900, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		//If the user clicks the tic-tac-toe game button, create a new tic-tac-toe game object
		if(e.getSource() == ticBtn)
		{
			new TicTacToe();
		}

		//If the user clicks the crazy taxi game button, create a new crazy taxi game object
		if(e.getSource() == taxiBtn)
		{
			new CrazyTaxi();
		}

		//If the user clicks the instructions button, create a new instructions panel object
		if(e.getSource() == instructionsBtn)
		{
			new Instructions();
		}

		//If the user clicks the exit button
		if(e.getSource() == exitBtn)
		{
			//Create an int variable holding the option selected by user when prompted
			int option = JOptionPane.showConfirmDialog(null, confirmLabel, "Mini Games", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
					imgSadSmiley);

			//If the user clicks the yes option, terminate the program
			if(option == JOptionPane.YES_OPTION)
			{
				JOptionPane.showMessageDialog(null, outputLabel, "Mini Games", JOptionPane.INFORMATION_MESSAGE, imgSmiley);
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) 
	{
		new MiniGames();
	}
}