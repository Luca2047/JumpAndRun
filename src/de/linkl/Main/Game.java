package de.linkl.Main;

import de.linkl.GameObjects.GameObject;
import de.linkl.GameObjects.Player;
import de.linkl.Handler.*;
import de.linkl.State.ObjectID;
import de.linkl.Tools.Camera;
import de.linkl.Tools.LevelLoader;
import de.linkl.Tools.SoundPlayer;
import de.linkl.Tools.TextBox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Game extends Canvas implements Runnable {

    private boolean running = false;
    protected static boolean paused = false;
    protected static boolean inMenu = true;
    public static boolean completed = false;
    private double ticksPerSecond = 60;
    private int pauseTimer = 0;
    public static int width = 1280, height = 710;
    public static int totalWidth = 10240;

    Window window;

    Thread thread;
    ObjectHandler objectHandler;
    ObjectHandler backgroundHandler;
    CoinHandler coinHandler;
    DeathHandler deathHandler;
    KeyHandler keyHandler;
    LevelLoader levelLoader;
    Camera camera;
    BufferedImage gameBackground;
    ScrollingBackground scrollingBackground;
    SoundPlayer gameSoundPlayer;
    SoundPlayer menuSoundPlayer;
    TextBox endingText1;
    TextBox endingText2;
    TextBox pausedText1;
    TextBox pausedText2;


    public void init() {                                                                        //in der init Methode werden alle Objekte erstellt und zugewiesen; wird beim Start durch die run Methode aufgerufen

        try {
            gameBackground = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/map/sky.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        scrollingBackground = new ScrollingBackground("/de/linkl/Graphics/scrollBackground.png");
        keyHandler = new KeyHandler();
        objectHandler = new ObjectHandler();
        backgroundHandler = new ObjectHandler();
        coinHandler = new CoinHandler();
        deathHandler = new DeathHandler();

        camera = new Camera(0, 0);
        levelLoader = new LevelLoader(objectHandler, backgroundHandler, keyHandler);
        levelLoader.load("rsc/Level/Level1.txt");

        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        menuSoundPlayer = new SoundPlayer();
        menuSoundPlayer.load();

        gameSoundPlayer = new SoundPlayer();
        gameSoundPlayer.load();

        window = new Window(1280, 710, "JavaGame");
        window.add(this);
        window.setVisible(true);
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        run();
    }

    @Override
    public void run() {                                                                             // wird ausgeführt wenn der Thread gestartet wird
        init();                                                                                     // beinhaltet die Game-Loop, dass das Spiel auf einer
        this.requestFocusInWindow();                                                                // bestimmten Geschwindigkeit läuft (hier: 60 ticks pro Sekunde)

        long lastTime = System.nanoTime();
        double ns = 1000000000 / ticksPerSecond;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                render();
                frames++;
                delta--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("| FPS: " + frames + " || TICKS: " + updates + " |");
                frames = 0;
                updates = 0;
            }
        }
    }

    public void tick() {                                                    // "updatet" die Informationen bei jedem Tick
        if (!paused && !inMenu) {
            objectHandler.tick();
            backgroundHandler.tick();
            coinHandler.tick();
            deathHandler.tick();

            for (GameObject gameObject : objectHandler.objects) {           // geht die Liste durch und sucht den Player, dieser soll das fokussierte Objekt der Kamera sein
                if (gameObject.getId() == ObjectID.PLAYER) {
                    camera.tick(gameObject);
                }
            }
            if (keyHandler.rPressed) {                                      // mit "R" lässt sich das Level neu starten
                gameSoundPlayer.stop();
                levelLoader.load(levelLoader.loadedlevel);
                CoinHandler.collectedCoins = 0;
                DeathHandler.deathcount = 0;
                completed = false;
            }
        }
        if (keyHandler.escPressed && pauseTimer >= 60) {                                         // mit escape kann man pausieren, nur einmal pro Sekunde
            paused = !paused;
            pauseTimer = 0;
        }
        pauseTimer++;
    }

    public void render() {                                                                      // stellt die anzuzeigenden Objekte dar
        BufferStrategy bs = this.getBufferStrategy();                                           // BufferStrategy verwaltet was auf dem Bildschirm angezeigt wird
                                                                                                // am Anfang ist diese null, weshalb hier erst eine erstellt wird und dann die Methode nochmal aufruft
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        if (inMenu) {                                                                              // zeigt das Menu an
            scrollingBackground.render(g);
            window.titleBox.render(g);
            menuSoundPlayer.loop(SoundPlayer.menuTheme);
        }
        else {                                                                                      // zeigt das Spiel an
            if (!completed) {
                menuSoundPlayer.stop();
                gameSoundPlayer.loop(SoundPlayer.theme);
                g.fillRect(0,0,width,height);
                g.drawImage(gameBackground, 0, 0, Game.width, Game.height, null);

                g2d.translate(-camera.getX(), -camera.getY());

                backgroundHandler.render(g);
                objectHandler.render(g);                                                            // rendert jedes Objekt aus der Liste des Objecthandlers
                coinHandler.render(g, (int)camera.getX() + 1200, (int)camera.getY() + 20);
                deathHandler.render(g, (int)camera.getX() + 1200, (int)camera.getY() + 55);

                g2d.translate(camera.getX(), camera.getY());

                if (paused) {
                    pausedText1 = new TextBox(width/2-150, 200, "paused");
                    pausedText2 = new TextBox(width/2-500, 300, "press esc to continue");
                    pausedText1.render(g);
                    pausedText2.render(g);
                }
            }
            else {
                g.fillRect(0,0,width,height);
                g.drawImage(gameBackground, 0, 0, Game.width, Game.height, null);
                gameSoundPlayer.loop(SoundPlayer.levelComplete);
                endingText1 = new TextBox(width/2-200, 200, "well done");
                endingText2 = new TextBox(width/2-450, 350, "press r to restart");
                objectHandler.render(g);
                endingText1.render(g);
                endingText2.render(g);
                coinHandler.render(g, width/2-100, 450);
                deathHandler.render(g, width/2+100, 450);
            }
        }

        g.dispose();                                                                            // dispose() ist eine Methode, die die benötigten Systemressourcen,
        bs.show();                                                                              // welche für das Objekt benötigt, freigibt
    }

    public static void endGame() {
        completed = true;
    }
}
