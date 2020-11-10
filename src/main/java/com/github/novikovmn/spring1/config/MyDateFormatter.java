package com.github.novikovmn.spring1.config;

import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MyDateFormatter implements Formatter<Date> {
    private static final String PROPERTY_DATE_FORMAT = "date.format";
    private static final SimpleDateFormat DEFAULT_FORMAT = new SimpleDateFormat("dd - mm <-> yyyy");

    private Map<Locale, SimpleDateFormat> cache = new HashMap<>();
    private final MessageSource messageSource;

    public MyDateFormatter(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public Date parse(String s, Locale locale) throws ParseException {
        return getFormat(locale).parse(s);
    }

    @Override
    public String print(Date date, Locale locale) {
        return getFormat(locale).format(date);
    }

    private SimpleDateFormat getFormat(Locale locale) {
        SimpleDateFormat format = cache.get(locale);
        if (format != null) return format;

        String pattern = messageSource.getMessage(PROPERTY_DATE_FORMAT, null, locale);
        format = pattern == null ? DEFAULT_FORMAT : new SimpleDateFormat(pattern);
        cache.put(locale, format);
        return format;
    }
}
