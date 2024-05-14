package be.droei.entityMasker.config;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("entity masker")
public interface EntityMaskerConfig extends Config {
    @ConfigItem(
            position = 0,
            keyName = "showImage",
            name = "Display images over masks",
            description = "Displays a random image over the masked entities, these can be changed in the mask images folder."
    )
    default boolean dontShowImage()
    {
        return false;
    }
}
