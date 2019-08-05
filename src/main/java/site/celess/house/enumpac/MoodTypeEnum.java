package site.celess.house.enumpac;

import lombok.Getter;

/**
 * @Author: 小海
 * @Date： 2019/08/03 08:46
 * @Description：
 */
@Getter
public enum MoodTypeEnum {
    // 恐惧
    HORROR(1, "恐惧"),
    // 愤怒
    ANGER(2, "愤怒"),
    // 悲哀
    SORROW(3, "悲哀"),
    // 快乐
    HAPPINESS(4, "快乐");

    private int type;
    private String name;

    MoodTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
