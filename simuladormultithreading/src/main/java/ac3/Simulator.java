package ac3;

import ac3.Processors.Processor;
import ac3.Utils.PerformanceMetrics;

public class Simulator {
    private Processor processor;
    private PerformanceMetrics metrics;

    public Simulator(Processor processor) {
        this.processor = processor;
        this.metrics = new PerformanceMetrics();
    }

    public void runSimulation() {
        processor.execute();
        metrics.setCycles(processor.getCycles());
        metrics.setInstructionsExecuted(processor.getInstructionsExecuted());
        metrics.setBubbleCycles(processor.getBubbleCycles());
    }

    public PerformanceMetrics getMetrics() {
        return metrics;
    }
}