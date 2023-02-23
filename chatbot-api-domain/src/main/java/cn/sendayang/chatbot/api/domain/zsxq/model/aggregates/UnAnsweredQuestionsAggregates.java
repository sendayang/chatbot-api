package cn.sendayang.chatbot.api.domain.zsxq.model.aggregates;

import cn.sendayang.chatbot.api.domain.zsxq.model.req.ReqData;

/**
 * @author 杨小宝
 * @description  未回答问题的聚合信息
 * @github
 * @Copyright
 * @Date {2023/2/23}
 */


public class UnAnsweredQuestionsAggregates {

    private boolean succeeded;
    private ReqData resp_data;

    public boolean isSucceeded(){
        return succeeded;
    }

    public void setSucceeded(boolean succeeded){
        this.succeeded = succeeded;
    }

    public ReqData getResp_data(){
        return resp_data;
    }

    public void setResp_data(ReqData resp_data){
        this.resp_data = resp_data;
    }
}
