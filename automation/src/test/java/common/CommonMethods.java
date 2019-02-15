package common;


import model.Computer;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class CommonMethods {
    static final List<Computer> validComputerDataList = new ArrayList<>();
    static final List<Computer> invalidComputerDataList = new ArrayList<>();

    public static void readDataFromFile() {

        try (Stream<String> stream = Files.lines(Paths.get("computers.txt"), StandardCharsets.UTF_8)) {
            stream.forEach(s -> {
                String[] dataLine = s.split(";");
                if (dataLine.length > 0) {
                    Computer computer = new Computer(dataLine[0], dataLine[1], dataLine[2], dataLine[3], dataLine[4]);
                    if (dataLine[4].equals("valid"))
                        validComputerDataList.add(computer);
                    else
                        invalidComputerDataList.add(computer);
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Computer getValidComputerData() {
        Random r = new Random();
        return validComputerDataList.get(r.nextInt(validComputerDataList.size()));
    }

    public static Computer getInValidComputerData() {
        Random r = new Random();
        return invalidComputerDataList.get(r.nextInt(invalidComputerDataList.size()));
    }
}
