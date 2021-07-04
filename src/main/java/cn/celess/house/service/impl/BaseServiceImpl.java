package cn.celess.house.service.impl;

import cn.celess.house.entity.BaseEntity;
import cn.celess.house.entity.dto.BaseDTO;
import cn.celess.house.entity.vo.BaseVO;
import cn.celess.house.enums.ResponseEnum;
import cn.celess.house.exception.ResponseException;
import cn.celess.house.service.IBaseService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: 小海
 * @date： 2021/06/05 11:28
 * @description：
 */
public abstract class BaseServiceImpl<E extends BaseEntity<E, ID>, ID, VO extends BaseVO<E>, DTO extends BaseDTO<E>> implements IBaseService<E, ID, VO, DTO> {

    public abstract JpaRepository<E, ID> getJpaRepository();

    @Override
    public VO insert(DTO t) {
        checkNotNull(t);
        return getJpaRepository().save(t.toEntity()).toViewObject();
    }

    @Override
    public boolean remove(ID id) {
        checkNotNull(id);
        getJpaRepository().deleteById(id);
        return true;
    }

    @Override
    public boolean remove(ID[] ids) {
        checkNotNull(ids);
        getJpaRepository().deleteAllByIdInBatch(List.of(ids));
        return true;
    }

    @Override
    public VO update(DTO t) {
        checkNotNull(t);
        E entity = t.toEntity();
        if (entity.getPrimaryKey() != null) {
            return getJpaRepository().save(entity).toViewObject();
        }
        throw new ResponseException(ResponseEnum.PARAMETER_PK_NULL);
    }

    @Override
    public VO queryById(ID id) {
        checkNotNull(id);
        E e = getJpaRepository().findById(id).orElse(null);
        return e != null ? e.toViewObject() : null;
    }

    @Override
    public List<VO> queryAll() {
        return getJpaRepository().findAll()
                .stream()
                .map(e -> (VO) e.toViewObject())
                .collect(Collectors.toList());
    }

    private void checkNotNull(Object ob) {
        if (ob == null) {
            throw new ResponseException(ResponseEnum.PARAMETER_PK_NULL);
        }
    }
}
