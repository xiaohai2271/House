package site.celess.house.service;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;
import site.celess.house.entity.Mood;

import java.util.List;

/**
 * @Author: 小海
 * @Date： 2019/08/02 21:04
 * @Description：
 */
@Service
public interface MoodService {

    /***
     * 新增mood
     * @param type 情绪类型
     * @param desc 描述
     * @param score 打分
     * @return
     */
    Mood create(Integer type, String desc, Integer score);

    Mood update(Integer type, String desc, Integer score, Integer id);

    List<Mood> getAll();

    JSONArray getAllMoodType();

}
