package de.linkl.Handler;

import de.linkl.GameObjects.GameObject;
import de.linkl.Main.Game;

import java.awt.*;
import java.util.LinkedList;

public class ObjectHandler {

    public LinkedList<GameObject>objects = new LinkedList<>();
    private GameObject tempObject;

    public void render(Graphics g) {                                                    // führt für jedes Object in der LinkedList die render()-Methode aus
        for (GameObject object : objects) {
            tempObject = object;
            tempObject.render(g);
        }
    }

    public void tick() {                                                      // führt für jedes Object in der LinkedList die tick()-Methode aus
        for (int i = 0; i < objects.size(); i++) {
            tempObject = objects.get(i);
            tempObject.tick(objects);
        }
        if (Game.completed) {
            removeAll();
        }
    }

    public void addObject(GameObject object) {                                          // fügt ein GameObject der LinkedList hinzu
        this.objects.add(object);
    }

    public void removeObject(GameObject object) {                                       // entfernt ein GameObject aus der LinkedList
        this.objects.remove(object);
    }

    public void removeAll() {
        this.objects.removeAll(objects);
    }
}
