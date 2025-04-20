package com.eod.sitree.member.domain.model;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

@Getter
public class Careers {

    private Integer totalYears;

    private Integer totalMonths;

    private List<Career> careerList;

    public Careers(Integer totalYears, Integer totalMonths, List<Career> careerList) {
        this.totalYears = totalYears;
        this.totalMonths = totalMonths;
        this.careerList = Optional.ofNullable(careerList).orElseGet(ArrayList::new);
    }

    public Careers() {

        this.totalYears = 0;
        this.totalMonths = 0;
        this.careerList = new ArrayList<>();
    }

    public void updatePeriod(Period totalPeriod) {

        if (totalPeriod == null) {
            return;
        }

        this.totalYears = totalPeriod.getYears();
        this.totalMonths = totalPeriod.getMonths();
    }
}
