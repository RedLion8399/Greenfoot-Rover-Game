import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Charge can be picked up by the rover to restock his munitions.
 * 
 * @version 0.1.0 - 01.10.2025
 * @author Paul Jonas Dohle
 */
public class Charge extends Actor {

    /** The amount of munitions the Charge contains. */
    public int munitions;

    /**
     * The Charge is created at the given position
     * with a random color of either red or blue.
     * It generates a random amount of munitions.
     * 
     * @param x the x position of the Charge
     * @param y the y position of the Charge
     * 
     * @version 0.1.0 - 01.10.2025
     * @author Paul Jonas Dohle
     */
    public Charge(int x, int y) {
        if (Greenfoot.getRandomNumber(2) == 0) {
            setImage("images/chargeRed.png");
        } else {
            setImage("images/chargeBlue.png");
        }
    }
}
