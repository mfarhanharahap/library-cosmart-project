package com.example.librarycosmartproject.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
@Component
public class DateUtil {
    public Date stringToDate(String value, String pattern){
        try{
            return new SimpleDateFormat(pattern).parse(value);
        } catch (Exception e){
            log.warn("Cannot parse String: {}, Pattern: {}", value, pattern);
            log.warn("Error while parsing date DateUtil.stringToDate: {}", e.getMessage());
        }
        return null;
    }
}
