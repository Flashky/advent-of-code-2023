package com.adventofcode.flashk.day20;

import java.util.List;

import com.adventofcode.flashk.day20.jgrapht.PulsePropagationJGraphT;
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

@DisplayName(TestDisplayName.DAY_20)
@TestMethodOrder(OrderAnnotation.class)
public class Day20Test extends PuzzleTest {

	private final static String INPUT_FOLDER = TestFolder.DAY_20;

	@Test
	void paintGraph() {
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE_2);
		PulsePropagationJGraphT pulsePropagationJGraphT = new PulsePropagationJGraphT(inputs);
	}

	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE + " - Single push")
	public void testSolvePart1SinglePush() {

		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		PulsePropagationJGraphT pulsePropagation = new PulsePropagationJGraphT(inputs);
		long result = pulsePropagation.solveA(1);

		assertEquals(32, result);

	}

	
	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	public void testSolvePart1Sample() {
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);

		PulsePropagationJGraphT pulsePropagation = new PulsePropagationJGraphT(inputs);
		long result = pulsePropagation.solveA(1000);

		assertEquals(32000000, result);
	}

	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	void testSolvePart1Sample2() {

		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE_2);

		PulsePropagationJGraphT pulsePropagation = new PulsePropagationJGraphT(inputs);
		long result = pulsePropagation.solveA(1000);

		// TODO AQUI SE VE EL PROBLEMA!!
		/*

		button -low-> broadcaster
		broadcaster -low-> a
		a -high-> inv
		a -high-> con
		inv -low-> b
		b -high-> con
		con -low-> output
		con -low-> output

		EXPECTED:
		button -low-> broadcaster
		broadcaster -low-> a
		a -high-> inv
		a -high-> con
		inv -low-> b
		con -high-> output
		b -high-> con
		con -low-> output
		 */

		// Casualmente llega al mismo estado de salida, contando bien los pulsos,
		// pero el orden de los mismos y sus valores no son correctos.
		// Probablemente el error esté en el conjunction.
		assertEquals(11687500, result);
	}
	
	@Test
	@Order(2)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_ONE_INPUT)
	public void testSolvePart1Input() {
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);

		PulsePropagationJGraphT pulsePropagation = new PulsePropagationJGraphT(inputs);
		long result = pulsePropagation.solveA(1000);

		assertEquals(763500168, result);

	}
	
	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	@Disabled
	public void testSolvePart2Sample() {

		// Does not apply
		
	}
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	public void testSolvePart2Input() {
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);

		PulsePropagationJGraphT pulsePropagation = new PulsePropagationJGraphT(inputs);
		long result = pulsePropagation.solveB();

		assertEquals(207652583562007L, result);
	}

}
