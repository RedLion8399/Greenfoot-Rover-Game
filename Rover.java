import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Rover is the main character of the game.
 * 
 * The Rover is controlled by one of two players.
 * It can drive, turn ad shoot (at least if it has
 * munitions wich can be picked up).
 * When hit it lossed lives until it is dead and the game is over.
 * 
 * @version 0.1.0 - 01.10.2025
 * @author Paul Jonas Dohle
 */
public class Rover extends Actor {

    private int munitions;
    private int lives;
    private Scoreboard scoreboard;
    private Typ roverTyp;

    /**
     * Creates a new Rover.
     * 
     * @param roverTyp the type of the Rover
     */
    public Rover(Typ roverTyp) {
        this.roverTyp = roverTyp;

        if (roverTyp == Typ.RED) {
            setImage("images/roverRed.png");
        } else {
            setImage("images/roverBlue.png");
        }
    }

    /**
     * The main method of the Rover.
     * If the game runs this method is called in a loop.
     */
    public void act() {
    }

    /**
     * Observes and processes the user's input from the keyboard.
     * It moves the rover and shoots beams.
     */
    public void getUserInput() {
        if (roverTyp == Typ.RED) {
            if (Greenfoot.isKeyDown("up")) {
                drive();
            }
            if (Greenfoot.isKeyDown("down")) {
                shoot();
            }
            if (Greenfoot.isKeyDown("left")) {
                turn(Direction.LEFT);
            }
            if (Greenfoot.isKeyDown("right")) {
                turn(Direction.RIGHT);
            }
        }

        if (roverTyp == Typ.BLUE) {
            if (Greenfoot.isKeyDown("w")) {
                drive();
            }
            if (Greenfoot.isKeyDown("s")) {
                shoot();
            }
            if (Greenfoot.isKeyDown("a")) {
                turn(Direction.LEFT);
            }
            if (Greenfoot.isKeyDown("d")) {
                turn(Direction.RIGHT);
            }
        }
    }

    /**
     * Move the Rover by one unit in the specified direction.
     * If the rover is facing a hill or is on the edge of the world it
     * will not move.
     * 
     * @param forward if true, the rover will move forward, if false,
     *                it will move backward
     */
    public void drive(boolean forward) {
        if (forward) {
            if (!isHillAhead()) {
                move(1);
            }
        }
    }

    /**
     * Move the Rover by one unit in the direction it is currently facing.
     * If the rover is facing a hill or is on the edge of the world it
     * will not move.
     */
    public void drive() {
        drive(true);
    }

    /**
     * Turn the Rover by 90 degrees in the specified direction.
     * 
     * @param direction
     */
    public void turn(Direction direction) {
        switch (direction) {
            case RIGHT -> turn(90);
            case LEFT -> turn(-90);
            default -> {
            }
        }
    }

    /**
     * Shoots a beam in the direction the rover is currently facing.
     * The beam will move until it hits the edge of the world, a hill or
     * another rover.
     * If it hits a rover, the rover which was hit will loose one life.
     * If it hits a hill, the beam will be destroyed.
     * The beam will move in a straight line until it hits something.
     */
    public void shoot() {
    }

    /**
     * Called when the rover has been hit by a beam.
     * The rover will loose one life.
     */
    public void hit() {
    }

    /**
     * Checks if there is a hill directly in front of the rover.
     * 
     * @return true if there is a hill ahead, false otherwise
     */
    public boolean isHillAhead() {
        int rot = getRotation();

        return (getOneObjectAtOffset(1, 0, Hill.class) != null && rot == 0) ||
                (getOneObjectAtOffset(0, 1, Hill.class) != null && rot == 90) ||
                (getOneObjectAtOffset(-1, 0, Hill.class) != null && rot == 180) ||
                (getOneObjectAtOffset(0, -1, Hill.class) != null && rot == 270);
    }

    /**
     * If the rover is standing on a charge object, this method
     * will pick up the charge and add the munitions to the rover's
     * current munitions.
     */
    public void takeCharge() {
    }

    protected void addedToWorld(World world) {
        scoreboard = new Scoreboard(world);
    }

    /**
     * A helper class for displaying the rovers lives and munitions.
     * It also provides space for messages like a console.
     * 
     * @version 0.1.0 - 01.10.2025
     * @author Paul Jonas Dohle
     */
    class Scoreboard extends Actor {

        public Scoreboard(World world) {
            setImage("images/nachricht.png");

            if (roverTyp == Typ.RED) {
                world.addObject(this, 2, 3);
            } else {
                world.addObject(this, 21, 3);
            }
        }

        /**
         * Updates the scoreboard to show the current lives and munitions of the rover.
         * This method should be called every time the rover's lives or munitions
         * change.
         */
        public void update() {
        }

        /**
         * Displays a message on a part of the Rover's scoreboard for one second.
         * If the display is null, this method does nothing.
         * 
         * @param messageText the message to be displayed
         */
        private void message(String messageText) {
        }

        /**
         * Clears the message on the scoreboard.
         */
        public void clear() {
            getImage().clear();
            setImage("images/nachricht.png");
        }
    }

    /**
     * A helper class for beams.
     * 
     * Beams are used to shoot at other rovers.
     * If they hit a hill, they will be destroyed.
     * If they hit a rover, the rover will loose one life.
     * 
     * @version 0.1.0 - 01.10.2025
     * @author Paul Jonas Dohle
     */
    class Beam extends Actor {
        /**
         * Creates a new beam shooting in the direction
         * the rover is currently facing.
         */
        public Beam() {
        }

        /**
         * Moves the beam in the direction it is currently
         * facing.
         */
        public void act() {
        }

        /**
         * Checks if the beam has hit a hill.
         * 
         * @return true if the beam has hit a hill, false otherwise
         */
        public boolean isHillHit() {
        }

        /**
         * Checks if the beam has hit a rover.
         * 
         * @return true if the beam has hit a rover, false otherwise
         */
        public boolean isRoverHit() {
        }

        /**
         * Checks if the beam has hit the edge of the world.
         * 
         * @return true if the beam has hit the edge of the world, false otherwise
         */
        public boolean isEdgeHit() {
        }
    }

    /**
     * The type of the Rover.
     * 
     * @link Typ#RED
     * @link Typ#BLUE
     * 
     * @version 0.1.0 - 01.10.2025
     * @author Paul Jonas Dohle
     */
    public enum Typ {
        RED, BLUE
    }

    /**
     * The direction the Rover is facing or turning.
     * 
     * 
     * @version 0.1.0 - 01.10.2025
     * @author Paul Jonas Dohle
     */
    enum Direction {
        RIGHT, LEFT, FORWARD, BACKWARD;
    }
}
