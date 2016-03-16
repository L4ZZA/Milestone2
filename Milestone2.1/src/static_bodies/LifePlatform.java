/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package static_bodies;


import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import dynamic_bodies.BonusLife;
import dynamic_bodies.SuperMario;
import java.awt.Color;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Pietro
 */
public class LifePlatform extends Walkable{
    
   // private final double PLATFORM_BOTTOM= -3.4685683;
    private boolean done;
    private final World world;
    private Vec2 position;
    private SuperMario mario;
    
    BodyImage firstImage = new BodyImage("data/unknown.png");
    BodyImage secondImage = new BodyImage("data/hit.png");
    
    /**
     * 
     * @param world the world within create the platform
     * @param shape the shape of the platform
     * @param i index of the loop, used to set the relative position 
     * @param mario at the moment not utilised, but I was trying to use this object reference to develop the listener inside this class instead of the world class 
     */
    public LifePlatform(World world, Vec2 pos, SuperMario mario){
        super(world,0.5f, 0.5f,pos);
        this.world = world;
        done= false;
        this.mario = mario;
        addImage(firstImage);
        position = pos;
        addCollisionListener(new Collision(this));
        
    }
    
    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public SuperMario getMario() {
        return mario;
    }

    public void setMario(SuperMario mario) {
        this.mario = mario;
    }
    
    
    private class Collision implements CollisionListener{
        LifePlatform p;
        
        Collision(LifePlatform p){
            this.p = p;
        }
        
        @Override
        public void collide(CollisionEvent e) {
            if(e.getOtherBody().getClass() == SuperMario.class && !done && 
                    e.getOtherBody().getPosition().y < p.BOTTOM){
                    BonusLife bl = new BonusLife(world,p.getPosition());
                    done = true;
                    removeAllImages();
                    addImage(secondImage);
            }
            
        }
        
    }
}
