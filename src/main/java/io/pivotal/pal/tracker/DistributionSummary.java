package io.pivotal.pal.tracker;

import java.util.*;

public class DistributionSummary {
    private List<Integer> sizeList = new ArrayList();

    public void record(int size) {
        sizeList.add(size);
    }
}
