package cn.ekit.cache;

import cn.ekit.async.AsyncUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cheng
 * @date 2022/6/12 10:52
 * description
 */
public class CacheUtil {

    private static long max_alive_second = 6;

    public CacheUtil() {
    }

    private static final Map<String, ValueWrap> cache = new ConcurrentHashMap<>(8);

    public static Object getCache(String key) {
        ValueWrap valueWrap = cache.get(key);
        if (valueWrap == null) {
            return null;
        }
        return valueWrap.getValue();
    }

    public static void addCache(String key, Object value) {
        clearCache();
        cache.put(key, new ValueWrap(value));
    }

    private static void clearCache() {
        for (Map.Entry<String, ValueWrap> entry : cache.entrySet()) {
            final ValueWrap value = entry.getValue();
            long bornTime = value.getTime();
            long nowTime = System.currentTimeMillis();
            if ((nowTime - bornTime) / 1000 >= max_alive_second) {
                cache.remove(entry.getKey());
            }
        }
    }

    private static class ValueWrap {

        public ValueWrap(Object obj) {
            this.value = obj;
            time = System.currentTimeMillis();
        }

        public long getTime() {
            return time;
        }

        public Object getValue() {
            return value;
        }

        private long time;
        private Object value;
    }

    public static void main(String[] args) {
        addCache("a", "sss");
        AsyncUtil.safeSleep(5000);
        addCache("b", "bbb");
        String a = (String) getCache("a");
        System.out.println(a);
    }

}
