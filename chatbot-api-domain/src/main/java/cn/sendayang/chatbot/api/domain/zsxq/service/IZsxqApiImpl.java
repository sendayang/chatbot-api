package cn.sendayang.chatbot.api.domain.zsxq.service;

import cn.sendayang.chatbot.api.domain.zsxq.IZsxqApi;
import cn.sendayang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
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
        return null;
    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {
        return false;
    }
}
