package com.juego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class nave {

    public static BufferedImage cargarImagen(String ruta){

        try (InputStream is = nave.class.getResourceAsStream(ruta)){
            return ImageIO.read(Objects.requireNonNull(is,"no se encontro el archivo: " + ruta));
        } catch (IOException e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
            return null;
        }

    }

}
