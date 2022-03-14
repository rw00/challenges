package org.rw.challenges.snip;

import java.util.List;


public class CaseProcessingResult {
    public final int maxProfit;
    public final List<List<List<Integer>>> ordering;

    public CaseProcessingResult(int maxProfit, List<List<List<Integer>>> ordering) {
        this.maxProfit = maxProfit;
        this.ordering = ordering;
    }
}
