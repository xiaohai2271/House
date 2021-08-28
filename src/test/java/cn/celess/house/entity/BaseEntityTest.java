package cn.celess.house.entity;

import cn.celess.house.AbstractTest;
import cn.celess.house.entity.dto.BaseDTO;
import cn.celess.house.entity.vo.BaseVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest extends AbstractTest {
    static class TestVO implements BaseVO<TestEntity> {
        private int id;
        private String name;
    }

    static class TestEntity implements BaseEntity<TestEntity, Integer>, BaseDTO<TestEntity> {
        private int id;
        private String name;

        @Override
        public Integer getPrimaryKey() {
            return id;
        }

        @Override
        public TestEntity toEntity() {
            return this;
        }

        @Override
        public TestVO toViewObject() {
            return copy(this, new TestVO());
        }
    }

    @Test
    void beanCopy() {
        TestEntity testEntity = new TestEntity();
        TestVO testVO = testEntity.toViewObject();
        assertEquals(testEntity.id, testVO.id);
        assertEquals(testEntity.name, testVO.name);
    }
}
