package ac3.Controles;

import java.util.List;

import ac3.Instruction;

public class DataDependency {
    
    // Verifica se há dependência de dados entre duas instruções
    public boolean hasDependency(Instruction instr1, Instruction instr2) {
        String[] operands1 = instr1.getOperands();
        String[] operands2 = instr2.getOperands();

        for (String operand1 : operands1) {
            for (String operand2 : operands2) {
                if (operand1.equals(operand2)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Encontra e retorna a instrução que causa dependência de dados na fila de instruções
    public Instruction findDependentInstruction(List<Instruction> instructionQueue, Instruction currentInstruction) {
        for (Instruction instr : instructionQueue) {
            if (hasDependency(instr, currentInstruction)) {
                return instr;
            }
        }
        return null;
    }
}