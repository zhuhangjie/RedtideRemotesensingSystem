package com.zhuhangjie.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PicExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PicExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(Date value) {
            addCriterionForJDBCDate("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(Date value) {
            addCriterionForJDBCDate("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(Date value) {
            addCriterionForJDBCDate("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<Date> values) {
            addCriterionForJDBCDate("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andChlIsNull() {
            addCriterion("chl is null");
            return (Criteria) this;
        }

        public Criteria andChlIsNotNull() {
            addCriterion("chl is not null");
            return (Criteria) this;
        }

        public Criteria andChlEqualTo(String value) {
            addCriterion("chl =", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlNotEqualTo(String value) {
            addCriterion("chl <>", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlGreaterThan(String value) {
            addCriterion("chl >", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlGreaterThanOrEqualTo(String value) {
            addCriterion("chl >=", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlLessThan(String value) {
            addCriterion("chl <", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlLessThanOrEqualTo(String value) {
            addCriterion("chl <=", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlLike(String value) {
            addCriterion("chl like", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlNotLike(String value) {
            addCriterion("chl not like", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlIn(List<String> values) {
            addCriterion("chl in", values, "chl");
            return (Criteria) this;
        }

        public Criteria andChlNotIn(List<String> values) {
            addCriterion("chl not in", values, "chl");
            return (Criteria) this;
        }

        public Criteria andChlBetween(String value1, String value2) {
            addCriterion("chl between", value1, value2, "chl");
            return (Criteria) this;
        }

        public Criteria andChlNotBetween(String value1, String value2) {
            addCriterion("chl not between", value1, value2, "chl");
            return (Criteria) this;
        }

        public Criteria andRtIsNull() {
            addCriterion("rt is null");
            return (Criteria) this;
        }

        public Criteria andRtIsNotNull() {
            addCriterion("rt is not null");
            return (Criteria) this;
        }

        public Criteria andRtEqualTo(String value) {
            addCriterion("rt =", value, "rt");
            return (Criteria) this;
        }

        public Criteria andRtNotEqualTo(String value) {
            addCriterion("rt <>", value, "rt");
            return (Criteria) this;
        }

        public Criteria andRtGreaterThan(String value) {
            addCriterion("rt >", value, "rt");
            return (Criteria) this;
        }

        public Criteria andRtGreaterThanOrEqualTo(String value) {
            addCriterion("rt >=", value, "rt");
            return (Criteria) this;
        }

        public Criteria andRtLessThan(String value) {
            addCriterion("rt <", value, "rt");
            return (Criteria) this;
        }

        public Criteria andRtLessThanOrEqualTo(String value) {
            addCriterion("rt <=", value, "rt");
            return (Criteria) this;
        }

        public Criteria andRtLike(String value) {
            addCriterion("rt like", value, "rt");
            return (Criteria) this;
        }

        public Criteria andRtNotLike(String value) {
            addCriterion("rt not like", value, "rt");
            return (Criteria) this;
        }

        public Criteria andRtIn(List<String> values) {
            addCriterion("rt in", values, "rt");
            return (Criteria) this;
        }

        public Criteria andRtNotIn(List<String> values) {
            addCriterion("rt not in", values, "rt");
            return (Criteria) this;
        }

        public Criteria andRtBetween(String value1, String value2) {
            addCriterion("rt between", value1, value2, "rt");
            return (Criteria) this;
        }

        public Criteria andRtNotBetween(String value1, String value2) {
            addCriterion("rt not between", value1, value2, "rt");
            return (Criteria) this;
        }

        public Criteria andCloudIsNull() {
            addCriterion("cloud is null");
            return (Criteria) this;
        }

        public Criteria andCloudIsNotNull() {
            addCriterion("cloud is not null");
            return (Criteria) this;
        }

        public Criteria andCloudEqualTo(String value) {
            addCriterion("cloud =", value, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudNotEqualTo(String value) {
            addCriterion("cloud <>", value, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudGreaterThan(String value) {
            addCriterion("cloud >", value, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudGreaterThanOrEqualTo(String value) {
            addCriterion("cloud >=", value, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudLessThan(String value) {
            addCriterion("cloud <", value, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudLessThanOrEqualTo(String value) {
            addCriterion("cloud <=", value, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudLike(String value) {
            addCriterion("cloud like", value, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudNotLike(String value) {
            addCriterion("cloud not like", value, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudIn(List<String> values) {
            addCriterion("cloud in", values, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudNotIn(List<String> values) {
            addCriterion("cloud not in", values, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudBetween(String value1, String value2) {
            addCriterion("cloud between", value1, value2, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudNotBetween(String value1, String value2) {
            addCriterion("cloud not between", value1, value2, "cloud");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(Double value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(Double value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(Double value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(Double value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(Double value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(Double value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<Double> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<Double> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(Double value1, Double value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(Double value1, Double value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andIsredtideIsNull() {
            addCriterion("isredtide is null");
            return (Criteria) this;
        }

        public Criteria andIsredtideIsNotNull() {
            addCriterion("isredtide is not null");
            return (Criteria) this;
        }

        public Criteria andIsredtideEqualTo(String value) {
            addCriterion("isredtide =", value, "isredtide");
            return (Criteria) this;
        }

        public Criteria andIsredtideNotEqualTo(String value) {
            addCriterion("isredtide <>", value, "isredtide");
            return (Criteria) this;
        }

        public Criteria andIsredtideGreaterThan(String value) {
            addCriterion("isredtide >", value, "isredtide");
            return (Criteria) this;
        }

        public Criteria andIsredtideGreaterThanOrEqualTo(String value) {
            addCriterion("isredtide >=", value, "isredtide");
            return (Criteria) this;
        }

        public Criteria andIsredtideLessThan(String value) {
            addCriterion("isredtide <", value, "isredtide");
            return (Criteria) this;
        }

        public Criteria andIsredtideLessThanOrEqualTo(String value) {
            addCriterion("isredtide <=", value, "isredtide");
            return (Criteria) this;
        }

        public Criteria andIsredtideLike(String value) {
            addCriterion("isredtide like", value, "isredtide");
            return (Criteria) this;
        }

        public Criteria andIsredtideNotLike(String value) {
            addCriterion("isredtide not like", value, "isredtide");
            return (Criteria) this;
        }

        public Criteria andIsredtideIn(List<String> values) {
            addCriterion("isredtide in", values, "isredtide");
            return (Criteria) this;
        }

        public Criteria andIsredtideNotIn(List<String> values) {
            addCriterion("isredtide not in", values, "isredtide");
            return (Criteria) this;
        }

        public Criteria andIsredtideBetween(String value1, String value2) {
            addCriterion("isredtide between", value1, value2, "isredtide");
            return (Criteria) this;
        }

        public Criteria andIsredtideNotBetween(String value1, String value2) {
            addCriterion("isredtide not between", value1, value2, "isredtide");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("year is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("year is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(Integer value) {
            addCriterion("year =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(Integer value) {
            addCriterion("year <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(Integer value) {
            addCriterion("year >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("year >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(Integer value) {
            addCriterion("year <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(Integer value) {
            addCriterion("year <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<Integer> values) {
            addCriterion("year in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<Integer> values) {
            addCriterion("year not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(Integer value1, Integer value2) {
            addCriterion("year between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(Integer value1, Integer value2) {
            addCriterion("year not between", value1, value2, "year");
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