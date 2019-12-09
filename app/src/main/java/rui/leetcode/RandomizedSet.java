package rui.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

//不会
public class RandomizedSet {
    /**
     * Initialize your data structure here.
     */

    private Set<Integer> set;

    public RandomizedSet() {
        set = new HashSet<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        return set.add(val);
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        return set.remove(val);
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        Random random = new Random();
        return random.nextInt();
    }

    public static void main(String[] args) {

    }
}
