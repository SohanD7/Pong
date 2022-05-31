package com.company;
import java.awt.*;


public class Score extends Rectangle
{
    static int WIDTH;
    static int HEIGHT;
    int player1;
    int player2;

    Score(int WIDTH, int HEIGHT)
    {
        Score.WIDTH = WIDTH;
        Score.HEIGHT = HEIGHT;
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas",Font.PLAIN,60));

        g.drawLine(WIDTH/2,0,WIDTH/2,HEIGHT); //draw the half-court line
        g.drawOval((WIDTH/2)-45,(HEIGHT/2)-45,90,90); //draw the center circle

        g.drawString(String.valueOf(player1),(WIDTH/2)-53,50);
        g.drawString(String.valueOf(player2),(WIDTH/2)+20,50);
    }
}
