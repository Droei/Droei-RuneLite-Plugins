package be.Droei.entityMasker.overlay;

import be.Droei.entityMasker.Managers.EntityManager;
import be.Droei.entityMasker.Managers.ImageManager;
import be.Droei.entityMasker.Managers.MaskManager;
import be.Droei.entityMasker.config.EntityMaskerConfig;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;
import java.util.List;

public class EntityMaskerPluginOverlay extends Overlay {

    final EntityManager entityManager;
    final Client client;
    final EntityMaskerConfig config;
    final ImageManager imageManager;
    final MaskManager maskManager;
    List<NPC> npcs;


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
        npcs = entityManager.updateConfigEntities();

        // This line is known to cause lagg
        // I will fix this soon, set it false in config, set a warning and communicated to users
        if(config.showImages()) imageManager.placeImage(npcs, graphics, client);

        maskManager.maskEntities(npcs);

        return null;
    }

    public void clear(){
        npcs = entityManager.updateConfigEntities();
        entityManager.getEntities().clear();
        maskManager.clearAllMasksList(npcs);
    }
}
