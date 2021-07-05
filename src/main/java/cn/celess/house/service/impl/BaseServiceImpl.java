package cn.celess.house.service.impl;

import cn.celess.house.entity.BaseEntity;
import cn.celess.house.entity.dto.BaseDTO;
import cn.celess.house.entity.vo.BaseVO;
import cn.celess.house.enums.ResponseEnum;
import cn.celess.house.exception.ResponseException;
import cn.celess.house.service.IBaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: 小海
 * @date： 2021/06/05 11:28
 * @description：
 */
public abstract class BaseServiceImpl<E extends BaseEntity<E, ID>, ID, VO extends BaseVO<E>, DTO extends BaseDTO<E>> implements IBaseService<E, ID, VO, DTO> {

    public abstract JpaRepository<E, ID> getJpaRepository();

    protected final Function<E, VO> defaultConsumer = E::toViewObject;

    public VO afterExecution(E entity, Function<E, VO> function) {
        // just for fuck jpa many relationship
        // if any good idea, I will try to replace this
        return function.apply(entity);
    }

    @Override
    @Transactional
    public VO insert(DTO t) {
        checkNotNull(t);
        return afterExecution(getJpaRepository().save(t.toEntity()), defaultConsumer);
    }

    @Override
    @Transactional
    public boolean remove(ID id) {
        checkNotNull(id);
        getJpaRepository().deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(ID[] ids) {
        checkNotNull(ids);
        getJpaRepository().deleteAllByIdInBatch(List.of(ids));
        return true;
    }

    @Override
    @Transactional
    public VO update(DTO t) {
        checkNotNull(t);
        E entity = t.toEntity();
        if (entity.getPrimaryKey() != null) {
            return afterExecution(getJpaRepository().save(entity), defaultConsumer);
        }
        throw new ResponseException(ResponseEnum.PARAMETER_PK_NULL);
    }

    @Override
    @Transactional
    public VO queryById(ID id) {
        checkNotNull(id);
        E e = getJpaRepository().findById(id).orElse(null);
        return e != null ? afterExecution(e, defaultConsumer) : null;
    }

    @Override
    @Transactional
    public List<VO> queryAll() {
        return getJpaRepository().findAll()
                .stream()
                .map(e -> afterExecution(e, defaultConsumer))
                .collect(Collectors.toList());
    }

    private void checkNotNull(Object ob) {
        if (ob == null) {
            throw new ResponseException(ResponseEnum.PARAMETER_PK_NULL);
        }
    }
}
