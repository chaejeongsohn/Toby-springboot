package toby.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import toby.annotation.MyAutoConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        // 1. classLoader 및 생성자로 주입하기 전 코드
//        return new String[] {
//                "toby.config.DispatcherSubletConfig",
//                "toby.config.TomcatWebServerConfig"
//        };

        // 2.
//        Iterable<String> candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
//        return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);

        // 3.
        List<String> autoConfigs = new ArrayList<>();

        // Java 5 스타일
//        for (String candidates : ImportCandidates.load(MyAutoConfiguration.class, classLoader)){
//            autoConfigs.add(candidates);
//        };

        // Java 8 스타일: forEach 사용
        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);


//        return autoConfigs.toArray(new String[0]);

        // String Array 의 생성자를 넘겨준다.
//        return autoConfigs.toArray(String[]::new);

        // String[] 을 String Array 로 변환
        return Arrays.copyOf(autoConfigs.toArray(), autoConfigs.size(), String[].class);
    }
}
