/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels;

import city.cs.engine.World;
import dynamic_bodies.SuperMario;
import java.awt.event.KeyListener;

/**
 *
 * @author Pietro
 */
public abstract class Level extends World implements KeyListener{ 
    //player object
    
    //list of characters (non-player)
    
    //list of platforms
    
    //ground
    
    //level 
    protected boolean completed;
    
    //steplistener
    public abstract void setStepListener(/*Tracker tracker*/);
    
    //drawing
    public abstract void draw();
    
    //level complete
    public abstract boolean isCompleted();
    
    // get player
    public abstract SuperMario getPlayer();
}
