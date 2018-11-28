package com.web.validation.boot;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

/**
 * 加载类
 * @author chenhaiyang
 */
public class ImportedClassSelector  implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> imports = new ArrayList<>();
        imports.add("com.web.validation.boot.interceptor.ValidationInterceptor");
        return imports.toArray(new String[0]);
    }
}
