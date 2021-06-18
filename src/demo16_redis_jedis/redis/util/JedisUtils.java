package demo16_redis_jedis.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * JedisUtils
 * 连接池的工具类
 * 加载配置文件，配置连接池参数，提供获取连接的方法
 *
 * @autho kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/1/14
 */
public class JedisUtils {
    private static JedisPool pool;

    static {
        //读取配置文件
        InputStream is = JedisUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        //创建Properties对象
        Properties pro = new Properties();
        //关联文件
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取数据，设置到JedisPoolConfig中
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
        config.setMaxTotal(Integer.parseInt(pro.getProperty("maxIdle")));
        //初始化JedisPool
        pool = new JedisPool(config, pro.getProperty("host"), Integer.parseInt(pro.getProperty("port")));
    }

    /**
     * 获取连接
     */
    public static Jedis getJedis() {
        return pool.getResource();
    }
}
