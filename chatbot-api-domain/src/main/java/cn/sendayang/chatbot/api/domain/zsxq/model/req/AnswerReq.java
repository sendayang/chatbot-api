package cn.sendayang.chatbot.api.domain.zsxq.model.req;

/**
 * @author 杨小宝
 * @description 请求问答接口信息
 * @github
 * @Copyright
 * @Date {2023/2/27}
 */
public class AnswerReq {
    private ReqData req_data;

    public AnswerReq(ReqData req_data) {
        this.req_data = req_data;
    }

    public ReqData getReq_data() {
        return req_data;
    }

    public void setReq_data(ReqData req_data) {
        this.req_data = req_data;
    }
}
