package org.midnight.generator.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * created by Annie since 2020/6/3 17:48
 * intro: Annie
 */
@AllArgsConstructor
@Getter
public enum DatabaseEnum {
    mysql(0),oracle(1),postgres(2);

    private int type;
}
