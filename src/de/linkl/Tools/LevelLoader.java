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

    public LevelLoader(ObjectHandler objectHandler,  ObjectHandler backgroundHandler,KeyHandler keyHandler) {
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
                    if (nr == 80) {                                                                         // eine Reihe hat hier genau 80 Zeichen
                        nr = 0;
                        row++;
                    }

                    if (object == 0) {                                                                      // die verschiedenen Arten von Objekten, die je nach Wert in dem txt - Dokument platziert werden

                    } else if (object == 1) {
                        objectHandler.addObject(new Player(nr*32, row*32, ObjectID.PLAYER, keyHandler));
                    } else if (object == 2) {
                        objectHandler.addObject(new Tile(nr*32,row*32, 2, ObjectID.TILE));
                    } else if (object == 3) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 3, ObjectID.TILE));
                    } else if (object == 4) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 4, ObjectID.TILE));
                    } else if (object == 5) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 5, ObjectID.TILE));
                    } else if (object == 6) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 6, ObjectID.TILE));
                    } else if (object == 7) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 7, ObjectID.TILE));
                    } else if (object == 8) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 8, ObjectID.TILE));
                    } else if (object == 9) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 9, ObjectID.TILE));
                    } else if (object == 10) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 10, ObjectID.TILE));
                    } else if (object == 11) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 11, ObjectID.TILE));
                    } else if (object == 12) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 12, ObjectID.TILE));
                    } else if (object == 13) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 13, ObjectID.TILE));
                    } else if (object == 14) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 14, ObjectID.TILE));
                    } else if (object == 15) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 15, ObjectID.TILE));
                    } else if (object == 16) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 16, ObjectID.TILE));
                    } else if (object == 17) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 17, ObjectID.TILE));
                    } else if (object == 18) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 18, ObjectID.TILE));
                    } else if (object == 19) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 19, ObjectID.TILE));
                    } else if (object == 20) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 20, ObjectID.TILE));
                    } else if (object == 21) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 21, ObjectID.TILE));
                    } else if (object == 22) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 22, ObjectID.TILE));
                    } else if (object == 23) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 23, ObjectID.TILE));
                    } else if (object == 24) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 24, ObjectID.TILE));
                    } else if (object == 25) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 25, ObjectID.TILE));
                    } else if (object == 26) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 26, ObjectID.TILE));
                    } else if (object == 27) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 27, ObjectID.TILE));
                    } else if (object == 28) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 28, ObjectID.TILE));
                    } else if (object == 29) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 29, ObjectID.TILE));
                    } else if (object == 30) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 30, ObjectID.TILE));
                    } else if (object == 31) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 31, ObjectID.TILE));
                    } else if (object == 32) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 32, ObjectID.TILE));
                    } else if (object == 33) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 33, ObjectID.TILE));
                    } else if (object == 34) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 34, ObjectID.TILE));
                    } else if (object == 35) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 35, ObjectID.TILE));
                    } else if (object == 36) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 36, ObjectID.TILE));
                    } else if (object == 37) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 37, ObjectID.TILE));
                    } else if (object == 38) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 38, ObjectID.TILE));
                    } else if (object == 39) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 39, ObjectID.TILE));
                    } else if (object == 40) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 40, ObjectID.TILE));
                    } else if (object == 41) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 41, ObjectID.TILE));
                    } else if (object == 42) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 42, ObjectID.TILE));
                    } else if (object == 43) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 43, ObjectID.TILE));
                    } else if (object == 44) {
                        objectHandler.addObject(new Tile(nr * 32, row * 32, 44, ObjectID.TILE));
                    }


                    else if (object == 97) {
                        objectHandler.addObject(new Bee(nr * 32, row * 32, ObjectID.BEE));
                    } else if (object == 98) {
                        objectHandler.addObject(new Coin(nr * 32, row * 32, ObjectID.COIN));
                    } else if (object == 99) {
                        objectHandler.addObject(new Bunny(nr * 32, row * 32, ObjectID.ENEMY));
                    } else if (object == 96) {
                        objectHandler.addObject(new Mushroom(nr * 32, row * 32, ObjectID.MUSHROOM));
                    } else if (object == 95) {
                        objectHandler.addObject(new Pig(nr * 32, row * 32, ObjectID.PIG));
                    } else if (object == 94) {
                        objectHandler.addObject(new Turtle(nr * 32, row * 32, ObjectID.TURTLE));
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
