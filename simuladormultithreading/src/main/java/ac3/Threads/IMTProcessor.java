package ac3.Threads;

import java.util.List;

import ac3.Instruction;
import ac3.Processors.PipelineStage;

public class IMTProcessor extends MultithreadingProcessor {

    public IMTProcessor(List<PipelineStage> pipelineStages, List<ThreadContext> threadContexts) {
        super(pipelineStages, threadContexts);
    }

    @Override
    public void execute() {
        int currentThread = 0;

        while (anyThreadHasInstructions()) {
            cycles++;
            ThreadContext threadContext = threadContexts.get(currentThread);

            if (!threadContext.getInstructionQueue().isEmpty()) {
                Instruction instruction = threadContext.getInstructionQueue().remove(0);

                // Simular a execução em um processador com IMT
                boolean instructionExecuted = false;
                for (PipelineStage stage : pipelineStages) {
                    if (!stage.isBusy()) {
                        stage.setCurrentInstruction(instruction);
                        instructionExecuted = true;
                        instructionsExecuted++;
                        break;
                    }
                }

                if (!instructionExecuted) {
                    bubbleCycles++;
                }
            }

            // Avançar para o próximo thread
            currentThread = (currentThread + 1) % threadContexts.size();

            // Avançar o pipeline
            for (PipelineStage stage : pipelineStages) {
                if (stage.isBusy()) {
                    stage.setCurrentInstruction(null);
                }
            }
        }
    }

    private boolean anyThreadHasInstructions() {
        for (ThreadContext threadContext : threadContexts) {
            if (!threadContext.getInstructionQueue().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}