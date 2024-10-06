package com.xweb.starter.modules.security.domain.entity;

import com.xweb.starter.common.dbmappingtypes.MultiLanguage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MastRoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MastRoleExample() {
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
        protected List<Criterion> mrNameCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
            mrNameCriteria = new ArrayList<>();
        }

        public List<Criterion> getMrNameCriteria() {
            return mrNameCriteria;
        }

        protected void addMrNameCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            mrNameCriteria.add(new Criterion(condition, value, "com.xweb.starter.common.typehandler.MultiLanguageTypeHandler"));
            allCriteria = null;
        }

        protected void addMrNameCriterion(String condition, MultiLanguage value1, MultiLanguage value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            mrNameCriteria.add(new Criterion(condition, value1, value2, "com.xweb.starter.common.typehandler.MultiLanguageTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0
                || mrNameCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(mrNameCriteria);
            }
            return allCriteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
            allCriteria = null;
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

        public Criteria andMrIdIsNull() {
            addCriterion("mr_id is null");
            return (Criteria) this;
        }

        public Criteria andMrIdIsNotNull() {
            addCriterion("mr_id is not null");
            return (Criteria) this;
        }

        public Criteria andMrIdEqualTo(String value) {
            addCriterion("mr_id =", value, "mrId");
            return (Criteria) this;
        }

        public Criteria andMrIdNotEqualTo(String value) {
            addCriterion("mr_id <>", value, "mrId");
            return (Criteria) this;
        }

        public Criteria andMrIdGreaterThan(String value) {
            addCriterion("mr_id >", value, "mrId");
            return (Criteria) this;
        }

        public Criteria andMrIdGreaterThanOrEqualTo(String value) {
            addCriterion("mr_id >=", value, "mrId");
            return (Criteria) this;
        }

        public Criteria andMrIdLessThan(String value) {
            addCriterion("mr_id <", value, "mrId");
            return (Criteria) this;
        }

        public Criteria andMrIdLessThanOrEqualTo(String value) {
            addCriterion("mr_id <=", value, "mrId");
            return (Criteria) this;
        }

        public Criteria andMrIdLike(String value) {
            addCriterion("mr_id like", value, "mrId");
            return (Criteria) this;
        }

        public Criteria andMrIdNotLike(String value) {
            addCriterion("mr_id not like", value, "mrId");
            return (Criteria) this;
        }

        public Criteria andMrIdIn(List<String> values) {
            addCriterion("mr_id in", values, "mrId");
            return (Criteria) this;
        }

        public Criteria andMrIdNotIn(List<String> values) {
            addCriterion("mr_id not in", values, "mrId");
            return (Criteria) this;
        }

        public Criteria andMrIdBetween(String value1, String value2) {
            addCriterion("mr_id between", value1, value2, "mrId");
            return (Criteria) this;
        }

        public Criteria andMrIdNotBetween(String value1, String value2) {
            addCriterion("mr_id not between", value1, value2, "mrId");
            return (Criteria) this;
        }

        public Criteria andMrNameIsNull() {
            addCriterion("mr_name is null");
            return (Criteria) this;
        }

        public Criteria andMrNameIsNotNull() {
            addCriterion("mr_name is not null");
            return (Criteria) this;
        }

        public Criteria andMrNameEqualTo(MultiLanguage value) {
            addMrNameCriterion("mr_name =", value, "mrName");
            return (Criteria) this;
        }

        public Criteria andMrNameNotEqualTo(MultiLanguage value) {
            addMrNameCriterion("mr_name <>", value, "mrName");
            return (Criteria) this;
        }

        public Criteria andMrNameGreaterThan(MultiLanguage value) {
            addMrNameCriterion("mr_name >", value, "mrName");
            return (Criteria) this;
        }

        public Criteria andMrNameGreaterThanOrEqualTo(MultiLanguage value) {
            addMrNameCriterion("mr_name >=", value, "mrName");
            return (Criteria) this;
        }

        public Criteria andMrNameLessThan(MultiLanguage value) {
            addMrNameCriterion("mr_name <", value, "mrName");
            return (Criteria) this;
        }

        public Criteria andMrNameLessThanOrEqualTo(MultiLanguage value) {
            addMrNameCriterion("mr_name <=", value, "mrName");
            return (Criteria) this;
        }

        public Criteria andMrNameIn(List<MultiLanguage> values) {
            addMrNameCriterion("mr_name in", values, "mrName");
            return (Criteria) this;
        }

        public Criteria andMrNameNotIn(List<MultiLanguage> values) {
            addMrNameCriterion("mr_name not in", values, "mrName");
            return (Criteria) this;
        }

        public Criteria andMrNameBetween(MultiLanguage value1, MultiLanguage value2) {
            addMrNameCriterion("mr_name between", value1, value2, "mrName");
            return (Criteria) this;
        }

        public Criteria andMrNameNotBetween(MultiLanguage value1, MultiLanguage value2) {
            addMrNameCriterion("mr_name not between", value1, value2, "mrName");
            return (Criteria) this;
        }

        public Criteria andMrEnableDeleteIsNull() {
            addCriterion("mr_enable_delete is null");
            return (Criteria) this;
        }

        public Criteria andMrEnableDeleteIsNotNull() {
            addCriterion("mr_enable_delete is not null");
            return (Criteria) this;
        }

        public Criteria andMrEnableDeleteEqualTo(Boolean value) {
            addCriterion("mr_enable_delete =", value, "mrEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMrEnableDeleteNotEqualTo(Boolean value) {
            addCriterion("mr_enable_delete <>", value, "mrEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMrEnableDeleteGreaterThan(Boolean value) {
            addCriterion("mr_enable_delete >", value, "mrEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMrEnableDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("mr_enable_delete >=", value, "mrEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMrEnableDeleteLessThan(Boolean value) {
            addCriterion("mr_enable_delete <", value, "mrEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMrEnableDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("mr_enable_delete <=", value, "mrEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMrEnableDeleteIn(List<Boolean> values) {
            addCriterion("mr_enable_delete in", values, "mrEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMrEnableDeleteNotIn(List<Boolean> values) {
            addCriterion("mr_enable_delete not in", values, "mrEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMrEnableDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("mr_enable_delete between", value1, value2, "mrEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMrEnableDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("mr_enable_delete not between", value1, value2, "mrEnableDelete");
            return (Criteria) this;
        }

        public Criteria andMrEnableEditIsNull() {
            addCriterion("mr_enable_edit is null");
            return (Criteria) this;
        }

        public Criteria andMrEnableEditIsNotNull() {
            addCriterion("mr_enable_edit is not null");
            return (Criteria) this;
        }

        public Criteria andMrEnableEditEqualTo(Boolean value) {
            addCriterion("mr_enable_edit =", value, "mrEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMrEnableEditNotEqualTo(Boolean value) {
            addCriterion("mr_enable_edit <>", value, "mrEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMrEnableEditGreaterThan(Boolean value) {
            addCriterion("mr_enable_edit >", value, "mrEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMrEnableEditGreaterThanOrEqualTo(Boolean value) {
            addCriterion("mr_enable_edit >=", value, "mrEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMrEnableEditLessThan(Boolean value) {
            addCriterion("mr_enable_edit <", value, "mrEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMrEnableEditLessThanOrEqualTo(Boolean value) {
            addCriterion("mr_enable_edit <=", value, "mrEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMrEnableEditIn(List<Boolean> values) {
            addCriterion("mr_enable_edit in", values, "mrEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMrEnableEditNotIn(List<Boolean> values) {
            addCriterion("mr_enable_edit not in", values, "mrEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMrEnableEditBetween(Boolean value1, Boolean value2) {
            addCriterion("mr_enable_edit between", value1, value2, "mrEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMrEnableEditNotBetween(Boolean value1, Boolean value2) {
            addCriterion("mr_enable_edit not between", value1, value2, "mrEnableEdit");
            return (Criteria) this;
        }

        public Criteria andMrWeightIsNull() {
            addCriterion("mr_weight is null");
            return (Criteria) this;
        }

        public Criteria andMrWeightIsNotNull() {
            addCriterion("mr_weight is not null");
            return (Criteria) this;
        }

        public Criteria andMrWeightEqualTo(Integer value) {
            addCriterion("mr_weight =", value, "mrWeight");
            return (Criteria) this;
        }

        public Criteria andMrWeightNotEqualTo(Integer value) {
            addCriterion("mr_weight <>", value, "mrWeight");
            return (Criteria) this;
        }

        public Criteria andMrWeightGreaterThan(Integer value) {
            addCriterion("mr_weight >", value, "mrWeight");
            return (Criteria) this;
        }

        public Criteria andMrWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("mr_weight >=", value, "mrWeight");
            return (Criteria) this;
        }

        public Criteria andMrWeightLessThan(Integer value) {
            addCriterion("mr_weight <", value, "mrWeight");
            return (Criteria) this;
        }

        public Criteria andMrWeightLessThanOrEqualTo(Integer value) {
            addCriterion("mr_weight <=", value, "mrWeight");
            return (Criteria) this;
        }

        public Criteria andMrWeightIn(List<Integer> values) {
            addCriterion("mr_weight in", values, "mrWeight");
            return (Criteria) this;
        }

        public Criteria andMrWeightNotIn(List<Integer> values) {
            addCriterion("mr_weight not in", values, "mrWeight");
            return (Criteria) this;
        }

        public Criteria andMrWeightBetween(Integer value1, Integer value2) {
            addCriterion("mr_weight between", value1, value2, "mrWeight");
            return (Criteria) this;
        }

        public Criteria andMrWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("mr_weight not between", value1, value2, "mrWeight");
            return (Criteria) this;
        }

        public Criteria andMrRemarkIsNull() {
            addCriterion("mr_remark is null");
            return (Criteria) this;
        }

        public Criteria andMrRemarkIsNotNull() {
            addCriterion("mr_remark is not null");
            return (Criteria) this;
        }

        public Criteria andMrRemarkEqualTo(String value) {
            addCriterion("mr_remark =", value, "mrRemark");
            return (Criteria) this;
        }

        public Criteria andMrRemarkNotEqualTo(String value) {
            addCriterion("mr_remark <>", value, "mrRemark");
            return (Criteria) this;
        }

        public Criteria andMrRemarkGreaterThan(String value) {
            addCriterion("mr_remark >", value, "mrRemark");
            return (Criteria) this;
        }

        public Criteria andMrRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("mr_remark >=", value, "mrRemark");
            return (Criteria) this;
        }

        public Criteria andMrRemarkLessThan(String value) {
            addCriterion("mr_remark <", value, "mrRemark");
            return (Criteria) this;
        }

        public Criteria andMrRemarkLessThanOrEqualTo(String value) {
            addCriterion("mr_remark <=", value, "mrRemark");
            return (Criteria) this;
        }

        public Criteria andMrRemarkLike(String value) {
            addCriterion("mr_remark like", value, "mrRemark");
            return (Criteria) this;
        }

        public Criteria andMrRemarkNotLike(String value) {
            addCriterion("mr_remark not like", value, "mrRemark");
            return (Criteria) this;
        }

        public Criteria andMrRemarkIn(List<String> values) {
            addCriterion("mr_remark in", values, "mrRemark");
            return (Criteria) this;
        }

        public Criteria andMrRemarkNotIn(List<String> values) {
            addCriterion("mr_remark not in", values, "mrRemark");
            return (Criteria) this;
        }

        public Criteria andMrRemarkBetween(String value1, String value2) {
            addCriterion("mr_remark between", value1, value2, "mrRemark");
            return (Criteria) this;
        }

        public Criteria andMrRemarkNotBetween(String value1, String value2) {
            addCriterion("mr_remark not between", value1, value2, "mrRemark");
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