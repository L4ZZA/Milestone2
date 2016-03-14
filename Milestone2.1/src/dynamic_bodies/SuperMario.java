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
import org.jbox2d.common.Vec2;

/**
 *
 * @author Pietro
 */
public class SuperMario extends Walker {

    private static final float JUMPING_SPEED = 15;
    private static final float WALKING_SPEED = 3;
    private boolean isMoving=false,released=true;
    // Remember:  using the keyword static below means the fields shape and image belong to the class, rather than any instance. 
    // That means there is a single shape and image for all instances of the class.
    private static final Shape face = new PolygonShape(
            0.206f,0.454f, 0.39f,0.191f, 0.383f,-0.075f, 0.161f,-0.202f, -0.244f,-0.199f, -0.42f,-0.071f, -0.427f,0.098f, -0.131f,0.45f);

    private static final Shape body = new PolygonShape(
            0.143f,-0.217f, 0.266f,-0.334f, 0.21f,-0.731f, -0.251f,-0.735f, -0.304f,-0.551f, -0.304f,-0.27f, -0.247f,-0.21f);
    private static BodyImage imageStill =
        new BodyImage("data/mario_stop.png", 3);

    private String name = "Mario";
    private int orangeCount;
    private boolean moving, //true = mario is moving , false = mario is not moving
                    direction;//true = moving right  ,  false = moving left
    private World world;

    public SuperMario(World world) {
        super(world);
        this.world = world;
        Fixture faceFixture = new SolidFixture(this,face);
        Fixture bodyFixture = new SolidFixture(this,body);
        
        addImage(imageStill);
        orangeCount = 0;
        setName(name);
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
                    addImage(new BodyImage("data/mario_stop.png",3));
                }
                break;
                
            case KeyEvent.VK_LEFT: // walk left
                startWalking(-WALKING_SPEED); 
                setDirection(false);
                removeAllImages();
                addImage(new BodyImage("data/mario_move_left.png",3));
                break;
                
            case KeyEvent.VK_RIGHT: // walk right
                startWalking(WALKING_SPEED); 
                setDirection(true);
                removeAllImages();
                addImage(new BodyImage("data/mario_move_right.png",3));
                break;
            
            case KeyEvent.VK_SPACE:
                if(released){
                    new FireBall(world, getPosition(), getDirection());
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
