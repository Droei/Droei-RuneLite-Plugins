package be.droei.entityMasker.config;

import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;

@ConfigGroup("entity masker")
public interface EntityMaskerConfig extends Config {
    @ConfigItem(
            position = 1,
            keyName = "showImage",
            name = "Display images over masks",
            description = "Displays a random image over the masked entities, these can be changed in the mask images folder."
    )
    default boolean showImages()
    {
        return true;
    }

    @ConfigItem(
            position = 2,
            keyName = "imageScaling",
            name = "Scale the size of images",
            description = "How do you want the images to scale: 5 means original * 5."
    )
    default int getImageScaling()
    {
        return 5;
    }

    @Alpha
    @ConfigItem(
            position = 3,
            keyName = "maskColor",
            name = "Mask Color",
            description = "Color of the mask"
    )
    default Color getMaskColor()
    {
        return Color.PINK;
    }

    @ConfigItem(
            position = 4,
            keyName = "showEntityName",
            name = "show the entity name",
            description = "Show the name of the entity above the Mask."
    )
    default boolean showEntityName()
    {
        return true;
    }

    @Alpha
    @ConfigItem(
            position = 5,
            keyName = "borderLightTextColor",
            name = "Border and text Color",
            description = "Used for the outer edge of the mask and name"
    )
    default Color getBorderAndTextColor()
    {
        return Color.WHITE;
    }

    @ConfigItem(
            position = 6,
            keyName = "entitiesToMask",
            name = "NPC's you want masked",
            description = "List of NPC's you want to mask. Format: (NPC), (NPC)"
    )
    default String getNpcToHighlight()
    {
        return "name,don't mask capybaras,name";
    }

    @ConfigItem(
            position = 7,
            keyName = "epilepsy",
            name = "Epilepsy - mask flashy npc's",
            description = "Mask out all flashy npc's"
    )
    default boolean epilepsy()
    {
        return false;
    }

    @ConfigItem(
            position = 8,
            keyName = "arachnophobia",
            name = "Arachnophobia - mask spiders",
            description = "Mask out all spiders"
    )
    default boolean arachnophobia()
    {
        return false;
    }

    @ConfigItem(
            position = 9,
            keyName = "Ophidiophobia",
            name = "Ophidiophobia - mask snakes",
            description = "Mask out all snakes"
    )
    default boolean ophidiophobia() {
        return false;
    }
    @ConfigItem(
            position = 10,
            keyName = "Cynophobia",
            name = "Cynophobia - mask dogs",
            description = "Mask out all dogs"
    )
    default boolean cynophobia() {
        return false;
    }
}
