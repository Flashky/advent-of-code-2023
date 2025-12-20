package com.adventofcode.flashk.day17;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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
import com.adventofcode.flashk.common.test.utils.Timer;
import com.adventofcode.flashk.common.test.utils.Input;

import static java.lang.IO.println;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName(TestDisplayName.DAY_17)
@TestMethodOrder(OrderAnnotation.class)
@Disabled
public class Day17Test extends PuzzleTest {

	private final static String INPUT_FOLDER = TestFolder.DAY_17;

	@BeforeAll
	public static void beforeAll() {
		Timer.printHeader(TestDisplayName.DAY_17);
	}


	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	public void testSolvePart1Sample() {

		// Read input file
		int[][] inputs = Input.read2DIntArray(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		ClumsyCrucible clumsyCrucible = new ClumsyCrucible(inputs);
		long result = clumsyCrucible.solveA();

		assertEquals(102, result);

	}

	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	public void testSolvePart1SingleSample() {

		// Read input file
		int[][] inputs = Input.read2DIntArray(INPUT_FOLDER, TestFilename.INPUT_FILE_SINGLE_SAMPLE);

		ClumsyCrucible clumsyCrucible = new ClumsyCrucible(inputs);
		long result = clumsyCrucible.solveA();

		assertEquals(7, result);

	}
	
	@Test
	@Order(2)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_ONE_INPUT)
	public void testSolvePart1Input() {
		

		// Read input file
		int[][] inputs = Input.read2DIntArray(INPUT_FOLDER, TestFilename.INPUT_FILE);

		ClumsyCrucible clumsyCrucible = new ClumsyCrucible(inputs);
		long result = clumsyCrucible.solveA();

		assertEquals(742, result);
	}
	
	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE + " - Example 1")
	public void testSolvePart2Sample() {
		

		// Read input file
		int[][] inputs = Input.read2DIntArray(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		ClumsyCrucible clumsyCrucible = new ClumsyCrucible(inputs);
		long result = clumsyCrucible.solveB();

		assertEquals(94, result);
	}

	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE + " - Example 2")
	public void testSolvePart2SingleSample() {


		// Read input file
		int[][] inputs = Input.read2DIntArray(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE_2);

		ClumsyCrucible clumsyCrucible = new ClumsyCrucible(inputs);
		long result = clumsyCrucible.solveB();

		assertEquals(71, result);
	}
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	public void testSolvePart2Input() {

		// Read input file
		int[][] inputs = Input.read2DIntArray(INPUT_FOLDER, TestFilename.INPUT_FILE);

		ClumsyCrucible clumsyCrucible = new ClumsyCrucible(inputs);
		long result = clumsyCrucible.solveB();

		println("Solution: " + result);

		// 917 -> too low
		
	}

}
