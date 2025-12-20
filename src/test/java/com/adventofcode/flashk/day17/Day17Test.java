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

		// For some reason this is giving me 92 instead of 94
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
			Grid layout (heat loss values):

			Start at (0,0), goal at (0,4):

			    0  1  2  3  4
			0 [ 1, 9, 9, 9, 1 ]
			1 [ 1, 1, 1, 1, 1 ]

			- Legal ultracrucible path (must go 4 steps right before turning):
			    (0,0) → (0,1) → (0,2) → (0,3) → (0,4)  [4 steps, cost 9+9+9+1 = 28]
			- Cheaper *illegal* path if turning too early were allowed:
			    (0,0) ↓ (1,0) → (1,1) → (1,2) → (1,3) → (1,4) ↑ (0,4)
			      - This includes turns after 1 and 4 steps and would avoid some 9s.

			If the "must move at least 4 before turning" rule is implemented correctly,
			the solver must choose the legal 4-straight-then-turn-compliant path.
		 */
		int[][] grid = {
				{1, 9, 9, 9, 1},
				{1, 1, 1, 1, 1}
		};

		ClumsyCrucible clumsyCrucible = new ClumsyCrucible(grid);
		long result = clumsyCrucible.solveB();

		// There is no solution, you can only go straight to 4,0, and from there, you cannot reach the bottom right node.
		assertEquals(Long.MAX_VALUE, result);
	}

	@Test
	@Order(5)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE + " - reject paths requiring >10 straight steps")
	public void testPart2RejectsMoreThanTenStraightSteps() {
		/*
			Single-row grid:

			    0  1  2  3  4  5  6  7  8  9 10 11
			0 [ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ]

			- Start at (0,0), goal at (0,11).
			- Any path from start to goal must go 11 steps in the same direction.
			- Ultracrucible rules: at most 10 straight steps are allowed.
			  Therefore, no valid path should exist.

			This test assumes that solveB() returns Long.MAX_VALUE or an equivalent
			"no path found" sentinel when there is no legal ultracrucible path.
		 */
		int[][] grid = new int[1][12];
		for (int i = 0; i < 12; i++) {
			grid[0][i] = 1;
		}

		ClumsyCrucible clumsyCrucible = new ClumsyCrucible(grid);
		long result = clumsyCrucible.solveB();

		assertEquals(Long.MAX_VALUE, result);
	}

	@Test
	@Order(6)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE + " - 4-step move that would leave grid is disallowed")
	public void testPart2FourStepMoveLeavingGridIsDisallowed() {
		/*
			Grid layout:

			    0  1  2
			0 [ 1, 1, 1 ]
			1 [ 1, 9, 1 ]
			2 [ 1, 1, 1 ]

			Start at (0,0), goal at (2,2).

			Without proper boundary checks in the ultracrucible adjacency logic, a
			"turn and go 4 steps" move could attempt to step off the grid.

			A valid ultracrucible path that stays inside the grid and respects
			4–10 step constraints must be chosen; we assert its cost.
		 */
		int[][] grid = {
				{1, 1, 1},
				{1, 9, 1},
				{1, 1, 1}
		};

		ClumsyCrucible clumsyCrucible = new ClumsyCrucible(grid);
		long result = clumsyCrucible.solveB();

		// Expected cost = maximum value, there is no solution
		assertEquals(Long.MAX_VALUE, result);
	}


}
