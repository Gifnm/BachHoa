package com.spring.main.api;

import java.sql.Timestamp;

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
            @RequestParam(value = "mile-stone") String mileStoneType) {
        System.out.println(startDate + " - " + endDate + " - " + mileStoneType);
        return this.statisticService.getListIncreaseRevenue(startDate, endDate, mileStoneType);
    }

    @GetMapping("revenue")
    public Float[] getListRevenue(@RequestParam(value = "start-date") String startDate,
            @RequestParam(value = "end-date") String endDate,
            @RequestParam(value = "mile-stone") String mileStoneType) {
        System.out.println(startDate + " - " + endDate + " - " + mileStoneType);
        return this.statisticService.getListRevenue(startDate, endDate, mileStoneType);
    }

    @GetMapping("revenue/mile-stone-type")
    public String[] getListMileStone(@RequestParam(value = "start-date") String startDate,
            @RequestParam(value = "end-date") String endDate,
            @RequestParam(value = "mile-stone") String mileStoneType) {
        System.out.println("CONTROLLER " + startDate + " - " + endDate + " - " + mileStoneType);
        return this.statisticService.getListMileStone(startDate, endDate, mileStoneType);
    }
}
