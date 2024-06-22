package ac3.Processors;

import java.util.List;

import ac3.Instruction;

public abstract class Processor {
    protected List<PipelineStage> pipelineStages;
    protected List<Instruction> instructionQueue;
    protected int cycles;
    protected int instructionsExecuted;
    protected int bubbleCycles;

    public Processor(List<PipelineStage> pipelineStages, List<Instruction> instructionQueue) {
        this.pipelineStages = pipelineStages;
        this.instructionQueue = instructionQueue;
        this.cycles = 0;
        this.instructionsExecuted = 0;
        this.bubbleCycles = 0;
    }

    public abstract void execute();

    public int getCycles() {
        return cycles;
    }

    public int getInstructionsExecuted() {
        return instructionsExecuted;
    }

    public int getBubbleCycles() {
        return bubbleCycles;
    }

    public double getIPC() {
        return (double) instructionsExecuted / cycles;
    }
}