package ac3;

public class Instruction {
    private String opcode;
    private String[] operands;

    public Instruction(String opcode, String[] operands) {
        this.opcode = opcode;
        this.operands = operands;
    }

    public String getOpcode() {
        return opcode;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public String[] getOperands() {
        return operands;
    }

    public void setOperands(String[] operands) {
        this.operands = operands;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(opcode).append(" ");
        for (String operand : operands) {
            sb.append(operand).append(" ");
        }
        return sb.toString().trim();
    }
}