/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package static_bodies;

import city.cs.engine.BodyImage;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Pietro
 */
public class Pipe extends Walkable{

    private static BodyImage image= new BodyImage("data/pipe.png",2);
    
    public Pipe(World w, Vec2 position) {
        super(w, 1.15f, 1f, position);
        addImage(image);
    }
    
}
