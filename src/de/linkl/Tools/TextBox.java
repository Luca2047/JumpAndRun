package de.linkl.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
        int[] lettersAsNumber = new int[letters.length];
        for (int i = 0; i< letters.length; i++) {
            switch (letters[i]) {
                case 'a':
                    lettersAsNumber[i] = 0;
                    break;
                case 'b':
                    lettersAsNumber[i] = 1;
                    break;
                case 'c':
                    lettersAsNumber[i] = 2;
                    break;
                case 'd':
                    lettersAsNumber[i] = 3;
                    break;
                case 'e':
                    lettersAsNumber[i] = 4;
                    break;
                case 'f':
                    lettersAsNumber[i] = 5;
                    break;
                case 'g':
                    lettersAsNumber[i] = 6;
                    break;
                case 'h':
                    lettersAsNumber[i] = 7;
                    break;
                case 'i':
                    lettersAsNumber[i] = 8;
                    break;
                case 'j':
                    lettersAsNumber[i] = 9;
                    break;
                case 'k':
                    lettersAsNumber[i] = 10;
                    break;
                case 'l':
                    lettersAsNumber[i] = 11;
                    break;
                case 'm':
                    lettersAsNumber[i] = 12;
                    break;
                case 'n':
                    lettersAsNumber[i] = 13;
                    break;
                case 'o':
                    lettersAsNumber[i] = 14;
                    break;
                case 'p':
                    lettersAsNumber[i] = 15;
                    break;
                case 'q':
                    lettersAsNumber[i] = 16;
                    break;
                case 'r':
                    lettersAsNumber[i] = 17;
                    break;
                case 's':
                    lettersAsNumber[i] = 18;
                    break;
                case 't':
                    lettersAsNumber[i] = 19;
                    break;
                case 'u':
                    lettersAsNumber[i] = 20;
                    break;
                case 'v':
                    lettersAsNumber[i] = 21;
                    break;
                case 'w':
                    lettersAsNumber[i] = 22;
                    break;
                case 'x':
                    lettersAsNumber[i] = 23;
                    break;
                case 'y':
                    lettersAsNumber[i] = 24;
                    break;
                case 'z':
                    lettersAsNumber[i] = 25;
                    break;
                case ' ':
                    lettersAsNumber[i] = 26;
            }
        }
        return lettersAsNumber;
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