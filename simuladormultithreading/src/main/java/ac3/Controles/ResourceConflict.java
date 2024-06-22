package ac3.Controles;

import java.util.List;

import ac3.Instruction;
import ac3.Processors.PipelineStage;

public class ResourceConflict {
    
    // Verifica se há conflito de recursos entre duas instruções
    public boolean hasConflict(PipelineStage stage, Instruction instruction) {
        // Este método pode ser expandido para verificar conflitos mais complexos
        return stage.isBusy();
    }

    // Encontra e retorna o estágio de pipeline que causa conflito de recursos
    public PipelineStage findConflictingStage(List<PipelineStage> pipelineStages, Instruction instruction) {
        for (PipelineStage stage : pipelineStages) {
            if (hasConflict(stage, instruction)) {
                return stage;
            }
        }
        return null;
    }
}