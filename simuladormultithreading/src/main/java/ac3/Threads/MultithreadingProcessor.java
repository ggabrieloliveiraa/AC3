package ac3.Threads;

import java.util.List;

import ac3.Processors.PipelineStage;
import ac3.Processors.Processor;

public abstract class MultithreadingProcessor extends Processor {
    protected List<ThreadContext> threadContexts;

    public MultithreadingProcessor(List<PipelineStage> pipelineStages, List<ThreadContext> threadContexts) {
        super(pipelineStages, null);  // Passa null para instructionQueue, pois cada thread tem sua própria fila
        this.threadContexts = threadContexts;
    }

    public abstract void execute();
}
