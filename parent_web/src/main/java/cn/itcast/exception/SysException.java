package cn.itcast.exception;

public class SysException extends Exception{
    private static final long serialVersionUID = 4055945147128016300L;

    // 异常提示信息
    private String message = null;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SysException(String message) {
        this.message = message;
    }
}
