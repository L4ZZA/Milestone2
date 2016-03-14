/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels;

import dynamic_bodies.SuperMario;
import java.awt.event.KeyEvent;
import static_bodies.Ground;

/**
 *
 * @author Pietro
 */
public class Level2 extends Level {
    
    int currentLevel;
    SuperMario mario;
    
    public Level2(int l){
        currentLevel = l;
        Ground g = new Ground(this);
        mario = new SuperMario(this);
    }
    
    @Override
    public void setStepListener() {
    }

    @Override
    public void draw() {
    }

    @Override
    public boolean isCompleted() {
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public SuperMario getPlayer() {
        return mario;
    }

}
