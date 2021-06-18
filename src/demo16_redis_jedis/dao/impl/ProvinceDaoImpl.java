package demo16_redis_jedis.dao.impl;

import demo16_redis_jedis.dao.ProvinceDao;
import demo16_redis_jedis.domain.Province;
import demo16_redis_jedis.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * ProvinceImpl
 *
 * @autho kit chen
 * @github https://github.com/meethigher
 * @blog https://meethigher.top
 * @time 2021/1/15
 */
public class ProvinceDaoImpl implements ProvinceDao {
    //1.声明成员变量 jdbcTemplate
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDs());
    @Override
    public List<Province> findAll() {
        //1.定义sql
        String sql="select * from province";
        //2.执行sql
        List<Province> provinceList = template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return provinceList;
    }
}
