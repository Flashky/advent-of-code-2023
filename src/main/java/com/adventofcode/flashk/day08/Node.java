package com.adventofcode.flashk.day08;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Node {

    private final String label;

    @Setter
    private Node left;

    @Setter
    private Node right;

    public Node(String label) {
        this.label = label;
    }

    public Node(String label, Node left, Node right) {
        this.label = label;
        this.left = left;
        this.right = right;
    }

    public Node move(char movement) {
        return movement == 'L' ? left : right;
    }

}
