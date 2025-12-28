package com.adventofcode.flashk.day05;

import java.util.List;

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
import com.adventofcode.flashk.common.test.utils.Input;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName(TestDisplayName.DAY_05)
@TestMethodOrder(OrderAnnotation.class)
class Day05Test {

	private static final String INPUT_FOLDER = TestFolder.DAY_05;
	
	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	void testSolvePart1Sample() {
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		YouGiveASeedAFertilizer puzzle = new YouGiveASeedAFertilizer(inputs);
		assertEquals(35,puzzle.solveA());
		
	}

	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_DEBUG)
	@Disabled("(Disabled) Part 1 Sample - Reason: Sample test just for analysing the behaviour for part 2")
	void testSolvePart1SampleTest() {

		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, "sample_test.input");

		YouGiveASeedAFertilizer puzzle = new YouGiveASeedAFertilizer(inputs);

		assertEquals(35,puzzle.solveA());

	}
	
	@Test
	@Order(2)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_ONE_INPUT)
	void testSolvePart1Input() {
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);

		YouGiveASeedAFertilizer puzzle = new YouGiveASeedAFertilizer(inputs);
		assertEquals(579439039L,puzzle.solveA());
		
	}
	
	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	void testSolvePart2Sample() {

		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		YouGiveASeedAFertilizer puzzle = new YouGiveASeedAFertilizer(inputs);

		assertEquals(46,puzzle.solveB());
	}
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	@Disabled("(Disabled) Part 2 Input - Reason: Solved via brute force. Requires 30 minutes to run.")
	void testSolvePart2Input() {
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);

		YouGiveASeedAFertilizer puzzle = new YouGiveASeedAFertilizer(inputs);

		assertEquals(7873084, puzzle.solveB());
		
	}

}
