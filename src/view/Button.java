package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Button {
	
	public int x, y , width, height, id;
	private String text;
	private Rectangle bounds;
	private boolean isMouseOver, isMousePressed;
	
	//For normal buttons
	public Button(String text, int x, int y, int width, int height){
		
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;
		
		initBounds();
	}
	
	public Button(String text, int x, int y, int width, int height, int id){
		
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;
		
		initBounds();
	}
	
	//For tile buttons
	public Button(int x, int y, int width, int height, int id){
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		
		initBounds();
	}
	
	private void initBounds(){
		
		this.bounds = new Rectangle(x, y, width, height);
	}
	
	public void draw(Graphics g){
		
		//Body
		drawBody(g);

		//Border
		drawBorder(g);
		
		//Text
		drawText(g);
	}
	
	private void drawBorder(Graphics g){
		
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		if(isMousePressed)
		{
			g.drawRect(x + 1, y + 1, width - 2, height - 2);
			g.drawRect(x + 2, y + 2, width - 4, height - 4);
		}
		
	}
	
	private void drawBody(Graphics g){
		
		if(isMouseOver)
			g.setColor(Color.gray);
		else
			g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}
	
	private void drawText(Graphics g){
		
		int w = g.getFontMetrics().stringWidth(text);
		int h = g.getFontMetrics().getHeight();
		g.drawString(text, x - w /2 + width/2, y + h /2 + height /2);
	}
	
	public void resetBooleans(){
		
		this.isMouseOver = false;
		this.isMousePressed = false;
	}
	
	public void setIsMousePressed(boolean isMousePressed){
		
		this.isMousePressed = isMousePressed;
	}
	public void setIsMouseOver(boolean isMouseOver){
		
		this.isMouseOver = isMouseOver;
	}
	
	public boolean isMouseOver(){
		
		return isMouseOver;
	}
	
	public boolean isMousePressed(){
		
		return isMousePressed;
	}
	public Rectangle getBounds(){
		
		return bounds;
	}
	
	public int getId(){
		
		return id;
	}
}

