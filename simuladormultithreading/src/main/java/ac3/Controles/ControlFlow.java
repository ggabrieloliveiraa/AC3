package ac3.Controles;

import java.util.List;

import ac3.Instruction;

public class ControlFlow {
    
    // Verifica se uma instrução é uma instrução de controle de fluxo (ex.: branch, jump)
    public boolean isControlInstruction(Instruction instruction) {
        String opcode = instruction.getOpcode();
        return opcode.equals("BEQ") || opcode.equals("BNE") || opcode.equals("JAL") || opcode.equals("JALR");
    }

    // Verifica se há controle de fluxo entre duas instruções
    public boolean hasControlDependency(Instruction instr1, Instruction instr2) {
        return isControlInstruction(instr1);
    }

    // Encontra e retorna a instrução que causa dependência de controle na fila de instruções
    public Instruction findControlDependentInstruction(List<Instruction> instructionQueue, Instruction currentInstruction) {
        for (Instruction instr : instructionQueue) {
            if (hasControlDependency(instr, currentInstruction)) {
                return instr;
            }
        }
        return null;
    }
}
