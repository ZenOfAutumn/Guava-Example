package functionProgram;

import org.junit.Test;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public class FunctionProgram {
	
	
	
	public static class StringSupplier implements Supplier<String>{
		
		private Object obj;
		
		public String get() {
			return obj.toString();
		}
	}
	
	@Test
	public void functionParse(){

	}
	
	
	@Test
	public void supplyParse(){
		
		final int i = 1;
		
		Supplier<String> supplier  = new Supplier<String>() {
			public String get() {
				return String.valueOf(i);
			};
		};
		
		Supplier<String> supplierCache = Suppliers.memoize(supplier);
		
		System.out.println(supplierCache.get());
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
