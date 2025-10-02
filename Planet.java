import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The main world of the game.
 * 
 * It controlls the generation of the world and the relevant actor objects.
 * Additionaly it controlls the game logic and propability of events.
 * 
 * @version 0.1.0 - 01.10.2025
 * @author Paul Jonas Dohle
 */
public class Planet extends World {
    private static int zellenGroesse = 50;

    private Rover playerRed;
    private Rover playerBlue;

    /**
     * Creates a new world with a size of 16x12 cells and instanciates the players
     * It also instanciates the Rover objects for each player
     */
    public Planet() {
        super(24, 12, zellenGroesse);
        setBackground("images/boden.png");
        setPaintOrder(String.class, Rover.class, Charge.class, Hill.class);
        Greenfoot.setSpeed(20);

        playerRed = new Rover(Rover.Typ.RED);
        playerBlue = new Rover(Rover.Typ.BLUE);
        addObject(playerRed, 5, 5);
        playerRed.setRotation(180);

        addObject(playerBlue, 18, 6);
    }

    /**
     * Randomly generates charges at random positions in the world.
     * Aproximately every 200th call of this method a charge will be generated.
     */
    public void generateCharges() {
    }
}