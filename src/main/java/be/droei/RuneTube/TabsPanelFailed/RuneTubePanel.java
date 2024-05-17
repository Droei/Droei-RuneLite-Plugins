package be.droei.RuneTube.TabsPanelFailed;

import be.droei.RuneTube.Api.ApiProcessor;
import be.droei.RuneTube.PanelManager.HTMLStringCreator;
import be.droei.RuneTube.classes.VideoData;
import be.droei.RuneTube.enums.ChannelTagEnum;
import lombok.Getter;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.materialtabs.MaterialTab;
import net.runelite.client.ui.components.materialtabs.MaterialTabGroup;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class RuneTubePanel extends PluginPanel {

    private final JPanel display = new JPanel();

    private final MaterialTabGroup tabGroup = new MaterialTabGroup(display);

    @Getter
    private final ApiProcessor apiProcessor;

    @Inject
    private RuneTubePanel(ApiProcessor apiProcessor)
    {
        super(false);

        this.apiProcessor = apiProcessor;
        List<VideoData> videoData = apiProcessor.GetAllVideos();

        setLayout(new BorderLayout());
        setBackground(ColorScheme.PROGRESS_INPROGRESS_COLOR);

        String type = "All";
//        MaterialTab allTab = new MaterialTab(type, tabGroup,
//                new RuneTubeTab(GeneratePanel(videoData) , type));
//        tabGroup.addTab(allTab);

        type = "Variety";
        MaterialTab varietyTab = new MaterialTab(type, tabGroup,
                new RuneTubeTab(GeneratePanel(apiProcessor.FilterByTag(videoData, ChannelTagEnum.VARIETY)) , type));
        tabGroup.addTab(varietyTab);

//        type = "Progress";
//        MaterialTab progressTab = new MaterialTab(type, tabGroup,
//                new RuneTubeTab(GeneratePanel(apiProcessor.FilterByTag(videoData, ChannelTagEnum.PROGRESS)) , type));
//        tabGroup.addTab(progressTab);
//
//        type = "PvP";
//        MaterialTab pvpTab = new MaterialTab(type, tabGroup,
//                new RuneTubeTab(GeneratePanel(apiProcessor.FilterByTag(videoData, ChannelTagEnum.PVP)) , type));
//        tabGroup.addTab(pvpTab);

//        tabGroup.select(allTab);

        add(tabGroup, BorderLayout.NORTH);
        add(display, BorderLayout.CENTER);
    }

    public JPanel GeneratePanel(List<VideoData> videoData){
        JPanel capyPanel = new JPanel();
        capyPanel.setBackground(ColorScheme.BORDER_COLOR);
        capyPanel.setLayout(new GridLayout(0, 1));

        for(VideoData vid : videoData){

            JLabel html = new JLabel(HTMLStringCreator.htmlImage(vid));
            html.setBorder(new EmptyBorder(1, 0, 1, 0));

//            Runnable callback = () -> LinkBrowser.browse("https://www.youtube.com/watch?v="+vid.id);
//            html.addMouseListener(new MouseAdapter()
//            {
//                @Override
//                public void mouseReleased(MouseEvent e)
//                {
//                    callback.run();
//                }
//                @Override
//                public void mouseEntered(MouseEvent e)
//                {
//                    html.setCursor(new Cursor(Cursor.HAND_CURSOR));
//                }
//
//                @Override
//                public void mouseExited(MouseEvent e)
//                {
//                    html.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//                }
//            });
            capyPanel.add(html);
        }
        return capyPanel;
    }
}
