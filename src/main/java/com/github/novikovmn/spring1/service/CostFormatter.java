package com.github.novikovmn.spring1.service;

import org.springframework.stereotype.Service;

@Service("costFormatter")
public class CostFormatter {

    public String print(Integer cost) { return cost / 100 + "р. " + cost % 100 + "к."; }

    public String print(Integer rub, Integer cop) { return rub + "р. " + cop + "к."; }

    public Integer parse(Integer rub, Integer cop) {
        return rub * 100 + cop;
    }

    public Integer getRub(Integer cost) { return cost / 100; }

    public Integer getCop(Integer cost) { return cost % 100; }
}