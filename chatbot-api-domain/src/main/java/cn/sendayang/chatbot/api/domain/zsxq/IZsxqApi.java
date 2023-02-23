package cn.sendayang.chatbot.api.domain.zsxq;

import cn.sendayang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;


import java.io.IOException;


/**
 * @author 杨小宝
 * @description
 * @github
 * @Copyright
 * @Date {2023/2/23}
 */

public interface IZsxqApi {

    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException;

    boolean answer(String groupId,String cookie,String topicId,String text,boolean silenced) throws IOException;
}
