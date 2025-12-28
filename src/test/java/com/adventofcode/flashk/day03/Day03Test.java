package com.adventofcode.flashk.day03;

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

@DisplayName(TestDisplayName.DAY_03)
@TestMethodOrder(OrderAnnotation.class)
class Day03Test {

	private static final String INPUT_FOLDER = TestFolder.DAY_03;

	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	void testSolvePart1Sample() {

		// Read input file
		char[][] input = Input.read2DCharArray(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		GearRatios gearRatios = new GearRatios(input);
		int result = gearRatios.solveA();

		assertEquals(4361, result);
	}
	
	@Test
	@Order(2)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_ONE_INPUT)
	void testSolvePart1Input() {

		// Read input file
		char[][] input = Input.read2DCharArray(INPUT_FOLDER, TestFilename.INPUT_FILE);

		GearRatios gearRatios = new GearRatios(input);
		long result = gearRatios.solveA();

		assertEquals(519444, result);

	}
	
	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	void testSolvePart2Sample() {

		// Read input file
		char[][] input = Input.read2DCharArray(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		GearRatios gearRatios = new GearRatios(input);
		int result = gearRatios.solveB();

		assertEquals(467835, result);

	}
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	void testSolvePart2Input() {

		// Read input file
		char[][] input = Input.read2DCharArray(INPUT_FOLDER, TestFilename.INPUT_FILE);

		GearRatios gearRatios = new GearRatios(input);
		int result = gearRatios.solveB();

		assertEquals(74528807, result);
	}

}
