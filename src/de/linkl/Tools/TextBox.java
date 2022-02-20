package de.linkl.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class TextBox {

    private int x,y;
    private final int scale = 5;
    private final char[] letters;
    BufferedImage allLetters;
    BufferedImage[] text;

    public TextBox(int x, int y, String text) {
        this.x = x;
        this.y = y;
        letters = new char[text.length()];
        text.getChars(0,text.length(), letters, 0);

        loadSprites();
    }

    private int[] getLettersAsNumbers() {
        int[] lettersAsNumbers = new int[letters.length];
        for (int i = 0; i< letters.length; i++) {
            switch (letters[i]) {
                case 'a':
                    lettersAsNumbers[i] = 0;
                    break;
                case 'b':
                    lettersAsNumbers[i] = 1;
                    break;
                case 'c':
                    lettersAsNumbers[i] = 2;
                    break;
                case 'd':
                    lettersAsNumbers[i] = 3;
                    break;
                case 'e':
                    lettersAsNumbers[i] = 4;
                    break;
                case 'f':
                    lettersAsNumbers[i] = 5;
                    break;
                case 'g':
                    lettersAsNumbers[i] = 6;
                    break;
                case 'h':
                    lettersAsNumbers[i] = 7;
                    break;
                case 'i':
                    lettersAsNumbers[i] = 8;
                    break;
                case 'j':
                    lettersAsNumbers[i] = 9;
                    break;
                case 'k':
                    lettersAsNumbers[i] = 10;
                    break;
                case 'l':
                    lettersAsNumbers[i] = 11;
                    break;
                case 'm':
                    lettersAsNumbers[i] = 12;
                    break;
                case 'n':
                    lettersAsNumbers[i] = 13;
                    break;
                case 'o':
                    lettersAsNumbers[i] = 14;
                    break;
                case 'p':
                    lettersAsNumbers[i] = 15;
                    break;
                case 'q':
                    lettersAsNumbers[i] = 16;
                    break;
                case 'r':
                    lettersAsNumbers[i] = 17;
                    break;
                case 's':
                    lettersAsNumbers[i] = 18;
                    break;
                case 't':
                    lettersAsNumbers[i] = 19;
                    break;
                case 'u':
                    lettersAsNumbers[i] = 20;
                    break;
                case 'v':
                    lettersAsNumbers[i] = 21;
                    break;
                case 'w':
                    lettersAsNumbers[i] = 22;
                    break;
                case 'x':
                    lettersAsNumbers[i] = 23;
                    break;
                case 'y':
                    lettersAsNumbers[i] = 24;
                    break;
                case 'z':
                    lettersAsNumbers[i] = 25;
                    break;
                case ' ':
                    lettersAsNumbers[i] = 26;
            }
        }
        return lettersAsNumbers;
    }

    private void loadSprites() {
        try {
            allLetters = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/textgray.png")));
            text = new BufferedImage[letters.length];

            for (int i=0; i<letters.length; i++) {
                if (getLettersAsNumbers()[i] == 26) {
                    text[i] = allLetters.getSubimage(6*8, 20, 8, 10);
                }
                else if (getLettersAsNumbers()[i] < 10) {
                    text[i] = allLetters.getSubimage(getLettersAsNumbers()[i]*8, 0, 8, 10);
                }
                else if (getLettersAsNumbers()[i] < 20) {
                    text[i] = allLetters.getSubimage((getLettersAsNumbers()[i]-10)*8, 10, 8, 10);
                }
                else if (getLettersAsNumbers()[i] > 19) {
                    text[i] = allLetters.getSubimage((getLettersAsNumbers()[i]-20)*8, 20, 8, 10);
                }

            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
        for (int i=0; i< text.length; i++) {
            g.drawImage(text[i], i*8+i*scale*8 +x, y, scale*8, scale*10, null);
        }
    }
}