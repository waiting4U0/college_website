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
                ", status=" + status +
                ", messageSource='" + messageSource + '\'' +
                '}';
    }

    private String messageTitle;
    private long messageType;
    private String messageContent;
    private String messageImg;
    private String messageAddition;
    private Integer status;
    private String messageSource;
//    private String keyword;




    public String getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(String messageSource) {
        this.messageSource = messageSource;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    //    private Image img;


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
