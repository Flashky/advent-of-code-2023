package com.adventofcode.flashk.day17;

import com.adventofcode.flashk.common.Vector2;
import org.apache.commons.lang3.tuple.ImmutablePair;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName(TestDisplayName.DAY_17)
@TestMethodOrder(OrderAnnotation.class)
public class Day17Test extends PuzzleTest {

	private final static String INPUT_FOLDER = TestFolder.DAY_17;

	@BeforeAll
	public static void beforeAll() {
		Timer.printHeader(TestDisplayName.DAY_17);
	}

	@Test
	void testSets() {
		Tile tile = new Tile(2,2,2);
		tile.visit(ImmutablePair.of(Vector2.left(), 1));
		tile.visit(ImmutablePair.of(Vector2.left(), 2));
		tile.visit(ImmutablePair.of(Vector2.left(), 3));
		tile.visit(ImmutablePair.of(Vector2.right(), 1));
		tile.visit(ImmutablePair.of(Vector2.right(), 2));
		tile.visit(ImmutablePair.of(Vector2.right(), 3));

		assertTrue(tile.isVisited(ImmutablePair.of(Vector2.left(),1)));
		assertTrue(tile.isVisited(ImmutablePair.of(Vector2.left(),2)));
		assertTrue(tile.isVisited(ImmutablePair.of(Vector2.left(),3)));
		assertTrue(tile.isVisited(ImmutablePair.of(Vector2.right(),1)));
		assertTrue(tile.isVisited(ImmutablePair.of(Vector2.right(),2)));
		assertTrue(tile.isVisited(ImmutablePair.of(Vector2.right(),3)));
		assertFalse(tile.isVisited(ImmutablePair.of(Vector2.right(),4)));
	}
	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	public void testSolvePart1Sample() {
		
		System.out.print("1 | sample | ");
		
		// Read input file
		int[][] inputs = Input.read2DIntArray(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		ClumsyCrucibleRefactor clumsyCrucible = new ClumsyCrucibleRefactor(inputs);
		long result = clumsyCrucible.solveA();

		assertEquals(102, result);

	}

	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	public void testSolvePart1SingleSample() {

		System.out.print("1 | sample | ");

		// Read input file
		int[][] inputs = Input.read2DIntArray(INPUT_FOLDER, TestFilename.INPUT_FILE_SINGLE_SAMPLE);

		ClumsyCrucibleRefactor clumsyCrucible = new ClumsyCrucibleRefactor(inputs);
		long result = clumsyCrucible.solveA();

		assertEquals(7, result);

	}
	
	@Test
	@Order(2)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_ONE_INPUT)
	public void testSolvePart1Input() {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		int[][] inputs = Input.read2DIntArray(INPUT_FOLDER, TestFilename.INPUT_FILE);

		ClumsyCrucibleRefactor clumsyCrucible = new ClumsyCrucibleRefactor(inputs);
		long result = clumsyCrucible.solveA();

		System.out.println("R: "+result);
	}
	
	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	public void testSolvePart2Sample() {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		int[][] inputs = Input.read2DIntArray(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		
	}
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	public void testSolvePart2Input() {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		int[][] inputs = Input.read2DIntArray(INPUT_FOLDER, TestFilename.INPUT_FILE);
		
	}

}
