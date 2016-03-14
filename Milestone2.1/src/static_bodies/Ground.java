/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package static_bodies;

import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Fixture;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import java.awt.Color;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Pietro
 */
public class Ground extends StaticBody{

    
    private Vec2 boundaries;
    
    private final float WIDTH=16, HEIGHT=1;
    private final float LEFT, BOTTOM, RIGHT, TOP; 
    
    private Shape shape=new BoxShape(WIDTH, HEIGHT);
    
    public Ground(World w) {
        super(w,new BoxShape(16, 1));
        setPosition(new Vec2(0,-12));
        LEFT = -8;
        BOTTOM =-15.5f;
        RIGHT = 8;
        TOP = -14.5f;
        Fixture g = new SolidFixture(this, shape);
        this.setFillColor(new Color(128, 64, 0));
    }
    
}
