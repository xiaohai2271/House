package cn.celess.house.entity;

import cn.celess.house.AbstractTest;
import cn.celess.house.entity.vo.BaseVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest extends AbstractTest {
    static class TestVO extends BaseVO<TestEntity> {
        private int id;
        private String name;
    }

    static class TestEntity extends BaseEntity<TestEntity, Integer> {
        private int id;
        private String name;

        @Override
        public Integer getPrimaryKey() {
            return id;
        }

        @Override
//      或者  public TestVO toViewObject() {
        public BaseVO<TestEntity> toViewObject() {
            return beanCopy(this, new TestVO());
        }
    }

    @Test
    void beanCopy() {
        TestEntity testEntity = new TestEntity();
        TestVO testVO = (TestVO) testEntity.toViewObject();
        assertEquals(testEntity.id, testVO.id);
        assertEquals(testEntity.name, testVO.name);
    }
}
