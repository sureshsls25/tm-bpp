package com.ms.bpp.util;

import com.ms.bpp.services.auth.UserDetailsImpl;
import com.ms.common.model.common.Descriptor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static com.ms.bpp.util.ApplicationConstant.TIMESTAMP_FORMAT;

public class CommonUtil {

    public static String getDateTimeString(Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat(TIMESTAMP_FORMAT);
        df.setTimeZone(tz);
        return df.format(date);
    }

    public static Descriptor getBPPDetails() {
        Descriptor descriptor = new Descriptor();
        descriptor.setCode("BPP");
        descriptor.setName("Tutoring and Mentorship");
        descriptor.setShortDesc("");
        descriptor.setLongDesc("BPP act as the provider platform for enabling discovery of mentorship sessions");
        return descriptor;
    }

    public static UserDetailsImpl getCurrentUserDetails() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static boolean isEmpty(Object request) {
        return ObjectUtils.isEmpty(request);
    }
}
