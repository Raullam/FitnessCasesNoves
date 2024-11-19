/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi.componentimatge;

/**
 *
 * @author Rulox
 */
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImageSaver {

    public static void saveImage(String path, Image image) {
        try {
            // Asegúrate de que el nombre del archivo tenga la extensión .png
            if (!path.endsWith(".png")) {
                path += ".png"; // Añadir la extensión .png si no está presente
            }

            // Guardar la imagen
            File outputFile = new File(path);
            ImageIO.write((java.awt.image.BufferedImage) image, "PNG", outputFile);
            JOptionPane.showMessageDialog(null, "Imagen guardada correctamente como: " + path);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
