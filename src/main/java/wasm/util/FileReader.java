package wasm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {

    public static byte[] readByName(String name) {
        File file = new File(FileReader.class.getResource("").getFile() + "../../../../../resources/main/" + name);

        byte[] bytes = new byte[(int)file.length()];

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            int r = inputStream.read(bytes, 0, bytes.length);
            if (r < 0) {
                throw new RuntimeException("read error");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("read error");
        }

        return bytes;
    }

}
