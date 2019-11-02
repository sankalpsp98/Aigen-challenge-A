package com.sankalp.aigen.data;

import java.util.ArrayList;
import java.util.List;

public class dataWire {
    private static List<carData> resultsDataWire =new ArrayList<>();

    public static List<carData> getResultsDataWire() {
        return resultsDataWire;
    }

    public static void setResultsDataWire(List<carData> resultsDataWire) {
        dataWire.resultsDataWire = resultsDataWire;
    }
}
