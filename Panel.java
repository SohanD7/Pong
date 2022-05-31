package com.company;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Panel extends JPanel implements Runnable //Runnable allows program to run on a thread
{
    static final int WIDTH = 1000; //static width throughout the program, will be final
    static final int HEIGHT = 600;
    static final Dimension SCREEN_SIZE = new Dimension(WIDTH, HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread thread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    Panel()
    {
        newPaddles();
        newBall();
        score = new Score(WIDTH,HEIGHT);
        this.setFocusable(true); //reads key strokes
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        thread = new Thread(this);
        thread.start();
    }

    public void newBall()
    {
        random = new Random();
        ball = new Ball((WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
    }

    public void newPaddles()
    {
        paddle1 = new Paddle(0,(HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2 = new Paddle(WIDTH-PADDLE_WIDTH,(HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }

    public void paint(Graphics g)
    {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    public void draw(Graphics g)
    {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move() //makes paddle movement smoother
    {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollision()
    {
        //prevents paddles from traveling past the edges of the window
        if (paddle1.y <= 0)
            paddle1.y = 0;
        if (paddle1.y >= (HEIGHT - PADDLE_HEIGHT))
            paddle1.y = HEIGHT - PADDLE_HEIGHT;
        if (paddle2.y <= 0)
            paddle2.y = 0;
        if (paddle2.y >= (HEIGHT - PADDLE_HEIGHT))
            paddle2.y = HEIGHT - PADDLE_HEIGHT;

        //bounce ball off top and bottom edges
        if (ball.y <= 0)
        {
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= HEIGHT-BALL_DIAMETER)
        {
            ball.setYDirection(-ball.yVelocity);
        }

        //bounce ball off paddle
        if (ball.intersects(paddle1)) //because the ball and paddle classes extend Rectangle we can use intersects()
        {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity*=1.1;
            if (ball.yVelocity > 0)
            {
                ball.yVelocity*=1.1;
            } else
            {
                ball.yVelocity*=(-1.1);
            }
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);

        }
        if (ball.intersects(paddle2))
        {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity*=1.01;
            if (ball.yVelocity > 0)
            {
                ball.yVelocity*=1.1;
            } else
            {
                ball.yVelocity*=(-1.1);
            }
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //give a player a point and starts new round
        if (ball.x <= 0) //player 2 point
        {
            score.player2++;
            newPaddles();
            newBall();
        }
        if (ball.x >= WIDTH-BALL_DIAMETER) //player 1 point
        {
            score.player1++;
            newPaddles();
            newBall();
        }
    }

    public void run()
    {
        //game loop taken from stackoverflow
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true)
        {
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if (delta >= 1)
            {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    //AL is Action Listener which checks for key inputs
    public class AL extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e)
        {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
