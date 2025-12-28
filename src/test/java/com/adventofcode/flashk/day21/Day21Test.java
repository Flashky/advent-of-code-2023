package com.adventofcode.flashk.day21;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.adventofcode.flashk.common.test.constants.TestDisplayName;
import com.adventofcode.flashk.common.test.constants.TestFilename;
import com.adventofcode.flashk.common.test.constants.TestFolder;
import com.adventofcode.flashk.common.test.constants.TestTag;
import com.adventofcode.flashk.common.test.utils.Input;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName(TestDisplayName.DAY_21)
@TestMethodOrder(OrderAnnotation.class)
class Day21Test {

	private static final String INPUT_FOLDER = TestFolder.DAY_21;


	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	void testSolvePart1Sample() {

		System.out.print("1 | sample | ");

		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		StepCounter stepCounter = new StepCounter(inputs, true);
		long result = stepCounter.solveA(6);

		assertEquals(16, result);

	}

	@Test
	@Order(2)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_ONE_INPUT)
	void testSolvePart1Input() {

		System.out.print("1 | input  | ");

		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, TestFilename.INPUT_FILE);

		StepCounter stepCounter = new StepCounter(inputs, true);
		long result = stepCounter.solveA(64);

		assertEquals(3733, result);

	}

	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE + " - 7x7 test matrix")
	void testSolvePart2Sample() {

		// Read input file

		// WARNING: I ignore the original sample file as it is not useful to test the real input
		// Instead of it, I use this special crafter test input.

		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, "sample_7x7.input");

		StepCounter stepCounter = new StepCounter(inputs, true);
		long result = stepCounter.solveB(31);

		assertEquals(848, result);
	}

	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	void testSolvePart2Input() {

		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, TestFilename.INPUT_FILE);

		StepCounter stepCounter = new StepCounter(inputs, false);
		long result = stepCounter.solveB(26501365);

		assertEquals(617729401414635L, result);
	}

	@ParameterizedTest
	@MethodSource("provideMapArguments")
	void mapStepTest(String filename, int steps, int expected){
		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, filename);

		StepCounter stepCounter = new StepCounter(inputs, false);
		assertEquals(expected, stepCounter.solveA(steps));

	}

	private static Stream<Arguments> provideMapArguments() {

		return Stream.of(
				// Odd map (only center)
				Arguments.of("data.input", 0, 1),
				Arguments.of("data.input", 1, 4),
				Arguments.of("data.input", 194, 7541),
				Arguments.of("data.input", 195, 7553),
				Arguments.of("data.input", 196, 7541),
				Arguments.of("data.input", 197, 7553),


				// Odd maps (excluding center)
				Arguments.of("data_left_s_odd.input", 0, 1),
				Arguments.of("data_left_s_odd.input", 1, 3),
				Arguments.of("data_left_s_odd.input", 194, 7553),
				Arguments.of("data_left_s_odd.input", 195, 7541),
				Arguments.of("data_left_s_odd.input", 196, 7553),
				Arguments.of("data_left_s_odd.input", 197, 7541),
				Arguments.of("data_right_s_odd.input", 0, 1),
				Arguments.of("data_right_s_odd.input", 1, 3),
				Arguments.of("data_right_s_odd.input", 194, 7553),
				Arguments.of("data_right_s_odd.input", 195, 7541),
				Arguments.of("data_right_s_odd.input", 196, 7553),
				Arguments.of("data_right_s_odd.input", 197, 7541),
				Arguments.of("data_top_s_odd.input", 0, 1),
				Arguments.of("data_top_s_odd.input", 1, 3),
				Arguments.of("data_top_s_odd.input", 194, 7553),
				Arguments.of("data_top_s_odd.input", 195, 7541),
				Arguments.of("data_top_s_odd.input", 196, 7553),
				Arguments.of("data_top_s_odd.input", 197, 7541),
				Arguments.of("data_bottom_s_odd.input", 0, 1),
				Arguments.of("data_bottom_s_odd.input", 1, 3),
				Arguments.of("data_bottom_s_odd.input", 194, 7553),
				Arguments.of("data_bottom_s_odd.input", 195, 7541),
				Arguments.of("data_bottom_s_odd.input", 196, 7553),
				Arguments.of("data_bottom_s_odd.input", 197, 7541)

		);
	}


}