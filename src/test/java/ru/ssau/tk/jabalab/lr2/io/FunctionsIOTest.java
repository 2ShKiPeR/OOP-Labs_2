package ru.ssau.tk.jabalab.lr2.io;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.jabalab.lr2.functions.TabulatedFunction;
import ru.ssau.tk.jabalab.lr2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.jabalab.lr2.functions.ArrayTabulatedFunction;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionsIOTest {

    private static final String TEMP_DIR = "temp";
    private static final String TEST_FILE = TEMP_DIR + "/testFunction.txt";
    private static final String JSON_FILE = TEMP_DIR + "/testFunction.json";
    private static TabulatedFunction function;

    @BeforeAll
    static void setUp() throws IOException {
        Files.createDirectories(Paths.get(TEMP_DIR));
        function = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 4, 9});
    }

    @AfterAll
    static void tearDown() throws IOException {
        try (var paths = Files.walk(Paths.get(TEMP_DIR))) {
            paths.sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }


    @Test
    void testWriteAndReadTabulatedFunction() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE))) {
            FunctionsIO.writeTabulatedFunction(writer, function);
        }

        TabulatedFunction readFunction;
        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_FILE))) {
            readFunction = FunctionsIO.readTabulatedFunction(reader, new ArrayTabulatedFunctionFactory());
        }

        assertEquals(function.getCount(), readFunction.getCount());
        for (int i = 0; i < function.getCount(); i++) {
            assertEquals(function.getX(i), readFunction.getX(i));
            assertEquals(function.getY(i), readFunction.getY(i));
        }
    }

    @Test
    void testWriteAndReadBinaryTabulatedFunction() throws IOException {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(TEST_FILE))) {
            FunctionsIO.writeTabulatedFunction(outputStream, function);
        }

        TabulatedFunction readFunction;
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(TEST_FILE))) {
            readFunction = FunctionsIO.readTabulatedFunction(inputStream, new ArrayTabulatedFunctionFactory());
        }

        assertEquals(function.getCount(), readFunction.getCount());
        for (int i = 0; i < function.getCount(); i++) {
            assertEquals(function.getX(i), readFunction.getX(i));
            assertEquals(function.getY(i), readFunction.getY(i));
        }
    }

    @Test
    void testSerializeAndDeserialize() throws IOException, ClassNotFoundException {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(TEST_FILE))) {
            FunctionsIO.serialize(outputStream, function);
        }

        TabulatedFunction readFunction;
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(TEST_FILE))) {
            readFunction = FunctionsIO.deserialize(inputStream);
        }

        assertEquals(function.getCount(), readFunction.getCount());
        for (int i = 0; i < function.getCount(); i++) {
            assertEquals(function.getX(i), readFunction.getX(i));
            assertEquals(function.getY(i), readFunction.getY(i));
        }
    }
}