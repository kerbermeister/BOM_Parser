/*
    To add new part you should specify:
    1) its repair level (int value from 2 to 5)
    2) section name (String value) from e-num "Sections"
 */

package project;

import project.BomBuilder.Sections;

public enum Parts {
    MAINBOARD(3, Sections.DEN.name()), POWER_BOARD(3, Sections.PSU.name()), SPEAKER(3, Sections.SPK.name()),
    RMC(2, Sections.RMC.name()), LCD(5, Sections.LCD.name()), CONTROL_BOARD(3, Sections.CTR.name()), IR_BOARD(3, Sections.IRD.name()),
    DISPLAY_BOARD(3, Sections.IDS.name()), USB(3, Sections.USB.name()), CD_MECHANISM(3, Sections.CDS.name());

    private int repairLevel;
    private String section;

    Parts(int repairLevel, String section) {
        this.repairLevel = repairLevel;
        this.section = section;
    }

    public int getRepairLevel() {
        return repairLevel;
    }

    public String getSection() {
        return this.section;
    }
}
