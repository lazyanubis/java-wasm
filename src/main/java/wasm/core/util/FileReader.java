package wasm.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class FileReader {

    public static byte[] readByName(String name) {
        URL url = FileReader.class.getResource("");
        if (null == url) {
            throw new RuntimeException("can not find file");
        }
        File file = new File(url.getFile() + "../../../../../../resources/main/" + name);

        byte[] bytes = new byte[(int)file.length()];

        try (FileInputStream inputStream = new FileInputStream(file)) {
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
