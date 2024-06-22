package ac3.Processors;

import java.util.List;

import ac3.Instruction;
import ac3.Threads.IMTProcessor;
import ac3.Threads.BMTProcessor;

public class ScalarProcessor extends Processor {

    public ScalarProcessor(List<PipelineStage> pipelineStages, List<Instruction> instructionQueue) {
        super(pipelineStages, instructionQueue);
    }

    @Override
    public void execute() {
        while (!instructionQueue.isEmpty()) {
            cycles++;
            Instruction instruction = instructionQueue.remove(0);

            // Simular a execução em um processador escalar
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

            // Avançar o pipeline
            for (PipelineStage stage : pipelineStages) {
                if (stage.isBusy()) {
                    stage.setCurrentInstruction(null);
                }
            }
        }
    }
}