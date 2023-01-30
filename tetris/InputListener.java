package se.liu.chrer268.tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputListener extends KeyAdapter
{
    private Board board;

    public InputListener(Board board) {
        this.board = board;
    }

    public void bindKey(JFrame frame) {
        JComponent pane = frame.getRootPane();
        final InputMap input = pane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        final ActionMap action = pane.getActionMap();

        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveLeft");
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRight");
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "rotateRight");
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "rotateLeft");
        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "down");

        action.put("moveLeft", new DirectionAction(Direction.LEFT));
        action.put("moveRight", new DirectionAction(Direction.RIGHT));
        action.put("rotateRight", new RotationAction(Direction.RIGHT));
        action.put("rotateLeft", new RotationAction(Direction.LEFT));
        action.put("down", new DirectionAction(Direction.DOWN));
    }


    //Från programmeringstips
    private class DirectionAction extends AbstractAction
    {
        private Direction direction;

        private DirectionAction(Direction direction) {
            this.direction = direction;
        }

        @Override public void actionPerformed(final ActionEvent e) {
            board.move(direction);
        }
    }

    private class RotationAction extends AbstractAction
    {
        private Direction rotation;

        private RotationAction(Direction rotation) {
            this.rotation = rotation;
        }

        @Override public void actionPerformed(final ActionEvent e) {
            board.rotate(rotation);
        }
    }
}
