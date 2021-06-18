package demo16_redis_jedis.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo16_redis_jedis.dao.ProvinceDao;
import demo16_redis_jedis.dao.impl.ProvinceDaoImpl;
import demo16_redis_jedis.domain.Province;
import demo16_redis_jedis.redis.util.JedisUtils;
import demo16_redis_jedis.service.ProvinceService;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * ProvinceServiceImpl
 *
 * @autho kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/1/15
 */
public class ProvinceServiceImpl implements ProvinceService {
    //声明dao
    private ProvinceDao dao= new ProvinceDaoImpl();
    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }

    /**
     * 使用Redis的缓存
     * @return
     */
    @Override
    public String findAllJson() {
        //1.从Redis中查询数据
        //1.1获取redis的客户端文件
        Jedis jedis = JedisUtils.getJedis();
        String province = jedis.get("province");
        //2.判断province是否为空
        if(province==null||province.length()==0){
            //redis中没有数据，从数据库中查询
            System.out.println("redis中没有数据，查询数据库..");
            List<Province> all = dao.findAll();
            //将获取到的数据，序列化为json，并存到redis
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                province = objectMapper.writeValueAsString(all);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //将json数据，存入redis中
            jedis.set("province",province);
            jedis.close();
        }else{
            System.out.println("redis中有数据，查询缓存..");
        }
        return province;
    }
}
