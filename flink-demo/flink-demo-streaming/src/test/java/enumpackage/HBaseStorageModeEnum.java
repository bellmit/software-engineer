package enumpackage;

import lombok.Getter;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/5 17:20
 * @Description
 */
@Getter
public enum HBaseStorageModeEnum implements CodeEnum {
    /**
     * STRING
     */
    STRING(0, "STRING"),
    /**
     * NATIVE
     */
    NATIVE(1, "NATIVE"),
    /**
     * PHOENIX
     */
    PHOENIX(2, "PHOENIX");

    private Integer code;

    private String message;

    HBaseStorageModeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}