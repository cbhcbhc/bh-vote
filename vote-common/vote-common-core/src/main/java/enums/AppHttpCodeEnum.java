package enums;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    FAIL(401,"你无权操作,请登录后在操作"),
    VALID_FAIL(402,"valid 校验失败"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    INSERT_FAIL(405,"插入数据失败"),
    DATA_EXIST(406,"数据已经存在"),
    RATE_LIMIT(409,"服务器限流异常，请稍候再试"),
    SYSTEM_ERROR(500,"出现错误"),
    USER_EXIST(500,"账号已注册,请重新登录"),
    USER_NOT_EXIST(501,"账号未注册"),
    LOGIN_ERROR(502,"用户名或密码错误" ),
    TOKEN_EMPTY(503,"token为空" ),
    TOKEN_ERROR(504, "token非法");




    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
