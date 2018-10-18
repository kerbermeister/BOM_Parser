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
    private static final List<String> remoteControlTemplates = new ArrayList<String>();

    private Map<List<String>, Parts> patternsMap;

    public Map<List<String>, Parts> getPatternsMap() {
        return patternsMap;
    }

    public TvPartsPatterns() {
        patternsMap = new HashMap<List<String>, Parts>();
        initialize();
    }

    private void initialize() {
        mainBoardTemplates.add("maineboard");
        mainBoardTemplates.add("mainboard");


        ledPanelTemplates.add("lcd");
        ledPanelTemplates.add("lcm");
        ledPanelTemplates.add("led panel");
        ledPanelTemplates.add("led");
        ledPanelTemplates.add("panel");
        ledPanelTemplates.add("lcm-panel");

        speakerTemplates.add("speaker");

        remoteControlTemplates.add("remote control");

        patternsMap.put(mainBoardTemplates, Parts.MAINBOARD);
        patternsMap.put(speakerTemplates, Parts.SPEAKER);
        patternsMap.put(ledPanelTemplates, Parts.LCD);
        patternsMap.put(remoteControlTemplates, Parts.RMC);
    }
}
