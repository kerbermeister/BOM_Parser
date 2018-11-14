package project.PartsPatterns;

import project.Parts;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TvPartsPatterns extends Patterns {

    private final List<String> mainBoardTemplates;
    private final List<String> ledPanelTemplates;
    private final List<String> speakerTemplates;
    private final List<String> remoteControlTemplates;
    private final List<String> powerBoardTemplates;
    private final List<String> irBoardTemplates;
    private final List<String> controlBoardTemplates;

    private Map<List<String>, Parts> patternsMap;

    {
        patternsMap = new HashMap<List<String>, Parts>();
        mainBoardTemplates = new ArrayList<String>();
        ledPanelTemplates = new ArrayList<String>();
        speakerTemplates = new ArrayList<String>();
        controlBoardTemplates = new ArrayList<String>();
        remoteControlTemplates = new ArrayList<String>();
        powerBoardTemplates = new ArrayList<String>();
        irBoardTemplates = new ArrayList<String>();
        initialize();
    }

    @Override
    public Map<List<String>, Parts> getPatternsMap() {
        return patternsMap;
    }

    private void initialize() {
        mainBoardTemplates.add("maineboard");
        mainBoardTemplates.add("mainboard");
        mainBoardTemplates.add("mainpcbassembly");
        mainBoardTemplates.add("mainpcbassy");
        mainBoardTemplates.add("decoder board");

        powerBoardTemplates.add("power board");
        powerBoardTemplates.add("power supply board");

        ledPanelTemplates.add("lcd");
        ledPanelTemplates.add("lcm");
        ledPanelTemplates.add("led panel");
        ledPanelTemplates.add("led");
        ledPanelTemplates.add("panel");
        ledPanelTemplates.add("lcm-panel");
        ledPanelTemplates.add("open cell");

        controlBoardTemplates.add("control board");
        controlBoardTemplates.add("KEY board");

        speakerTemplates.add("speaker");

        irBoardTemplates.add("IR board");
        irBoardTemplates.add("remote control pcb");

        remoteControlTemplates.add("JX-1233");
        remoteControlTemplates.add("remote control");

        patternsMap.put(mainBoardTemplates, Parts.MAINBOARD);
        patternsMap.put(speakerTemplates, Parts.SPEAKER);
        patternsMap.put(irBoardTemplates, Parts.IR_BOARD);
        patternsMap.put(ledPanelTemplates, Parts.LCD);
        patternsMap.put(controlBoardTemplates, Parts.CONTROL_BOARD);
        patternsMap.put(remoteControlTemplates, Parts.RMC);
        patternsMap.put(powerBoardTemplates, Parts.POWER_BOARD);
    }
}
