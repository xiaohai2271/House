package cn.celess.house.util;

import cn.celess.house.entity.dto.TodoItemDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class StringsUtilTest {

    @Test
    void getMD5() {
        assertEquals("e10adc3949ba59abbe56e057f20f883e", StringsUtil.getMD5("123456"));
    }

    @Test
    void toJson() {
        StringsUtil.getAndSetDateFormat("yyyy-MM-dd HH:mm:ss");
        TodoItemDTO todoItemDTO = new TodoItemDTO();
        todoItemDTO.setId(1);
        todoItemDTO.setCreateDate(new Date(1622904575221L));
        todoItemDTO.setDescription(Mockito.anyString());
        todoItemDTO.setTitle("hello test");
        todoItemDTO.setTopicId(1);
        String s = StringsUtil.toJson(todoItemDTO);
        assertEquals(
                "{\"id\":1,\"title\":\"hello test\",\"description\":\"\",\"topicId\":1,\"createDate\":\"2021-06-05 22:49:35\",\"completeDate\":null,\"deadlineDate\":null,\"done\":null}"
                , s);
    }

    @Test
    void toObject() {
        StringsUtil.getAndSetDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = "{\"id\":1,\"title\":\"hello test\",\"description\":\"\",\"topicId\":1,\"createDate\":\"2021-06-05 22:49:35\",\"completeDate\":null,\"deadlineDate\":null}";
        TodoItemDTO obj = StringsUtil.toObject(s, TodoItemDTO.class);
        assertNotNull(obj);
        assertEquals(1, obj.getId());
        assertEquals(new Date(1622904575000L).getTime(), obj.getCreateDate().getTime());
        assertEquals("", obj.getDescription());
        assertEquals("hello test", obj.getTitle());
        assertEquals(1, obj.getTopicId());
        assertNull(obj.getCompleteDate());
    }
}
