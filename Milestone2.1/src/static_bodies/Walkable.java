/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package static_bodies;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Pietro
 */
public abstract class Walkable extends StaticBody{

    protected final float WIDTH, HEIGHT;
    protected float LEFT, BOTTOM, RIGHT, TOP; 
    
    protected BoxShape shape;
    
    protected Walkable(World w, float width, float height) {
        super(w, new BoxShape(width,height));
        shape = new BoxShape(width,height);
        WIDTH = width;
        HEIGHT = height;
        SolidFixture f = new SolidFixture(this,shape);
        f.setFriction(5);
        Shape leftside = new BoxShape(0.01f, HEIGHT-.001f, new Vec2(-WIDTH, 0));
        Shape rightside = new BoxShape(0.01f, HEIGHT-.001f, new Vec2(WIDTH, 0));
        SolidFixture sidel = new SolidFixture(this,leftside);
        SolidFixture sider = new SolidFixture(this,rightside);
        sidel.setFriction(0);
        sider.setFriction(0);
    }
    
    protected Walkable(World w, float width, float height, Vec2 position){
        this(w,width,height);
        setPosition(position);
        LEFT = getPosition().x - (WIDTH);
        RIGHT = getPosition().x + (WIDTH);
        TOP = getPosition().y + (HEIGHT);
        BOTTOM = getPosition().y - (HEIGHT);
    }
 
    public float getBound(String s){
        if(s.equalsIgnoreCase("top")){
            return TOP;
        }
        else if(s.equalsIgnoreCase("right")){
            return RIGHT;
        }
        else if(s.equalsIgnoreCase("bottom")){
            return BOTTOM;
        }
        else if(s.equalsIgnoreCase("left")){
            return LEFT;
        }
        else{
            return 0;
        }
    }
    
    public float getWidth(){
        return WIDTH;
    }
    
    public float getHeight(){
        return HEIGHT;
    }
}
