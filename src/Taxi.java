import javax.swing.*;
import java.awt.*;

public class Taxi 
{
	//Declare fields
	private int xPos, yPos;
	private Image imgCar;
	private ImageIcon img;

	//Create a no-arg constructor to initialize all fields
	public Taxi()
	{
		xPos = 50;
		yPos = 230;
		img = new ImageIcon("assets\\taxi\\taxi2.png");
		imgCar = img.getImage();
	}

	//This method will return the image of the car
	public Image getImage()
	{
		return imgCar;
	}

	//This method will return the width of the car image
	public int getWidth()
	{
		return img.getIconWidth();
	}

	//This method will return the height of the car image
	public int getHeight()
	{
		return img.getIconHeight();
	}

	//This method returns the car's x-position
	public int getX()
	{
		return xPos;
	}

	//This method returns the car's y-position
	public int getY()
	{
		return yPos;
	}
	
	public void setX(int x)
	{
		this.xPos = x;
	}

	public void setY(int y)
	{
		this.yPos = y;
	}

	public void moveVertical (int pixels)
	{
		yPos += pixels;
	}
}