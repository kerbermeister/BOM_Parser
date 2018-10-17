package project.PartsPatterns;

import project.Parts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestTvPartsPatterns extends Patterns {

    private static final List<String> mainBoardTemplates = new ArrayList<String>();
    private static final List<String> ledPanelTemplates = new ArrayList<String>();
    private static final List<String> speakerTemplates = new ArrayList<String>();

    private Map<List<String>, Parts> patternsMap;

    @Override
    public Map<List<String>, Parts> getPatternsMap() {
        return patternsMap;
    }

    public TestTvPartsPatterns() {
        patternsMap = new HashMap<List<String>, Parts>();
        initialize();
    }

    private void initialize() {
        mainBoardTemplates.add("mainboard");
        mainBoardTemplates.add("main board");
        mainBoardTemplates.add("maineboard");
        mainBoardTemplates.add("MAINBOARD");
        mainBoardTemplates.add("Mainboard");
        mainBoardTemplates.add("Main board");
        mainBoardTemplates.add("Main_board");
        mainBoardTemplates.add("main-board");

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
        ledPanelTemplates.add("Panel");
        ledPanelTemplates.add("led-panel");
        ledPanelTemplates.add("Led-panel");
        ledPanelTemplates.add("lcm-panel");
        ledPanelTemplates.add("LED-Panel");
        ledPanelTemplates.add("led_panel");
        ledPanelTemplates.add("LED_PANEL");

        speakerTemplates.add("Speaker");
        speakerTemplates.add("speaker");
        speakerTemplates.add("SPEAKER");

        patternsMap.put(mainBoardTemplates, Parts.MAINBOARD);
        patternsMap.put(speakerTemplates, Parts.SPEAKER);
        patternsMap.put(ledPanelTemplates, Parts.LCD);
    }
}
