package com.adventofcode.flashk.day08;

import org.apache.commons.math3.util.ArithmeticUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HauntedWasteland {

    private static final Pattern NETWORK_PATTERN = Pattern.compile("(\\w*) = \\((\\w*), (\\w*)\\)");
    private static final String STARTING_NODE = "AAA";
    private static final String ENDING_NODE = "ZZZ";
    private static final String GHOST_STARTING_NODE = "A";
    private static final String GHOST_ENDING_NODE = "Z";
    private final char[] movements;
    private Node startNode;
    private final Set<Node> ghostStartNodes = new HashSet<>();

    public HauntedWasteland(List<String> inputs) {

        // Initialize movements
        movements = inputs.get(0).toCharArray();

        // Initialize nodes
        Map<String, Node> createdNodes = new HashMap<>();
        for(String input : inputs) {
            Matcher matcher = NETWORK_PATTERN.matcher(input);
            if(matcher.find()) {
                String label = matcher.group(1);
                String leftLabel = matcher.group(2);
                String rightLabel = matcher.group(3);

                Node left = createdNodes.getOrDefault(leftLabel, new Node(leftLabel));
                createdNodes.putIfAbsent(leftLabel, left);

                Node right = createdNodes.getOrDefault(rightLabel, new Node(rightLabel));
                createdNodes.putIfAbsent(rightLabel, right);

                Node node = createdNodes.getOrDefault(label, new Node(label, left, right));
                createdNodes.putIfAbsent(label, node);

                node.setLeft(left);
                node.setRight(right);

                if(node.getLabel().equals(STARTING_NODE)) {
                    startNode = node;
                }

                if(node.getLabel().endsWith(GHOST_STARTING_NODE)) {
                    ghostStartNodes.add(node);
                }
            }
        }

    }

    public long solveA() {
        return pathfind(startNode, false);
    }

    public long solveB() {
        return ghostStartNodes.stream()
                .map(n -> pathfind(n, true))
                .reduce(1L, ArithmeticUtils::lcm);
    }

    private long pathfind(Node startNode, boolean isGhost) {
        long steps = 0;
        Node currentNode = startNode;
        int movementIndex = 0;
        do {
            char movement = movements[movementIndex++ % movements.length];
            currentNode = currentNode.move(movement);
            steps++;
        } while (!isEnd(currentNode, isGhost));

        return steps;

    }

    private boolean isEnd(Node node, boolean isGhost) {
        String label = node.getLabel();
        if(isGhost && label.endsWith(GHOST_ENDING_NODE)) {
            return true;
        }

        return !isGhost && label.equals(ENDING_NODE);
    }

}
