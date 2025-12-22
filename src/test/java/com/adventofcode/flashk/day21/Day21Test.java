package com.adventofcode.flashk.day21;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
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
import com.adventofcode.flashk.common.test.utils.PuzzleTest;
import com.adventofcode.flashk.common.test.utils.Input;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static java.lang.IO.println;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName(TestDisplayName.DAY_21)
@TestMethodOrder(OrderAnnotation.class)
public class Day21Test extends PuzzleTest {

	private final static String INPUT_FOLDER = TestFolder.DAY_21;

	@BeforeAll
	public static void beforeAll() {
		//Timer.printHeader(TestDisplayName.DAY_21);
	}


	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	public void testSolvePart1Sample() {

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
	public void testSolvePart1Input() {

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
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	public void testSolvePart2Sample() {

		System.out.print("2 | sample | ");

		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		StepCounter stepCounter = new StepCounter(inputs, true);
		long result = stepCounter.solveA(65);

		System.out.println("R: "+result);
	}

	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	public void testSolvePart2Input() {

		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, TestFilename.INPUT_FILE);

		StepCounter stepCounter = new StepCounter(inputs, false);
		long result = stepCounter.solveB(26501365);

		assertEquals(617729401414635L, result);
	}

	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT + " - 7x7 test")
	void testSolvePart2Sample7x7() {

		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, "sample_7x7.input");

		StepCounter stepCounter = new StepCounter(inputs, true);
		long result = stepCounter.solveB(31);

		assertEquals(848, result);

	}

	@Test
	public void testInputMapRightOddSteps() {

		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, "data_right_s_odd.input");

		StepCounter stepCounter = new StepCounter(inputs, true);
		long result = stepCounter.solveA(1);

		System.out.println("R: "+result);
		// 0   -> 1    - Global step: 66
		// 1   -> 3    - Global step: 67
		// 65  -> 1941 - Se toca casilla central.
		// 129 -> 5643
		// 130 -> 5676 - Se toca extremo izquierdo.
		// 131 -> 5773
		// 132 -> 5804
		// 194 -> 7553
		// 195 -> 7541
		// 196 -> 7553 - Primera repetición de patrón impar. Paso relativo 196 => Paso 261 global (196+65)
		// 197 -> 7541 First odd pattern repetition

	}

	@Test
	public void testInputMapLeftOddSteps() {

		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, "data_left_s_odd.input");

		StepCounter stepCounter = new StepCounter(inputs, true);
		long result = stepCounter.solveA(130);

		System.out.println("R: "+result);
		// 0 -> 1 (global step: 66)
		// 1 -> 3 (global step: 67)
		// 65  -> 1926 - Se toca casilla central.
		// 129 -> 5631
		// 130 -> 5712 - Se toca extremo derecho.
		// 190 -> 7543
		// 191 -> 7533
		// 192 -> 7549
		// 193 -> 7539
		// 194 -> 7553
		// 195 -> 7541
		// 196 -> 7553 First odd pattern repetition (131 + 65 = 196 - Global step = 196 + 66 = 262)
		// 197 -> 7541 First even pattern repetition

	}

	@Test
	public void testInputMapTopOddSteps() {

		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, "data_top_s_odd.input");

		StepCounter stepCounter = new StepCounter(inputs, true);
		long result = stepCounter.solveA(130);

		System.out.println("R: "+result);
		// step -> result
		// 0   -> 1    - Global step: 65
		// 1   -> 3    - Global step: 66
		// 64  -> 1880
		// 65  -> 1961 - Se toca la casilla central.
		// 129 -> 5638
		// 130 -> 5701 - Se toca extremo inferior.
		// 131 -> 5768
		// 132 -> 5829
		// 194 -> 7553
		// 195 -> 7541
		// 196 -> 7553 First odd pattern repetition (131 + 65 = 196 - Global step = 196 + 66 = 262)
		// 197 -> 7541 First even pattern repetition

	}

	@Test
	public void testInputMapBottomOddSteps() {

		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, "data_bottom_s_odd.input");

		StepCounter stepCounter = new StepCounter(inputs, true);
		long result = stepCounter.solveA(130);

		System.out.println("R: "+result);
		// step -> result
		// 0   -> 1    - Global step: 66
		// 1   -> 3    - Global step: 67
		// 64  -> 1884
		// 65  -> 1928 - Se toca la casilla central.
		// 129 -> 5636
		// 130 -> 5687 - Se toca extremo superior.
		// 131 -> 5766
		// 132 -> 5815
		// 194 -> 7553
		// 195 -> 7541
		// 196 -> 7553 First odd pattern repetition (131 + 65 = 196 - Global step = 196 + 66 = 262)
		// 197 -> 7541 First even pattern repetition

	}


	@Test
	public void testInputMapRightStepsE() {

		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, "data_right_s_even.input");

		StepCounter stepCounter = new StepCounter(inputs, true);
		long result = stepCounter.solveA(131);

		System.out.println("R: "+result);
		// 0   -> 2    - Global step: 65
		// 1   -> 5    - Global step: 66
		// 65  -> 1989
		// 66  -> 2038 - Se toca casilla central.
		// 129 ->
		// 130 ->
		// 131 -> 5762 - Se toca extremo lateral
		// 132 ->
		// 194 -> 7541
		// 195 -> 7553
		// 196 -> 7541 - Primera repetición de patrón impar. Paso relativo 196 => Paso 261 global (196+65)
		// 197 -> 7553 - First odd pattern repetition

	}

	@Test
	void grid7x7CornerTrapezoid() {

		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, "7x7_empty.input");

		StepCounter stepCounter = new StepCounter(inputs, true);
		long result = stepCounter.solveA(12);
		println("R: "+result);

	}

	@Test
	void grid7x7CornerTrapezoidFilled() {

		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, "7x7_bottom_left.input");

		StepCounter stepCounter = new StepCounter(inputs, true);
		long result = stepCounter.solveA(9);

		// En el trapezoide 7x7 hace falta dar 9 pasos, o bien dar 7 pasos y flipear parity.
		println("R: "+result);

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

				// Even maps
				/*
				Arguments.of("data_left_s_even.input", 0, 2),
				Arguments.of("data_left_s_even.input", 1, 5),
				Arguments.of("data_left_s_even.input", 194, 7541),
				Arguments.of("data_left_s_even.input", 195, 7553),
				Arguments.of("data_left_s_even.input", 196, 7541),
				Arguments.of("data_left_s_even.input", 197, 7553),
				Arguments.of("data_right_s_even.input", 0, 2),
				Arguments.of("data_right_s_even.input", 1, 5),
				Arguments.of("data_right_s_even.input", 194, 7541),
				Arguments.of("data_right_s_even.input", 195, 7553),
				Arguments.of("data_right_s_even.input", 196, 7541),
				Arguments.of("data_right_s_even.input", 197, 7553),
				Arguments.of("data_top_s_even.input", 0, 2),
				Arguments.of("data_top_s_even.input", 1, 5),
				Arguments.of("data_top_s_even.input", 194, 7541),
				Arguments.of("data_top_s_even.input", 195, 7553),
				Arguments.of("data_top_s_even.input", 196, 7541),
				Arguments.of("data_top_s_even.input", 197, 7553),
				Arguments.of("data_bottom_s_even.input", 0, 2),
				Arguments.of("data_bottom_s_even.input", 1, 5),
				Arguments.of("data_bottom_s_even.input", 194, 7541),
				Arguments.of("data_bottom_s_even.input", 195, 7553),
				Arguments.of("data_bottom_s_even.input", 196, 7541),
				Arguments.of("data_bottom_s_even.input", 197, 7553),

				 */

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