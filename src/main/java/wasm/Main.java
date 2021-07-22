package wasm;

import wasm.core.Module;
import wasm.core.VirtualMachine;
import wasm.core.WasmReader;

public class Main {

    public static void main(String[] args) {
        String[] names = {
//                "ch01_hw.wasm",
//                "hw_rust.wasm",
//
////                "ch03_eg1_num.wasm",
//                "ch03_eg1_num2.wasm",
//                "ch03_eg2_var.wasm",
//                "ch03_eg3_mem.wasm",
//                "ch03_eg4_block.wasm",
//                "ch03_eg5_br.wasm",
//                "ch03_eg6_call.wasm",
//
//                "ch05_cz.wasm",
////                "ch05_num.wasm",
//                "ch05_num2.wasm",
//                "ch05_param.wasm",

        };
        for (String n : names) {
            System.out.println("=============== " + n + " ===============");
            Module module = WasmReader.readByName(n);
            System.out.println(module.dump());
        }

//        VirtualMachine.execStartFunction(WasmReader.readByName("ch05_cz.wasm"));
        VirtualMachine.execStartFunction(WasmReader.readByName("ch05_num2.wasm"));
//        VirtualMachine.execStartFunction(WasmReader.readByName("ch05_param.wasm"));

    }


}
