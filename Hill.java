import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The hill is a simple obstacle and does nithing then blocking the way.
 * 
 * @version 0.1.0 - 01.10.2025
 * @author Paul Jonas Dohle
 */
public class Hill extends Actor {

    /**
     * It creates a hill.
     */
    public Hill() {
    }

    /**
     * It creates a hill at the specified location.
     * 
     * @param x The x position of the hill.
     * @param y The y position of the hill.
     */
    public Hill(int x, int y) {
    }

    protected void addedToWorld(World world) {
        allignToGrid();
    }

    private void allignToGrid() {
        int x = getX();
        int y = getY();
        x -= (x % 50) - 25;
        y -= (y % 50) - 25;
        setLocation(x, y);
    }
}
