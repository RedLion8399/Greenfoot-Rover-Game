import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Planet extends World {
    private static int zellenGroesse = 50;

    public Planet() {
        super(16, 12, zellenGroesse);
        setBackground("images/boden.png");
        setPaintOrder(String.class, Rover.class, Gestein.class, Hill.class);
        Greenfoot.setSpeed(20);
    }
}