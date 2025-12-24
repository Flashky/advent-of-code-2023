package com.adventofcode.flashk.day12;

import java.util.List;

import com.adventofcode.flashk.day12.v2.SpringRecord;
import com.adventofcode.flashk.day12.v2.HotSpringsRefactor;
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
import com.adventofcode.flashk.common.test.utils.Input;

import static java.lang.IO.println;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName(TestDisplayName.DAY_12)
@TestMethodOrder(OrderAnnotation.class)
public class Day12Test extends PuzzleTest {

	private final static String INPUT_FOLDER = TestFolder.DAY_12;

	@BeforeAll
	public static void beforeAll() {
	}
	
	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	public void testSolvePart1Sample() {
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		HotSpringsRefactor hotSprings = new HotSpringsRefactor(inputs, false);
		long result = hotSprings.solve();

		assertEquals(21, result);
		
	}
	
	@Test
	@Order(2)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_ONE_INPUT)
	public void testSolvePart1Input() {
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);

		HotSpringsRefactor hotSprings = new HotSpringsRefactor(inputs, false);
		long result = hotSprings.solve();

		assertEquals(7195, result);

	}

	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	@Disabled
	public void testSolvePart1Pattern() {

		// Arrange & Act
		SpringRecord hotSpringsSpringRecordRefactor = new SpringRecord(".# 1", false);

		// Assert
		assertEquals(".#", hotSpringsSpringRecordRefactor.getRow());
		assertEquals(1, hotSpringsSpringRecordRefactor.getNumbers().size());
		assertEquals(1, hotSpringsSpringRecordRefactor.getNumbers().getFirst());

	}

	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	@Disabled
	public void testSolvePart2RepeatPattern() {

		// Arrange & Act
		SpringRecord hotSpringsSpringRecordRefactor = new SpringRecord(".# 1", true);

		// Assert
		assertEquals(".#?.#?.#?.#?.#", hotSpringsSpringRecordRefactor.getRow());
		assertEquals(5, hotSpringsSpringRecordRefactor.getNumbers().size());

		for(Integer number : hotSpringsSpringRecordRefactor.getNumbers()) {
			assertEquals(1, number);
		}
	}
	
	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	@Disabled
	public void testSolvePart2Sample() {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		HotSprings hotSprings = new HotSprings(inputs, true);
		long result = hotSprings.solveA();

		// TODO enable the test
		assertEquals(525152, result);

	}
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	@Disabled
	public void testSolvePart2Input() {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);
		HotSpringsRefactor hotSprings = new HotSpringsRefactor(inputs, true);

		long result = hotSprings.solve();
		println("Result: "+result);
	}

}
