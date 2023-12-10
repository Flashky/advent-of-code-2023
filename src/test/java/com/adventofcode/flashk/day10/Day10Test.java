package com.adventofcode.flashk.day10;

import java.util.List;

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

@DisplayName(TestDisplayName.DAY_10)
@TestMethodOrder(OrderAnnotation.class)
public class Day10Test extends PuzzleTest {

	private final static String INPUT_FOLDER = TestFolder.DAY_10;

	@BeforeAll
	public static void beforeAll() {
		Timer.printHeader(TestDisplayName.DAY_10);
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

		PipeMaze pipeMaze = new PipeMaze(inputs);
		int result = pipeMaze.solveA();

		assertEquals(4, result);
	}

	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE + " 2")
	public void testSolvePart1Sample2() {

		System.out.print("1 | sample | ");

		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE_2);

		PipeMaze pipeMaze = new PipeMaze(inputs);
		int result = pipeMaze.solveA();

		assertEquals(8, result);
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

		PipeMaze pipeMaze = new PipeMaze(inputs);
		int result = pipeMaze.solveA();

		assertEquals(6907, result);
	}
	
	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	public void testSolvePart2Sample() {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE_3);

		PipeMaze pipeMaze = new PipeMaze(inputs);
		int result = pipeMaze.solveB();

		assertEquals(4, result);
	}

	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE + " 2")
	public void testSolvePart2Sample2() {

		System.out.print("2 | sample | ");

		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE_4);

		PipeMaze pipeMaze = new PipeMaze(inputs);
		int result = pipeMaze.solveB();

		assertEquals(4, result);
	}

	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE + " 3")
	public void testSolvePart2Sample3() {

		System.out.print("2 | sample | ");

		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE_5);

		PipeMaze pipeMaze = new PipeMaze(inputs);
		int result = pipeMaze.solveB();

		assertEquals(8, result);

	}

	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE + " 4")
	public void testSolvePart2Sample4() {

		System.out.print("2 | sample | ");

		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE_6);

		PipeMaze pipeMaze = new PipeMaze(inputs);
		int result = pipeMaze.solveB();

		assertEquals(10, result);

	}

	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	public void testSolvePart2Input() {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		char[][] inputs = Input.read2DCharArray(INPUT_FOLDER, TestFilename.INPUT_FILE);

		PipeMaze pipeMaze = new PipeMaze(inputs);
		int result = pipeMaze.solveB();

		assertEquals(541, result);
		// 2908 -> Too high
	}

}
