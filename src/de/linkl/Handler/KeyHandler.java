package de.linkl.Handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean dPressed, aPressed, spacePressed, altPressed, rPressed, escPressed, shiftPressed, upPressed, downPressed, rightPressed, leftPressed;
    public boolean num1Pressed, num2Pressed, num3Pressed, num4Pressed, num5Pressed, num6Pressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            dPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            aPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_ALT) {
            altPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            escPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            rPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_1) {
            num1Pressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            num2Pressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_3) {
            num3Pressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_4) {
            num4Pressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_5) {
            num5Pressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_6) {
            num6Pressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            dPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            aPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ALT) {
            altPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            escPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            rPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_1) {
            num1Pressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            num2Pressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_3) {
            num3Pressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_4) {
            num4Pressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_5) {
            num5Pressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_6) {
            num6Pressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
    }
}
