/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic_bodies;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.SolidFixture;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Pietro
 */
public class Rose extends DynamicBody{

    private static final BodyImage image =new BodyImage("data/rose.png");
    
    public Rose(World w) {
        super(w);
        SolidFixture f = new SolidFixture(this,new BoxShape(0.5f,0.5f));
        addImage(image);
    }
    
    public Rose(World w, Vec2 pos){
        this(w);
        setPosition(pos);
    }
    
}
