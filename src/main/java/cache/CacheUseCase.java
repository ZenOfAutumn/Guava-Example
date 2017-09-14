package cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import com.google.common.base.Supplier;
import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import common.CommonMethod;

public class CacheUseCase {

	private static final MapJoiner MAP_JOINER = Joiner.on("#").withKeyValueSeparator("=");
	
	private static RemovalListener<String, Object> objectListner=new RemovalListener<String,Object>()
	{
		
		public void onRemoval(RemovalNotification<String,Object>notification){
				System.out.println(notification.getKey()+"="+notification.getValue()+" has removed");
				System.out.println("the reason is " + notification.getCause());

		}
	};
	
	private static LoadingCache<String, Object> objectCache=

	CacheBuilder.newBuilder().recordStats().expireAfterWrite(1L,TimeUnit.MINUTES).maximumSize(2L).removalListener(objectListner).ticker(Ticker.systemTicker()).build(new CacheLoader<String,Object>(){@Override public Object load(String key)throws Exception{return new String(key);};});	

	private static CacheBuilder builderBySpec =CacheBuilder.from(CacheBuilderSpec.parse("concurrencyLevel=10,refreshAfterWrite=5s"));
	
	
	private static CacheLoader<String, Object> cacheLoaderFromFunction = CacheLoader.from(new Function<String, Object>() {
		public Object apply(String input) {
			return input;
		}
	});
	
	private static CacheLoader<Object, String> cacheLoaderFromSupplier = CacheLoader.from(new Supplier<String>() {
		public String get() {
			return "1";
		};
	});	
			
		
			
	@Test
	public void cacheTest() throws ExecutionException{
		objectCache.put("1", new String("1"));
		objectCache.put("2", new String("2"));
		objectCache.getIfPresent("1");
		objectCache.get("3",new Callable<Object>() {
			public Object call() throws Exception {
				return new String("3");
			}
		});
		objectCache.getIfPresent("1");
		objectCache.getIfPresent("1");
		
		System.out.println(objectCache.size());
		System.out.println(MAP_JOINER.join(objectCache.asMap()));
		
		CacheStats stats = objectCache.stats();
		System.out.println(stats.toString());
	}
	
	@Test
	public void cacheParse(){
		System.out.println(Joiner.on("\n").join(CommonMethod.nonStaticFieldsInfo(RemovalCause.class)));
	}
	
}
