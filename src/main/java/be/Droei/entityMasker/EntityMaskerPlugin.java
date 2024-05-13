package be.droei.entityMasker;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
        name = "Entity Masker"
)
public class EntityMaskerPlugin extends Plugin
{

    @Override
    protected void startUp()
    {
        log.info("Entity Masker started!");
    }

    @Override
    protected void shutDown()
    {
        log.info("Entity Masker stopped!");
    }
}
