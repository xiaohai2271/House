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

    /**
     * insert a data in the db
     *
     * @param type  the type of the mood ,more detail see {@link site.celess.house.enumpac.MoodTypeEnum}
     * @param desc  the description of the mood
     * @param score the score of the mood , it should be between 0 and 10
     * @return mood data after insert
     */
    Mood create(Integer type, String desc, Integer score);

    /**
     * update a mood data in the db
     *
     * @param type  the type of the mood ,more detail see {@link site.celess.house.enumpac.MoodTypeEnum}
     * @param desc  the description of the mood
     * @param score the score of the mood , it should be between 0 and 10
     * @param id    the id of the data
     * @return mood data after update
     */
    Mood update(Integer type, String desc, Integer score, Integer id);

    /**
     * get all mood data
     *
     * @return a list of mood data
     */
    List<Mood> getAll();

    /**
     * get all mood type , its origin is {@link site.celess.house.enumpac.MoodTypeEnum}
     *
     * @return a json array of mood type
     */
    JSONArray getAllMoodType();

}
