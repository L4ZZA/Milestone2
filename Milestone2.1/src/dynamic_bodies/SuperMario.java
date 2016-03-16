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
import city.cs.engine.GhostlyFixture;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.Walker;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;
import levels.Level;
import main.Frame;
import org.jbox2d.common.Vec2;
import static_bodies.Lifes;

/**
 *
 * @author Pietro
 */
public class SuperMario extends Walker {

    private static final float JUMPING_SPEED = 10.5f;
    private static final float WALKING_SPEED = 3;
    private boolean released=true;
    
    // Remember:  using the keyword static below means the fields shape and image belong to the class, rather than any instance. 
    // That means there is a single shape and image for all instances of the class.
    private static final Shape face = new PolygonShape(
            0.095f,0.655f, 0.4f,0.6f, 0.525f,0.105f, 0.315f,-0.23f, -0.41f,-0.24f, -0.505f,0.135f, -0.435f,0.59f);
    private static final Shape body = new PolygonShape(
            0.32f,-0.23f, 0.505f,-0.37f, 0.5f,-0.985f, -0.515f,-0.99f, -0.52f,-0.89f, -0.375f,-0.23f);
    private static BodyImage imageStill = new BodyImage("data/mario_stop.png", 2);
    private static BodyImage left = new BodyImage("data/mario_move_left.png",2);
    private static BodyImage right = new BodyImage("data/mario_move_right.png",2);
    public static BodyImage opaqueRight = new BodyImage("data/mario_move_right_opaque.png",2);
    public static BodyImage opaqueLeft = new BodyImage("data/mario_move_left_opaque.png",2);
    SolidFixture faceFixture;
    SolidFixture bodyFixture;
    
    private boolean moving; //true = mario is moving , false = mario is not moving
    public boolean direction;//true = moving right  ,  false = moving left
    
    private Level world;
    
    private final int LIFE_MAX=5;
    private int currentLife=5;
    private Lifes[] heartsList = new Lifes[LIFE_MAX];
    
    public boolean goombaTouched =false;
    private final float gravityScale;
    
    private int rosesPicked;
    
    public SuperMario(Level world) {
        super(world);
        this.world = world;
        faceFixture = new SolidFixture(this,face);
        bodyFixture = new SolidFixture(this,body);
        GhostlyFixture gf = new GhostlyFixture(this, new CircleShape(3));
        released=true;
        rosesPicked=0;
        addImage(imageStill);
        gravityScale = getGravityScale();
        direction = true;
        for (int i = 0; i < LIFE_MAX; i++) {
            Lifes life = new Lifes(world,new Vec2(-15+i,11));
            heartsList[i]= life;
        }
        addCollisionListener(new Collision());
    }
    
    public SuperMario(Level world, Vec2 pos){
        this(world);
        setPosition(pos);
    }

    public int getRosesCount() {
        return rosesPicked;
    }
    
    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean isMoving) {
        this.moving = isMoving;
    }   

    public void decreaseLife(){
        if(currentLife>=1){
            heartsList[currentLife-1].destroy();
            heartsList[currentLife-1] = null;
            currentLife--;
        }
        else{
            dead();
        }
    }
    
    public void increaseLife(){
        if(currentLife < LIFE_MAX){
            currentLife++;
            if(currentLife == 0)
                heartsList[currentLife-1] = new Lifes(world,new Vec2(-15,11));
            else
                heartsList[currentLife-1] = new Lifes(world,heartsList[currentLife-2].getPosition().addLocal(1, 0));
        }
    }
    
    public void touchable(){
        goombaTouched=false;
        getFixtureList().set(1, faceFixture);
        getFixtureList().set(2, bodyFixture);
        setGravityScale(gravityScale);
    }
    
    public void untouchable(int prev){
        if(world.getStepCount() < prev+100){
            System.out.println("prev: "+prev +"\nstep: "+world.getStepCount());
            goombaTouched=true;
            GhostlyFixture faceFixture2 = new GhostlyFixture(this,face);
            GhostlyFixture bodyFixture2 = new GhostlyFixture(this,face);
            getFixtureList().set(1, faceFixture2);
            getFixtureList().set(2, bodyFixture2);
            setGravityScale(gravityScale);
        }
        else{
            touchable();
        }
    }
    
    public void dead(){
        this.destroy();
        try {
            Thread.sleep(1000);
            Frame.restartLevel();
        } catch (InterruptedException ex) {
            Logger.getLogger(SuperMario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void keyPressed(int k) {
        switch(k){
            case KeyEvent.VK_Q: // Q = quit
                System.exit(0);
                break;
                
            case KeyEvent.VK_UP: // arrow up = jump
                moving=true;
                Vec2 v = getLinearVelocity();// only jump if mario is not already jumping
                if (Math.abs(v.y) < 0.01f) {
                    jump(JUMPING_SPEED); 
                }
                else{
                    moving=false;
                }
                break;
                
            case KeyEvent.VK_LEFT: // walk left
                startWalking(-WALKING_SPEED);
                moving=true; 
                direction=false;
                removeAllImages();
                if(!goombaTouched)
                    addImage(left);
                else
                    addImage(opaqueLeft);
                break;
                
            case KeyEvent.VK_RIGHT: // walk right
                startWalking(WALKING_SPEED); 
                moving=true;
                direction=true;
                removeAllImages();
                if(!goombaTouched)
                    addImage(right);
                else
                    addImage(opaqueRight);
                break;
            
            case KeyEvent.VK_SPACE:
                if(released){
                    FireBall fireBall;
                    //if the ball is created on top of the character is goin to disappear due to the collision
                    if(direction)
                        fireBall = new FireBall(world, getPosition().add(new Vec2(0.5f,0)), direction);
                    else
                        
                        fireBall = new FireBall(world, getPosition().add(new Vec2(-0.5f,0)), direction);
                    released=false;
                }
                break;
                
        }
    }

    public void keyReleased(int k) {
        switch(k){
            case KeyEvent.VK_LEFT:
                moving=false;
                stopWalking();
                break;
                
            case KeyEvent.VK_RIGHT:
                moving=false;
                stopWalking();
                break;
                
            case KeyEvent.VK_SPACE:
                released=true;
                break;
        }
    }
    
    private class Collision implements CollisionListener{
        
        @Override
        public void collide(CollisionEvent e) {
            
            if(e.getOtherBody().getClass() == BonusLife.class){
                increaseLife();
                e.getOtherBody().destroy();
            }
            if(e.getOtherBody().getClass() == Plant.class){
                decreaseLife();
            }
            if(e.getOtherBody().getClass() == Goomba.class && !goombaTouched){
                //int prev =world.getStepCount();
                //untouchable(prev);
                if(direction){
                    move(new Vec2(-1,0));
                }
                else{
                    move(new Vec2(1,0));
                }
                for (int i = 0; i < 3; i++) {
                    decreaseLife();
                }
            }
            if(e.getOtherBody().getClass() == Rose.class){
                rosesPicked++;
                e.getOtherBody().destroy();
            }
            if(e.getOtherBody().getClass() == Princess.class && rosesPicked == world.getNumberOfRoses()){
                Frame.nextLevel(world.currentLevel);
                rosesPicked=0;
            }
        }
        
    }

}
