package cn.ekit.common.consts;

/**
 * @author cheng
 * @date 2023/2/4 19:03
 * description
 */
public enum SerialNoTypeEnum {

    /**
     *
     */
    FILE_ID("FI", "文件id");

    private String code;
    private String desc;

    SerialNoTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }
}
