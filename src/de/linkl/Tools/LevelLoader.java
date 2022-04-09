package de.linkl.Tools;

import de.linkl.GameObjects.*;
import de.linkl.GameObjects.BackgroundObjects.Cloud;
import de.linkl.GameObjects.BackgroundObjects.FloatingIsland;
import de.linkl.Handler.KeyHandler;
import de.linkl.Handler.ObjectHandler;
import de.linkl.Main.Game;
import de.linkl.State.ObjectID;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LevelLoader {

    private int nr, row;                                                                                // Anzahl an Zeichen pro Reihe und Anzahl an Reihen
    private boolean levelloaded;
    public String loadedlevel;

    Scanner scanner;                                                                                    // Klasse von Java, die einfache Zeichen lesen kann
    File file;
    ObjectHandler objectHandler;
    ObjectHandler backgroundHandler;
    KeyHandler keyHandler;

    public LevelLoader(ObjectHandler objectHandler, ObjectHandler backgroundHandler, KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
        this.objectHandler = objectHandler;
        this.backgroundHandler = backgroundHandler;
        this.nr = -1;
        this.row = 0;
        this.levelloaded = false;
    }

    public void load(String path) {                                                                     // Methode um eine Txt Datei zu laden
        if (levelloaded) {
            objectHandler.removeAll();
            backgroundHandler.removeAll();
            levelloaded = false;
            load(path);
        }
        else {
            file = new File(path);                                                                          // Festlegung des Pfades für die Datei

            try {
                scanner = new Scanner(file);                                                                // Scanner ließt die Datei
                row = 0;
                nr = -1;

                while (scanner.hasNextInt()) {                                                              // "während es ein nächstes Zeichen (int) gibt"
                    int object = scanner.nextInt();                                                         // int object meint die aktuelle Zahl die der Scanner gelesen hat
                    nr++;
                    if (nr == Game.totalWidth/32) {                                                         // eine Reihe hat hier genau 320 Zeichen
                        nr = 0;
                        row++;
                    }

                    if (object == 0) {                                                                      // die verschiedenen Arten von Objekten, die je nach Wert in dem txt - Dokument platziert werden

                    } else if (object == 1) {
                        objectHandler.addObject(new Player(nr*32, row*32, keyHandler));
                    } else if (object == 2) {
                        objectHandler.addObject(new Tile(nr*32,row*32, 2));
                    } else if (object == 3) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 3));
                    } else if (object == 4) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 4));
                    } else if (object == 5) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 5));
                    } else if (object == 6) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 6));
                    } else if (object == 7) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 7));
                    } else if (object == 8) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 8));
                    } else if (object == 9) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 9));
                    } else if (object == 10) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 10));
                    } else if (object == 11) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 11));
                    } else if (object == 12) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 12));
                    } else if (object == 13) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 13));
                    } else if (object == 14) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 14));
                    } else if (object == 15) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 15));
                    } else if (object == 16) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 16));
                    } else if (object == 17) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 17));
                    } else if (object == 18) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 18));
                    } else if (object == 19) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 19));
                    } else if (object == 20) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 20));
                    } else if (object == 21) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 21));
                    } else if (object == 22) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 22));
                    } else if (object == 23) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 23));
                    } else if (object == 24) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 24));
                    } else if (object == 25) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 25));
                    } else if (object == 26) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 26));
                    } else if (object == 27) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 27));
                    } else if (object == 28) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 28));
                    } else if (object == 29) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 29));
                    } else if (object == 30) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 30));
                    } else if (object == 31) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 31));
                    } else if (object == 32) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 32));
                    } else if (object == 33) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 33));
                    } else if (object == 34) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 34));
                    } else if (object == 35) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 35));
                    } else if (object == 36) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 36));
                    } else if (object == 37) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 37));
                    } else if (object == 38) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 38));
                    } else if (object == 39) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 39));
                    } else if (object == 40) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 40));
                    } else if (object == 41) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 41));
                    } else if (object == 42) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 42));
                    } else if (object == 43) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 43));
                    } else if (object == 44) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 44));
                    } else if (object == 45) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 45));
                    } else if (object == 46) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 46));
                    }

                    else if (object == 97) {
                        objectHandler.addObject(new Bee(nr * 32, row * 32));
                    } else if (object == 98) {
                        objectHandler.addObject(new Coin(nr * 32, row * 32));
                    } else if (object == 99) {
                        objectHandler.addObject(new Bunny(nr * 32, row * 32));
                    } else if (object == 96) {
                        objectHandler.addObject(new Mushroom(nr * 32, row * 32));
                    } else if (object == 94) {
                        objectHandler.addObject(new Pig(nr * 32, row * 32, false));
                    } else if (object == 93) {
                        objectHandler.addObject(new Pig(nr * 32, row * 32, true));
                    } else if (object == 95) {
                        objectHandler.addObject(new Turtle(nr * 32, row * 32));
                    } else if (object == 90) {
                        objectHandler.addObject(new End(nr * 32, row * 32));
                    }
                }

                backgroundHandler.addObject(new Cloud(Game.totalWidth, 100, null));
                backgroundHandler.addObject(new Cloud(Game.totalWidth/2, 50, null));
                backgroundHandler.addObject(new Cloud(Game.totalWidth/6, 150, null));
                backgroundHandler.addObject(new Cloud(Game.totalWidth + Game.totalWidth/2, 175, null));
                backgroundHandler.addObject(new FloatingIsland(Game.totalWidth, 450, null));
                backgroundHandler.addObject(new FloatingIsland(Game.totalWidth/6 + 200, 550, null));

                levelloaded = true;
                loadedlevel = path;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
