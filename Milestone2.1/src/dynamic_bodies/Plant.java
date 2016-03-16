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
import city.cs.engine.StaticBody;
import city.cs.engine.Walker;
import city.cs.engine.World;
import main.Frame;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Pietro
 */
public class Plant extends StaticBody{

    float radius = 4.5f;
    
    Shape shape = new PolygonShape(
        0.659f,1.227f, 0.895f,0.783f, 0.884f,-0.681f, 0.552f,-1.239f, -0.569f,-1.239f, -0.907f,-0.681f, -0.907f,0.783f, -0.681f,1.233f);
    BodyImage image = new BodyImage("data/plant.png",2.5f);
    
    float TRIGGERED_POSITION, UNTRIGGERED_POSITION;
    
    public Plant(World w) {
        super(w);
        SolidFixture sf = new SolidFixture(this,shape);
        sf.setRestitution(1.2f);
        Shape range = new CircleShape(radius, new Vec2(0,-1.7f));
        GhostlyFixture gf = new GhostlyFixture(this,range);
        addImage(image);
    }
    
    public Plant(World w, Vec2 pos) {
        this(w);
        TRIGGERED_POSITION=pos.y+1.3f;
        UNTRIGGERED_POSITION=pos.y -1.5f;
        setPosition(new Vec2(pos.x,TRIGGERED_POSITION));
    }
}
