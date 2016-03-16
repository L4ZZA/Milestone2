/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic_bodies;

import city.cs.engine.BodyImage;
import city.cs.engine.DynamicBody;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.Walker;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Pietro
 */
public class Princess extends Walker{

    private static final Shape body = new PolygonShape(
        0.14f,0.911f, 0.39f,0.572f, 0.462f,-0.847f, 0.14f,-0.962f, -0.25f,-0.966f, -0.496f,-0.839f, -0.411f,0.669f, -0.169f,0.911f);
    private static final BodyImage image =new BodyImage("data/princess_left.png",2);
    
    public Princess(World w) {
        super(w);
        SolidFixture f = new SolidFixture(this,body);
        addImage(image);
    }
    
    public Princess(World w, Vec2 pos){
        this(w);
        setPosition(pos);
    }
    
}
