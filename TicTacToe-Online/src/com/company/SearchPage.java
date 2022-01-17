package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SearchPage extends JLayeredPane implements KeyListener, FocusListener {
    private JTextField registerTextField;
    private BackButton backButton;
    private String proptText;

    public SearchPage() {
        proptText = "Enter Server IP";

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
        panelUp.setPreferredSize(new Dimension(100,190));
        panelRight.setPreferredSize(new Dimension(200,100));
        panelLeft.setPreferredSize(new Dimension(200,100));
        panelDown.setPreferredSize(new Dimension(100,70));
        panelCenter.setPreferredSize(new Dimension(100,100));
        panelBackground.setSize(1100,700);

        panelBackground.setLayout(new BorderLayout());

        ImageIcon chooseGameImage = new ImageIcon("image\\Find Your Server.png");
        JLabel chooseGameLabel = new JLabel();
        chooseGameLabel.setIcon(chooseGameImage);
        panelUp.add(chooseGameLabel);
        chooseGameLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseGameLabel.setVerticalAlignment(JLabel.CENTER);

        //PANEL CENTER LAYOUT
        panelCenter.setLayout(new GridLayout(2, 1, 0, 10));
        JPanel panelCenterTop = new JPanel();
        JPanel panelCenterBottom = new JPanel();

        panelCenterTop.setOpaque(false);
        panelCenterBottom.setOpaque(false);
        panelCenter.add(panelCenterTop);
        panelCenter.add(panelCenterBottom);

        //Creating Logo Label
        registerTextField = new JTextField(proptText, 20);
        registerTextField.setForeground(Color.lightGray);
        registerTextField.setBounds(0, 75, 320, 111);

        ImageIcon image = new ImageIcon("image\\textfield1.png");
        Image convertImage = image.getImage();
        Image newImage = convertImage.getScaledInstance(320, 200, Image.SCALE_SMOOTH);
        ImageIcon imageTextField = new ImageIcon(newImage);

        JLabel labelTextField = new JLabel();
        labelTextField.setIcon(imageTextField);

        registerTextField.setOpaque(false);
        labelTextField.setOpaque(false);
        registerTextField.setFont(new Font("MV Boli", Font.PLAIN, 30));

        labelTextField.add(registerTextField);
        panelCenterTop.add(labelTextField);

        //Creating Match Buttons
        registerTextField.addKeyListener(this);
        registerTextField.addFocusListener(this);


        backButton=new BackButton(100, 50);
        backButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.PAGE_AXIS));
        panelRight.add(Box.createVerticalGlue());
        panelRight.add(backButton);


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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (registerTextField.isFocusOwner()) {

            if (e.getKeyCode() >= 48 & e.getKeyCode() <= 57 ||
                    //e.getKeyCode() >= 65 & e.getKeyCode() <= 90 ||
                    e.getKeyCode() == 46 || e.getKeyCode() == 8) {
            } else {
                registerTextField.setText("");
                JOptionPane.showMessageDialog(null, "Enter only IP of The Host Server!", "Username", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == registerTextField){
            if(registerTextField.getText().equals(proptText)) {
                registerTextField.setForeground(Color.BLACK);
                registerTextField.setText("");}
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource() == registerTextField){
            if(registerTextField.getText().equals("")) {
                registerTextField.setForeground(Color.LIGHT_GRAY);
                registerTextField.setText(proptText);}
        }
    }

    public BackButton getBackButton() {
        return backButton;
    }
}

