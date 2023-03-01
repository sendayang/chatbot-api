package cn.sendayang.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;

/**
 * @author yxb
 * @description 启动入口
 * @github
 * @Copyright
 * @Date 2023-2-21
 */

public class ApiTest {

    @Test
   public void query_unanswer_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();


        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/15552521881542/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie","_uab_collina=166101377849273171538702; sensorsdata2015jssdkcross={\"distinct_id\":\"182bc2264ee18-08a9e4e3d624198-1b525635-1484784-182bc2264efaa3\",\"first_id\":\"\",\"props\":{\"$latest_traffic_source_type\":\"直接流量\",\"$latest_search_keyword\":\"未取到值_直接打开\",\"$latest_referrer\":\"\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTgyYmMyMjY0ZWUxOC0wOGE5ZTRlM2Q2MjQxOTgtMWI1MjU2MzUtMTQ4NDc4NC0xODJiYzIyNjRlZmFhMyJ9\",\"history_login_id\":{\"name\":\"\",\"value\":\"\"},\"$device_id\":\"182bc2264ee18-08a9e4e3d624198-1b525635-1484784-182bc2264efaa3\"}; zsxq_access_token=5DF5C668-D6AE-CB07-459D-E3AFE5AD2FB2_2C487193DD642552; abtest_env=beta");
        get.addHeader("Content-Type","application/json");

        CloseableHttpResponse response = httpClient.execute(get);
        if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/181512188541442/answer");
        post.addHeader("cookie","zsxq_access_token=D10C68D7-A269-5F85-E26E-1FC7D8B6BCD3_2C487193DD642552; zsxqsessionid=a5619b71c70fbf0944146c4db6f1a37b; sensorsdata2015jssdkcross={\"distinct_id\":\"418482445115418\",\"first_id\":\"182b95c1f4e1d4-04a22b1f88e26e8-26021d51-2073600-182b95c1f4f4b7\",\"props\":{\"$latest_traffic_source_type\":\"直接流量\",\"$latest_search_keyword\":\"未取到值_直接打开\",\"$latest_referrer\":\"\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTgyYjk1YzFmNGUxZDQtMDRhMjJiMWY4OGUyNmU4LTI2MDIxZDUxLTIwNzM2MDAtMTgyYjk1YzFmNGY0YjciLCIkaWRlbnRpdHlfbG9naW5faWQiOiI0MTg0ODI0NDUxMTU0MTgifQ==\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"418482445115418\"},\"$device_id\":\"182b95c1f4e1d4-04a22b1f88e26e8-26021d51-2073600-182b95c1f4f4b7\"}; abtest_env=product");
        post.addHeader("Content-Type","application/json;charset=utf8");

        String paramJson="{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson,ContentType.create("text/json","UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer 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");

        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"帮我写一个java冒泡排序\", \"temperature\": 0, \"max_tokens\": 1024}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }
}
