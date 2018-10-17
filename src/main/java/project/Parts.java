package project;

import project.BomBuilder.Sections;

public enum Parts {
    MAINBOARD(3, Sections.DEN.name()), POWER_BOARD(3, Sections.PSU.name()), SPEAKER(3, Sections.SPK.name()),
    RMC(2, Sections.RMC.name()), LCD(5, Sections.LCD.name());

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
