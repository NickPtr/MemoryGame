
import java.awt.Color;
import javax.swing.Icon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Νίκος
 */
//klasi gia tin dimiourgia twn kartwn
class Tile 
{
    String shape;
    Color color;
    Icon icon;
    
    public Tile(String shape, Color color, Icon icon){
        this.color=color;
        this.shape=shape;
        this.icon=icon;
    }
    
    public String GetShape()
    {
        return shape;
    }
    
    public Color GetColor()
    {
        return color;
    }
    
    public Icon GetIcon()
    {
        return icon;
    }
}
