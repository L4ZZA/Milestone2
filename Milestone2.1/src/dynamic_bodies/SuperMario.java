/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic_bodies;

import city.cs.engine.BodyImage;
import city.cs.engine.Fixture;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.Walker;
import city.cs.engine.World;
import java.awt.event.KeyEvent;
import levels.Level;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Pietro
 */
public class SuperMario extends Walker {

    private static final float JUMPING_SPEED = 10;
    private static final float WALKING_SPEED = 3;
    private boolean isMoving=false,released=true;
    // Remember:  using the keyword static below means the fields shape and image belong to the class, rather than any instance. 
    // That means there is a single shape and image for all instances of the class.
    private static final Shape face = new PolygonShape(
            0.095f,0.655f, 0.4f,0.6f, 0.525f,0.105f, 0.315f,-0.23f, -0.41f,-0.24f, -0.505f,0.135f, -0.435f,0.59f);

    private static final Shape body = new PolygonShape(
            0.32f,-0.23f, 0.505f,-0.37f, 0.5f,-0.985f, -0.515f,-0.99f, -0.52f,-0.89f, -0.375f,-0.23f);
    private static BodyImage imageStill =
        new BodyImage("data/mario_stop.png", 2);

    private int orangeCount;
    private boolean moving, //true = mario is moving , false = mario is not moving
                    direction;//true = moving right  ,  false = moving left
    private Level world;

    public SuperMario(Level world) {
        super(world);
        this.world = world;
        Fixture faceFixture = new SolidFixture(this,face);
        Fixture bodyFixture = new SolidFixture(this,body);
        addImage(imageStill);
        setDirection(true);
    }

    public int getOrangeCount() {
        return orangeCount;
    }

    public void incrementOrangeCount() {
        orangeCount++;
        System.out.println("Tasty!  Orange count = " + orangeCount);
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean isMoving) {
        this.moving = isMoving;
    }

    public boolean getDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }    


    public void keyPressed(int k) {
        switch(k){
            case KeyEvent.VK_Q: // Q = quit
                System.exit(0);
                break;
                
            case KeyEvent.VK_UP: // arrow up = jump
                Vec2 v = getLinearVelocity();// only jump if mario is not already jumping
                if (Math.abs(v.y) < 0.01f) {
                    jump(JUMPING_SPEED); 
                    removeAllImages();
                    addImage(new BodyImage("data/mario_stop.png",2));
                }
                break;
                
            case KeyEvent.VK_LEFT: // walk left
                startWalking(-WALKING_SPEED); 
                setDirection(false);
                removeAllImages();
                addImage(new BodyImage("data/mario_move_left.png",2));
                break;
                
            case KeyEvent.VK_RIGHT: // walk right
                startWalking(WALKING_SPEED); 
                setDirection(true);
                removeAllImages();
                addImage(new BodyImage("data/mario_move_right.png",2));
                break;
            
            case KeyEvent.VK_SPACE:
                if(released){
                    FireBall fireBall = new FireBall(world, getPosition(), getDirection());
                    released=false;
                }
                break;
                
        }
    }

    public void keyReleased(int k) {
        switch(k){
            case KeyEvent.VK_LEFT:
                stopWalking();
                break;
                
            case KeyEvent.VK_RIGHT:
                stopWalking();
                break;
                
            case KeyEvent.VK_SPACE:
                released=true;
                break;
        }
    }

}
