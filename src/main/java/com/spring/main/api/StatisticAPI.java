package com.spring.main.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.Service.StatisticService;

@CrossOrigin("*")
@RestController
@RequestMapping("/bachhoa/api/statistic/")
public class StatisticAPI {

    @Autowired
    StatisticService statisticService;

    @GetMapping("increase-revenue")
    public Float[] getListIncreaseRevenue(@RequestParam(value = "start-date") String startDate,
            @RequestParam(value = "end-date") String endDate,
            @RequestParam(value = "mile-stone") String mileStoneType,
            @RequestParam(value = "store-id") int storeId) {
        System.out.println("+ IRevenue:" + startDate + " - " + endDate + " - " + mileStoneType);
        return this.statisticService.getListIncreaseRevenue(startDate, endDate, mileStoneType, storeId);
    }
    
    @GetMapping("increase-cost")
    public Float[] getListIncreaseCost(@RequestParam(value = "start-date") String startDate,
            @RequestParam(value = "end-date") String endDate,
            @RequestParam(value = "mile-stone") String mileStoneType,
            @RequestParam(value = "store-id") int storeId) {
        System.out.println("+ ICost:" + startDate + " - " + endDate + " - " + mileStoneType);
        return this.statisticService.getListIncreaseRevenue(startDate, endDate, mileStoneType, storeId);
    }

    @GetMapping("revenue")
    public Float[] getListRevenue(@RequestParam(value = "start-date") String startDate,
            @RequestParam(value = "end-date") String endDate,
            @RequestParam(value = "mile-stone") String mileStoneType,
            @RequestParam(value = "store-id") int storeId) {
        System.out.println("+ revenue:" + startDate + " - " + endDate + " - " + mileStoneType);
        return this.statisticService.getListRevenue(startDate, endDate, mileStoneType, storeId);
    }
    
    @GetMapping("cost")
    public Float[] getListCost(@RequestParam(value = "start-date") String startDate,
            @RequestParam(value = "end-date") String endDate,
            @RequestParam(value = "mile-stone") String mileStoneType,
            @RequestParam(value = "store-id") int storeId) {
        System.out.println("+ cost:" + startDate + " - " + endDate + " - " + mileStoneType);
        return this.statisticService.getListCost(startDate, endDate, mileStoneType, storeId);
    }

    @GetMapping("revenue/mile-stone-type")
    public String[] getListMileStone(@RequestParam(value = "start-date") String startDate,
            @RequestParam(value = "end-date") String endDate,
            @RequestParam(value = "mile-stone") String mileStoneType,
            @RequestParam(value = "store-id") int storeId) {
        System.out.println("CONTROLLER :" + startDate + " - " + endDate + " - " + mileStoneType);
        return this.statisticService.getListMileStone(startDate, endDate, mileStoneType, storeId);
    }
}
