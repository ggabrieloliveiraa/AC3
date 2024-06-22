package ac3.Processors;

import java.util.ArrayList;
import java.util.List;

import ac3.Instruction;
import ac3.Threads.SMTProcessor;

public class SuperscalarProcessor extends Processor {
    private int issueWidth;

    public SuperscalarProcessor(List<PipelineStage> pipelineStages, List<Instruction> instructionQueue, int issueWidth) {
        super(pipelineStages, instructionQueue);
        this.issueWidth = issueWidth;
    }

    @Override
    public void execute() {
        while (!instructionQueue.isEmpty()) {
            cycles++;
            List<Instruction> instructionsToIssue = new ArrayList<>();

            for (int i = 0; i < issueWidth && !instructionQueue.isEmpty(); i++) {
                instructionsToIssue.add(instructionQueue.remove(0));
            }

            // Simular a execução em um processador superescalar
            for (Instruction instruction : instructionsToIssue) {
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

            // Avançar o pipeline
            for (PipelineStage stage : pipelineStages) {
                if (stage.isBusy()) {
                    stage.setCurrentInstruction(null);
                }
            }
        }
    }
}