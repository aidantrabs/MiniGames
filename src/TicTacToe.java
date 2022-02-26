import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class TicTacToe extends JFrame implements ActionListener
{	
	//Declare global variables 
	
	//Declare and initialize 2 JPanel objects 
	private JPanel mainPanel = new JPanel();
	private JPanel btnPanel = new JPanel();

	//Declare and initialize 2D button array
	private JButton[][] btn = new JButton[3][3];

	//Declare and initialize JLabel for title
	private JLabel titleLabel = new JLabel();

	//Declare and initialize JLabel for JOptionPane (player x)
	private JLabel oWinLabel = new JLabel("PLAYER O WINS!  ");

	//Declare and initialize JLabel for JOptionPane (player o)
	private JLabel xWinLabel = new JLabel("PLAYER X WINS!  ");

	//Declare and initialize JLabel for JOptionPane (tie)
	private JLabel tieLabel = new JLabel("IT'S A TIE!  ");
	
	//Declare and initialize JLabel for play again JOptionPane
	private JLabel playAgainLabel = new JLabel("Would You Like To Play Again?   ");

	//Declare and initialize JLabel for timer
	private JLabel timerLabel = new JLabel();

	//Declare and initialize 2 ImageIcon objects for both players
	private ImageIcon imgX = new ImageIcon("assets\\tictactoe\\x.png");
	private ImageIcon imgO = new ImageIcon("assets\\tictactoe\\o.png");

	//Declare and initialize 2 ImageIcon objects for win and tie JOptionPanes
	private ImageIcon imgWin = new ImageIcon("assets\\tictactoe\\win.png");
	private ImageIcon imgTie = new ImageIcon("assets\\tictactoe\\tie.png");
	
	//Declare and initialize play again ImageIcon object 
	private ImageIcon imgPlayAgain = new ImageIcon("assets\\tictactoe\\play again.png");

	//Declare and initialize Dimension object 
	private Dimension d = new Dimension(130, 130);

	//Declare and initialize int variable for number of clicks
	private int numClicks = 0;

	//Declare and initialize timer object
	private Timer t = new Timer(1000, this);

	//Declare and initialize DecimalFormat
	private DecimalFormat df = new DecimalFormat(":00");

	//Declare and initialize 2 int variables for minutes and seconds for the timer
	private int mins = 0;
	private int secs = 0;

	public TicTacToe() 
	{
		//Set properties of title label
		titleLabel.setFont(new Font("Impact", Font.BOLD, 45));
		titleLabel.setText("TIC TAC TOE");

		//Set properties of wins/tie label 
		oWinLabel.setFont(new Font("Impact", Font.ITALIC, 25));
		xWinLabel.setFont(new Font("Impact", Font.ITALIC, 25));
		tieLabel.setFont(new Font("Impact", Font.ITALIC, 25));

		//Set properties of timer label
		timerLabel.setFont(new Font("Impact", Font.PLAIN, 23));
		timerLabel.setText("Time Elapsed: ");
		
		//Set properties of play again label
		playAgainLabel.setFont(new Font("Impact", Font.ITALIC, 23));

		//Set properties of button panel
		btnPanel.setLayout(new GridLayout(3, 3, 5, 5));
		btnPanel.setBackground(new Color(231, 234, 212));

		//Create a loop that starts at 0 and increments until the number of rows of the button array is met
		for (int i = 0; i < btn.length; i++)
		{
			//Create a loop that starts at 0 and increments until the number of columns of the button array for each row is met
			for (int j = 0; j < btn[i].length; j++)
			{
				//Set properties of JButton 2D array and add to panel
				btn[i][j] = new JButton();
				btn[i][j].setPreferredSize(d);
				btn[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				btn[i][j].setMargin(new Insets(0, 0, 0, 0));
				btn[i][j].setBackground(Color.WHITE);
				btn[i][j].setActionCommand(i + "," + j);
				btn[i][j].addActionListener(this);
				btnPanel.add(btn[i][j]);
			}
		}

		//Set properties of main panel add components
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		mainPanel.setBackground(new Color(231, 234, 212));
		mainPanel.add(titleLabel);
		mainPanel.add(btnPanel);
		mainPanel.add(timerLabel);

		//Set properties of JFrame
		setContentPane(mainPanel);
		setSize(550, 600);
		setLocationRelativeTo(null);
		setTitle("Tic Tac Toe");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) 
	{
		//Create a loop that starts at 0 and increments until the number of rows of the button array is met
		for (int i = 0; i < btn.length; i++)
		{
			//Create a loop that starts at 0 and increments until the number of columns of the button array for each row is met
			for (int j = 0; j < btn[i].length; j++)
			{
				//If the user clicks any button
				if(e.getSource() == btn[i][j])
				{
					//Start the timer
					t.start();

					//Increase the number of clicks by 1
					numClicks++;

					//If the number of clicks is even , set the icon to the O image and remove the action listener- this is player O
					if(numClicks % 2 == 0)
					{
						btn[i][j].setIcon(imgO);
						btn[i][j].removeActionListener(this);
					}

					//If the number of clicks is odd, set the icon to the X image and remove the action listener - this is player X
					else
					{
						btn[i][j].setIcon(imgX);
						btn[i][j].removeActionListener(this);
					}

					//Check all possible combinations for player O
					if((btn[0][0].getIcon() == imgO && btn[0][1].getIcon() == imgO && btn[0][2].getIcon() == imgO) 
							|| (btn[1][0].getIcon() == imgO && btn[1][1].getIcon() == imgO && btn[1][2].getIcon() == imgO)
							|| (btn[2][0].getIcon() == imgO && btn[2][1].getIcon() == imgO && btn[2][2].getIcon() == imgO)
							|| (btn[0][0].getIcon() == imgO && btn[1][0].getIcon() == imgO && btn[2][0].getIcon() == imgO)
							|| (btn[0][1].getIcon() == imgO && btn[1][1].getIcon() == imgO && btn[2][1].getIcon() == imgO)
							|| (btn[0][2].getIcon() == imgO && btn[1][2].getIcon() == imgO && btn[2][2].getIcon() == imgO)
							|| (btn[0][0].getIcon() == imgO && btn[1][1].getIcon() == imgO && btn[2][2].getIcon() == imgO)
							|| (btn[0][2].getIcon() == imgO && btn[1][1].getIcon() == imgO && btn[2][0].getIcon() == imgO))
					{
						//Stop the timer
						t.stop();

						//Display message indicating that player O has won
						JOptionPane.showMessageDialog(null, oWinLabel, "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE, imgWin);

						//Call play again method
						playAgain();
					}

					//Check all possible combinations for player X
					else if((btn[0][0].getIcon() == imgX && btn[0][1].getIcon() == imgX && btn[0][2].getIcon() == imgX)
							|| (btn[1][0].getIcon() == imgX && btn[1][1].getIcon() == imgX && btn[1][2].getIcon() == imgX)
							|| (btn[2][0].getIcon() == imgX && btn[2][1].getIcon() == imgX && btn[2][2].getIcon() == imgX)
							|| (btn[0][0].getIcon() == imgX && btn[1][0].getIcon() == imgX && btn[2][0].getIcon() == imgX)
							|| (btn[0][1].getIcon() == imgX && btn[1][1].getIcon() == imgX && btn[2][1].getIcon() == imgX)
							|| (btn[0][2].getIcon() == imgX && btn[1][2].getIcon() == imgX && btn[2][2].getIcon() == imgX)
							|| (btn[0][0].getIcon() == imgX && btn[1][1].getIcon() == imgX && btn[2][2].getIcon() == imgX)
							|| (btn[0][2].getIcon() == imgX && btn[1][1].getIcon() == imgX && btn[2][0].getIcon() == imgX))
					{
						//Stop the timer
						t.stop();

						//Display message indicating that player X has won
						JOptionPane.showMessageDialog(null, xWinLabel, "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE, imgWin);

						//Call play again method
						playAgain();
					}

					//Check if the board is full - this would indicate neither played has achieved 3 in a row - therefore a tie
					else if(numClicks == 9)
					{
						//Stop the timer
						t.stop();

						//Display message indicating that a tie has occurred 
						JOptionPane.showMessageDialog(null, tieLabel, "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE, imgTie);

						//Call play again method
						playAgain();
					}
				}
			}
		}

		//If the timer fires an actionevent
		if(e.getSource() == t)
		{
			secs++;

			//Check if the timer has passed 59 secs, if so reset the seconds to 0 and increment the minutes variable
			if(secs > 59)
			{
				secs = 0;
				mins++;				
			}

			//Set the text of the timer label to the minutes and seconds
			timerLabel.setText("Time Elapsed: " + mins + "" + df.format(secs));			
		}
	}

	//Play again method
	public void playAgain()
	{
		//Create an int variable holding the option selected by user when prompted
		int option = JOptionPane.showConfirmDialog(null, playAgainLabel, "Tic Tac Toe", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
				imgPlayAgain);

		//If the user clicks the no option, close the window
		if(option == JOptionPane.NO_OPTION)
		{
			super.dispose();
		}

		//If the user clicks yes, close original panel and create a new game
		else if(option == JOptionPane.YES_OPTION)
		{
			super.dispose();
			new TicTacToe();
		}

		//If the user tries to close the window without selecting an option
		else
		{
			//Display message indicating the user needs to choose an option
			JOptionPane.showMessageDialog(null, "Please select an option!", "Tic Tac Toe", JOptionPane.ERROR_MESSAGE);

			//Create an int variable holding the option selected by user when prompted again
			int opt = JOptionPane.showConfirmDialog(null, playAgainLabel, "Tic Tac Toe", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
					imgPlayAgain);

			//If the user clicks the no option, close the window
			if(opt == JOptionPane.NO_OPTION)
			{
				super.dispose();
			}

			//If the user clicks yes, close original panel and create a new game
			else if(opt == JOptionPane.YES_OPTION)
			{
				super.dispose();
				new TicTacToe();
			}
		}
	}

	public static void main(String[] args)
	{
		new TicTacToe();
	}
}