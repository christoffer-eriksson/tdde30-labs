package se.liu.chrer268.tetris;

import javax.swing.*;

public class MenuButton
{
    public static void menuClicked(MenuOption menu){
	if (menu == MenuOption.QUIT){
	    if (JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		System.exit(0);
	    }
	}
	if (menu == MenuOption.INFORMATION){
	    JOptionPane.showMessageDialog(null, "Tetris created by Christoffer Eriksson 21/22", "About", JOptionPane.PLAIN_MESSAGE);
	}
    }
}

