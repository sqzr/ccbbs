package com.sqzr.ccbbs.core.service;

import com.sqzr.ccbbs.core.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 基础service类,所有的service继承自此方法,提供了一些比较常用的方法
 *
 * @author weiyang
 */
public abstract class BaseService<T, ID extends Serializable> {
    // 需在具体的类里复写
    abstract protected BaseRepository<T, ID> getEntityRepository();

    @Transactional
    @Modifying
    public T update(T entity) {
        return getEntityRepository().saveAndFlush(entity);
    }

    @Transactional
    @Modifying
    public T save(T entity) {
        return getEntityRepository().save(entity);
    }

    @Transactional
    @Modifying
    public void delete(ID id) {
        getEntityRepository().delete(id);
    }

    public Long count(Specification<T> specification) {
        return getEntityRepository().count(specification);
    }

    public T findOneById(ID id) {
        return getEntityRepository().findOne(id);
    }

    public T findById(ID id) {
        return getEntityRepository().findOne(id);
    }

    public List<T> findAll() {
        return getEntityRepository().findAll();
    }

    public List<T> findAll(Iterable<ID> ids) {
        return getEntityRepository().findAll(ids);
    }

    public List<T> findAll(Sort sort) {
        return getEntityRepository().findAll(sort);
    }

    public List<T> findAll(Specification<T> specification) {
        return getEntityRepository().findAll(specification);
    }


    public List<T> findAll(Specification<T> specification, Sort sort) {
        return getEntityRepository().findAll(specification, sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return getEntityRepository().findAll(pageable);
    }

    public Page<T> findAll(Specification<T> specification, Pageable pageable) {
        return getEntityRepository().findAll(specification, pageable);
    }

    public Page<T> findAll(Specification<T> specification, PageRequest pageRequest) {
        return getEntityRepository().findAll(specification, pageRequest);
    }

}
