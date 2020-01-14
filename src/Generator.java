
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Νίκος
 */
public class Generator {

    //arxikopoihsi metablitwn
    private String level;
    public static ArrayList<Tile> tile = new ArrayList<Tile>();

    public Generator(String level) {
        this.level = level;

        //dimiourgia tou plithous twn kartwn analoga me tin epilogi pou exei dwsei o xristis
        switch (level) {
            case "εύκολο":
                tile.add(new Tile("circle", Color.BLACK, getColorIcon(Color.BLACK, "circle")));
                AddTiles(Color.RED);
                AddTiles(Color.BLUE);
                tile.add(new Tile("circle", Color.YELLOW, getColorIcon(Color.YELLOW, "circle")));
                tile.add(new Tile("circle", Color.YELLOW, getColorIcon(Color.YELLOW, "circle")));
                tile.add(new Tile("squere", Color.YELLOW, getColorIcon(Color.YELLOW, "squere")));
                tile.add(new Tile("squere", Color.YELLOW, getColorIcon(Color.YELLOW, "squere")));
                break;
            case "μέτριο":
                tile.add(new Tile("circle", Color.BLACK, getColorIcon(Color.BLACK, "circle")));
                tile.add(new Tile("squere", Color.BLACK, getColorIcon(Color.BLACK, "squere")));
                AddTiles(Color.RED);
                AddTiles(Color.BLUE);
                AddTiles(Color.YELLOW);
                AddTiles(Color.GREEN);
                AddTiles(Color.PINK);
                AddTiles(Color.CYAN);
                tile.add(new Tile("circle", Color.ORANGE, getColorIcon(Color.ORANGE, "circle")));
                tile.add(new Tile("circle", Color.ORANGE, getColorIcon(Color.ORANGE, "circle")));
                break;
            case "δύσκολο":
                tile.add(new Tile("circle", Color.BLACK, getColorIcon(Color.BLACK, "circle")));
                tile.add(new Tile("squere", Color.BLACK, getColorIcon(Color.BLACK, "squere")));
                AddTiles(Color.RED);
                AddTiles(Color.BLUE);
                AddTiles(Color.YELLOW);
                AddTiles(Color.GREEN);
                AddTiles(Color.PINK);
                AddTiles(Color.CYAN);
                AddTiles(Color.ORANGE);
                AddTiles(Color.MAGENTA);
                AddTiles(Color.GRAY);
                tile.add(new Tile("circle", Color.LIGHT_GRAY, getColorIcon(Color.LIGHT_GRAY, "circle")));
                tile.add(new Tile("circle", Color.LIGHT_GRAY, getColorIcon(Color.LIGHT_GRAY, "circle")));
                tile.add(new Tile("squere", Color.LIGHT_GRAY, getColorIcon(Color.LIGHT_GRAY, "squere")));
                tile.add(new Tile("squere", Color.LIGHT_GRAY, getColorIcon(Color.LIGHT_GRAY, "squere")));
                tile.add(new Tile("triangle", Color.LIGHT_GRAY, getColorIcon(Color.LIGHT_GRAY, "triangle")));
                tile.add(new Tile("triangle", Color.LIGHT_GRAY, getColorIcon(Color.LIGHT_GRAY, "triangle")));
                tile.add(new Tile("rhombus", Color.LIGHT_GRAY, getColorIcon(Color.LIGHT_GRAY, "rhombus")));
                tile.add(new Tile("rhombus", Color.LIGHT_GRAY, getColorIcon(Color.LIGHT_GRAY, "rhombus")));
                break;
            default:
                break;
        }
    }
    //sinartisi gia tin dimiourgia twn sximatwn kai twn xromatismo autwn 
    public void AddTiles(Color color) {
        for (int i = 0; i < 10; i++) {
            if (i < 2) {
                tile.add(new Tile("circle", color, getColorIcon(color, "circle")));
            } else if (i >= 2 && i < 4) {
                tile.add(new Tile("triangle", color, getColorIcon(color, "triangle")));
            } else if (i >= 4 && i < 6) {
                tile.add(new Tile("rectangle", color, getColorIcon(color, "rectangle")));
            } else if (i >= 6 && i < 8) {
                tile.add(new Tile("squere", color, getColorIcon(color, "squere")));
            } else {
                tile.add(new Tile("rhombus", color, getColorIcon(color, "rhombus")));
            }
        }
    }
    //sinartisi gia tin dimiourgia twn apaitoumenwn sximatwn se icons
    public Icon getColorIcon(Color color, String shape) {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(color);
        switch (shape) {
            case "rectangle":
                g.fillRect(25, 35, 50, 25);
                g.setColor(color);
                g.drawRect(25, 35, 50, 25);
                break;
            case "squere":
                g.fillRect(25, 25, 50, 50);
                g.setColor(color);
                g.drawRect(25, 25, 50, 50);
                break;
            case "circle":
                g.fillOval(25, 25, 50, 50);
                g.setColor(color);
                g.drawOval(25, 25, 50, 50);
                break;
            case "triangle":
                g.fillPolygon(new int[] {20, 40, 60}, new int[] {50, 10, 50}, 3);
                g.setColor(color);
                g.drawPolygon(new int[] {20, 40, 60}, new int[] {50, 10, 50}, 3);
                break;
            case "rhombus":
                g.fillPolygon(new int[] {10, 40, 60, 20}, new int[] {50, 25, 50, 25}, 4);
                g.setColor(color);
                g.drawPolygon(new int[] {10, 40, 60, 20}, new int[] {50, 25, 50, 25}, 4);
                break;
                 case "new":
                g.fillPolygon(new int[] {0, 0, 0, 0}, new int[] {0, 0, 0, 0}, 4);
                g.setColor(color);
                g.drawPolygon(new int[] {0, 0, 0, 0}, new int[] {0, 0, 0, 0}, 4);
                break;

        }

        return new ImageIcon(image);
    }

}
