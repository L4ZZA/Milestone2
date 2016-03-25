/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Pietro
 */
public class HomePanel extends JPanel{
    
    private int unlockedLevels=0;
    
    private JPanel page,
            topBar,content;
    private JLabel menu;
    private CollapsablePanel leftMenu;
    
    private final int numberOfLevels=6;
    private JPanel [] levelContainers = new JPanel[numberOfLevels];
    private LevelSelector [] levels = new LevelSelector[numberOfLevels];
    
    public HomePanel(){
        super(new BorderLayout());
        setPreferredSize(new Dimension(Frame.WIDTH*Frame.SCALE,Frame.HEIGHT*Frame.SCALE));
        setMinimumSize(new Dimension(Frame.WIDTH*Frame.SCALE,Frame.HEIGHT*Frame.SCALE));
        setOpaque(true);
        GridLayout grid = new GridLayout(2,numberOfLevels/2);
        
        page = new JPanel(new BorderLayout());
            
        content = new JPanel(grid);
        content.setBackground(Color.BLUE);
        
        menu = new JLabel("Menu");
        
        leftMenu = new CollapsablePanel(menu);
        leftMenu.setBackground(new Color(0,200,255));
        leftMenu.setPreferredSize(new Dimension(75,Frame.HEIGHT*Frame.SCALE));
        
        JLabel l =new JLabel("Unlocked: "+unlockedLevels+"/"+numberOfLevels, JLabel.CENTER);
        l.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        topBar= new JPanel(new BorderLayout());
        topBar.setBackground(new Color(0,200,255));
        topBar.setPreferredSize(new Dimension((Frame.WIDTH*Frame.SCALE)-leftMenu.getSize().width,50));
        topBar.add(l,BorderLayout.EAST);
        
        add(leftMenu, BorderLayout.LINE_START);
        add(page);
            page.add(topBar, BorderLayout.NORTH);
            page.add(content, BorderLayout.CENTER);
            
        for (int i = 0; i < numberOfLevels; i++) {
            levelContainers[i] = new JPanel(new BorderLayout());
            levelContainers[i].setBackground(new Color(100,i*30,255));
            content.add(levelContainers[i]);
            if(i==0)
                levels[i] = new LevelSelector(i+1,false);
            else
                levels[i] = new LevelSelector(i+1);
            levelContainers[i].add(levels[i], BorderLayout.CENTER);
        }
    }

    public void increaseUnlockedLevels() {
        if(unlockedLevels<7)
            unlockedLevels++;
    }

    /**
     * 
     * @param level is the level you're going to unlock
     */
    public void unlockLevel(int level) {
        LevelSelector l = levels[level-1];
        l.unlockLevel();
    }
    
    
    
    
    /**
     * helper class 
     */
    class LevelSelector extends JLabel implements MouseListener{

        int currentLevel;
        boolean locked;
        ImageIcon lock = new ImageIcon("data/lock.png");
        ImageIcon unlock = new ImageIcon("data/unlock.png");
        LevelSelector(int levelNumber){
            super("", JLabel.CENTER);
            super.setForeground(Color.WHITE);
            setFont(new Font("Times New Roman", Font.BOLD,90));
            setVerticalTextPosition(JLabel.CENTER);
            setHorizontalTextPosition(JLabel.CENTER);
            
            Border paddingBorder = BorderFactory.createEmptyBorder(30,30,30,30);
            this.setBorder(BorderFactory.createCompoundBorder(null,paddingBorder)); 
            
            locked = true;
            setIcon(lock);
            addMouseListener(this);
            currentLevel = levelNumber;
        }
        
        LevelSelector(int levelNumber, boolean locked){
           this(levelNumber);
           this.locked = locked;
           
           setText(""+currentLevel);
           setIcon(unlock);
           int IMAGE_WIDTH = unlock.getIconWidth();
           setIconTextGap(-IMAGE_WIDTH);
           revalidate();
        }
        
        
        void selectLevel(int l){
            if(!locked){
                Frame.startLevel(l);
            }
        }
        
        void unlockLevel(){
            if(locked){
                locked =false;
                setText(""+currentLevel);
                setIcon(unlock);
            }
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            selectLevel(currentLevel);
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
    }
    // end helper class
    
}
