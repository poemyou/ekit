package cn.ekit.file;

import lombok.Data;

import java.util.Objects;

/**
 * @author cheng
 * @date 2022/11/2 20:47
 * description
 */
@Data
public class Test {


    private boolean isDelete;

    public static void main(String[] args) {
        Test t = new Test();
        final boolean equals = Objects.equals("s", "d");
        System.out.println(equals);
        t.isDelete();
    }
}
