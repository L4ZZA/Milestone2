/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels;

import dynamic_bodies.Goomba;
import dynamic_bodies.Plant;
import dynamic_bodies.Princess;
import dynamic_bodies.Rose;
import dynamic_bodies.SuperMario;
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
public class Level5 extends Level{
    
    public Level5(int l){
        currentLevel = l;
        nRoses=3;
        //remember to put each walkable no more than 5 points above the closest one
        // the player might not be able to reach it
        Ground g = new Ground(this);
        mario = new SuperMario(this, new Vec2(g.getBound("left")+2, g.getBound("top")+1));
        
        Platform p = new Platform(this, new Vec2(2.5f,1));
        Platform pp =  new Platform(this, new Vec2(-9,-4f));
        Platform ppp = new Platform(this, new Vec2(10,6));
        Pipe pipe = new Pipe(this, new Vec2(-5f,g.getBound("top")+1));
        Plant plant = new Plant(this, new Vec2(pipe.getBound("left")+pipe.getWidth(),pipe.getBound("top")));
        goombaPlatform=pp;
        goomba = new Goomba(this,new Vec2(goombaPlatform.getPosition().x,goombaPlatform.getBound("top")));
        goomba.setLinearVelocity(new Vec2(6.5f,0));
        Platform pppp = new Platform(this,new Vec2(goombaPlatform.getPosition().x,ppp.getPosition().y));
        
        Pipe pipe2 = new Pipe(this, p.getPosition().addLocal(0, 1.5f));
        Plant plant2 = new Plant(this, new Vec2(pipe2.getBound("left")+pipe2.getWidth(),pipe2.getBound("top")));
        
        Princess princess = new Princess(this,new Vec2(ppp.getBound("right")-1, ppp.getBound("top")));
        Rose r = new Rose(this, new Vec2(pppp.getPosition().x-pppp.getWidth()+1, pppp.getBound("top")));
        Rose rr = new Rose(this, new Vec2(pppp.getPosition().x+pppp.getWidth()-1, pppp.getBound("top")));
        Rose rrr = new Rose(this, new Vec2(0, g.getBound("top")));
        LifePlatform lp = new LifePlatform(this, new Vec2(8,-6));
        setStepListener();
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
