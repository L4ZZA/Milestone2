/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels;

import dynamic_bodies.Plant;
import dynamic_bodies.Princess;
import dynamic_bodies.Rose;
import dynamic_bodies.SuperMario;
import java.awt.event.KeyEvent;
import main.PlayPanel;
import org.jbox2d.common.Vec2;
import static_bodies.Ground;
import static_bodies.Pipe;

/**
 *
 * @author Pietro
 */
public class Level1 extends Level {
    
    public Level1(int l){
        currentLevel = l;
        nRoses=2;
        //remember to put each walkable no more than 5 points above the closest one
        // the player might not be able to reach it
        Ground g = new Ground(this);
        Pipe pipe = new Pipe(this, new Vec2(-5f,g.getBound("top")+1));
        Plant plant = new Plant(this, new Vec2(pipe.getBound("left")+pipe.getWidth(),pipe.getBound("top")));
        mario = new SuperMario(this, new Vec2(g.getBound("left")+2, g.getBound("top")+1));
        
        Princess princess = new Princess(this,new Vec2(g.getBound("right")-8, g.getBound("top")+1));
        Rose r = new Rose(this, new Vec2(g.getPosition().x+1, g.getBound("top")));
        Rose rr = new Rose(this, new Vec2(g.getPosition().x-1, g.getBound("top")));
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
