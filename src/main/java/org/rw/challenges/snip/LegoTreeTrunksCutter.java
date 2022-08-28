package org.rw.challenges.snip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.rw.challenges.snip.CaseDetails.Builder;
import org.rw.challenges.snip.CaseDetails.TrunksInfo;
import org.rw.challenges.snip.CaseDetails.TrunksInfoBuilder;
import org.rw.challenges.snip.util.ArrayPermutationsHelper;
import org.rw.challenges.common.InputReader;
import org.rw.challenges.common.SimpleOutputWriter;


public class LegoTreeTrunksCutter {

    private static final int CUT_LENGTH = 3;
    private static final Map<Integer, Integer> CUT_PROFIT = Map.of( //
        1, -1, //
        2, 3, //
        3, 1 //
    );

    public static void main(String[] args) throws Exception {
        List<CaseDetails> caseInputs = readInputCases();
        new LegoTreeTrunksCutter().run(caseInputs);
    }

    private static List<CaseDetails> readInputCases() throws IOException {
        List<CaseDetails> caseInputs = new ArrayList<>();
        try (InputReader inputReader = new InputReader()) {
            while (true) {
                CaseDetails caseInput = readCaseDetails(inputReader);
                if (caseInput == null) {
                    break;
                }
                caseInputs.add(caseInput);
            }
        }
        return caseInputs;
    }

    private static CaseDetails readCaseDetails(InputReader inputReader) throws IOException {
        Builder caseDetailsBuilder = CaseDetails.builder();

        caseDetailsBuilder.z(inputReader.nextInt());
        if (caseDetailsBuilder.z == 0) {
            return null;
        }
        for (int s = 0; s < caseDetailsBuilder.z; s++) {
            TrunksInfoBuilder trunksInfoBuilder = TrunksInfo.builder();
            trunksInfoBuilder.e(inputReader.nextInt());
            for (int t = 0; t < trunksInfoBuilder.e; t++) {
                trunksInfoBuilder.trunkLength(inputReader.nextInt());
            }
            caseDetailsBuilder.trunksInput(trunksInfoBuilder.build());
        }

        return caseDetailsBuilder.build();
    }

    public void run(List<CaseDetails> caseInputs) {
        int caseNumber = 1;
        for (CaseDetails caseInput : caseInputs) {
            SimpleOutputWriter.write("Case " + caseNumber);
            CaseProcessingResult caseProcessingResult = processCase(caseInput);
            SimpleOutputWriter.write("Max profit: " + caseProcessingResult.maxProfit);
            SimpleOutputWriter.write("Order: " + buildOrderingResultString(caseProcessingResult.ordering));
            caseNumber++;
        }
    }

    protected static CaseProcessingResult processCase(CaseDetails caseDetails) { // can be unit tested now!
        int totalMaxProfit = 0;
        List<List<List<Integer>>> ordering = new ArrayList<>();
        for (TrunksInfo trunksInfo : caseDetails.trunksInfos) {
            int maxProfit = Integer.MIN_VALUE;
            List<List<Integer>> orderPermutations = ArrayPermutationsHelper.findPermutations(trunksInfo.trunksLengths);
            Map<Integer, List<List<Integer>>> orderingByProfitMap = new HashMap<>();
            for (List<Integer> orderPermutation : orderPermutations) {
                List<Integer> cuts = cut(orderPermutation);
                int profit = calculateProfit(cuts);
                if (profit >= maxProfit) {
                    maxProfit = profit;
                    orderingByProfitMap.computeIfAbsent(maxProfit, k -> new ArrayList<>()).add(orderPermutation);
                }
            }
            totalMaxProfit += maxProfit;
            ordering.add(orderingByProfitMap.get(maxProfit));
        }
        return new CaseProcessingResult(totalMaxProfit, ordering);
    }

    protected static List<Integer> cut(List<Integer> trunkLengths) {
        List<Integer> cuts = new ArrayList<>();
        int remainder = 0;
        for (int trunkLength : trunkLengths) {
            if (trunkLength + remainder < CUT_LENGTH) {
                cuts.add(trunkLength);
                remainder += trunkLength;
            } else {
                cuts.add(CUT_LENGTH - remainder);
                remainder += (trunkLength - CUT_LENGTH);
                while (remainder > 0) {
                    cuts.add(remainder);
                    remainder -= CUT_LENGTH;
                }
            }
        }
        return cuts;
    }

    protected static int calculateProfit(List<Integer> cuts) {
        return cuts.stream() //
                   .mapToInt(CUT_PROFIT::get) //
                   .sum();
    }

    protected static String buildOrderingResultString(List<List<List<Integer>>> ordering) {
        return ordering.stream() //
                       .map(LegoTreeTrunksCutter::listJoin) //
                       .collect(Collectors.joining(", "));
    }

    public static <T extends List<?>> String listJoin(T list) {
        return list.stream() //
                   .map(String::valueOf) //
                   .collect(Collectors.joining(" "));
    }
}
