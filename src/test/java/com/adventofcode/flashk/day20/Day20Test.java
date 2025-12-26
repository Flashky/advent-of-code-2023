package com.adventofcode.flashk.day20;

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

@DisplayName(TestDisplayName.DAY_20)
@TestMethodOrder(OrderAnnotation.class)
class Day20Test {

	private static final String INPUT_FOLDER = TestFolder.DAY_20;

	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE + " - Example 1 (single press)")
	void testSolvePart1SinglePush() {

		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		PulsePropagation pulsePropagation = new PulsePropagation(inputs);
		long result = pulsePropagation.solveA(1);

		assertEquals(32, result);

	}

	
	@Test
	@Order(2)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE + " - Example 1 (1000 presses)")
	void testSolvePart1Sample() {
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		PulsePropagation pulsePropagation = new PulsePropagation(inputs);
		long result = pulsePropagation.solveA(1000);

		assertEquals(32000000, result);
	}

	@Test
	@Order(3)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE + " - Example 2 (1000 presses)")
	void testSolvePart1Sample2() {

		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE_2);

		PulsePropagation pulsePropagation = new PulsePropagation(inputs);
		long result = pulsePropagation.solveA(1000);

		assertEquals(11687500, result);
	}
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_ONE_INPUT)
	void testSolvePart1Input() {
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);

		PulsePropagation pulsePropagation = new PulsePropagation(inputs);
		long result = pulsePropagation.solveA(1000);

		assertEquals(763500168, result);

	}
	
	@Test
	@Order(5)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	void testSolvePart2Input() {
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);

		PulsePropagation pulsePropagation = new PulsePropagation(inputs);
		long result = pulsePropagation.solveB();

		assertEquals(207652583562007L, result);
	}

}
