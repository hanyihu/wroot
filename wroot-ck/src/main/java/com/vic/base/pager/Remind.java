package com.vic.base.pager;


/**
 * 操作结束后的提醒.
 */
public class Remind {

    public static final Remind success() {
        return new Remind("success", "<strong>操作成功！</strong> ");
    }

    public static final Remind info() {
        return new Remind("info", "<strong>提醒：</strong> ");
    }

    public static final Remind warning() {
        return new Remind("warning", "<strong>注意！</strong> ");
    }

    public static final Remind danger() {
        return new Remind("danger", "<strong>操作错误！</strong> ");
    }

    public Remind() {
    }

    public Remind(String level, String message) {
        this.level = level;
        this.message = message;
    }

    /**
     * 等级.
     */
    private String level;
    /**
     * 消息.
     */
    private String message;

    /**
     * 等级.
     *
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * 等级.
     *
     * @param level the level to set
     */
    public Remind setLevel(String level) {
        this.level = level;
        return this;
    }

    /**
     * 消息.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 消息.
     *
     * @param message the message to set
     */
    public Remind setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 追加消息.
     *
     * @param message 消息内容
     */
    public Remind appendMessage(String message) {
        this.message += message;
        return this;
    }
}

