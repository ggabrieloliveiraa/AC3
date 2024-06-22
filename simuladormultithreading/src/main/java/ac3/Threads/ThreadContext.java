package ac3.Threads;

import java.util.List;

import ac3.Instruction;

public class ThreadContext {
    private int threadId;
    private List<Instruction> instructionQueue;

    public ThreadContext(int threadId, List<Instruction> instructionQueue) {
        this.threadId = threadId;
        this.instructionQueue = instructionQueue;
    }

    public int getThreadId() {
        return threadId;
    }

    public List<Instruction> getInstructionQueue() {
        return instructionQueue;
    }

    public void setInstructionQueue(List<Instruction> instructionQueue) {
        this.instructionQueue = instructionQueue;
    }

    @Override
    public String toString() {
        return "ThreadContext{" +
                "threadId=" + threadId +
                ", instructionQueue=" + instructionQueue +
                '}';
    }
}