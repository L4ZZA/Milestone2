/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import city.cs.engine.UserView;
import city.cs.engine.World;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import levels.Level;

/**
 *
 * @author Pietro
 */
public class PlayPanel extends UserView {

    Image bg;
    
    private Color titleColor;
    private Font titleFont,warning;
    
    private long start,elapsed;
    
    private final int INITIAL_TIME= 800;
    
    private Level world;
    
    private CollapsablePanel leftMenu;
    
    public PlayPanel(Level w) {
        super(w, Frame.WIDTH * Frame.SCALE, Frame.HEIGHT * Frame.SCALE);
        setFocusable(true);
        requestFocus();
        world = w;
        titleFont=new Font("TimesRoman", Font.PLAIN, 18);
        warning = new Font("TimesRoman", Font.BOLD,30);
        bg = new ImageIcon("data/bg1.png").getImage();
     
        titleColor = new Color(255,255,255);
         
        start = System.nanoTime();
        
        JLabel menu = new JLabel("Menu");
        
        leftMenu = new CollapsablePanel(menu);
        
        add(leftMenu);
        leftMenu.setBounds(200,1,10,10);
        leftMenu.revalidate();
    }
    
    public PlayPanel(Level w, String bg) {
        this(w);
        this.bg = new ImageIcon(bg).getImage();
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(bg, 0, 0, this);
    }
    
    @Override
    protected void paintForeground(Graphics2D g) {
        g.setColor(titleColor);
        g.setFont(titleFont);
        elapsed = INITIAL_TIME-((System.nanoTime() - start) / 10000000);    
        String a ="Score: "+ elapsed;
        if(world.currentLevel >0 && world.currentLevel <6){
            if(elapsed < 0.0001){    
                a ="Score: "+ 0;
                g.drawString(a, getSize().width- a.length() * (titleFont.getSize()/2), 50);
            }
            else{
                g.drawString(a, getSize().width- a.length() * (titleFont.getSize()/2), 50);
            }
            String s = "roses: "+world.getPlayer().getRosesCount()+"/"+world.getRoses();
            g.drawString(s,getSize().width- s.length() * (titleFont.getSize()/2), 20);
        }
        else if(world.currentLevel==6){
            titleColor= new Color(255,0,0);
            g.setColor(titleColor);
            g.setFont(warning);
            g.drawString("WINNER!!!", getSize().width/2, getSize().height/2);
        }
    }

}
