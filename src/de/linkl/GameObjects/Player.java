package de.linkl.GameObjects;

import de.linkl.Handler.AnimationHandler;
import de.linkl.Handler.CoinHandler;
import de.linkl.Handler.DeathHandler;
import de.linkl.Handler.KeyHandler;
import de.linkl.Main.Game;
import de.linkl.State.ObjectID;
import de.linkl.Tools.SoundPlayer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Objects;

public class Player extends GameObject {

    private final float g = 0.7f;                                         // Gravitationskonstante
    private final int maximumFallSpeed = 20;
    private boolean showHitbox = false;
    private int startX;
    private int startY;
    private int soundTimer = 0;

    private boolean onEnemy;

    public static BufferedImage[] idleRight;
    public static BufferedImage[] idleLeft;
    public static BufferedImage[] runRight;
    public static BufferedImage[] runLeft;
    public static BufferedImage[] jumpRight;
    public static BufferedImage[] jumpLeft;
    public static BufferedImage[] spinRight;
    public static BufferedImage[] spinLeft;

    KeyHandler keyHandler;
    AnimationHandler animationHandler;
    SoundPlayer soundPlayer;

    public Player(int x, int y, KeyHandler keyHandler) {
        super(x, y);
        this.startX = x;
        this.startY = y;
        this.id = ObjectID.PLAYER;
        this.keyHandler = keyHandler;
        this.animationHandler = new AnimationHandler();
        this.soundPlayer = new SoundPlayer();
        this.width = 32;
        this.height = 32;
        soundPlayer.load();
        loadSprites();

        animationHandler.setAnimation(idleRight);
        animationHandler.setDelay(45);
        facingRight = true;
        onEnemy = false;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animationHandler.getImage(), x, y, width, height, null);

