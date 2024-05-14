package be.droei.entityMasker.overlay;

import be.droei.entityMasker.Managers.EntityManager;
import be.droei.entityMasker.Managers.ImageManager;
import be.droei.entityMasker.Managers.MaskManager;
import be.droei.entityMasker.config.EntityMaskerConfig;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;
import java.util.List;

public class EntityMaskerPluginOverlay extends Overlay {

    EntityManager entityManager;
    Client client;
    EntityMaskerConfig config;
    ImageManager imageManager;
    MaskManager maskManager;

    @Inject
    private EntityMaskerPluginOverlay(EntityManager entityManager, Client client,
                                      EntityMaskerConfig config, ImageManager imageManager,
                                      MaskManager maskManager){
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);

        this.client = client;
        this.config = config;
        this.imageManager = imageManager;
        this.entityManager = entityManager;
        this.maskManager = maskManager;
    }

    @Override
    public Dimension render(Graphics2D graphics) {

        List<NPC> npcs = entityManager.updateAllEntities();
        imageManager.placeImage(npcs, graphics, client);
        maskManager.maskEntities(npcs);

        return null;
    }
}
