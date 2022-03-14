package org.rw.challenges.snip;

import java.util.ArrayList;
import java.util.List;


public class CaseDetails {
    public final int z;
    public final List<TrunksInfo> trunksInfos;

    private CaseDetails(int z, List<TrunksInfo> trunksInfos) {
        this.z = z;
        this.trunksInfos = trunksInfos;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        public int z = 0;
        private final List<TrunksInfo> trunksInfos = new ArrayList<>();

        public Builder z(int z) {
            this.z = z;
            return this;
        }

        public Builder trunksInput(TrunksInfo trunksInput) {
            this.trunksInfos.add(trunksInput);
            return this;
        }

        public CaseDetails build() {
            validate();
            return new CaseDetails(z, trunksInfos);
        }

        private void validate() {
            if (z < 0) {
                throw new IllegalStateException("Invalid z");
            }
            if (z != trunksInfos.size()) {
                throw new IllegalStateException("Invalid z");
            }
        }
    }

    public static class TrunksInfo {
        public final int e;
        public final int[] trunksLengths;

        private TrunksInfo(int e, int[] trunksLengths) {
            this.e = e;
            this.trunksLengths = trunksLengths;
        }

        public static TrunksInfoBuilder builder() {
            return new TrunksInfoBuilder();
        }
    }

    public static class TrunksInfoBuilder {
        public int e = 0;
        private final List<Integer> trunkLengths = new ArrayList<>();

        public TrunksInfoBuilder e(int e) {
            this.e = e;
            return this;
        }

        public TrunksInfoBuilder trunkLength(int trunkLength) {
            this.trunkLengths.add(trunkLength);
            return this;
        }

        public TrunksInfo build() {
            validate();
            return new TrunksInfo(e, trunkLengths.stream().mapToInt(i -> i).toArray());
        }

        private void validate() {
            if (e < 0) {
                throw new IllegalStateException("Invalid e");
            }
            if (e != trunkLengths.size()) {
                throw new IllegalStateException("Invalid amount of trunk lengths");
            }
            for (int trunkLength : trunkLengths) {
                if (trunkLength < 0) {
                    throw new IllegalStateException("Invalid trunkLength");
                }
            }
        }
    }
}