        if (showHitbox) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.RED);
            g2d.draw(getBoundsBottom());
            g2d.draw(getBoundsTop());
            g2d.draw(getBoundsRight());
            g2d.draw(getBoundsLeft());
        }
    }

    @Override
    public void tick(LinkedList<GameObject> objects) {
        if (falling || jumping) {                                           // lässt den Player fallen, bzw. ändert die y-Geschwindigkeit um g (Gravitationskonstante)
            this.speedY += g;
            if (this.speedY > maximumFallSpeed) {
                speedY = maximumFallSpeed;
            }
        }
        this.x += this.speedX;                                              // ändert die x und y Position um die jeweilige Geschwindigkeit
        this.y += this.speedY;

        if (speedX > 0 && !jumping && !onEnemy) {                           // legt den Zustand der Animation fest, also ob er springt, fällt oder nach link oder rechts läuft, oder nichts macht
            animationHandler.setAnimation(runRight);                        // und in welche Richtung er schaut
            animationHandler.setDelay(45);
            facingRight = true;
        } else if (speedX < 0 && !jumping && !onEnemy) {
            animationHandler.setAnimation(runLeft);
            animationHandler.setDelay(45);
            facingRight = false;
        } else if (speedX == 0 && facingRight && !onEnemy) {
            animationHandler.setAnimation(idleRight);
            animationHandler.setDelay(45);
        } else if (speedX == 0 && !onEnemy) {
            animationHandler.setAnimation(idleLeft);
            animationHandler.setDelay(45);
        } else if (facingRight && !onEnemy) {
            animationHandler.setAnimation(jumpRight);
            animationHandler.setDelay(-1);
        } else if (jumping && !onEnemy){
            animationHandler.setAnimation(jumpLeft);
            animationHandler.setDelay(-1);
        }

        input();                                                        // prüft Spieler Eingaben
        animationHandler.tick();
        collisions(objects);

        if (y + height >= Game.height) {                                   // wenn er aus der Map fällt wird er auf den Startpunkt gesetzt
            reset();
        }
        if (x <= 0) {                                                     // damit der Spieler nicht links rauslaufen kann
            x = 1;
            speedX = 0;
            animationHandler.setAnimation(idleLeft);
        }
        if ((Game.totalWidth - x-width)<= 0) {                            // damit der Spieler nicht rechts rauslaufen kann
            x = Game.totalWidth - width - 1;
            speedX = 0;
            animationHandler.setAnimation(idleRight);

        }
        if (soundTimer <10) {                                             // damit der sound nicht mehrmals zu schnell hintereinander getriggert werden kann
            soundTimer++;
        }
    }

    @Override
    public Rectangle getTotalBounds() {
        return new Rectangle(x + (width / 4), y, width - (width / 2), height);
    }
    public Rectangle getBoundsBottom() {
        return new Rectangle(x + (width / 4), y + (height / 2), width - (width / 2), height - (height / 2));
    }

    public Rectangle getBoundsTop() {
        return new Rectangle(x + (width / 4), y, width - (width / 2), height - (height / 2));
    }

    public Rectangle getBoundsRight() {
        return new Rectangle(x + width - (width / 6), y + (height / 4), width / 10, height - (height / 2));
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle(x + (width / 12), y + (height / 4), width / 10, height - (height / 2));
    }

    public void collisions(LinkedList<GameObject> objects) {                                    // prüft die Kollision mit anderen Objekten aus der Liste
        for (GameObject tempObject : objects) {
            if (tempObject.getId() == ObjectID.TILE) {                                          // wenn das Objekt in der Liste eine Tile ist
                if (getBoundsBottom().intersects(tempObject.getTotalBounds())) {               // wenn die Hitbox(unten) des Players sich mit der dieses Tiles überschneidet
                    y = tempObject.getY() - height;                                             // wird der Spieler wieder auf den Block gesetzt und seine y-Geschwindigkeit auf 0 gesetzt
                    speedY = 0;
                    falling = false;
                    jumping = false;
                    onEnemy = false;
                } else {
                    falling = true;
                }

                if (getBoundsTop().intersects(tempObject.getTotalBounds())) {               // damit der Spieler nicht oben in einen Block reinspringen kann
                    y = tempObject.getY() + tempObject.getHeight();
                    speedY = 0;
                } else {
                    if (getBoundsRight().intersects(tempObject.getTotalBounds())) {          // wenn die Hitbox(rechts) des Players sich mit der dieses Tiles überschneidet
                        x = tempObject.getX() - width;                                       // kann er nicht weiter laufen
                        speedX = 0;
                        animationHandler.setAnimation(idleRight);
                    }

                    if (getBoundsLeft().intersects(tempObject.getTotalBounds())) {           // wenn die Hitbox(links) des Players sich mit der dieses Tiles überschneidet
                        x = tempObject.getX() + tempObject.getWidth();                       // kann er nicht weiter laufen
                        speedX = 0;
                        animationHandler.setAnimation(idleLeft);
                    }
                }
            }

            if (tempObject.getId() == ObjectID.ENEMY || tempObject.getId() == ObjectID.BEE || tempObject.getId() == ObjectID.PIG) {
                if (getBoundsBottom().intersects(tempObject.getTotalBounds())) {            // wenn der Spieler auf bestimmte Gegner (Bunny, Bee, Pig) springt
                    if (soundTimer >= 10) {
                        soundPlayer.volume = -20;
                        soundPlayer.play(SoundPlayer.bump);
                        soundTimer = 0;
                    }

                    if (tempObject.getId() == ObjectID.ENEMY) {                             // nur bei einem Bunny wir dieser sofort entfernt
                        tempObject.setAlive(false);                                         // entfernt den Gegner aus der Liste
                    }

                    speedY = -8;                                                            // man springt von den Gegner ab
                    onEnemy = true;
                    if (facingRight) {                                                      // legt dabei die Richtung der Animation fest
                        animationHandler.setAnimation(spinRight);
                    } else {
                        animationHandler.setAnimation(spinLeft);
                    }
                    animationHandler.setDelay(60);
                    CoinHandler.collectedCoins++;
                } else if (getBoundsTop().intersects(tempObject.getTotalBounds()) || getBoundsRight().intersects(tempObject.getTotalBounds()) || getBoundsLeft().intersects(tempObject.getTotalBounds())) {
                    reset();                                                                // setzt den Spieler auf die Startposition zurück
                }
            }

            if (tempObject.getId() == ObjectID.COIN) {
                if(getTotalBounds().intersects(tempObject.getTotalBounds())) {
                    if (soundTimer >= 5) {
                        soundPlayer.volume = -30;
                        soundPlayer.play(SoundPlayer.coin);
                        soundTimer = 0;
                    }

                    tempObject.setAlive(false);
                    CoinHandler.collectedCoins++;                                               // erhöht die Menge gesammelter Münzen
                }
            }

            if (tempObject.getId() == ObjectID.MUSHROOM) {                                      // wenn der Spieler einen Mushroom berührt
                if(getBoundsBottom().intersects(tempObject.getBoundsTop())) {
                    if (soundTimer >= 10) {
                        soundPlayer.volume = -30;
                        soundPlayer.play(SoundPlayer.spring);
                        soundTimer = 0;
                    }
                    speedY = -19;                                                               // wird er nach oben geschleudert
                    jumping = true;

                    if (facingRight) {
                        animationHandler.setAnimation(jumpRight);
                    } else {
                        animationHandler.setAnimation(jumpLeft);
                    }
                    animationHandler.setDelay(-1);
                }
            }

            if (tempObject.getId() == ObjectID.TURTLE) {                                        // bei einer Turtle, stirbt der Spieler direkt
                if (getTotalBounds().intersects(tempObject.getTotalBounds())) {
                    reset();
                }
            }

            if (tempObject.getId() == ObjectID.END) {                                           // beim Berühren des Endportals, wird das Spiel beendet
                if (getTotalBounds().intersects(tempObject.getTotalBounds())){
                    Game.endGame();
                }
            }

        }
    }

    public void input() {                                                           // wandelt die Eingaben des KeyHandlers in Bewegung um
        if (keyHandler.dPressed) {                                                  // Bewegung nach rechts
            speedX = 5;
            facingRight = true;
        }
        if (keyHandler.aPressed) {                                                  // Bewegung nach links
            speedX = -5;
            facingRight = false;
        }
        if (!(keyHandler.aPressed || keyHandler.dPressed)) {                        // die Eingaben gleichen sich aus
            speedX = 0;
        }
        if (keyHandler.spacePressed && !jumping) {                                  // springen, nur wenn der Spieler auf dem Boden ist
            if (soundTimer >= 10) {
                soundPlayer.volume = -25;
                soundPlayer.play(SoundPlayer.playerJump);
                soundTimer = 0;
            }
            jumping = true;
            speedY = -14;
        }

    }

    public void loadSprites() {                                                         // lädt die Bilder mit allen Teilen der Animation und lädt die einzelnen Teile als Subimages in ein Array
        try {
            BufferedImage[] fullImage = new BufferedImage[6];
            fullImage[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/player/player_idleRight.png")));
            fullImage[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/player/player_idleLeft.png")));
            fullImage[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/player/player_runRight.png")));
            fullImage[3] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/player/player_runLeft.png")));
            fullImage[4] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/player/player_spinRight.png")));
            fullImage[5] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/player/player_spinLeft.png")));

            idleRight = new BufferedImage[11];
            idleLeft = new BufferedImage[11];
            runRight = new BufferedImage[12];
            runLeft = new BufferedImage[12];
            jumpRight = new BufferedImage[1];
            jumpLeft = new BufferedImage[1];
            spinRight = new BufferedImage[6];
            spinLeft = new BufferedImage[6];

            jumpRight[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/player/player_jumpRight.png")));
            jumpLeft[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/entity/player/player_jumpLeft.png")));

            for (int i=0; i<11; i++) {                                                              // hier wird das gesamte Bild durchgegangen und die einzelnen Subimages gemacht
                idleRight[i] = fullImage[0].getSubimage(i*32, 0, 32, 32);
                idleLeft[i] = fullImage[1].getSubimage(320-i*32, 0, 32, 32);
            }
            for (int i=0; i<12; i++) {
                runRight[i] = fullImage[2].getSubimage(i*32, 0, 32, 32);
                runLeft[i] = fullImage[3].getSubimage(352-i*32, 0, 32, 32);
            }
            for (int i=0; i<6; i++) {
                spinRight[i] = fullImage[4].getSubimage(i*32, 0, 32, 32);
                spinLeft[i] = fullImage[5].getSubimage(160-i*32, 0, 32, 32);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reset() {                                                                   // setzt den Spieler auf die Startposition zurück
        soundPlayer.volume = -10;
        soundPlayer.play(SoundPlayer.playerDown);
        x = startX;
        y = startY;
        facingRight = true;
        DeathHandler.deathcount++;                                                          // der Deathcounter wird um eins erhöht
    }
}