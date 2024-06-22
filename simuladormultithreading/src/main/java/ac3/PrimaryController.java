package ac3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ac3.Processors.*;
import ac3.Threads.*;
import ac3.Utils.*;

public class PrimaryController implements Initializable {

    @FXML
    private Label resultsLabel;

    @FXML
    private ChoiceBox<String> processorTypeChoiceBox;

    @FXML
    private ChoiceBox<String> multithreadingSupportChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        processorTypeChoiceBox.setItems(FXCollections.observableArrayList("Scalar", "Superscalar"));
        multithreadingSupportChoiceBox.setItems(FXCollections.observableArrayList("Nenhum", "IMT", "BMT", "SMT"));
        processorTypeChoiceBox.getSelectionModel().selectFirst();
        multithreadingSupportChoiceBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void runSimulation() {
        // Definir instruções RISC-V para a simulação (exemplo)
        Instruction instr1 = new Instruction("ADD", new String[]{"x1", "x2", "x3"});
        Instruction instr2 = new Instruction("SUB", new String[]{"x4", "x5", "x6"});
        List<Instruction> instructionQueue = new ArrayList<>();
        instructionQueue.add(instr1);
        instructionQueue.add(instr2);

        // Definir estágios do pipeline (exemplo)
        PipelineStage fetch = new PipelineStage("Fetch");
        PipelineStage decode = new PipelineStage("Decode");
        PipelineStage execute = new PipelineStage("Execute");
        PipelineStage memory = new PipelineStage("Memory");
        PipelineStage writeBack = new PipelineStage("WriteBack");
        List<PipelineStage> pipelineStages = List.of(fetch, decode, execute, memory, writeBack);

        // Lendo as seleções do usuário
        String processorType = processorTypeChoiceBox.getValue();
        String multithreadingSupport = multithreadingSupportChoiceBox.getValue();

        Processor processor = null;

        if ("Scalar".equals(processorType)) {
            if ("Nenhum".equals(multithreadingSupport)) {
                processor = new ScalarProcessor(pipelineStages, instructionQueue);
            } else if ("IMT".equals(multithreadingSupport)) {
                // Definir contextos de threads para IMT
                ThreadContext thread1 = new ThreadContext(1, new ArrayList<>(instructionQueue));
                ThreadContext thread2 = new ThreadContext(2, new ArrayList<>(instructionQueue));
                List<ThreadContext> threadContexts = new ArrayList<>();
                threadContexts.add(thread1);
                threadContexts.add(thread2);
                processor = new IMTProcessor(pipelineStages, threadContexts);
            } else if ("BMT".equals(multithreadingSupport)) {
                // Definir contextos de threads para BMT
                ThreadContext thread1 = new ThreadContext(1, new ArrayList<>(instructionQueue));
                ThreadContext thread2 = new ThreadContext(2, new ArrayList<>(instructionQueue));
                List<ThreadContext> threadContexts = new ArrayList<>();
                threadContexts.add(thread1);
                threadContexts.add(thread2);
                processor = new BMTProcessor(pipelineStages, threadContexts);
            }
        } else if ("Superscalar".equals(processorType)) {
            int issueWidth = 2;  // Exemplo de largura de emissão
            if ("Nenhum".equals(multithreadingSupport)) {
                processor = new SuperscalarProcessor(pipelineStages, instructionQueue, issueWidth);
            } else if ("IMT".equals(multithreadingSupport)) {
                // Definir contextos de threads para IMT
                ThreadContext thread1 = new ThreadContext(1, new ArrayList<>(instructionQueue));
                ThreadContext thread2 = new ThreadContext(2, new ArrayList<>(instructionQueue));
                List<ThreadContext> threadContexts = new ArrayList<>();
                threadContexts.add(thread1);
                threadContexts.add(thread2);
                processor = new IMTProcessor(pipelineStages, threadContexts);
            } else if ("BMT".equals(multithreadingSupport)) {
                // Definir contextos de threads para BMT
                ThreadContext thread1 = new ThreadContext(1, new ArrayList<>(instructionQueue));
                ThreadContext thread2 = new ThreadContext(2, new ArrayList<>(instructionQueue));
                List<ThreadContext> threadContexts = new ArrayList<>();
                threadContexts.add(thread1);
                threadContexts.add(thread2);
                processor = new BMTProcessor(pipelineStages, threadContexts);
            } else if ("SMT".equals(multithreadingSupport)) {
                // Definir contextos de threads para SMT
                ThreadContext thread1 = new ThreadContext(1, new ArrayList<>(instructionQueue));
                ThreadContext thread2 = new ThreadContext(2, new ArrayList<>(instructionQueue));
                List<ThreadContext> threadContexts = new ArrayList<>();
                threadContexts.add(thread1);
                threadContexts.add(thread2);
                processor = new SMTProcessor(pipelineStages, threadContexts);
            }
        }

        // Executar simulação
        if (processor != null) {
            Simulator simulator = new Simulator(processor);
            simulator.runSimulation();

            // Coletar e exibir resultados
            PerformanceMetrics metrics = simulator.getMetrics();
            String results = String.format("Total Cycles: %d%nInstructions Executed: %d%nBubble Cycles: %d%nIPC: %.2f",
                    metrics.getCycles(), metrics.getInstructionsExecuted(), metrics.getBubbleCycles(), metrics.getIPC());
            resultsLabel.setText(results);
        } else {
            resultsLabel.setText("Configuração inválida selecionada.");
        }
    }
}
