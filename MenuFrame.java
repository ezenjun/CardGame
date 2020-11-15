import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuFrame extends JFrame{
	public MenuFrame() {
		super("using JMenus");
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		JMenuItem aboutItem = new JMenuItem("About...");
		aboutItem.setMnemonic('A');
		fileMenu.add(aboutItem);
		aboutItem.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event) {
					JOptionPane.showMessageDialog(MenuFrame.this, "example of menus", "About",JOptionPane.PLAIN_MESSAGE);
				}
			}
		);
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('x');
		fileMenu.add(exitItem);
		exitItem.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						JOptionPane.showMessageDialog(MenuFrame.this, "Exit Game", "Exit",JOptionPane.PLAIN_MESSAGE);
						System.exit(0);
					}
				}
			);
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(fileMenu);
		
		JMenu formatMenu = new JMenu("Foramt");
		formatMenu.setMnemonic('r');
		
	}
}
