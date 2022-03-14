package org.rw.challenges.snip.util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;


public class ArrayPermutationsHelper {
    private ArrayPermutationsHelper() {
    }

    public static List<List<Integer>> findPermutations(int[] arr) {
        List<List<Integer>> permutations = new ArrayList<>();
        Arrays.sort(arr);
        collectPermutations(permutations, new ArrayDeque<>(), arr, new boolean[arr.length]);
        return permutations;
    }

    private static void collectPermutations(List<List<Integer>> permutations, Deque<Integer> currentPermutation, int[] arr, boolean[] used) {
        if (currentPermutation.size() == arr.length) {
            permutations.add(new ArrayList<>(currentPermutation));
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (used[i] || (i > 0 && arr[i] == arr[i - 1] && !used[i - 1])) {
                    continue;
                }
                used[i] = true;
                currentPermutation.push(arr[i]);
                collectPermutations(permutations, currentPermutation, arr, used);
                currentPermutation.pop();
                used[i] = false; // backtrack
            }
        }
    }
}
