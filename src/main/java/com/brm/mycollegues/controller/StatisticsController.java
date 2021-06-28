package com.brm.mycollegues.controller;

import com.brm.mycollegues.entity.Month;
import com.brm.mycollegues.repo.MonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("statistics")
@RestController
public class StatisticsController {

    @Autowired
    private MonthRepository monthRepository;

    @PostMapping("month/update")
    public boolean updateMonth(@RequestBody Month month){
        if (monthRepository.findByMonthNumberAndYearNumber(month.getMonthNumber(),
                month.getYearNumber()).isPresent()){
            return true;
        }
        else {
            monthRepository.save(month);
            return false;
        }
    }
}
