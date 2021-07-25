package wasm.core.structure;

import wasm.core.instruction.Instruction;

import java.util.ArrayList;
import java.util.List;

public class ControlStack {

    public final List<ControlFrame> frames = new ArrayList<>();

    public void push(ControlFrame frame) {
        frames.add(frame);
    }

    public ControlFrame pop() {
        return frames.remove(frames.size() - 1);
    }

    public int depth() {
        return frames.size();
    }

    public ControlFrame top() {
        return frames.get(frames.size() - 1);
    }

    public ControlFrame topCallFrame(int[] index) {
        for (int i = frames.size() - 1; 0 <= i; i--) {
            if (frames.get(i).instruction == Instruction.CALL) {
                index[0] = frames.size() - 1 - i; // 返回距离顶部的距离
                return frames.get(i);
            }
        }
        index[0] = -1;
        return null;
    }

    public void clear() {
        this.frames.clear();
    }

}
