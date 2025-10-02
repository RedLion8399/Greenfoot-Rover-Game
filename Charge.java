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
     * Creates a new charge with a random color.
     * 
     * @version 1.0.0 - 02.10.2025
     * @author Paul Jonas Dohle
     */
    public Charge() {
        if (Greenfoot.getRandomNumber(2) == 0) {
            setImage("images/chargeRed.png");
        } else {
            setImage("images/chargeBlue.png");
        }

        int randomNumber = Greenfoot.getRandomNumber(100);
        if (randomNumber < 40) {
            this.munitions += 1;
        } else if (randomNumber < 70) {
            this.munitions += 2;
        } else if (randomNumber < 90) {
            this.munitions += 3;
        } else {
            this.munitions += 4;
        }
    }
}
