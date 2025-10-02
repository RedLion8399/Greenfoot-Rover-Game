import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Rover is the main character of the game.
 * 
 * The Rover is controlled by one of two players.
 * It can drive, turn ad shoot (at least if it has
 * munitions wich can be picked up).
 * When hit it lossed lives until it is dead and the game is over.
 * 
 * @version 0.3.0 - 02.10.2025
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
        getUserInput();
        scoreboard.update();
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
            if (isHillAhead() || isRoverahead() || isScoreboardAhead()) {
                return;
            }
            move(1);
            takeCharge();
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
        if (munitions > 0) {
            Beam beam = new Beam(this);
            munitions--;
        }
    }

    /**
     * Called when the rover has been hit by a beam.
     * The rover will loose one life.
     */
    public void hit() {
    }

    /**
     * Checks if there is an object of the specified class directly ahead
     * of the rover. The rover's direction is taken into account.
     * 
     * @param object the class of objects to check for
     * @return true if there is an object ahead, false otherwise
     */
    public boolean isObjectAhead(Class<? extends Actor> object) {
        int rot = getRotation();

        return (getOneObjectAtOffset(1, 0, object) != null && rot == 0) ||
                (getOneObjectAtOffset(0, 1, object) != null && rot == 90) ||
                (getOneObjectAtOffset(-1, 0, object) != null && rot == 180) ||
                (getOneObjectAtOffset(0, -1, object) != null && rot == 270);
    }

    /**
     * Checks if there is a hill directly in front of the rover.
     * 
     * @return true if there is a hill ahead, false otherwise
     */
    public boolean isHillAhead() {
        return isObjectAhead(Hill.class);
    }

    /**
     * Checks if there is a rover directly in front of the rover.
     * 
     * @return true if there is a rover ahead, false otherwise
     */
    public boolean isRoverahead() {
        return isObjectAhead(Rover.class);
    }

    /**
     * Checks if there is a Scoreboard directly in front of the rover.
     * 
     * @return true if there is a Scoreboard ahead, false otherwise
     */
    public boolean isScoreboardAhead() {
        return isObjectAhead(Scoreboard.class);
    }

    /**
     * If the rover is standing on a charge object, this method
     * will pick up the charge and add the munitions to the rover's
     * current munitions.
     */
    public void takeCharge() {
        if (getOneObjectAtOffset(0, 0, Charge.class) != null) {
            Charge charge = (Charge) getOneObjectAtOffset(0, 0, Charge.class);
            this.munitions += charge.munitions;
            getWorld().removeObject(charge);
        }
    }

    protected void addedToWorld(World world) {
        scoreboard = new Scoreboard(world);
    }

    /**
     * A helper class for displaying the rovers lives and munitions.
     * 
     * @version 1.0.0 - 02.10.2025
     * @author Paul Jonas Dohle
     */
    class Scoreboard extends Actor {

        private String message;

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
            String messageText = String.format(
                    "Player: %s\n\nLives left: %d\nShots left: %d",
                    (roverTyp == Typ.RED ? "Red" : "Blue"), lives, munitions);

            clear();
            getImage().drawImage(new GreenfootImage(messageText, 25, Color.BLACK, new Color(0, 0, 0, 0)), 10, 10);
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
     * @version 1.0.0 - 02.10.2025
     * @author Paul Jonas Dohle
     */
    class Beam extends Actor {

        private Rover shooter;

        /**
         * Creates a new beam shooting in the direction
         * the rover is currently facing.
         */
        public Beam(Rover shooter) {
            setImage("images/beam.png");
            this.shooter = shooter;
            shooter.getWorld().addObject(this, shooter.getX(), shooter.getY());
            setRotation(shooter.getRotation());
        }

        /**
         * Moves the beam in the direction it is currently
         * facing.
         */
        public void act() {
            int x = this.getX();
            int y = this.getY();

            if (isHillHit() || isScoreboardHit()) {
                getWorld().removeObject(this);
                return;
            }

            if (isRoverHit()) {
                ((Rover) getOneObjectAtOffset(0, 0, Rover.class)).hit();
                getWorld().removeObject(this);
                return;
            }

            move(1);

            if (x == this.getX() && y == this.getY()) {
                // If it is stuck at the edge, remove it
                getWorld().removeObject(this);
            }
        }

        /**
         * Checks if the beam has hit a hill.
         * 
         * @return true if the beam has hit a hill, false otherwise
         */
        public boolean isHillHit() {
            return (getOneObjectAtOffset(0, 0, Hill.class) != null);
        }

        /**
         * Checks if the beam has hit a scoreboard.
         * 
         * @return true if the beam has hit a scoreboard, false otherwise
         */
        public boolean isScoreboardHit() {
            return (getOneObjectAtOffset(0, 0, Scoreboard.class) != null);
        }

        /**
         * Checks if the beam has hit a rover.
         * 
         * @return true if the beam has hit a rover, false otherwise
         */
        public boolean isRoverHit() {
            return (getOneObjectAtOffset(0, 0, Rover.class) != null &&
                    getOneObjectAtOffset(0, 0, Rover.class) != shooter);
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
