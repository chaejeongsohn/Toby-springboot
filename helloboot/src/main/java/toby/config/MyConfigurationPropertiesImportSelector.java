package toby.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;
import toby.annotation.EnableMyConfigurationProperties;

/**
 * 실제 import 할 class의 이름을 리턴해준다.
 */
public class MyConfigurationPropertiesImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        /**
         * 이 annotation 에 붙은 모든 attribute 를 다 가져온다.
         * 해당 class 의 name 을 return
         */
        MultiValueMap<String, Object> allAnnotationAttributes = importingClassMetadata.getAllAnnotationAttributes(EnableMyConfigurationProperties.class.getName());
        Class propertyClass = (Class) allAnnotationAttributes.getFirst("value");
        return new String[ ] {propertyClass.getName()};
    }
}
