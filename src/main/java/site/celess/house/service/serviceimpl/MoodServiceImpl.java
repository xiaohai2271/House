package site.celess.house.service.serviceimpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.celess.house.entity.Mood;
import site.celess.house.enumpac.MoodTypeEnum;
import site.celess.house.enumpac.ResponseEnum;
import site.celess.house.exception.ResponseException;
import site.celess.house.repository.MoodRepository;
import site.celess.house.service.MoodService;

import java.util.*;

/**
 * @Author: 小海
 * @Date： 2019/08/02 21:05
 * @Description：
 */
@Service
public class MoodServiceImpl implements MoodService {

    @Autowired
    MoodRepository moodRepository;


    @Override
    public Mood create(Integer type, String desc, Integer score) {
        Mood mood = new Mood();
        MoodTypeEnum[] values = MoodTypeEnum.values();
        // 对type进行比对
        for (MoodTypeEnum enu : values) {
            if (type.equals(enu.getType())) {
                mood.setType(enu.getType());
                break;
            }
        }
        if (mood.getType() == null) {
            throw new ResponseException(ResponseEnum.PARAMETER_ERROR);
        }
        mood.setDesc(desc);
        if (score > 10 || score < 0) {
            throw new ResponseException(ResponseEnum.PARAMETER_ERROR);
        }
        // 写入对象属性
        mood.setScore(score);
        mood.setTime(System.currentTimeMillis());
        mood = moodRepository.save(mood);
        return mood;
    }

    @Override
    public Mood update(Integer type, String desc, Integer score, Integer id) {
        // 判断id是否合法
        if (id == null || id <= 0) {
            throw new ResponseException(ResponseEnum.PARAMETER_ID_NOT_NULL);
        }
        Optional<Mood> moodOptional = moodRepository.findById(id);
        if (!moodOptional.isPresent()) {
            throw new ResponseException(ResponseEnum.DATA_NOT_EXIST);
        }
        Mood mood = moodOptional.get();
        // null值不进行更新
        if (type != null) {
            mood.setType(type);
        }
        if (desc != null) {
            mood.setDesc(desc);
        }
        if (score != null) {
            mood.setScore(score);
        }
        // 写入数据
        return moodRepository.save(mood);
    }

    @Override
    public List<Mood> getAll() {
        // 返回所以的mood
        // TODO : 进行分页
        return moodRepository.findAll();
    }

    @Override
    public JSONArray getAllMoodType() {
        JSONArray jsonArray = new JSONArray();
        // 返回json数组
        for (MoodTypeEnum m : MoodTypeEnum.values()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", m.getName());
            jsonObject.put("type", m.getType());
            // json数组存储jsonObject
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
