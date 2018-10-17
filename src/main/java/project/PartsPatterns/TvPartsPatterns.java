package project.PartsPatterns;

import project.Parts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TvPartsPatterns extends Patterns {

    private static final List<String> mainBoardTemplates = new ArrayList<String>();
    private static final List<String> ledPanelTemplates = new ArrayList<String>();
    private static final List<String> speakerTemplates = new ArrayList<String>();

    private Map<List<String>, Parts> patternsMap;

    public Map<List<String>, Parts> getPatternsMap() {
        return patternsMap;
    }

    public TvPartsPatterns() {
        patternsMap = new HashMap<List<String>, Parts>();
        initialize();
    }

    private void initialize() {
        mainBoardTemplates.add("main board");
        mainBoardTemplates.add("maineboard");
        mainBoardTemplates.add("mainboard");
        mainBoardTemplates.add("main_board");
        mainBoardTemplates.add("main-board");

        ledPanelTemplates.add("lcd");
        ledPanelTemplates.add("lcm");
        ledPanelTemplates.add("led panel");
        ledPanelTemplates.add("led");
        ledPanelTemplates.add("panel");
        ledPanelTemplates.add("led-panel");
        ledPanelTemplates.add("lcm-panel");
        ledPanelTemplates.add("led_panel");

        speakerTemplates.add("speaker");

        patternsMap.put(mainBoardTemplates, Parts.MAINBOARD);
        patternsMap.put(speakerTemplates, Parts.SPEAKER);
        patternsMap.put(ledPanelTemplates, Parts.LCD);
    }
}
