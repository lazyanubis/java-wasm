package wasm;

import wasm.core.Module;
import wasm.core.WasmReader;

public class Main {

    public static void main(String[] args) {
//        Module module = WasmReader.readByName("ch01_hw.wasm");
//        Module module = WasmReader.readByName("hw_rust.wasm");
//        Module module = WasmReader.readByName("ch03_eg1_num.wasm");
//        Module module = WasmReader.readByName("ch03_eg2_var.wasm");
//        Module module = WasmReader.readByName("ch03_eg3_mem.wasm");
//        Module module = WasmReader.readByName("ch03_eg4_block.wasm");
//        Module module = WasmReader.readByName("ch03_eg5_br.wasm");
        Module module = WasmReader.readByName("ch03_eg6_call.wasm");
        System.out.println(module.dump());
    }


}
