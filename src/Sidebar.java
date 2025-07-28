import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import assets.Theme;


public class Sidebar extends JFrame{
    /* Sidebar is the UI component that provides UI functionality
     * 
     */
    public Sidebar() {
        setWindowProperties();
        initializeSidebarContent();
    }

    private void initializeSidebarContent() { 
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(Theme.BACKGROUND_COLOR);
        sidebarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        add(sidebarPanel);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
        sidebarPanel.add(exitButton);
    }

    private void setWindowProperties() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = screenSize.width - 300;
        int y = 0; // Top of the screen. 
        System.out.println("Setting sidebar position to: " + x + ", " + y);

        setLocation(x, 10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, screenSize.height - 60); // Set width to 300px and height to full screen heightJF
        setUndecorated(true);
    }
}
