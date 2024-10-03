package com.xweb.starter.modules.security.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MastMenuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MastMenuExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMmIdIsNull() {
            addCriterion("mm_id is null");
            return (Criteria) this;
        }

        public Criteria andMmIdIsNotNull() {
            addCriterion("mm_id is not null");
            return (Criteria) this;
        }

        public Criteria andMmIdEqualTo(Integer value) {
            addCriterion("mm_id =", value, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmIdNotEqualTo(Integer value) {
            addCriterion("mm_id <>", value, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmIdGreaterThan(Integer value) {
            addCriterion("mm_id >", value, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mm_id >=", value, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmIdLessThan(Integer value) {
            addCriterion("mm_id <", value, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmIdLessThanOrEqualTo(Integer value) {
            addCriterion("mm_id <=", value, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmIdIn(List<Integer> values) {
            addCriterion("mm_id in", values, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmIdNotIn(List<Integer> values) {
            addCriterion("mm_id not in", values, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmIdBetween(Integer value1, Integer value2) {
            addCriterion("mm_id between", value1, value2, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mm_id not between", value1, value2, "mmId");
            return (Criteria) this;
        }

        public Criteria andMmParentIdIsNull() {
            addCriterion("mm_parent_id is null");
            return (Criteria) this;
        }

        public Criteria andMmParentIdIsNotNull() {
            addCriterion("mm_parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andMmParentIdEqualTo(Integer value) {
            addCriterion("mm_parent_id =", value, "mmParentId");
            return (Criteria) this;
        }

        public Criteria andMmParentIdNotEqualTo(Integer value) {
            addCriterion("mm_parent_id <>", value, "mmParentId");
            return (Criteria) this;
        }

        public Criteria andMmParentIdGreaterThan(Integer value) {
            addCriterion("mm_parent_id >", value, "mmParentId");
            return (Criteria) this;
        }

        public Criteria andMmParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mm_parent_id >=", value, "mmParentId");
            return (Criteria) this;
        }

        public Criteria andMmParentIdLessThan(Integer value) {
            addCriterion("mm_parent_id <", value, "mmParentId");
            return (Criteria) this;
        }

        public Criteria andMmParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("mm_parent_id <=", value, "mmParentId");
            return (Criteria) this;
        }

        public Criteria andMmParentIdIn(List<Integer> values) {
            addCriterion("mm_parent_id in", values, "mmParentId");
            return (Criteria) this;
        }

        public Criteria andMmParentIdNotIn(List<Integer> values) {
            addCriterion("mm_parent_id not in", values, "mmParentId");
            return (Criteria) this;
        }

        public Criteria andMmParentIdBetween(Integer value1, Integer value2) {
            addCriterion("mm_parent_id between", value1, value2, "mmParentId");
            return (Criteria) this;
        }

        public Criteria andMmParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mm_parent_id not between", value1, value2, "mmParentId");
            return (Criteria) this;
        }

        public Criteria andMmTypeIsNull() {
            addCriterion("mm_type is null");
            return (Criteria) this;
        }

        public Criteria andMmTypeIsNotNull() {
            addCriterion("mm_type is not null");
            return (Criteria) this;
        }

        public Criteria andMmTypeEqualTo(Short value) {
            addCriterion("mm_type =", value, "mmType");
            return (Criteria) this;
        }

        public Criteria andMmTypeNotEqualTo(Short value) {
            addCriterion("mm_type <>", value, "mmType");
            return (Criteria) this;
        }

        public Criteria andMmTypeGreaterThan(Short value) {
            addCriterion("mm_type >", value, "mmType");
            return (Criteria) this;
        }

        public Criteria andMmTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("mm_type >=", value, "mmType");
            return (Criteria) this;
        }

        public Criteria andMmTypeLessThan(Short value) {
            addCriterion("mm_type <", value, "mmType");
            return (Criteria) this;
        }

        public Criteria andMmTypeLessThanOrEqualTo(Short value) {
            addCriterion("mm_type <=", value, "mmType");
            return (Criteria) this;
        }

        public Criteria andMmTypeIn(List<Short> values) {
            addCriterion("mm_type in", values, "mmType");
            return (Criteria) this;
        }

        public Criteria andMmTypeNotIn(List<Short> values) {
            addCriterion("mm_type not in", values, "mmType");
            return (Criteria) this;
        }

        public Criteria andMmTypeBetween(Short value1, Short value2) {
            addCriterion("mm_type between", value1, value2, "mmType");
            return (Criteria) this;
        }

        public Criteria andMmTypeNotBetween(Short value1, Short value2) {
            addCriterion("mm_type not between", value1, value2, "mmType");
            return (Criteria) this;
        }

        public Criteria andMmPathIsNull() {
            addCriterion("mm_path is null");
            return (Criteria) this;
        }

        public Criteria andMmPathIsNotNull() {
            addCriterion("mm_path is not null");
            return (Criteria) this;
        }

        public Criteria andMmPathEqualTo(String value) {
            addCriterion("mm_path =", value, "mmPath");
            return (Criteria) this;
        }

        public Criteria andMmPathNotEqualTo(String value) {
            addCriterion("mm_path <>", value, "mmPath");
            return (Criteria) this;
        }

        public Criteria andMmPathGreaterThan(String value) {
            addCriterion("mm_path >", value, "mmPath");
            return (Criteria) this;
        }

        public Criteria andMmPathGreaterThanOrEqualTo(String value) {
            addCriterion("mm_path >=", value, "mmPath");
            return (Criteria) this;
        }

        public Criteria andMmPathLessThan(String value) {
            addCriterion("mm_path <", value, "mmPath");
            return (Criteria) this;
        }

        public Criteria andMmPathLessThanOrEqualTo(String value) {
            addCriterion("mm_path <=", value, "mmPath");
            return (Criteria) this;
        }

        public Criteria andMmPathLike(String value) {
            addCriterion("mm_path like", value, "mmPath");
            return (Criteria) this;
        }

        public Criteria andMmPathNotLike(String value) {
            addCriterion("mm_path not like", value, "mmPath");
            return (Criteria) this;
        }

        public Criteria andMmPathIn(List<String> values) {
            addCriterion("mm_path in", values, "mmPath");
            return (Criteria) this;
        }

        public Criteria andMmPathNotIn(List<String> values) {
            addCriterion("mm_path not in", values, "mmPath");
            return (Criteria) this;
        }

        public Criteria andMmPathBetween(String value1, String value2) {
            addCriterion("mm_path between", value1, value2, "mmPath");
            return (Criteria) this;
        }

        public Criteria andMmPathNotBetween(String value1, String value2) {
            addCriterion("mm_path not between", value1, value2, "mmPath");
            return (Criteria) this;
        }

        public Criteria andMmStateIsNull() {
            addCriterion("mm_state is null");
            return (Criteria) this;
        }

        public Criteria andMmStateIsNotNull() {
            addCriterion("mm_state is not null");
            return (Criteria) this;
        }

        public Criteria andMmStateEqualTo(Short value) {
            addCriterion("mm_state =", value, "mmState");
            return (Criteria) this;
        }

        public Criteria andMmStateNotEqualTo(Short value) {
            addCriterion("mm_state <>", value, "mmState");
            return (Criteria) this;
        }

        public Criteria andMmStateGreaterThan(Short value) {
            addCriterion("mm_state >", value, "mmState");
            return (Criteria) this;
        }

        public Criteria andMmStateGreaterThanOrEqualTo(Short value) {
            addCriterion("mm_state >=", value, "mmState");
            return (Criteria) this;
        }

        public Criteria andMmStateLessThan(Short value) {
            addCriterion("mm_state <", value, "mmState");
            return (Criteria) this;
        }

        public Criteria andMmStateLessThanOrEqualTo(Short value) {
            addCriterion("mm_state <=", value, "mmState");
            return (Criteria) this;
        }

        public Criteria andMmStateIn(List<Short> values) {
            addCriterion("mm_state in", values, "mmState");
            return (Criteria) this;
        }

        public Criteria andMmStateNotIn(List<Short> values) {
            addCriterion("mm_state not in", values, "mmState");
            return (Criteria) this;
        }

        public Criteria andMmStateBetween(Short value1, Short value2) {
            addCriterion("mm_state between", value1, value2, "mmState");
            return (Criteria) this;
        }

        public Criteria andMmStateNotBetween(Short value1, Short value2) {
            addCriterion("mm_state not between", value1, value2, "mmState");
            return (Criteria) this;
        }

        public Criteria andMmIconIsNull() {
            addCriterion("mm_icon is null");
            return (Criteria) this;
        }

        public Criteria andMmIconIsNotNull() {
            addCriterion("mm_icon is not null");
            return (Criteria) this;
        }

        public Criteria andMmIconEqualTo(String value) {
            addCriterion("mm_icon =", value, "mmIcon");
            return (Criteria) this;
        }

        public Criteria andMmIconNotEqualTo(String value) {
            addCriterion("mm_icon <>", value, "mmIcon");
            return (Criteria) this;
        }

        public Criteria andMmIconGreaterThan(String value) {
            addCriterion("mm_icon >", value, "mmIcon");
            return (Criteria) this;
        }

        public Criteria andMmIconGreaterThanOrEqualTo(String value) {
            addCriterion("mm_icon >=", value, "mmIcon");
            return (Criteria) this;
        }

        public Criteria andMmIconLessThan(String value) {
            addCriterion("mm_icon <", value, "mmIcon");
            return (Criteria) this;
        }

        public Criteria andMmIconLessThanOrEqualTo(String value) {
            addCriterion("mm_icon <=", value, "mmIcon");
            return (Criteria) this;
        }

        public Criteria andMmIconLike(String value) {
            addCriterion("mm_icon like", value, "mmIcon");
            return (Criteria) this;
        }

        public Criteria andMmIconNotLike(String value) {
            addCriterion("mm_icon not like", value, "mmIcon");
            return (Criteria) this;
        }

        public Criteria andMmIconIn(List<String> values) {
            addCriterion("mm_icon in", values, "mmIcon");
            return (Criteria) this;
        }

        public Criteria andMmIconNotIn(List<String> values) {
            addCriterion("mm_icon not in", values, "mmIcon");
            return (Criteria) this;
        }

        public Criteria andMmIconBetween(String value1, String value2) {
            addCriterion("mm_icon between", value1, value2, "mmIcon");
            return (Criteria) this;
        }

        public Criteria andMmIconNotBetween(String value1, String value2) {
            addCriterion("mm_icon not between", value1, value2, "mmIcon");
            return (Criteria) this;
        }

        public Criteria andMmNameIsNull() {
            addCriterion("mm_name is null");
            return (Criteria) this;
        }

        public Criteria andMmNameIsNotNull() {
            addCriterion("mm_name is not null");
            return (Criteria) this;
        }

        public Criteria andMmNameEqualTo(Object value) {
            addCriterion("mm_name =", value, "mmName");
            return (Criteria) this;
        }

        public Criteria andMmNameNotEqualTo(Object value) {
            addCriterion("mm_name <>", value, "mmName");
            return (Criteria) this;
        }

        public Criteria andMmNameGreaterThan(Object value) {
            addCriterion("mm_name >", value, "mmName");
            return (Criteria) this;
        }

        public Criteria andMmNameGreaterThanOrEqualTo(Object value) {
            addCriterion("mm_name >=", value, "mmName");
            return (Criteria) this;
        }

        public Criteria andMmNameLessThan(Object value) {
            addCriterion("mm_name <", value, "mmName");
            return (Criteria) this;
        }

        public Criteria andMmNameLessThanOrEqualTo(Object value) {
            addCriterion("mm_name <=", value, "mmName");
            return (Criteria) this;
        }

        public Criteria andMmNameIn(List<Object> values) {
            addCriterion("mm_name in", values, "mmName");
            return (Criteria) this;
        }

        public Criteria andMmNameNotIn(List<Object> values) {
            addCriterion("mm_name not in", values, "mmName");
            return (Criteria) this;
        }

        public Criteria andMmNameBetween(Object value1, Object value2) {
            addCriterion("mm_name between", value1, value2, "mmName");
            return (Criteria) this;
        }

        public Criteria andMmNameNotBetween(Object value1, Object value2) {
            addCriterion("mm_name not between", value1, value2, "mmName");
            return (Criteria) this;
        }

        public Criteria andMmCodeIsNull() {
            addCriterion("mm_code is null");
            return (Criteria) this;
        }

        public Criteria andMmCodeIsNotNull() {
            addCriterion("mm_code is not null");
            return (Criteria) this;
        }

        public Criteria andMmCodeEqualTo(String value) {
            addCriterion("mm_code =", value, "mmCode");
            return (Criteria) this;
        }

        public Criteria andMmCodeNotEqualTo(String value) {
            addCriterion("mm_code <>", value, "mmCode");
            return (Criteria) this;
        }

        public Criteria andMmCodeGreaterThan(String value) {
            addCriterion("mm_code >", value, "mmCode");
            return (Criteria) this;
        }

        public Criteria andMmCodeGreaterThanOrEqualTo(String value) {
            addCriterion("mm_code >=", value, "mmCode");
            return (Criteria) this;
        }

        public Criteria andMmCodeLessThan(String value) {
            addCriterion("mm_code <", value, "mmCode");
            return (Criteria) this;
        }

        public Criteria andMmCodeLessThanOrEqualTo(String value) {
            addCriterion("mm_code <=", value, "mmCode");
            return (Criteria) this;
        }

        public Criteria andMmCodeLike(String value) {
            addCriterion("mm_code like", value, "mmCode");
            return (Criteria) this;
        }

        public Criteria andMmCodeNotLike(String value) {
            addCriterion("mm_code not like", value, "mmCode");
            return (Criteria) this;
        }

        public Criteria andMmCodeIn(List<String> values) {
            addCriterion("mm_code in", values, "mmCode");
            return (Criteria) this;
        }

        public Criteria andMmCodeNotIn(List<String> values) {
            addCriterion("mm_code not in", values, "mmCode");
            return (Criteria) this;
        }

        public Criteria andMmCodeBetween(String value1, String value2) {
            addCriterion("mm_code between", value1, value2, "mmCode");
            return (Criteria) this;
        }

        public Criteria andMmCodeNotBetween(String value1, String value2) {
            addCriterion("mm_code not between", value1, value2, "mmCode");
            return (Criteria) this;
        }

        public Criteria andMmLevelChainIsNull() {
            addCriterion("mm_level_chain is null");
            return (Criteria) this;
        }

        public Criteria andMmLevelChainIsNotNull() {
            addCriterion("mm_level_chain is not null");
            return (Criteria) this;
        }

        public Criteria andMmLevelChainEqualTo(String value) {
            addCriterion("mm_level_chain =", value, "mmLevelChain");
            return (Criteria) this;
        }

        public Criteria andMmLevelChainNotEqualTo(String value) {
            addCriterion("mm_level_chain <>", value, "mmLevelChain");
            return (Criteria) this;
        }

        public Criteria andMmLevelChainGreaterThan(String value) {
            addCriterion("mm_level_chain >", value, "mmLevelChain");
            return (Criteria) this;
        }

        public Criteria andMmLevelChainGreaterThanOrEqualTo(String value) {
            addCriterion("mm_level_chain >=", value, "mmLevelChain");
            return (Criteria) this;
        }

        public Criteria andMmLevelChainLessThan(String value) {
            addCriterion("mm_level_chain <", value, "mmLevelChain");
            return (Criteria) this;
        }

        public Criteria andMmLevelChainLessThanOrEqualTo(String value) {
            addCriterion("mm_level_chain <=", value, "mmLevelChain");
            return (Criteria) this;
        }

        public Criteria andMmLevelChainLike(String value) {
            addCriterion("mm_level_chain like", value, "mmLevelChain");
            return (Criteria) this;
        }

        public Criteria andMmLevelChainNotLike(String value) {
            addCriterion("mm_level_chain not like", value, "mmLevelChain");
            return (Criteria) this;
        }

        public Criteria andMmLevelChainIn(List<String> values) {
            addCriterion("mm_level_chain in", values, "mmLevelChain");
            return (Criteria) this;
        }

        public Criteria andMmLevelChainNotIn(List<String> values) {
            addCriterion("mm_level_chain not in", values, "mmLevelChain");
            return (Criteria) this;
        }

        public Criteria andMmLevelChainBetween(String value1, String value2) {
            addCriterion("mm_level_chain between", value1, value2, "mmLevelChain");
            return (Criteria) this;
        }

        public Criteria andMmLevelChainNotBetween(String value1, String value2) {
            addCriterion("mm_level_chain not between", value1, value2, "mmLevelChain");
            return (Criteria) this;
        }

        public Criteria andMmLevelIsNull() {
            addCriterion("mm_level is null");
            return (Criteria) this;
        }

        public Criteria andMmLevelIsNotNull() {
            addCriterion("mm_level is not null");
            return (Criteria) this;
        }

        public Criteria andMmLevelEqualTo(Integer value) {
            addCriterion("mm_level =", value, "mmLevel");
            return (Criteria) this;
        }

        public Criteria andMmLevelNotEqualTo(Integer value) {
            addCriterion("mm_level <>", value, "mmLevel");
            return (Criteria) this;
        }

        public Criteria andMmLevelGreaterThan(Integer value) {
            addCriterion("mm_level >", value, "mmLevel");
            return (Criteria) this;
        }

        public Criteria andMmLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("mm_level >=", value, "mmLevel");
            return (Criteria) this;
        }

        public Criteria andMmLevelLessThan(Integer value) {
            addCriterion("mm_level <", value, "mmLevel");
            return (Criteria) this;
        }

        public Criteria andMmLevelLessThanOrEqualTo(Integer value) {
            addCriterion("mm_level <=", value, "mmLevel");
            return (Criteria) this;
        }

        public Criteria andMmLevelIn(List<Integer> values) {
            addCriterion("mm_level in", values, "mmLevel");
            return (Criteria) this;
        }

        public Criteria andMmLevelNotIn(List<Integer> values) {
            addCriterion("mm_level not in", values, "mmLevel");
            return (Criteria) this;
        }

        public Criteria andMmLevelBetween(Integer value1, Integer value2) {
            addCriterion("mm_level between", value1, value2, "mmLevel");
            return (Criteria) this;
        }

        public Criteria andMmLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("mm_level not between", value1, value2, "mmLevel");
            return (Criteria) this;
        }

        public Criteria andMmSortIsNull() {
            addCriterion("mm_sort is null");
            return (Criteria) this;
        }

        public Criteria andMmSortIsNotNull() {
            addCriterion("mm_sort is not null");
            return (Criteria) this;
        }

        public Criteria andMmSortEqualTo(Integer value) {
            addCriterion("mm_sort =", value, "mmSort");
            return (Criteria) this;
        }

        public Criteria andMmSortNotEqualTo(Integer value) {
            addCriterion("mm_sort <>", value, "mmSort");
            return (Criteria) this;
        }

        public Criteria andMmSortGreaterThan(Integer value) {
            addCriterion("mm_sort >", value, "mmSort");
            return (Criteria) this;
        }

        public Criteria andMmSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("mm_sort >=", value, "mmSort");
            return (Criteria) this;
        }

        public Criteria andMmSortLessThan(Integer value) {
            addCriterion("mm_sort <", value, "mmSort");
            return (Criteria) this;
        }

        public Criteria andMmSortLessThanOrEqualTo(Integer value) {
            addCriterion("mm_sort <=", value, "mmSort");
            return (Criteria) this;
        }

        public Criteria andMmSortIn(List<Integer> values) {
            addCriterion("mm_sort in", values, "mmSort");
            return (Criteria) this;
        }

        public Criteria andMmSortNotIn(List<Integer> values) {
            addCriterion("mm_sort not in", values, "mmSort");
            return (Criteria) this;
        }

        public Criteria andMmSortBetween(Integer value1, Integer value2) {
            addCriterion("mm_sort between", value1, value2, "mmSort");
            return (Criteria) this;
        }

        public Criteria andMmSortNotBetween(Integer value1, Integer value2) {
            addCriterion("mm_sort not between", value1, value2, "mmSort");
            return (Criteria) this;
        }

        public Criteria andMmRemarkIsNull() {
            addCriterion("mm_remark is null");
            return (Criteria) this;
        }

        public Criteria andMmRemarkIsNotNull() {
            addCriterion("mm_remark is not null");
            return (Criteria) this;
        }

        public Criteria andMmRemarkEqualTo(String value) {
            addCriterion("mm_remark =", value, "mmRemark");
            return (Criteria) this;
        }

        public Criteria andMmRemarkNotEqualTo(String value) {
            addCriterion("mm_remark <>", value, "mmRemark");
            return (Criteria) this;
        }

        public Criteria andMmRemarkGreaterThan(String value) {
            addCriterion("mm_remark >", value, "mmRemark");
            return (Criteria) this;
        }

        public Criteria andMmRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("mm_remark >=", value, "mmRemark");
            return (Criteria) this;
        }

        public Criteria andMmRemarkLessThan(String value) {
            addCriterion("mm_remark <", value, "mmRemark");
            return (Criteria) this;
        }

        public Criteria andMmRemarkLessThanOrEqualTo(String value) {
            addCriterion("mm_remark <=", value, "mmRemark");
            return (Criteria) this;
        }

        public Criteria andMmRemarkLike(String value) {
            addCriterion("mm_remark like", value, "mmRemark");
            return (Criteria) this;
        }

        public Criteria andMmRemarkNotLike(String value) {
            addCriterion("mm_remark not like", value, "mmRemark");
            return (Criteria) this;
        }

        public Criteria andMmRemarkIn(List<String> values) {
            addCriterion("mm_remark in", values, "mmRemark");
            return (Criteria) this;
        }

        public Criteria andMmRemarkNotIn(List<String> values) {
            addCriterion("mm_remark not in", values, "mmRemark");
            return (Criteria) this;
        }

        public Criteria andMmRemarkBetween(String value1, String value2) {
            addCriterion("mm_remark between", value1, value2, "mmRemark");
            return (Criteria) this;
        }

        public Criteria andMmRemarkNotBetween(String value1, String value2) {
            addCriterion("mm_remark not between", value1, value2, "mmRemark");
            return (Criteria) this;
        }

        public Criteria andMmEnableEditIsNull() {
            addCriterion("mm_enable_edit is null");
            return (Criteria) this;
        }

        public Criteria andMmEnableEditIsNotNull() {
            addCriterion("mm_enable_edit is not null");
            return (Criteria) this;
        }

        public Criteria andMmEnableEditEqualTo(Boolean value) {
            addCriterion("mm_enable_edit =", value, "mmEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMmEnableEditNotEqualTo(Boolean value) {
            addCriterion("mm_enable_edit <>", value, "mmEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMmEnableEditGreaterThan(Boolean value) {
            addCriterion("mm_enable_edit >", value, "mmEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMmEnableEditGreaterThanOrEqualTo(Boolean value) {
            addCriterion("mm_enable_edit >=", value, "mmEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMmEnableEditLessThan(Boolean value) {
            addCriterion("mm_enable_edit <", value, "mmEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMmEnableEditLessThanOrEqualTo(Boolean value) {
            addCriterion("mm_enable_edit <=", value, "mmEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMmEnableEditIn(List<Boolean> values) {
            addCriterion("mm_enable_edit in", values, "mmEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMmEnableEditNotIn(List<Boolean> values) {
            addCriterion("mm_enable_edit not in", values, "mmEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMmEnableEditBetween(Boolean value1, Boolean value2) {
            addCriterion("mm_enable_edit between", value1, value2, "mmEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMmEnableEditNotBetween(Boolean value1, Boolean value2) {
            addCriterion("mm_enable_edit not between", value1, value2, "mmEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMmEnableDeleteIsNull() {
            addCriterion("mm_enable_delete is null");
            return (Criteria) this;
        }

        public Criteria andMmEnableDeleteIsNotNull() {
            addCriterion("mm_enable_delete is not null");
            return (Criteria) this;
        }

        public Criteria andMmEnableDeleteEqualTo(Boolean value) {
            addCriterion("mm_enable_delete =", value, "mmEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMmEnableDeleteNotEqualTo(Boolean value) {
            addCriterion("mm_enable_delete <>", value, "mmEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMmEnableDeleteGreaterThan(Boolean value) {
            addCriterion("mm_enable_delete >", value, "mmEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMmEnableDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("mm_enable_delete >=", value, "mmEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMmEnableDeleteLessThan(Boolean value) {
            addCriterion("mm_enable_delete <", value, "mmEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMmEnableDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("mm_enable_delete <=", value, "mmEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMmEnableDeleteIn(List<Boolean> values) {
            addCriterion("mm_enable_delete in", values, "mmEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMmEnableDeleteNotIn(List<Boolean> values) {
            addCriterion("mm_enable_delete not in", values, "mmEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMmEnableDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("mm_enable_delete between", value1, value2, "mmEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMmEnableDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("mm_enable_delete not between", value1, value2, "mmEnableDelete");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
