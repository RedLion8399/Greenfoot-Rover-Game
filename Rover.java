import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Rover extends Actor {

    private Display anzeige;

    public void act() {
    }

    public void fahre() {
        int posX = getX();
        int posY = getY();

        if (isHill("vorne")) {
            message("Zu steil!");
        } else if (getRotation() == 270 && getY() == 1) {
            message("Ich kann mich nicht bewegen");
        } else {
            move(1);
            Greenfoot.delay(1);
        }

        if (posX == getX() && posY == getY() && !isHill("vorne")) {
            message("Ich kann mich nicht bewegen");
        }
    }

    public void drehe(String richtung) {
        if (richtung == "rechts") {
            turn(90);
        } else if (richtung == "links") {
            turn(-90);
        } else {
            message("Befehl nicht korrekt!");
        }
    }

    public boolean gesteinVorhanden() {
        if (getOneIntersectingObject(Gestein.class) != null) {
            message("Gestein gefunden!");
            return true;
        }

        message("Kein Gestein vorhanden!");
        return false;
    }

    public boolean isHill(String richtung) {
        int rot = getRotation();

        if (richtung == "vorne" && rot == 0 || richtung == "rechts" && rot == 270 || richtung == "links" && rot == 90) {
            if (getOneObjectAtOffset(1, 0, Hill.class) != null) {
                return true;
            }
        }

        if (richtung == "vorne" && rot == 180 || richtung == "rechts" && rot == 90
                || richtung == "links" && rot == 270) {
            if (getOneObjectAtOffset(-1, 0, Hill.class) != null) {
                return true;
            }
        }

        if (richtung == "vorne" && rot == 90 || richtung == "rechts" && rot == 0 || richtung == "links" && rot == 180) {
            if (getOneObjectAtOffset(0, 1, Hill.class) != null) {
                return true;
            }

        }

        if (richtung == "vorne" && rot == 270 || richtung == "rechts" && rot == 180
                || richtung == "links" && rot == 0) {
            if (getOneObjectAtOffset(0, -1, Hill.class) != null) {
                return true;
            }
        }

        if (richtung != "vorne" && richtung != "links" && richtung != "rechts") {
            message("Befehl nicht korrekt!");
        }

        return false;
    }

    public void nimmGestein() {
        if (gesteinVorhanden()) {
            Greenfoot.delay(1);
            removeTouching(Gestein.class);
        } else {
            message("Hier ist kein Gestein");
        }
    }

    private void message(String pText) {
        if (anzeige != null) {
            anzeige.print(pText);
            Greenfoot.delay(1);
            anzeige.clear();
        }
    }

    private void displayAusschalten() {
        getWorld().removeObject(anzeige);
    }

    protected void addedToWorld(World world) {
        setImage("images/rover.png");
        world = getWorld();
        anzeige = new Display();
        anzeige.setImage("images/nachricht.png");
        world.addObject(anzeige, 7, 0);
        if (getY() == 0) {
            setLocation(getX(), 1);
        }
        anzeige.print("Ich bin bereit");
    }

    class Display extends Actor {
        public void print(String message) {
            clear();
            getImage().drawImage(new GreenfootImage(message, 25, Color.BLACK, new Color(0, 0, 0, 0)), 10, 10);
        }

        public void clear() {
            getImage().clear();
            setImage("images/nachricht.png");
        }
    }
}
