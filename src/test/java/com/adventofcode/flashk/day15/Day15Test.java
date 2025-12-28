package com.adventofcode.flashk.day15;

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

@DisplayName(TestDisplayName.DAY_15)
@TestMethodOrder(OrderAnnotation.class)
class Day15Test {

	private static final String INPUT_FOLDER = TestFolder.DAY_15;

	@Test
	@Order(0)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SINGLE_SAMPLE)
	void testSolvePart1SingleSample() {

		LensLibrary lensLibrary = new LensLibrary("HASH");
		long result = lensLibrary.solveA();

		assertEquals(52, result);
	}

	@Test
	@Order(0)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SINGLE_SAMPLE)
	void testSolvePart1SingleValueRn() {

		LensLibrary lensLibrary = new LensLibrary("rn");
		long result = lensLibrary.solveA();

		assertEquals(0, result);
	}

	@Test
	@Order(0)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SINGLE_SAMPLE)
	void testSolvePart1SingleValueQp() {

		LensLibrary lensLibrary = new LensLibrary("qp");
		long result = lensLibrary.solveA();

		assertEquals(1, result);
	}

	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	void testSolvePart1Sample() {
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		LensLibrary lensLibrary = new LensLibrary(inputs.getFirst());
		long result = lensLibrary.solveA();

		assertEquals(1320, result);
	}
	
	@Test
	@Order(2)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_ONE_INPUT)
	void testSolvePart1Input() {
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);

		LensLibrary lensLibrary = new LensLibrary(inputs.getFirst());
		long result = lensLibrary.solveA();

		assertEquals(510792, result);

	}
	
	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	void testSolvePart2Sample() {
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		LensLibrary lensLibrary = new LensLibrary(inputs.getFirst());
		long result = lensLibrary.solveB();

		assertEquals(145, result);
	}
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	void testSolvePart2Input() {
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);

		LensLibrary lensLibrary = new LensLibrary(inputs.getFirst());
		long result = lensLibrary.solveB();

		assertEquals(269410, result);
		
	}




}
