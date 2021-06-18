package demo16_redis_jedis.service;

import demo16_redis_jedis.domain.Province;

import java.util.List;

/**
 * ProvinceService
 *
 * @autho kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/1/15
 */
public interface ProvinceService {
    public List<Province> findAll();
    public String findAllJson();
}
