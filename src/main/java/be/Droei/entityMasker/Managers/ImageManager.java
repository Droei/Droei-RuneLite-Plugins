package be.droei.entityMasker.Managers;

import be.droei.entityMasker.overlay.EntityMaskerPluginOverlay;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Point;
import net.runelite.api.VarClientInt;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.util.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class ImageManager {

    public static void placeImage(List<NPC> npcs, Graphics2D graphics, Client client){
        double imageSizeModifier = 5;
        int cameraZoom = (int) (Math.round((getZoom(client) / 8.96) * imageSizeModifier));

        final BufferedImage smiley = resize(ImageUtil.loadImageResource(EntityMaskerPluginOverlay.class, "/capy.png"), cameraZoom, cameraZoom) ;
        System.out.println();

        for (NPC npc : npcs){
            Point imageLocation = npc.getCanvasImageLocation(smiley, npc.getLogicalHeight());
            if (imageLocation != null)
            {
                OverlayUtil.renderImageLocation(graphics, imageLocation, smiley);
            }

        }
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    private static int getZoom(Client client)
    {
        return client.getVarcIntValue(VarClientInt.CAMERA_ZOOM_FIXED_VIEWPORT);
    }
}
