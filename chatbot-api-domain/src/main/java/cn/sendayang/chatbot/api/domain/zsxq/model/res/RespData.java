package cn.sendayang.chatbot.api.domain.zsxq.model.res;

import cn.sendayang.chatbot.api.domain.zsxq.model.vo.Topics;

import java.util.List;

/**
 * @author 杨小宝
 * @description
 * @github
 * @Copyright
 * @Date {2023/2/23}
 */


public class RespData {
    private List<Topics> topics;

    public List<Topics> getTopics(){
        return topics;
    }

    public void setTopics(List<Topics> topics){
        this.topics = topics;
    }

}
