package project.PartsPatterns;

import java.util.ArrayList;
import java.util.List;

public class PatternsToIgnore {

    private final List<String> ignoreList;

    {
        ignoreList = new ArrayList<String>();
        initialize();
    }

    private void initialize() {
        ignoreList.add("cabinet");
        ignoreList.add("bracket");
        ignoreList.add("wire");
        ignoreList.add("connector");
        ignoreList.add("cable");
        ignoreList.add("tcon");
        ignoreList.add("holder");
        ignoreList.add("lvds");
        ignoreList.add("protection");
        ignoreList.add("paper");
        ignoreList.add("audio");
        ignoreList.add("pad");
        ignoreList.add("antivibration");
        ignoreList.add("support");
        ignoreList.add("shelf");
        ignoreList.add("back");
        ignoreList.add("cover");
        ignoreList.add("sticker");
        ignoreList.add("label");
        ignoreList.add("insert");
        ignoreList.add("bar");
        ignoreList.add("matched");
        ignoreList.add("screw");
    }

    public List<String> getIgnoreList() {
        return ignoreList;
    }
}
