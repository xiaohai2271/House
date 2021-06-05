package cn.celess.house.service.impl;

import cn.celess.house.entity.BaseEntity;
import cn.celess.house.enums.ResponseEnum;
import cn.celess.house.exception.ResponseException;
import cn.celess.house.service.IBaseService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

/**
 * @author: 小海
 * @date： 2021/06/05 11:28
 * @description：
 */
public class BaseServiceImpl<T extends BaseEntity<?, ID>, ID, DAO extends JpaRepository<T, ID>> implements IBaseService<T, ID> {
    private DAO repository;

    public BaseServiceImpl() {
    }

    public BaseServiceImpl(DAO repository) {
        this.repository = repository;
    }

    @Override
    public T insert(T t) {
        return repository.save(t);
    }

    @Override
    public boolean remove(ID id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean remove(ID[] ids) {
        repository.deleteAllByIdInBatch(List.of(ids));
        return true;
    }

    @Override
    public T update(T t) {
        if (t.getPrimaryKey() != null) {
            return repository.save(t);
        }
        throw new ResponseException(ResponseEnum.PARAMETER_PK_NULL);
    }

    @Override
    public T queryById(ID id) {
        if (id == null) {
            throw new ResponseException(ResponseEnum.PARAMETER_PK_NULL);
        }
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<T> queryAll() {
        return repository.findAll();
    }
}
