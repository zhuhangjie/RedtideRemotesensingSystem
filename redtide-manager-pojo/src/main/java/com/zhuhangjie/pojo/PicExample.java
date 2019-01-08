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

        public Criteria andNcIsNull() {
            addCriterion("nc is null");
            return (Criteria) this;
        }

        public Criteria andNcIsNotNull() {
            addCriterion("nc is not null");
            return (Criteria) this;
        }

        public Criteria andNcEqualTo(Boolean value) {
            addCriterion("nc =", value, "nc");
            return (Criteria) this;
        }

        public Criteria andNcNotEqualTo(Boolean value) {
            addCriterion("nc <>", value, "nc");
            return (Criteria) this;
        }

        public Criteria andNcGreaterThan(Boolean value) {
            addCriterion("nc >", value, "nc");
            return (Criteria) this;
        }

        public Criteria andNcGreaterThanOrEqualTo(Boolean value) {
            addCriterion("nc >=", value, "nc");
            return (Criteria) this;
        }

        public Criteria andNcLessThan(Boolean value) {
            addCriterion("nc <", value, "nc");
            return (Criteria) this;
        }

        public Criteria andNcLessThanOrEqualTo(Boolean value) {
            addCriterion("nc <=", value, "nc");
            return (Criteria) this;
        }

        public Criteria andNcIn(List<Boolean> values) {
            addCriterion("nc in", values, "nc");
            return (Criteria) this;
        }

        public Criteria andNcNotIn(List<Boolean> values) {
            addCriterion("nc not in", values, "nc");
            return (Criteria) this;
        }

        public Criteria andNcBetween(Boolean value1, Boolean value2) {
            addCriterion("nc between", value1, value2, "nc");
            return (Criteria) this;
        }

        public Criteria andNcNotBetween(Boolean value1, Boolean value2) {
            addCriterion("nc not between", value1, value2, "nc");
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

        public Criteria andChlEqualTo(Boolean value) {
            addCriterion("chl =", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlNotEqualTo(Boolean value) {
            addCriterion("chl <>", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlGreaterThan(Boolean value) {
            addCriterion("chl >", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlGreaterThanOrEqualTo(Boolean value) {
            addCriterion("chl >=", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlLessThan(Boolean value) {
            addCriterion("chl <", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlLessThanOrEqualTo(Boolean value) {
            addCriterion("chl <=", value, "chl");
            return (Criteria) this;
        }

        public Criteria andChlIn(List<Boolean> values) {
            addCriterion("chl in", values, "chl");
            return (Criteria) this;
        }

        public Criteria andChlNotIn(List<Boolean> values) {
            addCriterion("chl not in", values, "chl");
            return (Criteria) this;
        }

        public Criteria andChlBetween(Boolean value1, Boolean value2) {
            addCriterion("chl between", value1, value2, "chl");
            return (Criteria) this;
        }

        public Criteria andChlNotBetween(Boolean value1, Boolean value2) {
            addCriterion("chl not between", value1, value2, "chl");
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

        public Criteria andCloudEqualTo(Boolean value) {
            addCriterion("cloud =", value, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudNotEqualTo(Boolean value) {
            addCriterion("cloud <>", value, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudGreaterThan(Boolean value) {
            addCriterion("cloud >", value, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudGreaterThanOrEqualTo(Boolean value) {
            addCriterion("cloud >=", value, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudLessThan(Boolean value) {
            addCriterion("cloud <", value, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudLessThanOrEqualTo(Boolean value) {
            addCriterion("cloud <=", value, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudIn(List<Boolean> values) {
            addCriterion("cloud in", values, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudNotIn(List<Boolean> values) {
            addCriterion("cloud not in", values, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudBetween(Boolean value1, Boolean value2) {
            addCriterion("cloud between", value1, value2, "cloud");
            return (Criteria) this;
        }

        public Criteria andCloudNotBetween(Boolean value1, Boolean value2) {
            addCriterion("cloud not between", value1, value2, "cloud");
            return (Criteria) this;
        }

        public Criteria andShpIsNull() {
            addCriterion("shp is null");
            return (Criteria) this;
        }

        public Criteria andShpIsNotNull() {
            addCriterion("shp is not null");
            return (Criteria) this;
        }

        public Criteria andShpEqualTo(Boolean value) {
            addCriterion("shp =", value, "shp");
            return (Criteria) this;
        }

        public Criteria andShpNotEqualTo(Boolean value) {
            addCriterion("shp <>", value, "shp");
            return (Criteria) this;
        }

        public Criteria andShpGreaterThan(Boolean value) {
            addCriterion("shp >", value, "shp");
            return (Criteria) this;
        }

        public Criteria andShpGreaterThanOrEqualTo(Boolean value) {
            addCriterion("shp >=", value, "shp");
            return (Criteria) this;
        }

        public Criteria andShpLessThan(Boolean value) {
            addCriterion("shp <", value, "shp");
            return (Criteria) this;
        }

        public Criteria andShpLessThanOrEqualTo(Boolean value) {
            addCriterion("shp <=", value, "shp");
            return (Criteria) this;
        }

        public Criteria andShpIn(List<Boolean> values) {
            addCriterion("shp in", values, "shp");
            return (Criteria) this;
        }

        public Criteria andShpNotIn(List<Boolean> values) {
            addCriterion("shp not in", values, "shp");
            return (Criteria) this;
        }

        public Criteria andShpBetween(Boolean value1, Boolean value2) {
            addCriterion("shp between", value1, value2, "shp");
            return (Criteria) this;
        }

        public Criteria andShpNotBetween(Boolean value1, Boolean value2) {
            addCriterion("shp not between", value1, value2, "shp");
            return (Criteria) this;
        }

        public Criteria andRedtidepointIsNull() {
            addCriterion("redtidepoint is null");
            return (Criteria) this;
        }

        public Criteria andRedtidepointIsNotNull() {
            addCriterion("redtidepoint is not null");
            return (Criteria) this;
        }

        public Criteria andRedtidepointEqualTo(Integer value) {
            addCriterion("redtidepoint =", value, "redtidepoint");
            return (Criteria) this;
        }

        public Criteria andRedtidepointNotEqualTo(Integer value) {
            addCriterion("redtidepoint <>", value, "redtidepoint");
            return (Criteria) this;
        }

        public Criteria andRedtidepointGreaterThan(Integer value) {
            addCriterion("redtidepoint >", value, "redtidepoint");
            return (Criteria) this;
        }

        public Criteria andRedtidepointGreaterThanOrEqualTo(Integer value) {
            addCriterion("redtidepoint >=", value, "redtidepoint");
            return (Criteria) this;
        }

        public Criteria andRedtidepointLessThan(Integer value) {
            addCriterion("redtidepoint <", value, "redtidepoint");
            return (Criteria) this;
        }

        public Criteria andRedtidepointLessThanOrEqualTo(Integer value) {
            addCriterion("redtidepoint <=", value, "redtidepoint");
            return (Criteria) this;
        }

        public Criteria andRedtidepointIn(List<Integer> values) {
            addCriterion("redtidepoint in", values, "redtidepoint");
            return (Criteria) this;
        }

        public Criteria andRedtidepointNotIn(List<Integer> values) {
            addCriterion("redtidepoint not in", values, "redtidepoint");
            return (Criteria) this;
        }

        public Criteria andRedtidepointBetween(Integer value1, Integer value2) {
            addCriterion("redtidepoint between", value1, value2, "redtidepoint");
            return (Criteria) this;
        }

        public Criteria andRedtidepointNotBetween(Integer value1, Integer value2) {
            addCriterion("redtidepoint not between", value1, value2, "redtidepoint");
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