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
    private static final List<String> powerBoardTemplates = new ArrayList<String>();
    private static final List<String> irBoardTemplates = new ArrayList<String>();
    private static final List<String> controlBoardTemplates = new ArrayList<String>();

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
        mainBoardTemplates.add("mainpcbassembly");
        mainBoardTemplates.add("mainpcbassy");

        powerBoardTemplates.add("power board");

        ledPanelTemplates.add("lcd");
        ledPanelTemplates.add("lcm");
        ledPanelTemplates.add("led panel");
        ledPanelTemplates.add("led");
        ledPanelTemplates.add("panel");
        ledPanelTemplates.add("lcm-panel");
        ledPanelTemplates.add("open cell");

        speakerTemplates.add("speaker");

        remoteControlTemplates.add("remote control");

        irBoardTemplates.add("IR board");

        controlBoardTemplates.add("control board");
        controlBoardTemplates.add("KEY board");

        patternsMap.put(mainBoardTemplates, Parts.MAINBOARD);
        patternsMap.put(speakerTemplates, Parts.SPEAKER);
        patternsMap.put(ledPanelTemplates, Parts.LCD);
        patternsMap.put(remoteControlTemplates, Parts.RMC);
        patternsMap.put(powerBoardTemplates, Parts.POWER_BOARD);
        patternsMap.put(irBoardTemplates, Parts.IR_BOARD);
        patternsMap.put(controlBoardTemplates, Parts.CONTROL_BOARD);
    }
}
