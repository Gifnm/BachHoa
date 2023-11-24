package com.spring.main.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.BillJPA;
import com.spring.main.model.Bill;

@Service
public class StatisticService {
    @Autowired
    private BillJPA billJpa;

    private String dateFormatString = "yyyy-MM-dd";

    public Map<String, Float> getMapRevenueEachMileStone(String startDate, String endDate, String typeMileStone,
            int storeId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        Timestamp stDate = null;
        Timestamp enDate = null;
        try {
            stDate = new Timestamp(dateFormat.parse(startDate).getTime());
            enDate = new Timestamp(dateFormat.parse(endDate).getTime());
            enDate.setHours(23);
            enDate.setMinutes(59);
            enDate.setSeconds(59);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Bill> bills = billJpa.findAllByTimeCreateBetween(stDate, enDate, storeId);
        Map<String, Float> revenueMap = new HashMap<>();
        for (Bill bill : bills) {
            String mileStone = getMileStone(bill.getTimeCreate(), typeMileStone);
            revenueMap.merge(mileStone, bill.getTotalAmount(), Float::sum);
            // System.out.println(revenueMap);
        }
        // Convert to TreeMap to automatically sort by keys
        Map<String, Float> sortedRevenueMap = new TreeMap<>(revenueMap);
        // Print the sorted map
        for (Map.Entry<String, Float> entry : sortedRevenueMap.entrySet()) {
            // System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        return sortedRevenueMap;
    }

    public String[] getListMileStone(String startDate, String endDate, String typeMileStone, int storeId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        Timestamp stDate = null;
        Timestamp enDate = null;
        try {
            stDate = new Timestamp(dateFormat.parse(startDate).getTime());
            enDate = new Timestamp(dateFormat.parse(endDate).getTime());
            enDate.setHours(23);
            enDate.setMinutes(59);
            enDate.setSeconds(59);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Bill> bills = billJpa.findAllByTimeCreateBetween(stDate, enDate, storeId);
        Set<String> mileStoneSet = new HashSet<>();

        for (Bill bill : bills) {
            mileStoneSet.add(getMileStone(bill.getTimeCreate(), typeMileStone));
        }
        List<String> mileStoneList = new ArrayList<>(mileStoneSet);
        Collections.sort(mileStoneList);
        return mileStoneList.toArray(new String[0]);
    }

    private String getMileStone(Timestamp timeCreate, String typeMileStone) {
        switch (typeMileStone) {
            case "day":
                return new SimpleDateFormat(dateFormatString).format(timeCreate);
            case "month":
                return new SimpleDateFormat("yyyy-MM").format(timeCreate);
            case "year":
                return new SimpleDateFormat("yyyy").format(timeCreate);
            default:
                throw new IllegalArgumentException("Unsupported typeMileStone: " + typeMileStone);
        }
    }

    public Float[] getListIncreaseRevenue(String startDate, String endDate, String typeMileStone, int storeId) {
        Map<String, Float> sortedRevenueMap = this.getMapRevenueEachMileStone(startDate, endDate, typeMileStone,
                storeId);

        // Cumulative revenue by milestone
        System.out.println("LIST INCREASE REVENUE: " + startDate + " - " + endDate + " - " + typeMileStone + "\n");
        float totalRevenue = 0;
        for (Map.Entry<String, Float> entry : sortedRevenueMap.entrySet()) {
            totalRevenue += entry.getValue();
            entry.setValue(totalRevenue);
            System.out.println(entry.getKey() + ": " + totalRevenue);
        }

        // Convert to List<Float>
        List<Float> revenueIncreaseList = new ArrayList<>(sortedRevenueMap.values());
        return revenueIncreaseList.toArray(new Float[0]);
    }

    public Float[] getListRevenue(String startDate, String endDate, String typeMileStone, int storeId) {
        Map<String, Float> sortedRevenueMap = this.getMapRevenueEachMileStone(startDate, endDate, typeMileStone,
                storeId);
        // Convert to List<Float>
        List<Float> revenueList = new ArrayList<>(sortedRevenueMap.values());
        return revenueList.toArray(new Float[0]);
    }

    // private Double calculateRevenue(Timestamp startDate, Timestamp endDate) {
    // List<Bill> bills = billJpa.findAllByTimeCreateBetween(startDate, startDate);
    // for (Bill bill : bills) {
    // List<Object[]> result = billJpa.calculateTotalAmountByDateRange(startDate,
    // endDate);
    // }
    // Double totalRevenue = 0.0;

    // for (Object[] row : result) {
    // // Assuming the query returns [Date, Double] for each row
    // Date date = (Date) row[0];
    // Double revenue = (Double) row[1];

    // totalRevenue += revenue;
    // }

    // return totalRevenue;
    // }
}
