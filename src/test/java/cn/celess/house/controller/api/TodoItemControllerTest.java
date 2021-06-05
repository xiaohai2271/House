package cn.celess.house.controller.api;

import cn.celess.house.AbstractTest;
import cn.celess.house.dao.TodoTopicDao;
import cn.celess.house.entity.Response;
import cn.celess.house.entity.dto.TodoItemDTO;
import cn.celess.house.entity.vo.TodoItemVO;
import cn.celess.house.enums.ResponseEnum;
import cn.celess.house.util.StringsUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
class TodoItemControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TodoTopicDao todoTopicDao;

    @Test
    void create() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo/item/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(StringsUtil.toJson(createTodoItemDTO()))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    String s = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
                    Response response = StringsUtil.toObject(s, Response.class);
                    assertNotNull(response);
                    assertEquals(ResponseEnum.SUCCESS.getCode(), response.getCode());
                    TodoItemVO todoItemVO = StringsUtil.toObject(StringsUtil.toJson(response.getData()), TodoItemVO.class);
                    assertNotNull(todoItemVO);
                    assertNotNull(todoItemVO.getId());
                });
    }

    @Test
    void delete() {
    }

    @Test
    void testDelete() {
    }

    @Test
    void update() {
    }

    @Test
    void query() {
    }

    private TodoItemDTO createTodoItemDTO() {
        TodoItemDTO todoItemDTO = new TodoItemDTO();
        todoItemDTO.setCreateDate(new Date());
        todoItemDTO.setDescription(Mockito.anyString());
        todoItemDTO.setTitle("hello test");
        todoItemDTO.setTopicId(todoTopicDao.findAll().get(0).getId());
        return todoItemDTO;
    }
}
