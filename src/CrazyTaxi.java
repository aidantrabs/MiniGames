import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.Random;

public class CrazyTaxi extends JPanel implements ActionListener, KeyListener
{
	//Declare global variables

	//Declare and initialize JFrame object
	JFrame frame = new JFrame();

	//Declare and initialize Taxi object
	private Taxi taxi = new Taxi();

	//Declare and initialize 3 car objects
	private Obstacles obs1 = new Obstacles();
	private Obstacles obs2 = new Obstacles();
	private Obstacles obs3 = new Obstacles();

	//Declare and initialize timer object
	private Timer t = new Timer(100, this);

	//Declare and initialize 2 boolean variables for directions used to control car
	private boolean up = false;
	private boolean down = false;

	//Declare and initialize Random object
	private Random rnd = new Random();

	//Declare and initialize ImageIcon and Image to be used for instructions on screen (up arrow image)
	private ImageIcon upArrow = new ImageIcon("assets\\taxi\\up_arrow.png");
	private Image imgUp = upArrow.getImage();

	//Declare and initialize ImageIcon and Image to be used for instructions on screen (down arrow image)
	private ImageIcon downArrow = new ImageIcon("assets\\taxi\\down_arrow.png");
	private Image imgDown = downArrow.getImage();

	//Declare and initialize ImageIcon and Image to be used for game over 
	private ImageIcon gameOver = new ImageIcon("assets\\taxi\\lose.png");
	private Image imgGameOver = gameOver.getImage();

	//Declare and initialize Decimal Format object
	private DecimalFormat df = new DecimalFormat(":00");

	//Declare and initialize 2 integer variables to keep track of time elapsed
	private int secs = 0;
	private int msecs = 0;

	//Declare and initialize integer variable to hold value of score
	private int score = 0;

	//Declare and initialize boolean variable to check if user crashed
	private boolean crash = false;

	public CrazyTaxi()
	{
		//Set the dimension of the panel
		this.setPreferredSize(new Dimension(1000, 700));

		//Set properties of main JPanel object
		setLayout(null);
		setBackground(new Color(64, 64, 64));
		addKeyListener(this);
		setFocusable(true);

		//Set properties of JFrame
		frame.setContentPane(this);
		frame.setSize(1000, 500);
		frame.setTitle("Crazy Taxi");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.pack();	
		
		//Set an initial delay on the timer to give user time to get ready
		t.setInitialDelay(2000);
		
		//Start the timer
		t.start();
	}

