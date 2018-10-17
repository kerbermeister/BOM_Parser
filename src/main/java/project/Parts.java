package project;

public enum Parts {
    MAINBOARD(3), POWER_BOARD(3), SPEAKER(3), RMC(2), LCD(5);

    private int repairLevel;

    Parts(int repairLevel) {
        this.repairLevel = repairLevel;
    }

    public int getRepairLevel() {
        return repairLevel;
    }
}
