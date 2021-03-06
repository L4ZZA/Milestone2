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
        
        Ground g = new Ground(this);
        mario = new SuperMario(this, new Vec2(g.getBound("left")+2, g.getBound("top")+1));
        Pipe pipe = new Pipe(this, new Vec2(-5f,g.getBound("top")+1));
        Plant plant = new Plant(this, new Vec2(pipe.getBound("left")+pipe.getWidth(),pipe.getBound("top")));
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
