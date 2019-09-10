package cn.edu.swpu.info.college_website.domain;


import cn.edu.swpu.info.college_website.domain.base.BaseDomain;

public class Message extends BaseDomain {

    @Override
    public String toString() {
        return "Message{" +
                "messageTitle='" + messageTitle + '\'' +
                ", messageType=" + messageType +
                ", messageContent='" + messageContent + '\'' +
                ", messageImg='" + messageImg + '\'' +
                ", messageAddition='" + messageAddition + '\'' +
                '}';
    }

    private String messageTitle;  //新闻标题
    private long messageType;   //新闻类型
    private String messageContent;    //新闻内容
    private String messageImg;   //新闻图片
    private String messageAddition;   //新闻


    /**
     * 得到消息的标题
     *
     * @return Title
     */
    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }


    public long getMessageType() {
        return messageType;
    }

    public void setMessageType(long messageType) {
        this.messageType = messageType;
    }


    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }


    public String getMessageImg() {
        return messageImg;
    }

    public void setMessageImg(String messageImg) {
        this.messageImg = messageImg;
    }


    public String getMessageAddition() {
        return messageAddition;
    }

    public void setMessageAddition(String messageAddition) {
        this.messageAddition = messageAddition;
    }


}
