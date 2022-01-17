package com.company;

import javax.swing.*;
import java.awt.*;

public class WaitPage extends JLayeredPane {
    private WaitLabel waitLabel;
    private ExitButton exitButton;

    private BackButton backButton;

    public BackButton getBackButton() {
        return backButton;
    }

    public WaitPage() {

        //Creating HomePage components
        JPanel panelUp = new JPanel();
        JPanel panelRight = new JPanel();
        JPanel panelLeft = new JPanel();
        JPanel panelDown = new JPanel();
        JPanel panelCenter = new JPanel();
        JPanel panelBackground = new JPanel();

        //Testing components - - - - - - - - - - - - - - - - - - -
        panelUp.setBackground(new Color(168,218,220));
        panelDown.setBackground(Color.green);
        panelLeft.setBackground(Color.blue);
        panelRight.setBackground(Color.blue);
        panelCenter.setBackground(Color.orange);

        panelUp.setOpaque(false);
        panelRight.setOpaque(false);
        panelLeft.setOpaque(false);
        panelDown.setOpaque(false);
        panelCenter.setOpaque(false);
        panelBackground.setOpaque(false);
        //- - - - - - - - - - - - - - - - - - - - - - - - - - -  - -

        //Initializing Page Structure (component sizes)
        panelUp.setPreferredSize(new Dimension(100,150));
        panelRight.setPreferredSize(new Dimension(0,100));
        panelLeft.setPreferredSize(new Dimension(215,100));
        panelDown.setPreferredSize(new Dimension(100,200));
        panelCenter.setPreferredSize(new Dimension(100,100));
        panelBackground.setSize(1100,700);

        panelBackground.setLayout(new BorderLayout());

        //Creating Logo Label
        waitLabel = new WaitLabel();
        panelCenter.setLayout(new BorderLayout());
        panelCenter.add(waitLabel, BorderLayout.WEST);

        exitButton=new ExitButton(100, 50);
        exitButton.setAlignmentY(JLabel.BOTTOM_ALIGNMENT);
        exitButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panelDown.add(exitButton);

        backButton=new BackButton(100, 50);
        backButton.setAlignmentY(JLabel.BOTTOM_ALIGNMENT);
        backButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panelUp.add(backButton);


        //Creating Match Buttons
        panelUp.setLayout(new GridLayout(1,2,20,70));


        //Implementation in the frame
        panelBackground.add(panelUp, BorderLayout.NORTH);
        panelBackground.add(panelCenter, BorderLayout.CENTER);
        panelBackground.add(panelRight, BorderLayout.EAST);
        panelBackground.add(panelLeft, BorderLayout.WEST);
        panelBackground.add(panelDown, BorderLayout.SOUTH);
        this.add(panelBackground, JLayeredPane.DRAG_LAYER);

        //Page background
        AnimatedBackground animationPanel= new AnimatedBackground();
        Thread animationPanelThread = new Thread(animationPanel);
        animationPanelThread.start();
        this.add(animationPanel, JLayeredPane.DEFAULT_LAYER);

        //visibility
        this.setVisible(true);

    }

    public ExitButton getExitButton() {
        return exitButton;
    }

    public WaitLabel getWaitLabel() {
        return waitLabel;
    }
}
