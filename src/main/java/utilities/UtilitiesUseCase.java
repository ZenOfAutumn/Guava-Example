package utilities;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class UtilitiesUseCase {

	static Set<String> methods(Class clazz) {
		Set<String> methodSet = Sets.newHashSet();
		for (Method method : clazz.getDeclaredMethods()) {
			methodSet.add(method.getName());
		}
		return methodSet;
	}
	
	
	 Set<String> publicMethods(Class clazz) {
		Set<String> methodSet = Sets.newHashSet();
		for (Method method : clazz.getDeclaredMethods()) {
			if (Modifier.isPublic(method.getModifiers())) {
				methodSet.add(method.getName());
			}
		}
		return methodSet;
	}

	static void joinerTest() {
		Map<String, String> testMap = Maps.newLinkedHashMap();
		testMap.put("Washington D.C", "Redskins");
		testMap.put("New York City", "Giants");
		testMap.put("Philadelphia", "Eagles");
		testMap.put("Dallas", "Cowboys");
		String returnedString = Joiner.on("#").withKeyValueSeparator("=").join(testMap);
		System.out.println(returnedString);

	}

	static void SplitterTest() {
		String startString = "Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";
		Splitter.MapSplitter mapSplitter = Splitter.on("#").withKeyValueSeparator("=");
		Map<String, String> splitMap = mapSplitter.split(startString);
		System.out.println(Joiner.on("|").withKeyValueSeparator("="));


	}
	
	@Test
	public void stringsTest(){
		System.out.println(Strings.padStart("hello", 6, 'a'));
		System.out.println(Strings.padEnd("hello", 6, 'a'));
		System.out.println(Strings.repeat("a", 6));
		System.out.println(Strings.commonPrefix("abcdef", "abcdeg"));
		System.out.println(Strings.commonSuffix("abcdefg", "abcdfg"));
	}
	
	@Test
	public void charMatcherTest(){
		
		System.out.println(CharMatcher.WHITESPACE.replaceFrom(" 1 2 3 4 5",'|'));
		System.out.println(CharMatcher.JAVA_UPPER_CASE.trimAndCollapseFrom(" A a A ss d", '|'));
		
	}
	
	@Test
	public void preconditionsTest(){
		System.out.println(Joiner.on("\n").join(publicMethods(Preconditions
				.class)));
		
	}
	
	@Test
	public void objectsTest(){
		
		System.out.println(Objects.hashCode(new UtilitiesUseCase()));
		System.out.println(new Bean(1, "world").compareTo(new Bean(1, "world")));
		
	}

	
	private static class Bean implements Comparable<Bean>{
		
		private int id;
		
		private String name;
		
		
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Bean(int id, String name){
			this.id = id;
			this.name  =name;
		}
		
		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this).omitNullValues().add("id", id).add("name", name).toString();
		}
		
		public int compareTo(Bean o) {
			return ComparisonChain.start().compare(id, o.getId()).compare(name, o.getName()).result();
			
		}
		
	}

}
