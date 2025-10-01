import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Charge extends Actor {

    public Charge() {
        if (Greenfoot.getRandomNumber(2) == 0) {
            setImage("images/chargeRed.png");
        } else {
            setImage("images/chargeBlue.png");
        }
    }
}
