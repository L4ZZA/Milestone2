/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import city.cs.engine.UserView;
import city.cs.engine.World;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

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
    
    public PlayPanel(World w) {
        super(w, Frame.WIDTH * Frame.SCALE, Frame.HEIGHT * Frame.SCALE);
        setFocusable(true);
        requestFocus();
        
        titleFont=new Font("TimesRoman", Font.PLAIN, 18);
        warning = new Font("TimesRoman", Font.BOLD,30);
        bg = new ImageIcon("data/bg1.png").getImage();
     
        titleColor = new Color(255,255,255);
         
        start = System.nanoTime();
    }
    
    public PlayPanel(World w, String bg) {
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
        g.drawString("Score: "+ elapsed, 10, 20);
        if(elapsed < 0.0001){ 
            
        }
    }

}
