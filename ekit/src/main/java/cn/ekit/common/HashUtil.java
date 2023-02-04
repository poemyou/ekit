package cn.ekit.common;

/**
 * @author cheng
 * @date 2023/2/4 23:28
 * description
 */
public class HashUtil {

    public static int addHash(String key, int prime) {
        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); ++i) {
            hash += key.charAt(i);
        }
        return hash % prime;
    }
}
