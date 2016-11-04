package com.javaapi.test.spring.zotherFeature.javaconfig.conditional.conditionalAsMetaAnnotaion;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

public class OnSystemPropertyCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attributes
                = metadata.getAnnotationAttributes(KKConditionalOnSystemProperty.class.getName());
        Boolean systemPropertyExistsCheck = (Boolean)attributes.get("exists");
        String systemProperty = (String)attributes.get("value");

        if ((systemPropertyExistsCheck && (System.getProperty(systemProperty) != null)) ||
                (!systemPropertyExistsCheck && (System.getProperty(systemProperty) == null))) {
            return true;
        }
        return false;
    }
}