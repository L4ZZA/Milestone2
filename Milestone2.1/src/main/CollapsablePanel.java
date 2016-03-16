/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Pietro
 */
class CollapsablePanel extends JPanel {
 
    private boolean selected;
    JPanel contentPanel_;
    JLabel headerLabel;
    JLabel home,pause,unpause,exit;
    final int OFFSET = 50, PAD = 5;
    
    public CollapsablePanel(JLabel label) {
        super();
        GridBagLayout grid = new GridBagLayout();
        setLayout(grid);
        GridBagConstraints gbc = new GridBagConstraints();
 
        selected = false;
        headerLabel = label;
        headerLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        headerLabel.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                toggleSelection();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
            
        });
        
        home=new JLabel("Home");
        pause = new JLabel("Pause");
        unpause = new JLabel("Unpause");
        exit = new JLabel("Exit");
        
        contentPanel_ = new JPanel(new GridBagLayout());
        contentPanel_.setBackground(Color.WHITE);
        {
            gbc.fill = gbc.VERTICAL;
            gbc.insets = new Insets(5, 3, 5, 3);
            gbc.gridx = 0;
            gbc.gridwidth = gbc.RELATIVE;
            contentPanel_.add(home, gbc);
            gbc.gridwidth = gbc.REMAINDER;
            contentPanel_.add(pause, gbc);
            gbc.gridwidth = gbc.RELATIVE;
            contentPanel_.add(unpause, gbc);
            gbc.gridwidth = gbc.REMAINDER;
            contentPanel_.add(exit, gbc);
            
        }
        
        gbc.anchor = GridBagConstraints.NORTH;
        add(headerLabel,gbc);
        add(contentPanel_, gbc);
        contentPanel_.setVisible(false);
 
        JLabel padding = new JLabel();
        gbc.weighty = 1.0;
        add(padding, gbc); 
        
        
        home.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                Frame.goHome();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        pause.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                Frame.pause();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        unpause.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                Frame.unpause();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        exit.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
    
    public void toggleSelection() {
        selected = !selected;
 
        if (contentPanel_.isShowing())
            contentPanel_.setVisible(false);
        else
            contentPanel_.setVisible(true);
 
        validate();
 
        headerLabel.repaint();
    }
 /*try {
                open = ImageIO.read(new File("images/arrow_down_mini.png"));
                closed = ImageIO.read(new File("images/arrow_right_mini.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }*/
}