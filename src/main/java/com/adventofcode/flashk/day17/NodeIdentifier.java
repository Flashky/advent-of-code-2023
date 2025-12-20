package com.adventofcode.flashk.day17;

import static java.lang.IO.println;

import module java.base;
import com.adventofcode.flashk.common.Vector2;

public record NodeIdentifier(int x, int y, Vector2 dir, int steps) {
}
