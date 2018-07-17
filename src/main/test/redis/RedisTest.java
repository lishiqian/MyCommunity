package redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Set;

public class RedisTest {
    private Jedis jedis;

    @Before
    public void init(){
        jedis = new Jedis("47.95.255.200",6379);
    }
    @Test
    public void test(){
        //5种数据类型：string（字符串），hash（哈希），list（列表），set（集合）及zset(sorted set：有序集合)
        //添加简单key
        jedis.set("key1","aaaa");
        String value1 = jedis.get("key1");
        System.out.println(value1);
        //拼接
        jedis.append("key1","aaaa");


        //读取所有的key
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }

        //自动增加 自动减少
        Long value2 = jedis.incr("key2");
        System.out.println(value2);
        Long value3 = jedis.decr("key3");
        System.out.println(value3);

        //删除key
        Long value4 = jedis.del("aaaa");
        System.out.println(value4);




        //添加hash
        jedis.hset("hash1","field1","1");
        jedis.hset("hash1","field2","a");

        String filed2 = jedis.hget("hash1", "field2");
        System.out.println(filed2);

        //删除属性
        jedis.hdel("hash1", "field1");
        //读取hash所有的key
        Set<String> hash1 = jedis.hkeys("hash1");



        //list 添加元素
        jedis.lpush("list1",new String[]{"1","2","3"});
        jedis.rpush("list1",new String[]{"a","b","c"});
        List<String> list1 = jedis.lrange("list1", 0, -1);
        System.out.println(list1);

        //取元素
        jedis.lpop("list1");
        jedis.rpop("list1");


        //set添加
        jedis.sadd("set1",new String[]{"a","b","c","a","d"});
        jedis.srem("set1",new String[]{"a"});
        jedis.sadd("set2",new String[]{"a","b","c","a","d"});
        //集合交、并、差
        Set<String> sdiff = jedis.sdiff(new String[]{"set1", "set2"});
        Set<String> sinter = jedis.sinter(new String[]{"set1", "set2"});
        Set<String> sunion = jedis.sunion(new String[]{"set1", "set2"});

        //sorted set
        jedis.zadd("zset1",1,"a");
        jedis.zadd("zset1",2,"b");
        jedis.zadd("zset1",3,"c");
        jedis.zadd("zset1",4,"d");
        //遍历
        jedis.zrange("zset1",0,-1);
        jedis.zrange("zset1",0,-1);
        //反向遍历
        jedis.zrevrange("zset1",0,-1);
        //读取分数
        Set<Tuple> zset1 = jedis.zrangeWithScores("zset1", 0, -1);

    }
}
