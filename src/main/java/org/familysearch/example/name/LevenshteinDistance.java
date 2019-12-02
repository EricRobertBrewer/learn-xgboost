package org.familysearch.example.name;

/**
 * The Levenshtein edit distance algorithm.
 */
public class LevenshteinDistance implements EditDistance {

  /**
   * Will expand as necessary.
   */
  private int[][] d = { { 0 } };

  public LevenshteinDistance() {
  }

  /**
   * Considers only insertions, deletions, and substitutions of single characters.
   * Does not consider exchanging characters.
   * @param a A string.
   * @param b Another string.
   * @param ignoreCase When set to `true`, case will be ignored.
   * @return The edit distance by Levenshtein's algorithm from `a` to `b`.
   */
  @Override
  public int get(String a, String b, boolean ignoreCase) {
    // Ensure strings are non-null.
    if (a == null || a.length() == 0) {
      throw new IllegalArgumentException("`a` cannot be null or empty.");
    }
    if (b == null || b.length() == 0) {
      throw new IllegalArgumentException("`b` cannot be null or empty.");
    }
    // Optionally convert to lowercase.
    if (ignoreCase) {
      a = a.toLowerCase();
      b = b.toLowerCase();
    }
    // Check for equality.
    if (a.equals(b)) {
      return 0;
    }
    // Ensure `a.length()` <= `b.length()` as an optimization for the size of `d`.
    if (b.length() < a.length()) {
      String t = a;
      a = b;
      b = t;
    }
    // Extend the size of `d`, if necessary.
    if (d.length < a.length() + 1 || d[0].length < b.length() + 1) {
      final int iMax = Math.max(d.length, a.length() + 1);
      final int jMax = Math.max(d[0].length, b.length() + 1);
      int[][] d_ = new int[iMax][jMax];
      for (int i = 0; i < iMax; i++) {
        d_[i][0] = i;
      }
      for (int j = 1; j < jMax; j++) {
        d_[0][j] = j;
      }
      d = d_;
    }
    // Begin Levenshtein's algorithm.
    for (int i = 1; i < a.length() + 1; i++) {
      for (int j = 1; j < b.length() + 1; j++) {
        final int deletion = d[i - 1][j] + 1;
        final int insertion = d[i][j - 1] + 1;
        final int cost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
        final int substitution = d[i - 1][j - 1] + cost;
        d[i][j] = Math.min(deletion, Math.min(insertion, substitution));
      }
    }
    return d[a.length()][b.length()];
  }
}
