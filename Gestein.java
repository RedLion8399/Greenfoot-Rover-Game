import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Gestein extends Actor {

    public Gestein() {
        if (Greenfoot.getRandomNumber(2) == 0) {
            setImage("images/gesteinRot.png");
        } else {
            setImage("images/gesteinBlau.png");
        }
    }
}
