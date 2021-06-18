package demo16_redis_jedis.dao;

import demo16_redis_jedis.domain.Province;

import java.util.List;

/**
 * ProvinceDao
 *
 * @autho kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/1/15
 */
public interface ProvinceDao {
    public List<Province> findAll();
}
