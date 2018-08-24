package com.atguigu.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

//自定义逻辑返回需要的组件
public class MyImportSelector implements ImportSelector {

	//返回值就是要导入到容器中的组件的全类名
	//AnnotationMetadata: 当前标注@Import注解的类的所有注解信息
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		//importingClassMetadata
		//方法不要返回null值
		return new String[]{"com.atguigu.bean.Eat", "com.atguigu.bean.Sport"};
	}

}
