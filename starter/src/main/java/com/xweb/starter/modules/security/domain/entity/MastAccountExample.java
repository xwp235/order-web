package com.xweb.starter.modules.security.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MastAccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MastAccountExample() {
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

        public Criteria andAvatarIsNull() {
            addCriterion("avatar is null");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNotNull() {
            addCriterion("avatar is not null");
            return (Criteria) this;
        }

        public Criteria andAvatarEqualTo(String value) {
            addCriterion("avatar =", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotEqualTo(String value) {
            addCriterion("avatar <>", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThan(String value) {
            addCriterion("avatar >", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThanOrEqualTo(String value) {
            addCriterion("avatar >=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThan(String value) {
            addCriterion("avatar <", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThanOrEqualTo(String value) {
            addCriterion("avatar <=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLike(String value) {
            addCriterion("avatar like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotLike(String value) {
            addCriterion("avatar not like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarIn(List<String> values) {
            addCriterion("avatar in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotIn(List<String> values) {
            addCriterion("avatar not in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarBetween(String value1, String value2) {
            addCriterion("avatar between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotBetween(String value1, String value2) {
            addCriterion("avatar not between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("\"password\" is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("\"password\" is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("\"password\" =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("\"password\" <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("\"password\" >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("\"password\" >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("\"password\" <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("\"password\" <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("\"password\" like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("\"password\" not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("\"password\" in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("\"password\" not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("\"password\" between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("\"password\" not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andAccountExpiredIsNull() {
            addCriterion("account_expired is null");
            return (Criteria) this;
        }

        public Criteria andAccountExpiredIsNotNull() {
            addCriterion("account_expired is not null");
            return (Criteria) this;
        }

        public Criteria andAccountExpiredEqualTo(Boolean value) {
            addCriterion("account_expired =", value, "accountExpired");
            return (Criteria) this;
        }

        public Criteria andAccountExpiredNotEqualTo(Boolean value) {
            addCriterion("account_expired <>", value, "accountExpired");
            return (Criteria) this;
        }

        public Criteria andAccountExpiredGreaterThan(Boolean value) {
            addCriterion("account_expired >", value, "accountExpired");
            return (Criteria) this;
        }

        public Criteria andAccountExpiredGreaterThanOrEqualTo(Boolean value) {
            addCriterion("account_expired >=", value, "accountExpired");
            return (Criteria) this;
        }

        public Criteria andAccountExpiredLessThan(Boolean value) {
            addCriterion("account_expired <", value, "accountExpired");
            return (Criteria) this;
        }

        public Criteria andAccountExpiredLessThanOrEqualTo(Boolean value) {
            addCriterion("account_expired <=", value, "accountExpired");
            return (Criteria) this;
        }

        public Criteria andAccountExpiredIn(List<Boolean> values) {
            addCriterion("account_expired in", values, "accountExpired");
            return (Criteria) this;
        }

        public Criteria andAccountExpiredNotIn(List<Boolean> values) {
            addCriterion("account_expired not in", values, "accountExpired");
            return (Criteria) this;
        }

        public Criteria andAccountExpiredBetween(Boolean value1, Boolean value2) {
            addCriterion("account_expired between", value1, value2, "accountExpired");
            return (Criteria) this;
        }

        public Criteria andAccountExpiredNotBetween(Boolean value1, Boolean value2) {
            addCriterion("account_expired not between", value1, value2, "accountExpired");
            return (Criteria) this;
        }

        public Criteria andAccountLockedIsNull() {
            addCriterion("account_locked is null");
            return (Criteria) this;
        }

        public Criteria andAccountLockedIsNotNull() {
            addCriterion("account_locked is not null");
            return (Criteria) this;
        }

        public Criteria andAccountLockedEqualTo(Boolean value) {
            addCriterion("account_locked =", value, "accountLocked");
            return (Criteria) this;
        }

        public Criteria andAccountLockedNotEqualTo(Boolean value) {
            addCriterion("account_locked <>", value, "accountLocked");
            return (Criteria) this;
        }

        public Criteria andAccountLockedGreaterThan(Boolean value) {
            addCriterion("account_locked >", value, "accountLocked");
            return (Criteria) this;
        }

        public Criteria andAccountLockedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("account_locked >=", value, "accountLocked");
            return (Criteria) this;
        }

        public Criteria andAccountLockedLessThan(Boolean value) {
            addCriterion("account_locked <", value, "accountLocked");
            return (Criteria) this;
        }

        public Criteria andAccountLockedLessThanOrEqualTo(Boolean value) {
            addCriterion("account_locked <=", value, "accountLocked");
            return (Criteria) this;
        }

        public Criteria andAccountLockedIn(List<Boolean> values) {
            addCriterion("account_locked in", values, "accountLocked");
            return (Criteria) this;
        }

        public Criteria andAccountLockedNotIn(List<Boolean> values) {
            addCriterion("account_locked not in", values, "accountLocked");
            return (Criteria) this;
        }

        public Criteria andAccountLockedBetween(Boolean value1, Boolean value2) {
            addCriterion("account_locked between", value1, value2, "accountLocked");
            return (Criteria) this;
        }

        public Criteria andAccountLockedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("account_locked not between", value1, value2, "accountLocked");
            return (Criteria) this;
        }

        public Criteria andPasswordExpiredIsNull() {
            addCriterion("password_expired is null");
            return (Criteria) this;
        }

        public Criteria andPasswordExpiredIsNotNull() {
            addCriterion("password_expired is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordExpiredEqualTo(Boolean value) {
            addCriterion("password_expired =", value, "passwordExpired");
            return (Criteria) this;
        }

        public Criteria andPasswordExpiredNotEqualTo(Boolean value) {
            addCriterion("password_expired <>", value, "passwordExpired");
            return (Criteria) this;
        }

        public Criteria andPasswordExpiredGreaterThan(Boolean value) {
            addCriterion("password_expired >", value, "passwordExpired");
            return (Criteria) this;
        }

        public Criteria andPasswordExpiredGreaterThanOrEqualTo(Boolean value) {
            addCriterion("password_expired >=", value, "passwordExpired");
            return (Criteria) this;
        }

        public Criteria andPasswordExpiredLessThan(Boolean value) {
            addCriterion("password_expired <", value, "passwordExpired");
            return (Criteria) this;
        }

        public Criteria andPasswordExpiredLessThanOrEqualTo(Boolean value) {
            addCriterion("password_expired <=", value, "passwordExpired");
            return (Criteria) this;
        }

        public Criteria andPasswordExpiredIn(List<Boolean> values) {
            addCriterion("password_expired in", values, "passwordExpired");
            return (Criteria) this;
        }

        public Criteria andPasswordExpiredNotIn(List<Boolean> values) {
            addCriterion("password_expired not in", values, "passwordExpired");
            return (Criteria) this;
        }

        public Criteria andPasswordExpiredBetween(Boolean value1, Boolean value2) {
            addCriterion("password_expired between", value1, value2, "passwordExpired");
            return (Criteria) this;
        }

        public Criteria andPasswordExpiredNotBetween(Boolean value1, Boolean value2) {
            addCriterion("password_expired not between", value1, value2, "passwordExpired");
            return (Criteria) this;
        }

        public Criteria andMfaKeyIsNull() {
            addCriterion("mfa_key is null");
            return (Criteria) this;
        }

        public Criteria andMfaKeyIsNotNull() {
            addCriterion("mfa_key is not null");
            return (Criteria) this;
        }

        public Criteria andMfaKeyEqualTo(String value) {
            addCriterion("mfa_key =", value, "mfaKey");
            return (Criteria) this;
        }

        public Criteria andMfaKeyNotEqualTo(String value) {
            addCriterion("mfa_key <>", value, "mfaKey");
            return (Criteria) this;
        }

        public Criteria andMfaKeyGreaterThan(String value) {
            addCriterion("mfa_key >", value, "mfaKey");
            return (Criteria) this;
        }

        public Criteria andMfaKeyGreaterThanOrEqualTo(String value) {
            addCriterion("mfa_key >=", value, "mfaKey");
            return (Criteria) this;
        }

        public Criteria andMfaKeyLessThan(String value) {
            addCriterion("mfa_key <", value, "mfaKey");
            return (Criteria) this;
        }

        public Criteria andMfaKeyLessThanOrEqualTo(String value) {
            addCriterion("mfa_key <=", value, "mfaKey");
            return (Criteria) this;
        }

        public Criteria andMfaKeyLike(String value) {
            addCriterion("mfa_key like", value, "mfaKey");
            return (Criteria) this;
        }

        public Criteria andMfaKeyNotLike(String value) {
            addCriterion("mfa_key not like", value, "mfaKey");
            return (Criteria) this;
        }

        public Criteria andMfaKeyIn(List<String> values) {
            addCriterion("mfa_key in", values, "mfaKey");
            return (Criteria) this;
        }

        public Criteria andMfaKeyNotIn(List<String> values) {
            addCriterion("mfa_key not in", values, "mfaKey");
            return (Criteria) this;
        }

        public Criteria andMfaKeyBetween(String value1, String value2) {
            addCriterion("mfa_key between", value1, value2, "mfaKey");
            return (Criteria) this;
        }

        public Criteria andMfaKeyNotBetween(String value1, String value2) {
            addCriterion("mfa_key not between", value1, value2, "mfaKey");
            return (Criteria) this;
        }

        public Criteria andUsingMfaIsNull() {
            addCriterion("using_mfa is null");
            return (Criteria) this;
        }

        public Criteria andUsingMfaIsNotNull() {
            addCriterion("using_mfa is not null");
            return (Criteria) this;
        }

        public Criteria andUsingMfaEqualTo(Boolean value) {
            addCriterion("using_mfa =", value, "usingMfa");
            return (Criteria) this;
        }

        public Criteria andUsingMfaNotEqualTo(Boolean value) {
            addCriterion("using_mfa <>", value, "usingMfa");
            return (Criteria) this;
        }

        public Criteria andUsingMfaGreaterThan(Boolean value) {
            addCriterion("using_mfa >", value, "usingMfa");
            return (Criteria) this;
        }

        public Criteria andUsingMfaGreaterThanOrEqualTo(Boolean value) {
            addCriterion("using_mfa >=", value, "usingMfa");
            return (Criteria) this;
        }

        public Criteria andUsingMfaLessThan(Boolean value) {
            addCriterion("using_mfa <", value, "usingMfa");
            return (Criteria) this;
        }

        public Criteria andUsingMfaLessThanOrEqualTo(Boolean value) {
            addCriterion("using_mfa <=", value, "usingMfa");
            return (Criteria) this;
        }

        public Criteria andUsingMfaIn(List<Boolean> values) {
            addCriterion("using_mfa in", values, "usingMfa");
            return (Criteria) this;
        }

        public Criteria andUsingMfaNotIn(List<Boolean> values) {
            addCriterion("using_mfa not in", values, "usingMfa");
            return (Criteria) this;
        }

        public Criteria andUsingMfaBetween(Boolean value1, Boolean value2) {
            addCriterion("using_mfa between", value1, value2, "usingMfa");
            return (Criteria) this;
        }

        public Criteria andUsingMfaNotBetween(Boolean value1, Boolean value2) {
            addCriterion("using_mfa not between", value1, value2, "usingMfa");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNull() {
            addCriterion("enabled is null");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNotNull() {
            addCriterion("enabled is not null");
            return (Criteria) this;
        }

        public Criteria andEnabledEqualTo(Boolean value) {
            addCriterion("enabled =", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotEqualTo(Boolean value) {
            addCriterion("enabled <>", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThan(Boolean value) {
            addCriterion("enabled >", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThanOrEqualTo(Boolean value) {
            addCriterion("enabled >=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThan(Boolean value) {
            addCriterion("enabled <", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThanOrEqualTo(Boolean value) {
            addCriterion("enabled <=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledIn(List<Boolean> values) {
            addCriterion("enabled in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotIn(List<Boolean> values) {
            addCriterion("enabled not in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledBetween(Boolean value1, Boolean value2) {
            addCriterion("enabled between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotBetween(Boolean value1, Boolean value2) {
            addCriterion("enabled not between", value1, value2, "enabled");
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