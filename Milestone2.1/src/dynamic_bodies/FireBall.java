/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic_bodies;

import city.cs.engine.BodyImage;
import city.cs.engine.CircleShape;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.DynamicBody;
import city.cs.engine.Fixture;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Pietro
 */
public class FireBall extends DynamicBody{

    private final String name="FireBall";
    private Shape ballShape;
    private BodyImage ballRight,ballLeft;
    private boolean direction;
    private static final int gravity=0;
    private static final float velocity=10;
    private static float radius = 0.35f;
    
    public FireBall(World w) {
        super(w);
        setName(name);
        ballShape = new CircleShape(radius);
        Fixture fixture = new SolidFixture(this, ballShape);
        ballRight = new BodyImage("data/fireBall.png", 2*radius);
        setGravityScale(0);
    }
    
    public FireBall(World w, Vec2 position, boolean direction) {
        this(w);    // call the constructor of this class with this parameter
        this.direction = direction;
        float x= 0.69f;
        if(direction){
            addImage(ballRight);
            setPosition(position.add(new Vec2(x,0)));
            setLinearVelocity(new Vec2(velocity,0));
        }
        else{
            ballLeft = new BodyImage("data/fireBallLeft.png", 2*radius);
            addImage(ballLeft);
            setPosition(position.add(new Vec2(-x,0)));
            setLinearVelocity(new Vec2(-velocity,0));
        }
        
        addCollisionListener(new CollisionListener(){

            @Override
            public void collide(CollisionEvent e) {
                if(e.getOtherBody().getClass() == Plant.class){
                    e.getOtherBody().destroy();
                }
                destroy();
            }
            
        });
    }

    public boolean isDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }    
}
