/*
    This class is only an instruction that declares what any pattern class should contain.
 */

package project.PartsPatterns;

import project.Parts;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Patterns {

    private HashMap<List<String>, Parts> patternsMap;

    public Map<List<String>, Parts> getPatternsMap() {
        return patternsMap;
    }
}
