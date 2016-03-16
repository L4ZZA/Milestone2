/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import dynamic_bodies.Goomba;
import dynamic_bodies.Plant;
import dynamic_bodies.Princess;
import dynamic_bodies.Rose;
import dynamic_bodies.SuperMario;
import java.awt.event.KeyEvent;
import main.PlayPanel;
import org.jbox2d.common.Vec2;
import static_bodies.Ground;
import static_bodies.LifePlatform;
import static_bodies.Pipe;
import static_bodies.Platform;

/**
 *
 * @author Pietro
 */
public class Level6 extends Level{
    
    public Level6(int l){
        currentLevel = l;
        
    }

    @Override
    public void draw() {
    }

    @Override
    public boolean isCompleted() {
        return true;
    }

    @Override
    public void setView(PlayPanel view) {
        super.view = view;
    }
}
