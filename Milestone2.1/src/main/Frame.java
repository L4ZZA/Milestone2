/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import city.cs.engine.DebugViewer;
import city.cs.engine.Walker;
import dynamic_bodies.SuperMario;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import levels.Level;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;
import levels.Level5;
import levels.Level6;

/**
 *
 * @author Pietro
 */
public class Frame {
    // dimension
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int  SCALE = 2;
    private static JFrame window;
    private static HomePanel homepage;
    
    private static Controller controller;
    private static int currentLevel=0;
    
    private static PlayPanel view;
    private static Level world;
    private static Container pane;
    
    public static void main(String[] args){
        window = new JFrame("Game");
        homepage = new HomePanel();
        window.setContentPane(homepage);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setFocusable(true);
        window.requestFocus();
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
        controller = new Controller();
        window.addKeyListener(controller);
        /*to set the frame in the middle of the screen*/
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
    }
    
    /**
     * Select your level between 1 and 6.
     * 
     * If You put a different number from the ones specified before is not going to select anything.
     * 
     * @param level the level you want to play
     */
    public static void startLevel(int level){
        pane = window.getContentPane();
        
        switch(level){
            case 1:
                currentLevel=level;
                System.out.println(currentLevel);
                world= new Level1(level);
                controller.setWorld(world);
                controller.setPlayer(world.getPlayer());
                
                view = new PlayPanel(world);
                break;
            case 2:
                currentLevel=level;
                System.out.println(currentLevel);
                world= new Level2(level);
                controller.setWorld(world);
                controller.setPlayer(world.getPlayer());
                
                view = new PlayPanel(world,"data/bg2.png");
                break;
            case 3:
                currentLevel=level;
                System.out.println(currentLevel);
                world= new Level3(level);
                controller.setWorld(world);
                controller.setPlayer(world.getPlayer());
                
                view = new PlayPanel(world,"data/bg3.png");
                break;
            case 4:
                currentLevel=level;
                System.out.println(currentLevel);
                world= new Level4(level);
                controller.setWorld(world);
                controller.setPlayer(world.getPlayer());
                
                view = new PlayPanel(world,"data/bg4.png");
                break;
            case 5:
                currentLevel=level;
                System.out.println(currentLevel);
                world= new Level5(level);
                controller.setWorld(world);
                controller.setPlayer(world.getPlayer());
                
                view = new PlayPanel(world,"data/bg5.png");
                break;
            case 6:
                currentLevel=level;
                System.out.println(currentLevel);
                world= new Level6(level);
                controller.setWorld(world);
                controller.setPlayer(world.getPlayer());
                
                view = new PlayPanel(world,"data/bg5.png");
                break;
            default:
                System.out.println("Error unexisting level: "+level);
        }
        world.setView(view);
        //JFrame debugView = new DebugViewer(world, Frame.WIDTH * Frame.SCALE, Frame.HEIGHT * Frame.SCALE);
        window.setContentPane(view);
        window.repaint();
        window.revalidate();
        world.start();
    }
    
    /**
     * 
     * @param l the current level that you're playing
     */
    public static void nextLevel(int currentlevel){
        homepage.unlockLevel(currentlevel+1);
        world.stop();
        startLevel(currentLevel+1);
    }
    
    /**
     * pause the game
     */
    public static void pause(){
        if(world.isRunning())
            world.stop();
    }
    
    /**
     * restart the game
     */
    public static void unpause(){
        if(!world.isRunning())
            world.start();
    }
    
    public static void restartLevel(){
        currentLevel--;
        nextLevel(currentLevel);
    }
    
    public static void goHome(){
        window.setContentPane(homepage);
        window.repaint();
        window.revalidate();
    }
    
    /**
     * helper class for the keyboard interaction
     */
    private static class Controller extends KeyAdapter{
        Level world;
        SuperMario mario;
                
        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            mario.keyPressed(code);
        }
        
         @Override
        public void keyReleased(KeyEvent e) {
            int code = e.getKeyCode();
            mario.keyReleased(code);
        }

        public void setWorld(Level world) {
            this.world = world;
        }

        public void setPlayer(SuperMario player) {
            this.mario = player;
        }
    }
}
