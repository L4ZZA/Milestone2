/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import dynamic_bodies.Goomba;
import dynamic_bodies.SuperMario;
import main.PlayPanel;
import org.jbox2d.common.Vec2;
import static_bodies.Platform;

/**
 *
 * @author Pietro
 */
public abstract class Level extends World{ 
    //player object
    
    //list of characters (non-player)
    protected SuperMario mario;
    protected Goomba goomba;
    protected int nRoses=0;
    
    //list of platforms
    Platform goombaPlatform;
    //ground
    
    //level 
    public int currentLevel;
    protected PlayPanel view;
    protected boolean completed;
    protected Tracker stepListener =new Tracker();
    int stepCount= 0, previous= stepListener.stepCount;
    
    //view
    public abstract void setView(PlayPanel view);
    
    //steplistener
    protected void setStepListener(){
        addStepListener(stepListener);
    }
    
    public int getStepCount(){
        return stepListener.stepCount;
    }
    
    public Tracker getStepListener(){
        return stepListener;
    }
    
    public int getNumberOfRoses(){
        return nRoses;
    }
    
    //drawing
    public abstract void draw();
    
    //level complete
    public abstract boolean isCompleted();
    
    // get player
        
    public Level getWorld() {
        return this;
    }
    
    public SuperMario getActor() {
        return mario;
    }
    
    public void setPlayer(SuperMario mario){
        this.mario = mario;
    }
    
    //roses
    public int getRoses(){
        return nRoses;
    }
    
    protected class Tracker implements StepListener{

        int stepCount=0;
        final float HORIZONTAL_BOUNDS= 15.5f;
        
        @Override
        public void preStep(StepEvent e) {
            stepCount++;
            //if exist work with goomba
            if(goomba != null){
                if(goomba.getPosition().x > goombaPlatform.getBound("left")+.3f && !goomba.direction){
                    goomba.startWalking(-goomba.SPEED);
                }
                else if(goomba.getPosition().x < goombaPlatform.getBound("right")-.3f && goomba.direction){
                    goomba.startWalking(goomba.SPEED);
                }
                //System.out.println("goomba: "+goomba.getPosition().x);
                if(goomba.getPosition().x < goombaPlatform.getBound("left") && !goomba.direction){
                    goomba.direction=true;
                }
                else if(goomba.getPosition().x > goombaPlatform.getBound("right") && goomba.direction){
                    goomba.direction = false;
                }
            }
            /*
            if(mario.goombaTouched && mario.isMoving()){
                mario.removeAllImages();
                if(mario.direction)
                    mario.addImage(SuperMario.opaqueRight);
                else
                    mario.addImage(SuperMario.opaqueLeft);
            }
            */
        }

        @Override
        public void postStep(StepEvent e) {
            
            if(mario != null){
                if(mario.getPosition().x < -HORIZONTAL_BOUNDS && !mario.direction){
                    mario.setPosition(new Vec2(-HORIZONTAL_BOUNDS,mario.getPosition().y));
                }
                else if(mario.getPosition().x > HORIZONTAL_BOUNDS && mario.direction){
                    mario.setPosition(new Vec2(HORIZONTAL_BOUNDS,mario.getPosition().y));
                }
            }
        }
    }
}
