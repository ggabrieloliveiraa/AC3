package ac3.Processors;

import ac3.Instruction;

public class PipelineStage {
    private String name;
    private boolean busy;
    private Instruction currentInstruction;

    public PipelineStage(String name) {
        this.name = name;
        this.busy = false;
        this.currentInstruction = null;
    }

    public String getName() {
        return name;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public Instruction getCurrentInstruction() {
        return currentInstruction;
    }

    public void setCurrentInstruction(Instruction currentInstruction) {
        this.currentInstruction = currentInstruction;
        this.busy = (currentInstruction != null);
    }

    @Override
    public String toString() {
        return "PipelineStage{" +
                "name='" + name + '\'' +
                ", busy=" + busy +
                ", currentInstruction=" + (currentInstruction != null ? currentInstruction.toString() : "null") +
                '}';
    }
}
