package com.adventofcode.flashk.day24;

import java.util.List;

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

@DisplayName(TestDisplayName.DAY_24)
@TestMethodOrder(OrderAnnotation.class)
class Day24Test {

	private static final String INPUT_FOLDER = TestFolder.DAY_24;

	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	void testSolvePart1Sample() {
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		NeverTellMeTheOdds neverTellMeTheOdds = new NeverTellMeTheOdds(inputs, true);
		long result = neverTellMeTheOdds.solveA(7, 27);

		assertEquals(2, result);

	}
	
	@Test
	@Order(2)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_ONE_INPUT)
	void testSolvePart1Input() {
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);

		NeverTellMeTheOdds neverTellMeTheOdds = new NeverTellMeTheOdds(inputs, true);
		long result = neverTellMeTheOdds.solveA(200000000000000L, 400000000000000L);

		assertEquals(17244, result);
		
	}
	
	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	void testSolvePart2Sample() {
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		NeverTellMeTheOdds neverTellMeTheOdds = new NeverTellMeTheOdds(inputs, false);
		long result = neverTellMeTheOdds.solveB();

		assertEquals(47, result);

	}
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	void testSolvePart2Input() {

		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);

		NeverTellMeTheOdds neverTellMeTheOdds = new NeverTellMeTheOdds(inputs, false);
		long result = neverTellMeTheOdds.solveB();

		assertEquals(1025019997186820L, result);

	}

}
