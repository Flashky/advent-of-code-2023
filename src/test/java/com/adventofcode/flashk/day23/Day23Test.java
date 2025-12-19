package com.adventofcode.flashk.day23;

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

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName(TestDisplayName.DAY_23)
@TestMethodOrder(OrderAnnotation.class)
public class Day23Test extends PuzzleTest {

	private final static String INPUT_FOLDER = TestFolder.DAY_23;

	@BeforeAll
	public static void beforeAll() {
		Timer.printHeader(TestDisplayName.DAY_23);
	}

	
	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	public void testSolvePart1Sample() {

		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		ALongWalk aLongWalk = new ALongWalk(inputs);
		long result = aLongWalk.solveA();

		assertEquals(94, result);
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

		ALongWalk aLongWalk = new ALongWalk(inputs);
		long result = aLongWalk.solveA();

		assertEquals(2294, result);
	}
	
	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	public void testSolvePart2Sample() {

		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		ALongWalkBacktrack aLongWalkBacktrack = new ALongWalkBacktrack(inputs);
		long result = aLongWalkBacktrack.solveB();

		assertEquals(154, result);

	}
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	@Disabled("Backtrack bruteforce solution. Takes 30 minutes to finish")
	public void testSolvePart2Input() {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, TestFilename.INPUT_FILE);

		ALongWalkBacktrack aLongWalkBacktrack = new ALongWalkBacktrack(inputs);
		long result = aLongWalkBacktrack.solveB();

		assertEquals(6418, result);
	}

}
