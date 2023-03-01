package cn.sendayang.chatbot.api.domain.ai;

import java.io.IOException;

/**
 * @author 杨小宝
 * @description
 * @github
 * @Copyright
 * @Date {2023/3/1}
 */
public interface IOpenAI {

    String doChatGPT(String question) throws IOException;

}
