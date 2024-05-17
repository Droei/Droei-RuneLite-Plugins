package be.droei.RuneTube.Panel;

import be.droei.RuneTube.Api.ApiProcessor;
import be.droei.RuneTube.Api.RuneTubeApi;
import be.droei.RuneTube.classes.VideoData;
import com.google.inject.Inject;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.util.ImageUtil;
import net.runelite.client.util.LinkBrowser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.List;

import static be.droei.RuneTube.PanelManager.HTMLStringCreator.*;

public class RuneTubePanel extends PluginPanel
{
    @Inject
    private EventBus eventBus;
//    private final ApiProcessor apiProcessor = new ApiProcessor();
    RuneTubeApi runeTubeApi = new RuneTubeApi();

    public void init(){
        setLayout(new BorderLayout());
        setBackground(ColorScheme.DARK_GRAY_COLOR);

        add(Header(), BorderLayout.NORTH);
        add(Main(), BorderLayout.CENTER);
        add(Footer(), BorderLayout.SOUTH);

        eventBus.register(this);
    }

    public void deInit() {
        eventBus.unregister(this);
    }

    JPanel Main(){
        JPanel result = new JPanel();
        result.setBackground(ColorScheme.BORDER_COLOR);
        result.setLayout(new GridLayout(0, 1));


        for(VideoData videoData : runeTubeApi.getRecentVids()){
            JLabel html = new JLabel(htmlImage(videoData));
            html.setBorder(new EmptyBorder(1, 0, 1, 0));

            Runnable callback = () -> LinkBrowser.browse("https://www.youtube.com/watch?v="+videoData.id);
            html.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseReleased(MouseEvent e)
                {
                    callback.run();
                }
                @Override
                public void mouseEntered(MouseEvent e)
                {
                    html.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e)
                {
                    html.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });
            result.add(html);
        }
        return result;
    }

    JPanel Header(){
        JPanel result = new JPanel();
        JLabel title = new JLabel(htmlTitle());
        result.add(title);
        return result;
    }
    JPanel Footer(){
        JPanel result = new JPanel();
        result.setBackground(ColorScheme.DARK_GRAY_COLOR);
        result.setLayout(new GridLayout(3, 1));

        final Font smallFont = FontManager.getRunescapeSmallFont();
        JLabel version = new JLabel(htmlLabel("RuneTube is made by: Droei"));
        JLabel version2 = new JLabel(htmlLabel("Version: Alpha 0.0.1"));
        version.setFont(smallFont);

        result.add(version);
        result.add(version2);
        return result;
    }
}