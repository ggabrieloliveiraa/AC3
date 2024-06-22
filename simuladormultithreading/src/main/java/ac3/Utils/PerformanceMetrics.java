package ac3.Utils;

public class PerformanceMetrics {
    private int totalCycles;
    private int bubbleCycles;
    private int instructionsExecuted;

    public int getCycles() {
        return totalCycles;
    }

    public void setCycles(int totalCycles) {
        this.totalCycles = totalCycles;
    }

    public int getBubbleCycles() {
        return bubbleCycles;
    }

    public void setBubbleCycles(int bubbleCycles) {
        this.bubbleCycles = bubbleCycles;
    }

    public int getInstructionsExecuted() {
        return instructionsExecuted;
    }

    public void setInstructionsExecuted(int instructionsExecuted) {
        this.instructionsExecuted = instructionsExecuted;
    }

    public double getIPC() {
        return (double) instructionsExecuted / totalCycles;
    }
}
