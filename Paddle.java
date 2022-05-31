package com.company;
import java.awt.*;
import java.awt.event.*;


public class Paddle extends Rectangle
{
    int id;
    int yVelocity;
    int speed = 10;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id)
    {
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id = id;
    }

    public void keyPressed(KeyEvent e)
    {
        switch (id)
        {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W) //triggers if someone presses "W" key
                {
                    setYDirection(-speed); //for vertical direction negative numbers go up
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S) //triggers if someone presses "S" key
                {
                    setYDirection(speed);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP) //triggers if someone presses up arrow
                {
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN) //triggers if someone presses down arrow
                {
                    setYDirection(speed);
                    move();
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) //prevents the paddle from moving in a direction continuously
    {
        switch (id)
        {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W)
                {
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S)
                {
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP)
                {
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN)
                {
                    setYDirection(0);
                    move();
                }
                break;
        }
    }

    public void setYDirection(int yDirection)
    {
        yVelocity = yDirection;
    }

    public void move()
    {
        y = y + yVelocity;
    }

    public void draw(Graphics g)
    {
        if (id == 1) //player 1's paddle
            g.setColor(Color.blue);
        else
            g.setColor(Color.red);
        g.fillRect(x,y,width,height);
    }
}
