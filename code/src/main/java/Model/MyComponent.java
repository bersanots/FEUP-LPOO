package Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyComponent extends JComponent {

    public MyComponent() {}

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }

    protected void paintComponent(Graphics graphics, int x, int y, String pathname) {
        super.paintComponent(graphics);
        File resource = new File(pathname);
        BufferedImage image = null;
        try {
            image = ImageIO.read(resource);
            graphics.drawImage(image, x, y, null);
        } catch (IOException e) { }
    }
}
