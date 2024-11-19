/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi.componentimatge;

/**
 *
 * @author Rulox
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private Image image;
    private String imagePath;
    private int rotationAngle;

    public ImagePanel() {
        this.rotationAngle = 0;
    }

    // Método para cargar la imagen
    public void loadImage(String imagePath) {
        try {
            // Cargar la imagen
            this.image = ImageIO.read(new File(imagePath));
            this.imagePath = imagePath;
            this.rotationAngle = 0; // Reiniciar la rotación al cargar una nueva imagen
            
            // Ajustar el tamaño del panel al tamaño de la imagen
            if (this.image != null) {
                this.setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
                this.revalidate(); // Revalidar el contenedor para que se ajuste al nuevo tamaño
            }
            repaint();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para pintar la imagen en el panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            // Dibujar la imagen (ajustada al tamaño del panel)
            g.drawImage(image, 0, 0, this);
        }
    }

    public void rotateImage() {
        // Lógica para rotar la imagen (si es necesario)
        // Actualiza la imagen y vuelve a pintar
    }
}
