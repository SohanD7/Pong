package com.company;
import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame
{
    Panel panel;

    Frame()
    {
        panel = new Panel();
        this.add(panel);
        this.setTitle("Pong");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); //resizes the frame to fit the panel accordingly
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
