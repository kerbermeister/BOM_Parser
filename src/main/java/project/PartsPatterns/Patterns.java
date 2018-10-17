/*
    This class is only an instruction that declares what any pattern class should contain.
    BE AWARE: creating new template class you might not specify templates considering the different
    cases of letters size (both upper and lower), different symbols i.e. "- _ :" and etc.
    since MatcherImpl transforms case of template and String, deletes all the symbols except for letters
    of cell before comparing. Specifying new template you can just use a lower case, it will be enough.
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
