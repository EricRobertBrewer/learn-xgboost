package org.familysearch.example.name;

/**
 * Measure the number of edits (deletions, insertions, substitutions, exchanges) from one word to another.
 */
public interface EditDistance {
  /**
   * Get the edit distance between two strings.
   * The property `dist(a, b)` == `dist(b, a)` should always hold true.
   * Case is ignored.
   * @param a Any string.
   * @param b Any other string.
   * @param ignoreCase When set to `true`, case will be ignored.
   * @return The edit distance between the strings.
   */
  int get(String a, String b, boolean ignoreCase);
}
