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

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		long result = clumsyCrucible.solve(new AdjacentStrategyCrucible(clumsyCrucible));

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
		long result = clumsyCrucible.solve(new AdjacentStrategyCrucible(clumsyCrucible));

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
		long result = clumsyCrucible.solve(new AdjacentStrategyCrucible(clumsyCrucible));

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
		long result = clumsyCrucible.solve(new AdjacentStrategyUltraCrucible(clumsyCrucible));

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
		long result = clumsyCrucible.solve(new AdjacentStrategyUltraCrucible(clumsyCrucible));

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
		long result = clumsyCrucible.solve(new AdjacentStrategyUltraCrucible(clumsyCrucible));

		assertEquals(918, result);
		
	}

	/**
	 * Targeted ultracrucible tests for part 2 movement rules:
	 * 1) You can't turn before 4 straight steps.
	 * 2) You can't go more than 10 straight steps.
	 * 3) A 4-step move in a new direction that would leave the grid is invalid.
	 */
	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE + " - cannot turn before 4 straight steps")
	public void testPart2CannotTurnBeforeFourSteps() {

		/*
			Grid layout:

			    0  1  2  3  4
			0 [ 1, 9, 9, 9, 1 ]
			1 [ 1, 1, 1, 1, 1 ]

			Start at (0,0), goal at (0,4).

			Ultracrucible rules:
			- At least 4 steps are required.
			- At most 10 straight steps are allowed.

			1. Right first path is legal: (0,0) → (0,1) → (0,2) → (0,3) → (0,4)  [4 steps, cost 9+9+9+1 = 28]
			2. However, at the next step must turn to the right. There is no way o reach the end node (1,4), as it is
			   at 1 step (less than 4 minimum steps on any direction).

		 */

		int[][] grid = {
				{1, 9, 9, 9, 1},
				{1, 1, 1, 1, 1}
		};

		ClumsyCrucible clumsyCrucible = new ClumsyCrucible(grid);
		long result = clumsyCrucible.solve(new AdjacentStrategyUltraCrucible(clumsyCrucible));

		// When there is no solution, the algorithm will return Long.MAX value as there won't be any path to the bottom-right cell.
		assertEquals(Long.MAX_VALUE, result);
	}

	@Test
	@Order(5)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE + " - reject paths requiring >10 straight steps")
	public void testPart2RejectsMoreThanTenStraightSteps() {

		/*
			Single-row grid layout:

			    0  1  2  3  4  5  6  7  8  9 10 11
			0 [ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ]

			Start at (0,0), goal at (0,11).

			Ultracrucible rules:
			- At least 4 steps are required.
			- At most 10 straight steps are allowed.

			1. Right first path is legal: (0,0) → (10,0)
			2. Once at (10,0), it cannot move backwards, and cannot move just one step straight.
			3. The algorithm must return the total totalHeatloss value the bottom-right cell.

		 */

		int[][] grid = new int[1][12];
		for (int i = 0; i < 12; i++) {
			grid[0][i] = 1;
		}

		ClumsyCrucible clumsyCrucible = new ClumsyCrucible(grid);
		long result = clumsyCrucible.solve(new AdjacentStrategyUltraCrucible(clumsyCrucible));

		// When there is no solution, the algorithm will return Long.MAX value as there won't be any path to the bottom-right cell.
		assertEquals(Long.MAX_VALUE, result);
	}

	@Test
	@Order(6)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE + " - 4-step move that would leave grid is disallowed")
	public void testPart2FourStepMoveLeavingGridIsDisallowed() {

		// 3x3 grid: no initial 4-step straight move is possible in any direction
		int[][] grid = {
				{1, 1, 1},
				{1, 9, 1},
				{1, 1, 1}
		};

		ClumsyCrucible clumsyCrucible = new ClumsyCrucible(grid);
		long result = clumsyCrucible.solve(new AdjacentStrategyUltraCrucible(clumsyCrucible));

		// When there is no solution, the algorithm will return Long.MAX value as there won't be any path to the bottom-right cell.
		assertEquals(Long.MAX_VALUE, result);
	}


}
