package cn.sendayang.chatbot.api.domain.zsxq.service;

import cn.sendayang.chatbot.api.domain.zsxq.IZsxqApi;
import cn.sendayang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import cn.sendayang.chatbot.api.domain.zsxq.model.req.AnswerReq;
import cn.sendayang.chatbot.api.domain.zsxq.model.req.ReqData;
import cn.sendayang.chatbot.api.domain.zsxq.model.res.AnswerRes;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author 杨小宝
 * @description
 * @github
 * @Copyright
 * @Date {2023/2/23}
 */
public class IZsxqApiImpl implements IZsxqApi {

    private Logger logger = LoggerFactory.getLogger(IZsxqApiImpl.class);

    @Override
    public UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();


        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/" + groupId + "/topics?scope=unanswered_questions&count=20");

      //  get.addHeader("cookie","zsxq_access_token=D10C68D7-A269-5F85-E26E-1FC7D8B6BCD3_2C487193DD642552; zsxqsessionid=a5619b71c70fbf0944146c4db6f1a37b; sensorsdata2015jssdkcross={\"distinct_id\":\"418482445115418\",\"first_id\":\"182b95c1f4e1d4-04a22b1f88e26e8-26021d51-2073600-182b95c1f4f4b7\",\"props\":{\"$latest_traffic_source_type\":\"直接流量\",\"$latest_search_keyword\":\"未取到值_直接打开\",\"$latest_referrer\":\"\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTgyYjk1YzFmNGUxZDQtMDRhMjJiMWY4OGUyNmU4LTI2MDIxZDUxLTIwNzM2MDAtMTgyYjk1YzFmNGY0YjciLCIkaWRlbnRpdHlfbG9naW5faWQiOiI0MTg0ODI0NDUxMTU0MTgifQ==\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"418482445115418\"},\"$device_id\":\"182b95c1f4e1d4-04a22b1f88e26e8-26021d51-2073600-182b95c1f4f4b7\"}; abtest_env=product");
        get.addHeader("cookie",cookie);
        get.addHeader("Content-Type","application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String jsonStr = EntityUtils.toString(response.getEntity());
            return JSON.parseObject(jsonStr,UnAnsweredQuestionsAggregates.class);
        }else{
            throw new RuntimeException("queryUnAnsweredQuestionsTopicId err Code is "+response.getStatusLine().getStatusCode());
        }

    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/"+topicId+"/answer");
        post.addHeader("cookie",cookie);
        post.addHeader("Content-Type","application/json;charset=utf8");
        post.addHeader("user-agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");

        /** 测试数据
        String paramJson="{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";
       */

        AnswerReq answerReq = new AnswerReq(new ReqData(text, silenced));
        String paramJson = JSONObject.fromObject(answerReq).toString();

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json","UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("回答问题结果。groupId：{} topicId：{} jsonStr：{}", groupId, topicId, jsonStr);
            AnswerRes answerRes = JSON.parseObject(jsonStr,AnswerRes.class);
            return answerRes.isSucceeded();
        }else{
            throw new RuntimeException("answer Err code is"+response.getStatusLine().getStatusCode());
        }

    }
}
