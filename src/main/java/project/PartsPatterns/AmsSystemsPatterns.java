package project.PartsPatterns;

import project.Parts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmsSystemsPatterns extends Patterns {

    private final List<String> mainBoardTemplates;
    private final List<String> powerBoardTemplates;
    private final List<String> speakerTemplates;
    private final List<String> remoteControlTemplates;
    private final List<String> mechanismTemplates;
    private final List<String> usbTemplates;
    private final List<String> controlBoardTemplates;
    private final List<String> displayTemplates;

    private final Map<List<String>, Parts> patternsMap;

    {
        patternsMap = new HashMap<List<String>, Parts>();
        mainBoardTemplates = new ArrayList<String>();
        powerBoardTemplates = new ArrayList<String>();
        speakerTemplates = new ArrayList<String>();
        remoteControlTemplates = new ArrayList<String>();
        mechanismTemplates = new ArrayList<String>();
        usbTemplates = new ArrayList<String>();
        controlBoardTemplates = new ArrayList<String>();
        displayTemplates = new ArrayList<String>();
        initialize();
    }

    @Override
    public Map<List<String>, Parts> getPatternsMap() {
        return patternsMap;
    }

    private void initialize() {
        mainBoardTemplates.add("decoder board");
        mainBoardTemplates.add("decode");
        mainBoardTemplates.add("mainboard");

        powerBoardTemplates.add("powerpcb");
        powerBoardTemplates.add("powerboard");

        speakerTemplates.add("speaker");

        remoteControlTemplates.add("remotecontrol");
        remoteControlTemplates.add("rmc");

        mechanismTemplates.add("mechanism");
        mechanismTemplates.add("cdlens");

        usbTemplates.add("usbboard");
        usbTemplates.add("usb");

        controlBoardTemplates.add("controlboard");
        controlBoardTemplates.add("keyspcb");

        displayTemplates.add("display");

        patternsMap.put(mainBoardTemplates, Parts.MAINBOARD);
        patternsMap.put(powerBoardTemplates, Parts.POWER_BOARD);
        patternsMap.put(speakerTemplates, Parts.SPEAKER);
        patternsMap.put(remoteControlTemplates, Parts.RMC);
        patternsMap.put(mechanismTemplates, Parts.CD_MECHANISM);
        patternsMap.put(usbTemplates, Parts.USB);
        patternsMap.put(controlBoardTemplates, Parts.CONTROL_BOARD);
        patternsMap.put(displayTemplates, Parts.DISPLAY_BOARD);
    }
}
