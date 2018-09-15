package llcweb.com.domain.entity;

/**
 * @Author haien
 * @Description 自定义异常
 * @Date 2018/9/6
 * @Param
 * @return
 **/
public class BusinessException extends Exception {
    private int errCode;
    private String errMsg;

    public BusinessException() {
    }

    public BusinessException(int errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
