import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The main world of the game.
 * 
 * It controlls the generation of the world and the relevant actor objects.
 * Additionaly it controlls the game logic and propability of events.
 * 
 * @version 0.2.0 - 02.10.2025
 * @author Paul Jonas Dohle
 */
public class Planet extends World {

    private Rover playerRed;
    private Rover playerBlue;

    /**
     * Creates a new world with a size of 26x17 cells with a cell size of 50x50
     * pixels although the movement is undependent of the cell size.
     * It also instanciates the Rover objects for each player
     */
    public Planet() {
        super(1300, 850, 1);

        Greenfoot.setSpeed(50);
        setBackground("images/boden.png");
        setPaintOrder(String.class, Rover.class, Charge.class, Hill.class);

        playerRed = new Rover(Rover.Typ.RED);
        playerBlue = new Rover(Rover.Typ.BLUE);
        addObject(playerRed, 75, 425);
        playerRed.setRotation(180);

        addObject(playerBlue, 1225, 425);
    }

    /**
     * The act method is called every time the world is updated.
     */
    public void act() {
        generateCharges();
    }

    /**
     * Randomly generates charges at random positions in the world.
     * Aproximately every 200th call of this method a charge will be generated.
     */
    public void generateCharges() {
        if (Greenfoot.getRandomNumber(200) == 0) {
            int randX;
            int randY;
            do {
                randX = Greenfoot.getRandomNumber(getWidth());
                randY = Greenfoot.getRandomNumber(getHeight());
                randX -= (randX % 50) - 25;
                randY -= (randY % 50) - 25;
            } while (getObjectsAt(randX, randY, Rover.Scoreboard.class).size() > 0);
            Charge charge = new Charge();
            addObject(charge, randX, randY);
        }
    }
}