package com.sec.ax.restful.common.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.sec.ax.restful.common.LocalizationManager;

/**
 * 
 * @author heesik.jeon
 *
 */

public class ResourceBundleMessageSourceProxy implements LocalizationManager {

    @Autowired
    MessageSource resourceBundle;

    @Override
    public String getMessage(String code, Object[] args) {
        return resourceBundle.getMessage(code, args, new Locale("en", "US"));
    }

}
