package org.infinity.javabasics.designpattern.switchcasereplacement;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CaseAnnotation {
    CaseEnum value();
}