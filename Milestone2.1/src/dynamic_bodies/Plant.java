/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic_bodies;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.CircleShape;
import city.cs.engine.GhostlyFixture;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import levels.Level;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Pietro
 */
public class Plant extends StaticBody implements StepListener{

    float radius = 4.5f;
    
    Shape shape = new PolygonShape(
        0.659f,1.227f, 0.895f,0.783f, 0.884f,-0.681f, 0.552f,-1.239f, -0.569f,-1.239f, -0.907f,-0.681f, -0.907f,0.783f, -0.681f,1.233f);
    BodyImage image = new BodyImage("data/plant.png",2.5f);
    
    int firstStep=0, nextStep=0;
    Level world;
    
    float TRIGGERED_POSITION, UNTRIGGERED_POSITION;
    public static final float RANGE = 6.5f;
    
    public Plant(Level w) {
        super(w);
        this.world = w;
        SolidFixture sf = new SolidFixture(this,shape);
        sf.setRestitution(1.2f);
        Shape range = new CircleShape(radius, new Vec2(0,-1.7f));
        GhostlyFixture gf = new GhostlyFixture(this,range);
        addImage(image);
        w.getWorld().addStepListener(this);
    }
    
    public Plant(Level w, Vec2 pos) {
        this(w);
        TRIGGERED_POSITION=pos.y+1.3f;
        UNTRIGGERED_POSITION=pos.y -1.5f;
        setPosition(new Vec2(pos.x,UNTRIGGERED_POSITION));
    }

    public boolean inRangeLeft() {
        Body a = world.getActor();
        float gap = getPosition().x - a.getPosition().x;
        return gap < RANGE && gap > 0;
    }

    public boolean inRangeRight() {
        Body a = world.getActor();
        float gap = getPosition().x - a.getPosition().x;
        return gap > -RANGE && gap < 0;
    }
    
    @Override
    public void preStep(StepEvent e) {
        if (inRangeRight()) {
            if (getPosition().y != TRIGGERED_POSITION) {
                setPosition(new Vec2(getPosition().x,TRIGGERED_POSITION));
            }
        } else if (inRangeLeft()) {
            if (getPosition().y != TRIGGERED_POSITION) {
                setPosition(new Vec2(getPosition().x,TRIGGERED_POSITION));
            }
        } else {
            if (getPosition().y != UNTRIGGERED_POSITION) {
                setPosition(new Vec2(getPosition().x,UNTRIGGERED_POSITION));
            }
        }        
    }

    @Override
    public void postStep(StepEvent e) {
    }    
}
