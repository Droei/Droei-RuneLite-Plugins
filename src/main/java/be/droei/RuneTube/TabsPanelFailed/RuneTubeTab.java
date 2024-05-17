package be.droei.RuneTube.TabsPanelFailed;

import be.droei.RuneTube.PanelManager.HTMLStringCreator;
import net.runelite.client.ui.ColorScheme;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

class RuneTubeTab extends JPanel
{
    @Inject
    RuneTubeTab(JPanel capyPanel, String type)
    {
        System.out.println("lagg");
        BorderLayout layout = new BorderLayout();
//        layout.setHgap(5);
        setLayout(layout);
        setBackground(ColorScheme.DARKER_GRAY_COLOR);

        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel(HTMLStringCreator.htmlTitle());
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);


        add(capyPanel);
    }

}
