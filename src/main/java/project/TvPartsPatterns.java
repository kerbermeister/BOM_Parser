package project;

import java.util.ArrayList;
import java.util.List;

public final class TvPartsPatterns {
    public static final List<String> mainBoardTemplates = new ArrayList<String>();
    public static final List<String> ledPanelTemplates = new ArrayList<String>();

    public TvPartsPatterns() {
        initialize();
    }

    private static void initialize() {
        mainBoardTemplates.add("mainboard");
        mainBoardTemplates.add("main board");
        mainBoardTemplates.add("maineboard");
        mainBoardTemplates.add("MAINBOARD");
        mainBoardTemplates.add("Mainboard");
        mainBoardTemplates.add("Main board");

        ledPanelTemplates.add("LCD");
        ledPanelTemplates.add("Led Panel");
        ledPanelTemplates.add("LCM");
        ledPanelTemplates.add("LED PANEL");
        ledPanelTemplates.add("led panel");
        ledPanelTemplates.add("lcd");
        ledPanelTemplates.add("LED");
        ledPanelTemplates.add("led");
        ledPanelTemplates.add("panel");
        ledPanelTemplates.add("PANEL");

    }

}
