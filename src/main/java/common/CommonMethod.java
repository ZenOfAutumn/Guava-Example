package common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;

public class CommonMethod {

	public static final Joiner JOINER_WITH_NEXT_LINE = Joiner.on("\n").skipNulls();
	public static final Joiner JOINER_WITH_VERTICAL_LINE = Joiner.on("|").skipNulls();

	
	public static Set<String> methods(Class clazz) {
		Set<String> methodSet = Sets.newHashSet();
		for (Method method : clazz.getDeclaredMethods()) {
			methodSet.add(method.getName());
		}
		return methodSet;
	}

	public static Set<String> publicMethods(Class clazz) {
		Set<String> methodSet = Sets.newHashSet();
		for (Method method : clazz.getDeclaredMethods()) {
			if (Modifier.isPublic(method.getModifiers())) {
				methodSet.add(method.getName());
			}
		}
		return methodSet;
	}

	public static Set<String> publicMethodsInfo(Class clazz) {
		Set<String> methodSet = Sets.newHashSet();
		for (Method method : clazz.getDeclaredMethods()) {
			if (Modifier.isPublic(method.getModifiers())) {
				methodSet.add(method.toGenericString());
			}
		}
		return methodSet;
	}

	public static Set<String> nonStaticFieldsInfo(Class clazz) {
		Set<String> fieldSet = Sets.newHashSet();
		for (Field field : clazz.getDeclaredFields()) {
			if(Modifier.isStatic(field.getModifiers())){
				continue;
			}
			fieldSet.add(field.getName());
		}
		return fieldSet;
	}

}
