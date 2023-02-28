package cn.sendayang.chatbot.api.test;

import cn.sendayang.chatbot.api.domain.zsxq.IZsxqApi;
import cn.sendayang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import cn.sendayang.chatbot.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author 杨小宝
 * @description
 * @github
 * @Copyright
 * @Date {2023/2/28}
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {
   private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

   @Value("${chatbot-api.groupid}")
   private String groupId;

   @Value("${chatbot-api.cookie}")
   private String cookie;

   @Resource
   private IZsxqApi zsxqApi;

   @Test
   public void test_zsxqApi() throws IOException {
      UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
      logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));

      List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
      for (Topics topic :topics){
         String topicId = topic.getTopic_id();
         String text = topic.getQuestion().getText();
         logger.info("topicId:{},Text:{}",topicId,text);
      }

   }

}