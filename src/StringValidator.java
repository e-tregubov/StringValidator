import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StringValidator {

    PrintWriter invalidDataFile = new PrintWriter("invalidData.txt");

    public StringValidator() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get("data.txt"))) {
            stream.forEach(this::parse);
        }
        invalidDataFile.flush();
        invalidDataFile.close();
    }

    public static void main(String[] args) throws IOException {
        new StringValidator();
    }

    private void parse(String string) {
        if (!valid(string)) invalidDataFile.write(string + '\n');
    }

    private boolean valid(String string) {
        String[] parts = string.split(";");
        if (parts.length != 4) return false;
        return parts[0].matches("^[1-9]$|^[1-9]\\d$|^100$") &&
               parts[1].matches("^[A-Z][a-z]{2,29}+$|^[А-Я][а-я]{2,29}+$") &&
               parts[2].matches("^[1-9][.]\\d\\d$|^[1-9]\\d[.]\\d\\d$|^100[.]00$") &&
               parts[3].matches("^[1-9]$|^1\\d$|^20$");
    }

}