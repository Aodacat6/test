package com.mycom.config;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import javax.annotation.security.RunAs;
import javax.lang.model.element.VariableElement;
import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：songdalin
 * @date ：2024/2/28 下午 2:52
 * @description：
 * @modified By：
 * @version: 1.0
 */
@SpringBootTest(classes = {EsConfig.class})
public class EsTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient restHighLevelClient;

    //索引
    @Test
    void testCreateIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("hroisx");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    //索引
    @Test
    void testExistIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("hroisx");
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //索引
    @Test
    void testDeleteIndex() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("orders");
        AcknowledgedResponse response = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSON(response));
    }

    //创建文档
    @Test
    void testAddDoc() throws IOException {
        School school = new School("一中", 1L, null);
        IndexRequest indexRequest = new IndexRequest("hroisx");
        indexRequest.id("1");
        indexRequest.timeout("1s");
        IndexRequest sourceReq = indexRequest.source(JSON.toJSON(school), XContentType.JSON);
        IndexResponse response = restHighLevelClient.index(sourceReq, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSON(response));
        System.out.println(response.status());
    }

    //获取文档
    @Test
    void getDoc() throws IOException {
        GetRequest request = new GetRequest("orders", "2");
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        if (response.isExists()) {
            System.out.println("查询成功");
            System.out.println(response.getSourceAsString());
        }else {
            System.out.println("查询失败");
        }
        System.out.println(JSON.toJSON(response));
    }

    @Test
    void existDoc() throws IOException {
        GetRequest request = new GetRequest("hroisx", "2");
        boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);

    }

    @Test
    void updateDoc() throws IOException {
        UpdateRequest request = new UpdateRequest("hroisx", "2");
        School school = new School("一中111", 1L, null);
        UpdateRequest doc = request.doc(JSON.toJSON(school), XContentType.JSON);
        UpdateResponse update = restHighLevelClient.update(doc, RequestOptions.DEFAULT);
        System.out.println(update.status());
        System.out.println(update);
    }

    @Test
    void delDoc() throws IOException {
        DeleteRequest request = new DeleteRequest("hroisx", "1");
        request.timeout("1s");
        DeleteResponse delete = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.status());
        System.out.println(JSON.toJSON(delete));

    }

    //批量插入
    @Test
    void batchInsert() throws IOException {

        List<School> list = new ArrayList<>();
       // School school1 = new School("sdfsf", 11L);
        School school1 = getSchool2();
//        School school2 = new School("一中2", 7L);
//        School school3 = new School("一中3", 8L);
//        School school4 = new School("一中4", 9L);
//        School school5 = new School("一中5", 10L);
        list.add(school1);
//        list.add(school2);
//        list.add(school3);
//        list.add(school4);
//        list.add(school5);
        BulkRequest bulkRequest = new BulkRequest();
        for (School school : list) {
            bulkRequest.add(
                    //JSON.toJSON 不是 tojsonstring！！！！！
                    //new IndexRequest("orders").id(String.valueOf(school.id)).source(JSON.toJSON(school), XContentType.JSON)
                    new IndexRequest("orders112").id(String.valueOf(school.id)).source(objectMapper.writeValueAsString(school), XContentType.JSON)

            );
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.status());
        System.out.println(JSON.toJSON(bulk));
    }

    //查询
    @Test
    void query() throws IOException {
        SearchRequest searchRequest = new SearchRequest("orders112");
        //构造查询条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //精确查找
        //TermQueryBuilder queryBuilder = QueryBuilders.termQuery("name", "一中");
        //模糊查找
       MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("teachers.students.name", "小明");
        sourceBuilder.query(queryBuilder);

        //添加到查询条件
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        System.out.println(JSON.toJSONString(hits));
        if (hits.getHits() == null || hits.getHits().length == 0) {
            System.out.println("没有查到数据");
        }else {
            System.out.println("查到数据了");
        }
        for (SearchHit hit : hits.getHits()) {
            School school = objectMapper.readValue(hit.getSourceAsString(), School.class);
            System.out.println("学校的名字是：" + school.getName());
            //System.out.println(hit.getSourceAsString());
        }


    }

    //查询索引下全部的数据
    @Test
    void getAllDataByIndex() throws IOException {
        SearchRequest searchRequest = new SearchRequest("orders111");
        SearchSourceBuilder builder = new SearchSourceBuilder();

        builder.query(QueryBuilders.matchAllQuery());
        builder.sort("_id", SortOrder.ASC);

        searchRequest.source(builder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.status());
        System.out.println(searchResponse);
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(JSON.toJSON(hit.getSourceAsString()));
        }
    }

    @Test
    public void insertUser() throws IOException {
        User user = new User("1", "tom", 1);
        IndexRequest request = new IndexRequest("users");
        request.id(user.getId());
        request.source(new ObjectMapper().writeValueAsString(user), XContentType.JSON);
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.status());
    }

    @Test
    public void searchUsers() throws IOException {
        String name = "tom";
        SearchRequest searchRequest = new SearchRequest("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("name", name);
        //模糊查询
        FuzzyQueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("name", name);
        searchSourceBuilder.query(queryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse);
        SearchHits hits = searchResponse.getHits();
        List<User> users = new ArrayList<>();
        for (SearchHit hit : hits) {
            User user = new ObjectMapper().readValue(hit.getSourceAsString(), User.class);
            users.add(user);
        }
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void bulkInsert() throws IOException {
        List<User> userList = new ArrayList<>();
        User user1 = new User("1", "tom", 1);
        User user2 = new User("2", "tom2", 2);
        userList.add(user1);
        userList.add(user2);
        BulkRequest bulkRequest = new BulkRequest();
        for (User user : userList) {
            IndexRequest indexRequest = new IndexRequest("user");
            indexRequest.id(user.getId());
            indexRequest.source(new ObjectMapper().writeValueAsString(user), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }


    private School getSchool() {
        Student student1 = new Student("小明", 22.2);
        Student student2 = new Student("大海", 100);

        Teacher teacher = new Teacher("一班", 1L, 39, Arrays.asList(student1, student2));

        School school = new School("一中",1L,  Arrays.asList(teacher));

        return school;
    }

    private School getSchool2() {

        School school = new School("一中",2L,  null);

        return school;
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class User {
    private String id;
    private String name;
    private int age;

    // 省略getter和setter方法
}


@AllArgsConstructor
@NoArgsConstructor
@Data
class School {
    String name;

    Long id;

    private List<Teacher> teachers;

}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Teacher {

    String name;

    Long id;

    int age;

    private List<Student> students;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Student {

    String name;

    double score;

}