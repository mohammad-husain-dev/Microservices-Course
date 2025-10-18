package com.dilshad.social_media_rest_api.controller;

import com.dilshad.social_media_rest_api.beans.SomeBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("filtering")
    public SomeBean filtering(){
        return new SomeBean("value1", "value2", "value3");
    }

    @GetMapping("filtering-list")
    public List<SomeBean> filteringList(){
        return Arrays.asList(new SomeBean("value1", "value2", "value3"),
                            new SomeBean("value7", "value8", "value9"),
                            new SomeBean("value4", "value5", "value6"));
    }

    // --- Dynamic Filtering ---//
    @GetMapping("dynamic-filtering")
    public MappingJacksonValue dynamicfiltering(){
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
//        SimpleBeanPropertyFilter filter1 = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
//        FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter",filter1);
        mapping.setFilters(getFilter(new String[]{"field1", "field2"}, "someBeanFilter"));

        return  mapping;
    }

    @GetMapping("dynamic-filtering-list")
    public MappingJacksonValue dynamicfilteringList(){
        List<SomeBean> list=Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value7", "value8", "value9"),
                new SomeBean("value4", "value5", "value6"));
        MappingJacksonValue mapping = new MappingJacksonValue(list);
//        SimpleBeanPropertyFilter filter1 = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
//        FilterProvider filter=new SimpleFilterProvider().addFilter("someBeanFilter",filter1);
        mapping.setFilters(getFilter(new String[]{"field1", "field3"}, "someBeanFilter"));

        return  mapping;
    }

    FilterProvider getFilter(String[] properties, String filter_name){
        SimpleBeanPropertyFilter filter1 = SimpleBeanPropertyFilter.filterOutAllExcept(properties);
        return new SimpleFilterProvider().addFilter(filter_name,filter1);
    }
}
