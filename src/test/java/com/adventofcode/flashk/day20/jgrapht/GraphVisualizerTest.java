package com.adventofcode.flashk.day20.jgrapht;

import static org.junit.jupiter.api.Assertions.*;


import module java.base;
import com.adventofcode.flashk.common.test.constants.TestFilename;
import com.adventofcode.flashk.common.test.constants.TestFolder;
import com.adventofcode.flashk.common.test.utils.Input;
import com.adventofcode.flashk.day20.PulsePropagation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GraphVisualizerTest {

    private static final  String INPUT_FOLDER = TestFolder.DAY_20;
    private static final  String OUTPUT_FOLDER = TestFolder.DAY_20 + "/jgrapht";

    @ParameterizedTest
    @MethodSource("inputFileProvider")
    void paintTest(String inputFile, String expectedOutputFile) {

        // Arrange
        List<String> inputs = Input.readStringLines(INPUT_FOLDER, inputFile);
        List<String> outputs = Input.readStringLines(OUTPUT_FOLDER, expectedOutputFile);
        String expectedOutput = String.join("\r\n", outputs);
        expectedOutput += "\r\n";

        // Prepare graph
        PulsePropagation pulsePropagation = new PulsePropagation(inputs);
        GraphVisualizer graphVisualizer = new GraphVisualizer(pulsePropagation.getGraph());

        // Act
        String result = graphVisualizer.paint(false);

        // Assert
        assertEquals(expectedOutput, result);

    }

    @Test
    void paintAdjustedGraph() {

        // Arrange
        List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);
        List<String> outputs = Input.readStringLines(OUTPUT_FOLDER, "input_modified_part2.dot");
        String lineSeparator = System.lineSeparator();
        String expectedOutput = String.join(lineSeparator, outputs) + lineSeparator;

        // Prepare graph
        PulsePropagation pulsePropagation = new PulsePropagation(inputs);
        pulsePropagation.solveB();
        GraphVisualizer graphVisualizer = new GraphVisualizer(pulsePropagation.getGraph());

        // Act
        String result = graphVisualizer.paint(false);

        // Assert
        assertEquals(expectedOutput, result);


    }

    private static Stream<Arguments> inputFileProvider() {

        return Stream.of(
        Arguments.of("sample.input", "sample.dot"),
                Arguments.of("sample_2.input", "sample_2.dot"),
                Arguments.of("data.input", "input.dot"));

    }
}