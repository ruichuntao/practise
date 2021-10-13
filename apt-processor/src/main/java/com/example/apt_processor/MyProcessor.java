package com.example.apt_processor;

import com.example.apt_annotation.Print;
import com.google.auto.service.AutoService;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@AutoService(Process.class)
public class MyProcessor extends AbstractProcessor {
    /**
     * 扫描注解回调
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //拿到所有添加Print注解的成员变量
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Print.class);
        for (Element element : elements) {
            //拿到成员变量名
            Name simpleName = element.getSimpleName();
            //输出成员变量名
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,simpleName);
        }
        return false;
    }


    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        System.out.println("Hello,APT");
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,"Hello,APT");
    }

    /**
     * 要扫描扫描的注解，可以添加多个
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add(Print.class.getCanonicalName());
        return hashSet;
    }

    /**
     * 编译版本，固定写法就可以
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

}