	public void actionPerformed(ActionEvent e)
	{	
		//Check if the timer is running
		if(t.isRunning())
		{			
			//Move the obstacles (other cars) horizontally
			obs1.moveHorizontal(-30);
			obs2.moveHorizontal(-40);
			obs3.moveHorizontal(-50);

			//Check if the first obstacle crosses the end of the frame (behind the car)
			if(obs1.getX() < 0)
			{			
				//Set the score to the current score + 100 every time a car passes
				score = score + 100;

				//Reset the location of the obstacle to a random location along the y axis and set the x location to the end of the frame
				obs1.setY(rnd.nextInt(430)+1);
				obs1.setX(1000);
			}

			//Check if the second obstacle crosses the end of the frame (behind the car)
			if(obs2.getX() < 0)
			{
				//Set the score to the current score + 100 every time a car passes
				score = score + 100;

				//Check if the first object and the second object are too close to each other and give the second obstacle a new random location 
				//along the y axis
				if(obs1.getY() <= obs2.getY()+52)
				{
					obs2.setY(rnd.nextInt(430)+1);
					obs2.setX(1000);
				}

				//Reset the location of the obstacle to a random location along the y axis and set the x location to the end of the frame
				else
				{
					obs2.setY(rnd.nextInt(420)+1);
					obs2.setX(1000);
				}
			}

			//Check if the third obstacle crosses the end of the frame (behind the car)
			if(obs3.getX() < 0)
			{
				//Set the score to the current score + 100 every time a car passes
				score = score + 100;

				//Check if the first object and the second object are too close to each other and give the second obstacle a new random location 
				//along the y axis
				if(obs1.getY() <= obs3.getY()+52 || obs2.getY() <= obs3.getY()+52 )
				{
					obs3.setY(rnd.nextInt(420)+1);
					obs3.setX(1000);
				}

				//Reset the location of the obstacle to a random location along the y axis and set the x location to the end of the frame
				else
				{
					obs3.setY(rnd.nextInt(420)+1);
					obs3.setX(1000);
				}
			}
		}

		//Check if the user's car (taxi) is touching the top of the frame
		if(taxi.getY() < 6 || taxi.getY() + taxi.getHeight() > 500)
		{
			//Cancel any movement by moving it vertically by the same speed (15-15)
			taxi.moveVertical(15);
		}

		//Check if the user's car (taxi) is touching the bottom of the frame
		if(taxi.getY() > 452 || taxi.getY() + taxi.getHeight() > 500)
		{
			//Cancel any movement by moving it vertically by the same speed (-15+15)
			taxi.moveVertical(-15);
		}

		//If the timer is running and the user presses the down arrow key
		if(t.isRunning() && down)
		{
			//Move the taxi vertically downwards by 15
			taxi.moveVertical(15);
		}

		//If the timer is running and the user presses the up arrow key
		if(t.isRunning() && up)
		{
			//Move the taxi vertically upwards by 15
			taxi.moveVertical(-15);
		}

		//If the taxi crashes with the first obstacle
		if (taxi.getY() >= obs1.getY() && taxi.getY() <= obs1.getY() + taxi.getHeight())
		{
			if(taxi.getX() + taxi.getWidth() >= obs1.getX())
			{
				//Set crash to true
				crash = true;
			}
		}

		//If the taxi crashes with the second obstacle
		if (taxi.getY() >= obs2.getY() && taxi.getY() <= obs2.getY() + taxi.getHeight())
		{
			if(taxi.getX() + taxi.getWidth() >= obs2.getX())
			{
				//Set crash to true
				crash = true;
			}
		}

		//If the taxi crashes with the third obstacle
		if (taxi.getY() >= obs3.getY() && taxi.getY() <= obs3.getY() + taxi.getHeight())
		{
			if(taxi.getX() + taxi.getWidth() >= obs3.getX())
			{
				//Set crash to true 
				crash = true;
			}
		}

		//If the timer fires an actionevent
		if(e.getSource() == t)
		{
			msecs++;

			//Check if the timer has passed 59 milli secs, if so reset the milliseconds to 0 and increment the seconds variable
			if(msecs > 59)
			{
				msecs = 0;
				secs++;				
			}		
		}

		//Repaint the frame 
		repaint();
	}

	public void paintComponent(Graphics g)
	{
		//Repaints the frame and its components
		super.paintComponent(g);

		//Declare and initialize Graphics2D object
		Graphics2D g2 = (Graphics2D) g;

		//Set color and stroke of lines for lines between roads
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(3));

		//Draw 3 lines to divide roads
		g2.drawLine(0, 125, 1000, 125);
		g2.drawLine(0, 250, 1000, 250);
		g2.drawLine(0, 375, 1000, 375);

		//Set color of rectangles on each road
		g2.setColor(Color.YELLOW);

		//Draw yellow rectangles for first row of roads
		g2.fillRect(40, 50, 75, 20);
		g2.fillRect(195, 50, 75, 20);
		g2.fillRect(350, 50, 75, 20);
		g2.fillRect(505, 50, 75, 20);
		g2.fillRect(660, 50, 75, 20);
		g2.fillRect(815, 50, 75, 20);
		g2.fillRect(970, 50, 75, 20);

		//Draw yellow rectangles for second row of roads
		g2.fillRect(40, 175, 75, 20);
		g2.fillRect(195, 175, 75, 20);
		g2.fillRect(350, 175, 75, 20);
		g2.fillRect(505, 175, 75, 20);
		g2.fillRect(660, 175, 75, 20);
		g2.fillRect(815, 175, 75, 20);
		g2.fillRect(970, 175, 75, 20);

		//Draw yellow rectangles for third row of roads
		g2.fillRect(40, 300, 75, 20);
		g2.fillRect(195, 300, 75, 20);
		g2.fillRect(350, 300, 75, 20);
		g2.fillRect(505, 300, 75, 20);
		g2.fillRect(660, 300, 75, 20);
		g2.fillRect(815, 300, 75, 20);
		g2.fillRect(970, 300, 75, 20);

