package com.cdf.factory.user.service.i18n;

import java.util.Locale;

public interface MessageSourceService {
    String getMessage(String code);
    String getMessage(String code, Object[] args);
    String getMessage(String code, Object[] args, Locale locale);
    String getMessage(String code, Object[] args, String defaultMessage, Locale locale);
}
