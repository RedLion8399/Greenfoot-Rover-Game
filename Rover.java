import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Rover extends Actor {

    private Display anzeige;

    public void act() {
    }

    public void fahre() {
        int posX = getX();
        int posY = getY();

        if (huegelVorhanden("vorne")) {
            nachricht("Zu steil!");
        } else if (getRotation() == 270 && getY() == 1) {
            nachricht("Ich kann mich nicht bewegen");
        } else {
            move(1);
            Greenfoot.delay(1);
        }

        if (posX == getX() && posY == getY() && !huegelVorhanden("vorne")) {
            nachricht("Ich kann mich nicht bewegen");
        }
    }

    public void drehe(String richtung) {
        if (richtung == "rechts") {
            turn(90);
        } else if (richtung == "links") {
            turn(-90);
        } else {
            nachricht("Befehl nicht korrekt!");
        }
    }

    public boolean gesteinVorhanden() {
        if (getOneIntersectingObject(Gestein.class) != null) {
            nachricht("Gestein gefunden!");
            return true;
        }

        nachricht("Kein Gestein vorhanden!");
        return false;
    }

    public boolean huegelVorhanden(String richtung) {
        int rot = getRotation();

        if (richtung == "vorne" && rot == 0 || richtung == "rechts" && rot == 270 || richtung == "links" && rot == 90) {
            if (getOneObjectAtOffset(1, 0, Huegel.class) != null) {
                return true;
            }
        }

        if (richtung == "vorne" && rot == 180 || richtung == "rechts" && rot == 90
                || richtung == "links" && rot == 270) {
            if (getOneObjectAtOffset(-1, 0, Huegel.class) != null) {
                return true;
            }
        }

        if (richtung == "vorne" && rot == 90 || richtung == "rechts" && rot == 0 || richtung == "links" && rot == 180) {
            if (getOneObjectAtOffset(0, 1, Huegel.class) != null) {
                return true;
            }

        }

        if (richtung == "vorne" && rot == 270 || richtung == "rechts" && rot == 180
                || richtung == "links" && rot == 0) {
            if (getOneObjectAtOffset(0, -1, Huegel.class) != null) {
                return true;
            }
        }

        if (richtung != "vorne" && richtung != "links" && richtung != "rechts") {
            nachricht("Befehl nicht korrekt!");
        }

        return false;
    }

    public void nimmGestein() {
        if (gesteinVorhanden()) {
            Greenfoot.delay(1);
            removeTouching(Gestein.class);
        } else {
            nachricht("Hier ist kein Gestein");
        }
    }

    private void nachricht(String pText) {
        if (anzeige != null) {
            anzeige.anzeigen(pText);
            Greenfoot.delay(1);
            anzeige.loeschen();
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
        anzeige.anzeigen("Ich bin bereit");
    }

    class Display extends Actor {
        GreenfootImage bild;

        public Display() {
            bild = getImage();
        }

        public void anzeigen(String pText) {
            loeschen();
            getImage().drawImage(new GreenfootImage(pText, 25, Color.BLACK, new Color(0, 0, 0, 0)), 10, 10);
        }

        public void loeschen() {
            getImage().clear();
            setImage("images/nachricht.png");
        }
    }
}
