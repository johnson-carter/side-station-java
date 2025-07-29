package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import assets.Theme;

public class NeuButton extends JButton {
    private boolean isPressed = false;
    private boolean isHovered = false;
    private int cornerRadius = 20;
    private int shadowOffset = 8;
    private Color baseColor;
    private Color lightShadow;
    private Color darkShadow;
    
    public NeuButton(String text) {
        super(text);
        
        // Initialize colors for neumorphic design
        this.baseColor = Theme.BUTTON_COLOR;
        this.lightShadow = brighten(baseColor, 0.15f);
        this.darkShadow = darken(baseColor, 0.15f);
        
        setupButton();
        addInteractivity();
    }
    
    private void setupButton() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setBackground(baseColor);
        setForeground(Theme.TEXT_COLOR);
        
        // Set preferred size for better proportions
        setPreferredSize(new Dimension(140, 50));
        setFont(getFont().deriveFont(Font.BOLD, 14f));
        
        // Add padding
        setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
    }

    public void setButtonSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
    }
    public void setButtonColor(Color color) {
        this.baseColor = color;
        this.lightShadow = brighten(color, 0.15f);
        this.darkShadow = darken(color, 0.15f);
        repaint();
    }
    public void setShadowOffset(int offset) {
        this.shadowOffset = offset;
        repaint();
    }
    public void setCornerRadius(int radius){
        this.cornerRadius = radius;
        repaint();
    }
    
    private void addInteractivity() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isPressed = true;
                repaint();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
                repaint();
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                setCursor(Cursor.getDefaultCursor());
                repaint();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        
        // Enable anti-aliasing for smooth rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        int width = getWidth();
        int height = getHeight();
        
        // Create rounded rectangle shape
        RoundRectangle2D buttonShape = new RoundRectangle2D.Float(
            shadowOffset/2, shadowOffset/2,
            width - shadowOffset, height - shadowOffset,
            cornerRadius, cornerRadius
        );
        
        if (isPressed) {
            paintPressedState(g2d, buttonShape, width, height);
        } else {
            paintNormalState(g2d, buttonShape, width, height);
        }
        
        // Paint text
        paintText(g2d, width, height);
        
        g2d.dispose();
    }
    
    private void paintNormalState(Graphics2D g2d, RoundRectangle2D buttonShape, int width, int height) {
        // Draw dark shadow (bottom-right)
        RoundRectangle2D shadowShape = new RoundRectangle2D.Float(
            shadowOffset, shadowOffset,
            width - shadowOffset, height - shadowOffset,
            cornerRadius, cornerRadius
        );
        g2d.setColor(darkShadow);
        g2d.fill(shadowShape);
        
        // Draw light shadow (top-left)
        RoundRectangle2D lightShadowShape = new RoundRectangle2D.Float(
            0, 0,
            width - shadowOffset, height - shadowOffset,
            cornerRadius, cornerRadius
        );
        g2d.setColor(lightShadow);
        g2d.fill(lightShadowShape);
        
        // Draw main button surface
        Color surfaceColor = isHovered ? brighten(baseColor, 0.05f) : baseColor;
        g2d.setColor(surfaceColor);
        g2d.fill(buttonShape);
        
        // Add subtle inner highlight
        addInnerHighlight(g2d, buttonShape);
    }
    
    private void paintPressedState(Graphics2D g2d, RoundRectangle2D buttonShape, int width, int height) {
        // Inset shadow effect for pressed state
        RoundRectangle2D insetShape = new RoundRectangle2D.Float(
            shadowOffset/4, shadowOffset/4,
            width - shadowOffset/2, height - shadowOffset/2,
            cornerRadius, cornerRadius
        );
        
        // Draw inset shadow
        g2d.setColor(darkShadow);
        g2d.fill(insetShape);
        
        // Draw pressed surface (slightly darker)
        g2d.setColor(darken(baseColor, 0.08f));
        g2d.fill(buttonShape);
    }
    
    private void addInnerHighlight(Graphics2D g2d, RoundRectangle2D buttonShape) {
        // Create gradient for subtle inner highlight
        GradientPaint gradient = new GradientPaint(
            0, 0, new Color(255, 255, 255, 25),
            0, getHeight() / 3, new Color(255, 255, 255, 0)
        );
        
        g2d.setPaint(gradient);
        g2d.fill(buttonShape);
    }
    
    private void paintText(Graphics2D g2d, int width, int height) {
        g2d.setColor(getForeground());
        g2d.setFont(getFont());
        
        FontMetrics fm = g2d.getFontMetrics();
        String text = getText();
        
        if (text != null && !text.isEmpty()) {
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getAscent();
            
            int x = (width - textWidth) / 2;
            int y = (height + textHeight) / 2 - 2;
            
            // Add text shadow for depth
            if (!isPressed) {
                g2d.setColor(new Color(255, 255, 255, 80));
                g2d.drawString(text, x, y - 1);
            }
            
            g2d.setColor(getForeground());
            g2d.drawString(text, x, y + (isPressed ? 1 : 0));
        }
    }
    
    // Utility methods for color manipulation
    private Color brighten(Color color, float factor) {
        int r = Math.min(255, (int) (color.getRed() + (255 - color.getRed()) * factor));
        int g = Math.min(255, (int) (color.getGreen() + (255 - color.getGreen()) * factor));
        int b = Math.min(255, (int) (color.getBlue() + (255 - color.getBlue()) * factor));
        return new Color(r, g, b, color.getAlpha());
    }
    
    private Color darken(Color color, float factor) {
        int r = Math.max(0, (int) (color.getRed() * (1 - factor)));
        int g = Math.max(0, (int) (color.getGreen() * (1 - factor)));
        int b = Math.max(0, (int) (color.getBlue() * (1 - factor)));
        return new Color(r, g, b, color.getAlpha());
    }
}