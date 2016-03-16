/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic_bodies;

import city.cs.engine.BodyImage;
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
public class Goomba extends Walker{

    private static final Shape body = new PolygonShape(
        0.125f,0.5f, 0.436f,0.248f, 0.497f,-0.033f, 0.313f,-0.498f, -0.375f,-0.497f, -0.498f,-0.03f, -0.437f,0.25f, -0.125f,0.498f);
    private static final BodyImage image =new BodyImage("data/goomba.png");
    
    public boolean direction = true;// true = move right, false = move left
    private boolean touchedMario=false;
    public final float SPEED= 1.5f;
    
    public Goomba(World w) {
        super(w);
        SolidFixture f = new SolidFixture(this,body);
        addImage(image);
        
    }
    
    public Goomba(World w, Vec2 position){
        this(w);
        setPosition(position);
    }

    public boolean getTouchedMario() {
        return touchedMario;
    }

    public void setTouchedMario(boolean touchedMario) {
        this.touchedMario = touchedMario;
    }
    
}
