import io.qameta.allure.Attachment;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.stream.IntStream;

/**
 * @author eroshenkoam (Artem Eroshenko).
 */
public class TestExample {

    static IntStream range() {
        return IntStream.range(0, 200);
    }

    @ParameterizedTest
    @MethodSource("range")
    public void testOutput() {
        try (InputStream stream = ClassLoader.getSystemResourceAsStream("attachment.html")) {
            final String content = IOUtils.toString(stream, Charset.forName("UTF-8"));
            for (int i = 0; i < 50; i++) {
                attachContent(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Attachment(value = "imagediff", fileExtension = "json", type = "text/json")
    public byte[] attachContent(String content) {
        return content.getBytes();
    }
}
