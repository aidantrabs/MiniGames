import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Random;

public class Obstacles 
{
	//Declare global variables
	private int xPos, yPos;
	private Image imgCar;
	private ImageIcon img;
	
	//Declare and initialize Random object
	private Random rnd = new Random();
	
	//Declare and initialize integer value for random
	private int randomNum = 0;

	//Create a no-arg constructor to initialize all fields
	public Obstacles()
	{
		xPos = 1000;
		yPos = rnd.nextInt(490)+1;
		randomNum = rnd.nextInt(4)+1;
		img = new ImageIcon("assets\\taxi\\" + randomNum + ".png");
		imgCar = img.getImage();
	}

	//This method will return the image of the car
	public Image getImage()
	{
		return imgCar;
	}

	//This method will return the height of the car icon
	public int getHeight()
	{
		return img.getIconHeight();
	}

	//This method will return the width of the car icon
	public int getWidth()
	{
		return img.getIconWidth();
	}

	//This method will return the x position of the obstacle
	public int getX()
	{
		return xPos;
	}

	//This method will get the y position of the obstacle
	public int getY()
	{
		return yPos;
	}

	//This method will set the x position of the obstacle
	public void setX(int x)
	{
		this.xPos = x;
	}

	//This method will set the y position of the obstacle
	public void setY(int y)
	{
		this.yPos = y;
	}

	//This method will move the obstacle in the x axis by pixels
	public void moveHorizontal (int pixels)
	{
		xPos += pixels;
	}
}