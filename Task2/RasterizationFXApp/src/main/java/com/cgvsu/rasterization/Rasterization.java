package com.cgvsu.rasterization;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Rasterization {

    public static void drawEllipse(
            final GraphicsContext graphicsContext,
            final int centerX, final int centerY,
            final int a, final int b,
            final Color color)
    {
        final PixelWriter pw = graphicsContext.getPixelWriter();


        double a2 = a * a;
        double b2 = b * b;


        int x = 0;
        int y = b;
        double sigma = 2 * b2 + a2 * (1 - 2 * b);

        while (b2 * x <= a2 * y) {
            drawSymmetricPixels(pw, centerX, centerY, x, y, color);

            if (sigma >= 0) {
                sigma += 4 * a2 * (1 - y);
                y--;
            }
            sigma += b2 * ((4 * x) + 6);
            x++;
        }


        x = a;
        y = 0;
        sigma = 2 * a2 + b2 * (1 - 2 * a);

        while (a2 * y <= b2 * x) {
            drawSymmetricPixels(pw, centerX, centerY, x, y, color);

            if (sigma >= 0) {
                sigma += 4 * b2 * (1 - x);
                x--;
            }
            sigma += a2 * ((4 * y) + 6);
            y++;
        }
    }


    private static void drawSymmetricPixels(
            PixelWriter pw, int cx, int cy, int dx, int dy, Color color)
    {
        pw.setColor(cx + dx, cy + dy, color);
        pw.setColor(cx - dx, cy + dy, color);
        pw.setColor(cx + dx, cy - dy, color);
        pw.setColor(cx - dx, cy - dy, color);
    }
}
