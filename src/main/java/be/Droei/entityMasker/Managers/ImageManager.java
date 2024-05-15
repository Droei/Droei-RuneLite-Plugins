package be.droei.entityMasker.managers;

import be.droei.entityMasker.config.EntityMaskerConfig;
import be.droei.entityMasker.overlay.EntityMaskerPluginOverlay;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Point;
import net.runelite.api.VarClientInt;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.util.ImageUtil;

import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class ImageManager {

    final EntityMaskerConfig entityMaskerConfig;

    @Inject
    public ImageManager(EntityMaskerConfig entityMaskerConfig) {
        this.entityMaskerConfig = entityMaskerConfig;
    }

    public void placeImage(List<NPC> npcs, Graphics2D graphics, Client client){
        int cameraZoom = (int) (Math.round((getZoom(client) / 8.96) * entityMaskerConfig.getImageScaling()));

        final BufferedImage img = resize(ImageUtil.loadImageResource(EntityMaskerPluginOverlay.class, "/Jad.png"), cameraZoom, cameraZoom) ;

        for (NPC npc : npcs){
            Point imageLocation = npc.getCanvasImageLocation(img, npc.getLogicalHeight());
            if (imageLocation != null)
            {
                OverlayUtil.renderImageLocation(graphics, imageLocation, img);
            }

        }
    }

    public BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    private int getZoom(Client client)
    {
        return client.getVarcIntValue(VarClientInt.CAMERA_ZOOM_FIXED_VIEWPORT);
    }
}
