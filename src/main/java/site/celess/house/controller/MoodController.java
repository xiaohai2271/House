package site.celess.house.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.celess.house.entity.Response;
import site.celess.house.service.MoodService;
import site.celess.house.util.ResponseUtil;

/**
 * @Author: 小海
 * @Date： 2019/08/02 23:24
 * @Description：
 */
@RestController
public class MoodController {

    @Autowired
    MoodService moodService;

    @PostMapping("/mood")
    public Response create(@RequestParam("type") int type,
                           @RequestParam("desc") String desc,
                           @RequestParam("score") int score) {
        return ResponseUtil.success(moodService.create(type, desc, score));
    }

    @PutMapping("/mood")
    public Response update(@RequestParam(value = "type", required = false) int type,
                           @RequestParam(value = "desc", required = false) String desc,
                           @RequestParam(value = "score", required = false) int score,
                           @RequestParam("id") Integer id) {
        return ResponseUtil.success(moodService.update(type, desc, score, id));
    }

    @GetMapping("/mood")
    public Response getAll() {
        return ResponseUtil.success(moodService.getAll());
    }

    @GetMapping("/mood/type")
    public Response getAllMoodType() {
        return ResponseUtil.success(moodService.getAllMoodType());

    }

}
