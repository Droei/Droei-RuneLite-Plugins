package be.droei.entityMasker;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class EntityMaskerPluginTest
{
    public static void main(String[] args) throws Exception
    {
        ExternalPluginManager.loadBuiltin(EntityMaskerPlugin.class);
        RuneLite.main(args);
    }
}