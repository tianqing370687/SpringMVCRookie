package com.springmvc.niklaus.utils.json;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.ser.BeanSerializerFactory;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Created by nicholas.chi on 2016/9/27.
 */
public class JsonBinder {

    private static Logger logger = LoggerFactory.getLogger(JsonBinder.class);
    private static Object lock = new Object();
    private static JsonBinder jsonBinder;
    private static JsonBinder jsonBinderNonNull;
    private ObjectMapper mapper;
    private ObjectMapper mapperFilter;
    private JsonFactory jsonFactory;
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static JsonBinder getInstance(){
        if (jsonBinder == null)
        {
            synchronized (lock)
            {
                if (jsonBinder == null)
                {
                    jsonBinder = new JsonBinder(Inclusion.NON_DEFAULT);
                }
            }
        }
        return jsonBinder;
    }

    public static JsonBinder getInstanceNonNull(){
        if (jsonBinderNonNull == null)
        {
            synchronized (lock)
            {
                if (jsonBinderNonNull == null)
                {
                    jsonBinderNonNull = new JsonBinder(Inclusion.NON_NULL);
                }
            }
        }
        return jsonBinderNonNull;
    }
    protected JsonBinder(Inclusion inclusion){
        mapper = new ObjectMapper();
        // 设置输出包含的属性
        mapper.getSerializationConfig().setSerializationInclusion(inclusion);
        // 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
        mapper.getDeserializationConfig().set(
                DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(df);
        mapperFilter = new ObjectMapper();
        // 设置输出包含的属性
        mapperFilter.getSerializationConfig().setSerializationInclusion(inclusion);
        // 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
        mapperFilter.getDeserializationConfig().set(
                DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapperFilter.setDateFormat(df);
        BidBeanSerializerFactory bidBeanFactory = new BidBeanSerializerFactory(null);
        bidBeanFactory.setFilterId("executeFilter");
        mapperFilter.setSerializerFactory(bidBeanFactory);
        jsonFactory = new JsonFactory();
    }
    /**
     * 如果JSON字符串为Null或"null"字符串,返回Null. 如果JSON字符串为"[]",返回空集合.
     *
     * 如需读取集合如List/Map,且不是List<String>这种简单类型时使用如下语句: List<MyBean> beanList =
     * binder.getMapper().readValue(listString, new
     * TypeReference<List<MyBean>>() {});
     */
    public <T> T fromJson(String jsonString, Class<T> clazz){
        if (StringUtils.isEmpty(jsonString)){
            return null;
        }
        try{
            return mapper.readValue(jsonString, clazz);
        }catch (IOException e){
            e.printStackTrace();
            logger.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }
    public Object fromJsonToList(String jsonString, Class clazz){
        if (StringUtils.isEmpty(jsonString)){
            return null;
        }
        try{
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return mapper.readValue(jsonString, type);
        }catch (IOException e){
            logger.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }
    /**
     * 如果对象为Null,返回"null". 如果集合为空集合,返回"[]".
     */
    public String toJson(Object object){
        try{
            StringWriter sw = new StringWriter();
            JsonGenerator gen = jsonFactory.createJsonGenerator(sw);
            mapper.writeValue(gen, object);
            gen.close();
            return sw.toString();
        }catch (IOException e){
            logger.warn("write to json string error:" + object, e);
            return null;
        }
    }

    /**
     * @param object
     * @param except 排除字段
     * @return
     */
    public String toJsonAllExcept(Object object, String... except){
        try{
            StringWriter sw = new StringWriter();
            JsonGenerator gen = jsonFactory.createJsonGenerator(sw);
            SimpleFilterProvider filter = new SimpleFilterProvider();
            filter.addFilter("executeFilter",SimpleBeanPropertyFilter.serializeAllExcept(except));
            mapperFilter.setFilters(filter);
            mapperFilter.writeValue(gen, object);
            gen.close();
            return sw.toString();
        }catch (IOException e){
            logger.warn("write to json string error:" + object, e);
            return null;
        }
    }

    /**
     * @param object
     * @param except 仅包含字段
     * @return
     */
    public String toJsonOutAllExcept(Object object, String... except){
        try{
            StringWriter sw = new StringWriter();
            JsonGenerator gen = jsonFactory.createJsonGenerator(sw);
            SimpleFilterProvider filter = new SimpleFilterProvider();
            filter.addFilter("executeFilter",SimpleBeanPropertyFilter.filterOutAllExcept(except));
            mapperFilter.setFilters(filter);
            mapperFilter.writeValue(gen, object);
            gen.close();
            return sw.toString();
        }catch (IOException e){
            logger.warn("write to json string error:" + object, e);
            return null;
        }
    }

    public class BidBeanSerializerFactory extends BeanSerializerFactory {
        private Object filterId;
        public BidBeanSerializerFactory(Config config) {
            super(config);
        }
        @Override
        protected Object findFilterId(SerializationConfig config,BasicBeanDescription beanDesc) {
            return getFilterId();
        }
        public Object getFilterId() {
            return filterId;
        }
        public void setFilterId(Object filterId) {
            this.filterId = filterId;
        }
    }
}
