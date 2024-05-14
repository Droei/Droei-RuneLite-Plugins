package be.droei.entityMasker;

import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Point;
import net.runelite.api.VarClientInt;
import net.runelite.client.game.npcoverlay.HighlightedNpc;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.util.ImageUtil;

import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class EntityMaskerPluginOverlay extends Overlay {

    EntityMaskerPlugin entityMaskerPlugin;
    Client client;

    @Inject
    private EntityMaskerPluginOverlay(EntityMaskerPlugin entityMaskerPlugin, Client client){
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);

        this.entityMaskerPlugin = entityMaskerPlugin;
        this.client = client;
    }


    // when camera zoom is 896 height and width of 100 is correct
    // 896/8,96 = 100 = 100%
    // 128/8,96 = 14.3
    // 100/14,3 = 7

    @Override
    public Dimension render(Graphics2D graphics) {

        int cameraZoom = (int) (Math.round(getZoom() / 8.96)) * 5;

        final BufferedImage smiley = resize(ImageUtil.loadImageResource(getClass(), "/capy.png"), cameraZoom, cameraZoom) ;
        System.out.println();

        for (NPC npc : entityMaskerPlugin.getEntities()){
            Point imageLocation = npc.getCanvasImageLocation(smiley, npc.getLogicalHeight());
            if (imageLocation != null)
            {
                OverlayUtil.renderImageLocation(graphics, imageLocation, smiley);
            }

        }

        return null;
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    private int getZoom()
    {
        return client.getVarcIntValue(VarClientInt.CAMERA_ZOOM_FIXED_VIEWPORT);
    }
}
