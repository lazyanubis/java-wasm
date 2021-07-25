package wasm;

import wasm.core.instance.Module;
import wasm.core.numeric.U32;
import wasm.core.structure.ModuleInfo;
import wasm.core.structure.WasmReader;

public class Main {

    public static void main(String[] args) {
        String[] names = {
//                "ch01_hw.wasm",
//                "ch01_hw2.wasm",
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

//                "ch06_mem.wasm",
//                "ch06_mem2.wasm",

//                "ch07_fib.wasm",
////                "ch07_global.wasm",
//                "ch07_global2.wasm",
//                "ch07_local.wasm",
//                "ch07_max.wasm",
//                "ch07_sum.wasm",

//                "ch08_cmp.wasm",
//                "ch08_fac.wasm",
//                "ch08_sum.wasm",
//                "ch08_test.wasm",
//                "ch08_eg1_labels.wasm",
//                "ch08_eg2_nested_labels.wasm",
//                "ch08_eg3_label_names.wasm",
//                "ch08_eg4_add.wasm",
//                "ch08_eg5_calc.wasm",
//                "ch08_eg6_br.wasm",
//                "ch08_eg7_br_if.wasm",
//                "ch08_eg8_br_table.wasm",
//                "ch08_eg9_return.wasm",

//                "ch09_calc.wasm",

        };
        for (String n : names) {
            System.out.println("=============== " + n + " ===============");
            ModuleInfo moduleInfo = WasmReader.readByName(n);
            System.out.print(moduleInfo.dump());
        }

//        VirtualMachine.execStartFunction(WasmReader.readByName("ch05_cz.wasm"));
//        VirtualMachine.execStartFunction(WasmReader.readByName("ch05_num2.wasm"));
//        VirtualMachine.execStartFunction(WasmReader.readByName("ch05_param.wasm"));

//        VirtualMachine.execStartFunction(WasmReader.readByName("ch06_mem2.wasm"));

//        VirtualMachine.execStartFunction(WasmReader.readByName("ch07_fib.wasm"));
//        VirtualMachine.execStartFunction(WasmReader.readByName("ch07_global2.wasm"));
//        VirtualMachine.execStartFunction(WasmReader.readByName("ch07_local.wasm"));
//        VirtualMachine.execStartFunction(WasmReader.readByName("ch07_max.wasm"));

//        VirtualMachine.execStartFunction(WasmReader.readByName("ch08_cmp.wasm"));
//        VirtualMachine.execStartFunction(WasmReader.readByName("ch08_fac.wasm"));
//        VirtualMachine.execStartFunction(WasmReader.readByName("ch08_sum.wasm"));
//        VirtualMachine.execStartFunction(WasmReader.readByName("ch08_test.wasm"));
//        VirtualMachine.execStartFunction(WasmReader.readByName("ch08_eg1_labels.wasm"));
//        VirtualMachine.execStartFunction(WasmReader.readByName("ch08_eg2_nested_labels.wasm"));
//        VirtualMachine.execStartFunction(WasmReader.readByName("ch08_eg3_label_names.wasm"));
//        VirtualMachine.execStartFunction(WasmReader.readByName("ch08_eg4_add.wasm"));
//        VirtualMachine.execStartFunction(WasmReader.readByName("ch08_eg5_calc.wasm"));
//        VirtualMachine.execStartFunction(WasmReader.readByName("ch08_eg6_br.wasm"));
//        VirtualMachine.execStartFunction(WasmReader.readByName("ch08_eg7_br_if.wasm"));
//        VirtualMachine.execStartFunction(WasmReader.readByName("ch08_eg8_br_table.wasm"));
//        VirtualMachine.execStartFunction(WasmReader.readByName("ch08_eg9_return.wasm"));

//        VirtualMachine.execStartFunction(WasmReader.readByName("ch01_hw2.wasm"));

//        VirtualMachine.execStartFunction(WasmReader.readByName("ch09_calc.wasm"));


//        Module.newModule(WasmReader.readByName("ch01_hw2.wasm"));
//
//        Module.newModule(WasmReader.readByName("ch05_cz.wasm"));
//        Module.newModule(WasmReader.readByName("ch05_num2.wasm"));
//        Module.newModule(WasmReader.readByName("ch05_param.wasm"));
//
//        Module.newModule(WasmReader.readByName("ch06_mem2.wasm"));
//
//        Module.newModule(WasmReader.readByName("ch07_fib.wasm"));
//        Module.newModule(WasmReader.readByName("ch07_global2.wasm"));
//        Module.newModule(WasmReader.readByName("ch07_local.wasm"));
//        Module.newModule(WasmReader.readByName("ch07_max.wasm"));
//
//        Module.newModule(WasmReader.readByName("ch08_cmp.wasm"));
//        Module.newModule(WasmReader.readByName("ch08_fac.wasm"));
//        Module.newModule(WasmReader.readByName("ch08_sum.wasm"));
//        Module.newModule(WasmReader.readByName("ch08_test.wasm"));
//        Module.newModule(WasmReader.readByName("ch08_eg1_labels.wasm"));
//        Module.newModule(WasmReader.readByName("ch08_eg2_nested_labels.wasm"));
//        Module.newModule(WasmReader.readByName("ch08_eg3_label_names.wasm"));
//        Module.newModule(WasmReader.readByName("ch08_eg4_add.wasm"));
//        Module.newModule(WasmReader.readByName("ch08_eg5_calc.wasm"));
//        Module.newModule(WasmReader.readByName("ch08_eg6_br.wasm"));
//        Module.newModule(WasmReader.readByName("ch08_eg7_br_if.wasm"));
//        Module.newModule(WasmReader.readByName("ch08_eg8_br_table.wasm"));
//        Module.newModule(WasmReader.readByName("ch08_eg9_return.wasm"));
//
//        Module.newModule(WasmReader.readByName("ch09_calc.wasm"));

        Module.newModule(WasmReader.readByName("ch12_ctrl.wasm")).invoke("print_even", U32.valueOf(90));
        Module.newModule(WasmReader.readByName("ch13_prime.wasm")).invoke("main");

    }


}
