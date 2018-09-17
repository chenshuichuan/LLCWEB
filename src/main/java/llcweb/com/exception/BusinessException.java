package llcweb.com.exception;

/**
 * @Author haien
 * @Description 自定义异常
 * @Date 2018/9/6
 * @Param
 * @return
 **/
public class BusinessException extends Exception {
    private int errCode;

    public BusinessException() {
    }

    public BusinessException(int errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}
