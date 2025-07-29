import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import assets.Theme;
import components.NeuButton;

public class Sidebar extends JFrame{
    /* Sidebar is the UI component that provides UI functionality
     * 
     */
    public Sidebar() {
        setWindowProperties();
        initializeDashboardContent();
    }

    private void initializeDashboardContent() { 
        
        JPanel sidebarPanel = new JPanel();
        add(sidebarPanel);
        
        JPanel windowControlRail = new JPanel();
        windowControlRail.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        windowControlRail.setMinimumSize(new Dimension(getWidth(), 40));
        windowControlRail.setBackground(Theme.BACKGROUND_COLOR);

        NeuButton exitButton = new NeuButton("X");
        exitButton.setButtonSize(25, 25);
        exitButton.setButtonColor(Theme.EXIT_BUTTON_RED);
        exitButton.setShadowOffset(2);
        exitButton.setCornerRadius(10);
        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        NeuButton minimizeButton = new NeuButton("_");
        minimizeButton.setButtonSize(25, 25);
        minimizeButton.setButtonColor(Theme.BUTTON_COLOR);
        minimizeButton.setShadowOffset(2);
        minimizeButton.setCornerRadius(10);
        minimizeButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(minimizeButton);
            frame.setState(JFrame.ICONIFIED);
        });
        

        windowControlRail.add(minimizeButton);
        windowControlRail.add(exitButton);
        add(windowControlRail);

    }


    private void setWindowProperties() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = screenSize.width - 305;
        int y = 0; // Top of the screen. 
        System.out.println("Setting sidebar position to: " + x + ", " + y);

        setLocation(x, 5);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, screenSize.height - 60); // Set width to 300px and height to full screen heightJF
        setUndecorated(true);
    }
}