		//Draw yellow rectangles for forth row of roads
		g2.fillRect(40, 425, 75, 20);
		g2.fillRect(195, 425, 75, 20);
		g2.fillRect(350, 425, 75, 20);
		g2.fillRect(505, 425, 75, 20);
		g2.fillRect(660, 425, 75, 20);
		g2.fillRect(815, 425, 75, 20);
		g2.fillRect(970, 425, 75, 20);

		//Draw cars onto frame
		g2.drawImage(obs1.getImage(), obs1.getX(), obs1.getY(), this);
		g2.drawImage(obs2.getImage(), obs2.getX(), obs2.getY(), this);
		g2.drawImage(obs3.getImage(), obs3.getX(), obs3.getY(), this);
		g2.drawImage(taxi.getImage(), taxi.getX(), taxi.getY(), this);

		//Set color of lines at bottom of the frame
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(5));

		//Draw lines at bottom of frame
		g2.drawLine(0, 510, 1000, 510);
		g2.drawLine(500, 510, 500, 700);

		//Draw image of controls
		g2.drawImage(imgUp, 760, 530, this);		
		g2.drawImage(imgDown, 760, 610, this);

		//Set font of text for control title
		g2.setFont(new Font("Impact", Font.ITALIC, 50));

		//Draw string 
		g2.drawString("Controls -", 540, 615);

		//Set font of text for controls
		g2.setFont(new Font("Impact", Font.PLAIN, 23));

		//Draw strings for controls
		g2.drawString("Up Arrow:", 820, 570);
		g2.drawString("Down Arrow:", 820, 640);

		//Set color of movements strings
		g2.setColor(Color.RED);

		//Draw strings for control movements
		g2.drawString("Up", 920, 570);
		g2.drawString("Down", 940, 640);

		//Set color of score and time elapsed titles
		g2.setColor(Color.WHITE);

		//Set font of score and time elapsed titles
		g2.setFont(new Font("Impact", Font.PLAIN, 40));

		//Draw strings
		g2.drawString("Score:", 50, 580);		
		g2.drawString("Time Elapsed -", 50, 660);

		//Set color of score and time
		g2.setColor(Color.RED);

		//Draw strings
		g2.drawString("" + score, 170, 582);
		g2.drawString("" + secs + "" + df.format(msecs), 300, 660);

		//If the user crashes
		if(crash)
		{	
			//Stop the timer
			t.stop();

			//Set color to black		
			g2.setColor(Color.BLACK);

			//Draw rectangle
			g2.fillRect(0, 110, 1000, 325);

			//Draw image for game over
			g2.drawImage(imgGameOver, 325, 115, this);

			//Set color to red
			g2.setColor(Color.RED);

			//Draw string with instructions
			g2.drawString("Press Space to Continue...", 315, 490);
		}	
	}

	public void keyPressed(KeyEvent e)
	{
		//If the user clicks the down arrow key
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			//Set down to true and up to false
			down = true;
			up = false;
		}

		//If the user clicks the up arrow key
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			//Set up to true and down to false
			up = true;
			down = false;
		}

		//If the user crashes and presses the space bar to continue
		if(crash && e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			//Create an int variable holding the option selected by user when prompted
			int option = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Crazy Taxi", JOptionPane.YES_NO_OPTION);

			//If the user clicks the no option, close the window
			if(option == JOptionPane.NO_OPTION)
			{
				frame.dispose();
			}

			//If the user clicks yes, close original panel and create a new game
			else if(option == JOptionPane.YES_OPTION)
			{
				frame.dispose();
				new CrazyTaxi();
			}

			//If the user tries to close the window without selecting an option
			else
			{
				//Display message indicating the user needs to choose an option
				JOptionPane.showMessageDialog(null, "Please select an option!", "Crazy Taxi", JOptionPane.ERROR_MESSAGE);

				//Create an int variable holding the option selected by user when prompted again
				int opt = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Crazy Taxi", JOptionPane.YES_NO_OPTION);

				//If the user clicks the no option, close the window
				if(opt == JOptionPane.NO_OPTION)
				{
					frame.dispose();
				}

				//If the user clicks yes, close original panel and create a new game
				else if(opt == JOptionPane.YES_OPTION)
				{
					frame.dispose();
					new CrazyTaxi();
				}
			}
		}
	}

	//Unimplemented methods
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}

	public static void main(String[] args)
	{
		new CrazyTaxi();
	}
}