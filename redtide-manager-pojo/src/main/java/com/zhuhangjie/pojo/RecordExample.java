package com.zhuhangjie.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RecordExample() {
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

        public Criteria andPointNameIsNull() {
            addCriterion("point_name is null");
            return (Criteria) this;
        }

        public Criteria andPointNameIsNotNull() {
            addCriterion("point_name is not null");
            return (Criteria) this;
        }

        public Criteria andPointNameEqualTo(String value) {
            addCriterion("point_name =", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameNotEqualTo(String value) {
            addCriterion("point_name <>", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameGreaterThan(String value) {
            addCriterion("point_name >", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameGreaterThanOrEqualTo(String value) {
            addCriterion("point_name >=", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameLessThan(String value) {
            addCriterion("point_name <", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameLessThanOrEqualTo(String value) {
            addCriterion("point_name <=", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameLike(String value) {
            addCriterion("point_name like", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameNotLike(String value) {
            addCriterion("point_name not like", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameIn(List<String> values) {
            addCriterion("point_name in", values, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameNotIn(List<String> values) {
            addCriterion("point_name not in", values, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameBetween(String value1, String value2) {
            addCriterion("point_name between", value1, value2, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameNotBetween(String value1, String value2) {
            addCriterion("point_name not between", value1, value2, "pointName");
            return (Criteria) this;
        }

        public Criteria andRecordNumberIsNull() {
            addCriterion("record_number is null");
            return (Criteria) this;
        }

        public Criteria andRecordNumberIsNotNull() {
            addCriterion("record_number is not null");
            return (Criteria) this;
        }

        public Criteria andRecordNumberEqualTo(Long value) {
            addCriterion("record_number =", value, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberNotEqualTo(Long value) {
            addCriterion("record_number <>", value, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberGreaterThan(Long value) {
            addCriterion("record_number >", value, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("record_number >=", value, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberLessThan(Long value) {
            addCriterion("record_number <", value, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberLessThanOrEqualTo(Long value) {
            addCriterion("record_number <=", value, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberIn(List<Long> values) {
            addCriterion("record_number in", values, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberNotIn(List<Long> values) {
            addCriterion("record_number not in", values, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberBetween(Long value1, Long value2) {
            addCriterion("record_number between", value1, value2, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andRecordNumberNotBetween(Long value1, Long value2) {
            addCriterion("record_number not between", value1, value2, "recordNumber");
            return (Criteria) this;
        }

        public Criteria andPositionXIsNull() {
            addCriterion("position_x is null");
            return (Criteria) this;
        }

        public Criteria andPositionXIsNotNull() {
            addCriterion("position_x is not null");
            return (Criteria) this;
        }

        public Criteria andPositionXEqualTo(Double value) {
            addCriterion("position_x =", value, "positionX");
            return (Criteria) this;
        }

        public Criteria andPositionXNotEqualTo(Double value) {
            addCriterion("position_x <>", value, "positionX");
            return (Criteria) this;
        }

        public Criteria andPositionXGreaterThan(Double value) {
            addCriterion("position_x >", value, "positionX");
            return (Criteria) this;
        }

        public Criteria andPositionXGreaterThanOrEqualTo(Double value) {
            addCriterion("position_x >=", value, "positionX");
            return (Criteria) this;
        }

        public Criteria andPositionXLessThan(Double value) {
            addCriterion("position_x <", value, "positionX");
            return (Criteria) this;
        }

        public Criteria andPositionXLessThanOrEqualTo(Double value) {
            addCriterion("position_x <=", value, "positionX");
            return (Criteria) this;
        }

        public Criteria andPositionXIn(List<Double> values) {
            addCriterion("position_x in", values, "positionX");
            return (Criteria) this;
        }

        public Criteria andPositionXNotIn(List<Double> values) {
            addCriterion("position_x not in", values, "positionX");
            return (Criteria) this;
        }

        public Criteria andPositionXBetween(Double value1, Double value2) {
            addCriterion("position_x between", value1, value2, "positionX");
            return (Criteria) this;
        }

        public Criteria andPositionXNotBetween(Double value1, Double value2) {
            addCriterion("position_x not between", value1, value2, "positionX");
            return (Criteria) this;
        }

        public Criteria andPositionYIsNull() {
            addCriterion("position_y is null");
            return (Criteria) this;
        }

        public Criteria andPositionYIsNotNull() {
            addCriterion("position_y is not null");
            return (Criteria) this;
        }

        public Criteria andPositionYEqualTo(Double value) {
            addCriterion("position_y =", value, "positionY");
            return (Criteria) this;
        }

        public Criteria andPositionYNotEqualTo(Double value) {
            addCriterion("position_y <>", value, "positionY");
            return (Criteria) this;
        }

        public Criteria andPositionYGreaterThan(Double value) {
            addCriterion("position_y >", value, "positionY");
            return (Criteria) this;
        }

        public Criteria andPositionYGreaterThanOrEqualTo(Double value) {
            addCriterion("position_y >=", value, "positionY");
            return (Criteria) this;
        }

        public Criteria andPositionYLessThan(Double value) {
            addCriterion("position_y <", value, "positionY");
            return (Criteria) this;
        }

        public Criteria andPositionYLessThanOrEqualTo(Double value) {
            addCriterion("position_y <=", value, "positionY");
            return (Criteria) this;
        }

        public Criteria andPositionYIn(List<Double> values) {
            addCriterion("position_y in", values, "positionY");
            return (Criteria) this;
        }

        public Criteria andPositionYNotIn(List<Double> values) {
            addCriterion("position_y not in", values, "positionY");
            return (Criteria) this;
        }

        public Criteria andPositionYBetween(Double value1, Double value2) {
            addCriterion("position_y between", value1, value2, "positionY");
            return (Criteria) this;
        }

        public Criteria andPositionYNotBetween(Double value1, Double value2) {
            addCriterion("position_y not between", value1, value2, "positionY");
            return (Criteria) this;
        }

        public Criteria andWeatherIsNull() {
            addCriterion("weather is null");
            return (Criteria) this;
        }

        public Criteria andWeatherIsNotNull() {
            addCriterion("weather is not null");
            return (Criteria) this;
        }

        public Criteria andWeatherEqualTo(String value) {
            addCriterion("weather =", value, "weather");
            return (Criteria) this;
        }

        public Criteria andWeatherNotEqualTo(String value) {
            addCriterion("weather <>", value, "weather");
            return (Criteria) this;
        }

        public Criteria andWeatherGreaterThan(String value) {
            addCriterion("weather >", value, "weather");
            return (Criteria) this;
        }

        public Criteria andWeatherGreaterThanOrEqualTo(String value) {
            addCriterion("weather >=", value, "weather");
            return (Criteria) this;
        }

        public Criteria andWeatherLessThan(String value) {
            addCriterion("weather <", value, "weather");
            return (Criteria) this;
        }

        public Criteria andWeatherLessThanOrEqualTo(String value) {
            addCriterion("weather <=", value, "weather");
            return (Criteria) this;
        }

        public Criteria andWeatherLike(String value) {
            addCriterion("weather like", value, "weather");
            return (Criteria) this;
        }

        public Criteria andWeatherNotLike(String value) {
            addCriterion("weather not like", value, "weather");
            return (Criteria) this;
        }

        public Criteria andWeatherIn(List<String> values) {
            addCriterion("weather in", values, "weather");
            return (Criteria) this;
        }

        public Criteria andWeatherNotIn(List<String> values) {
            addCriterion("weather not in", values, "weather");
            return (Criteria) this;
        }

        public Criteria andWeatherBetween(String value1, String value2) {
            addCriterion("weather between", value1, value2, "weather");
            return (Criteria) this;
        }

        public Criteria andWeatherNotBetween(String value1, String value2) {
            addCriterion("weather not between", value1, value2, "weather");
            return (Criteria) this;
        }

        public Criteria andWaterPictureIsNull() {
            addCriterion("water_picture is null");
            return (Criteria) this;
        }

        public Criteria andWaterPictureIsNotNull() {
            addCriterion("water_picture is not null");
            return (Criteria) this;
        }

        public Criteria andWaterPictureEqualTo(String value) {
            addCriterion("water_picture =", value, "waterPicture");
            return (Criteria) this;
        }

        public Criteria andWaterPictureNotEqualTo(String value) {
            addCriterion("water_picture <>", value, "waterPicture");
            return (Criteria) this;
        }

        public Criteria andWaterPictureGreaterThan(String value) {
            addCriterion("water_picture >", value, "waterPicture");
            return (Criteria) this;
        }

        public Criteria andWaterPictureGreaterThanOrEqualTo(String value) {
            addCriterion("water_picture >=", value, "waterPicture");
            return (Criteria) this;
        }

        public Criteria andWaterPictureLessThan(String value) {
            addCriterion("water_picture <", value, "waterPicture");
            return (Criteria) this;
        }

        public Criteria andWaterPictureLessThanOrEqualTo(String value) {
            addCriterion("water_picture <=", value, "waterPicture");
            return (Criteria) this;
        }

        public Criteria andWaterPictureLike(String value) {
            addCriterion("water_picture like", value, "waterPicture");
            return (Criteria) this;
        }

        public Criteria andWaterPictureNotLike(String value) {
            addCriterion("water_picture not like", value, "waterPicture");
            return (Criteria) this;
        }

        public Criteria andWaterPictureIn(List<String> values) {
            addCriterion("water_picture in", values, "waterPicture");
            return (Criteria) this;
        }

        public Criteria andWaterPictureNotIn(List<String> values) {
            addCriterion("water_picture not in", values, "waterPicture");
            return (Criteria) this;
        }

        public Criteria andWaterPictureBetween(String value1, String value2) {
            addCriterion("water_picture between", value1, value2, "waterPicture");
            return (Criteria) this;
        }

        public Criteria andWaterPictureNotBetween(String value1, String value2) {
            addCriterion("water_picture not between", value1, value2, "waterPicture");
            return (Criteria) this;
        }

        public Criteria andWaterDemoIsNull() {
            addCriterion("water_demo is null");
            return (Criteria) this;
        }

        public Criteria andWaterDemoIsNotNull() {
            addCriterion("water_demo is not null");
            return (Criteria) this;
        }

        public Criteria andWaterDemoEqualTo(String value) {
            addCriterion("water_demo =", value, "waterDemo");
            return (Criteria) this;
        }

        public Criteria andWaterDemoNotEqualTo(String value) {
            addCriterion("water_demo <>", value, "waterDemo");
            return (Criteria) this;
        }

        public Criteria andWaterDemoGreaterThan(String value) {
            addCriterion("water_demo >", value, "waterDemo");
            return (Criteria) this;
        }

        public Criteria andWaterDemoGreaterThanOrEqualTo(String value) {
            addCriterion("water_demo >=", value, "waterDemo");
            return (Criteria) this;
        }

        public Criteria andWaterDemoLessThan(String value) {
            addCriterion("water_demo <", value, "waterDemo");
            return (Criteria) this;
        }

        public Criteria andWaterDemoLessThanOrEqualTo(String value) {
            addCriterion("water_demo <=", value, "waterDemo");
            return (Criteria) this;
        }

        public Criteria andWaterDemoLike(String value) {
            addCriterion("water_demo like", value, "waterDemo");
            return (Criteria) this;
        }

        public Criteria andWaterDemoNotLike(String value) {
            addCriterion("water_demo not like", value, "waterDemo");
            return (Criteria) this;
        }

        public Criteria andWaterDemoIn(List<String> values) {
            addCriterion("water_demo in", values, "waterDemo");
            return (Criteria) this;
        }

        public Criteria andWaterDemoNotIn(List<String> values) {
            addCriterion("water_demo not in", values, "waterDemo");
            return (Criteria) this;
        }

        public Criteria andWaterDemoBetween(String value1, String value2) {
            addCriterion("water_demo between", value1, value2, "waterDemo");
            return (Criteria) this;
        }

        public Criteria andWaterDemoNotBetween(String value1, String value2) {
            addCriterion("water_demo not between", value1, value2, "waterDemo");
            return (Criteria) this;
        }

        public Criteria andChlDemoIsNull() {
            addCriterion("chl_demo is null");
            return (Criteria) this;
        }

        public Criteria andChlDemoIsNotNull() {
            addCriterion("chl_demo is not null");
            return (Criteria) this;
        }

        public Criteria andChlDemoEqualTo(String value) {
            addCriterion("chl_demo =", value, "chlDemo");
            return (Criteria) this;
        }

        public Criteria andChlDemoNotEqualTo(String value) {
            addCriterion("chl_demo <>", value, "chlDemo");
            return (Criteria) this;
        }

        public Criteria andChlDemoGreaterThan(String value) {
            addCriterion("chl_demo >", value, "chlDemo");
            return (Criteria) this;
        }

        public Criteria andChlDemoGreaterThanOrEqualTo(String value) {
            addCriterion("chl_demo >=", value, "chlDemo");
            return (Criteria) this;
        }

        public Criteria andChlDemoLessThan(String value) {
            addCriterion("chl_demo <", value, "chlDemo");
            return (Criteria) this;
        }

        public Criteria andChlDemoLessThanOrEqualTo(String value) {
            addCriterion("chl_demo <=", value, "chlDemo");
            return (Criteria) this;
        }

        public Criteria andChlDemoLike(String value) {
            addCriterion("chl_demo like", value, "chlDemo");
            return (Criteria) this;
        }

        public Criteria andChlDemoNotLike(String value) {
            addCriterion("chl_demo not like", value, "chlDemo");
            return (Criteria) this;
        }

        public Criteria andChlDemoIn(List<String> values) {
            addCriterion("chl_demo in", values, "chlDemo");
            return (Criteria) this;
        }

        public Criteria andChlDemoNotIn(List<String> values) {
            addCriterion("chl_demo not in", values, "chlDemo");
            return (Criteria) this;
        }

        public Criteria andChlDemoBetween(String value1, String value2) {
            addCriterion("chl_demo between", value1, value2, "chlDemo");
            return (Criteria) this;
        }

        public Criteria andChlDemoNotBetween(String value1, String value2) {
            addCriterion("chl_demo not between", value1, value2, "chlDemo");
            return (Criteria) this;
        }

        public Criteria andCdomDemoIsNull() {
            addCriterion("cdom_demo is null");
            return (Criteria) this;
        }

        public Criteria andCdomDemoIsNotNull() {
            addCriterion("cdom_demo is not null");
            return (Criteria) this;
        }

        public Criteria andCdomDemoEqualTo(String value) {
            addCriterion("cdom_demo =", value, "cdomDemo");
            return (Criteria) this;
        }

        public Criteria andCdomDemoNotEqualTo(String value) {
            addCriterion("cdom_demo <>", value, "cdomDemo");
            return (Criteria) this;
        }

        public Criteria andCdomDemoGreaterThan(String value) {
            addCriterion("cdom_demo >", value, "cdomDemo");
            return (Criteria) this;
        }

        public Criteria andCdomDemoGreaterThanOrEqualTo(String value) {
            addCriterion("cdom_demo >=", value, "cdomDemo");
            return (Criteria) this;
        }

        public Criteria andCdomDemoLessThan(String value) {
            addCriterion("cdom_demo <", value, "cdomDemo");
            return (Criteria) this;
        }

        public Criteria andCdomDemoLessThanOrEqualTo(String value) {
            addCriterion("cdom_demo <=", value, "cdomDemo");
            return (Criteria) this;
        }

        public Criteria andCdomDemoLike(String value) {
            addCriterion("cdom_demo like", value, "cdomDemo");
            return (Criteria) this;
        }

        public Criteria andCdomDemoNotLike(String value) {
            addCriterion("cdom_demo not like", value, "cdomDemo");
            return (Criteria) this;
        }

        public Criteria andCdomDemoIn(List<String> values) {
            addCriterion("cdom_demo in", values, "cdomDemo");
            return (Criteria) this;
        }

        public Criteria andCdomDemoNotIn(List<String> values) {
            addCriterion("cdom_demo not in", values, "cdomDemo");
            return (Criteria) this;
        }

        public Criteria andCdomDemoBetween(String value1, String value2) {
            addCriterion("cdom_demo between", value1, value2, "cdomDemo");
            return (Criteria) this;
        }

        public Criteria andCdomDemoNotBetween(String value1, String value2) {
            addCriterion("cdom_demo not between", value1, value2, "cdomDemo");
            return (Criteria) this;
        }

        public Criteria andGranuleDemoIsNull() {
            addCriterion("granule_demo is null");
            return (Criteria) this;
        }

        public Criteria andGranuleDemoIsNotNull() {
            addCriterion("granule_demo is not null");
            return (Criteria) this;
        }

        public Criteria andGranuleDemoEqualTo(String value) {
            addCriterion("granule_demo =", value, "granuleDemo");
            return (Criteria) this;
        }

        public Criteria andGranuleDemoNotEqualTo(String value) {
            addCriterion("granule_demo <>", value, "granuleDemo");
            return (Criteria) this;
        }

        public Criteria andGranuleDemoGreaterThan(String value) {
            addCriterion("granule_demo >", value, "granuleDemo");
            return (Criteria) this;
        }

        public Criteria andGranuleDemoGreaterThanOrEqualTo(String value) {
            addCriterion("granule_demo >=", value, "granuleDemo");
            return (Criteria) this;
        }

        public Criteria andGranuleDemoLessThan(String value) {
            addCriterion("granule_demo <", value, "granuleDemo");
            return (Criteria) this;
        }

        public Criteria andGranuleDemoLessThanOrEqualTo(String value) {
            addCriterion("granule_demo <=", value, "granuleDemo");
            return (Criteria) this;
        }

        public Criteria andGranuleDemoLike(String value) {
            addCriterion("granule_demo like", value, "granuleDemo");
            return (Criteria) this;
        }

        public Criteria andGranuleDemoNotLike(String value) {
            addCriterion("granule_demo not like", value, "granuleDemo");
            return (Criteria) this;
        }

        public Criteria andGranuleDemoIn(List<String> values) {
            addCriterion("granule_demo in", values, "granuleDemo");
            return (Criteria) this;
        }

        public Criteria andGranuleDemoNotIn(List<String> values) {
            addCriterion("granule_demo not in", values, "granuleDemo");
            return (Criteria) this;
        }

        public Criteria andGranuleDemoBetween(String value1, String value2) {
            addCriterion("granule_demo between", value1, value2, "granuleDemo");
            return (Criteria) this;
        }

        public Criteria andGranuleDemoNotBetween(String value1, String value2) {
            addCriterion("granule_demo not between", value1, value2, "granuleDemo");
            return (Criteria) this;
        }

        public Criteria andSpectrumDemoIsNull() {
            addCriterion("spectrum_demo is null");
            return (Criteria) this;
        }

        public Criteria andSpectrumDemoIsNotNull() {
            addCriterion("spectrum_demo is not null");
            return (Criteria) this;
        }

        public Criteria andSpectrumDemoEqualTo(String value) {
            addCriterion("spectrum_demo =", value, "spectrumDemo");
            return (Criteria) this;
        }

        public Criteria andSpectrumDemoNotEqualTo(String value) {
            addCriterion("spectrum_demo <>", value, "spectrumDemo");
            return (Criteria) this;
        }

        public Criteria andSpectrumDemoGreaterThan(String value) {
            addCriterion("spectrum_demo >", value, "spectrumDemo");
            return (Criteria) this;
        }

        public Criteria andSpectrumDemoGreaterThanOrEqualTo(String value) {
            addCriterion("spectrum_demo >=", value, "spectrumDemo");
            return (Criteria) this;
        }

        public Criteria andSpectrumDemoLessThan(String value) {
            addCriterion("spectrum_demo <", value, "spectrumDemo");
            return (Criteria) this;
        }

        public Criteria andSpectrumDemoLessThanOrEqualTo(String value) {
            addCriterion("spectrum_demo <=", value, "spectrumDemo");
            return (Criteria) this;
        }

        public Criteria andSpectrumDemoLike(String value) {
            addCriterion("spectrum_demo like", value, "spectrumDemo");
            return (Criteria) this;
        }

        public Criteria andSpectrumDemoNotLike(String value) {
            addCriterion("spectrum_demo not like", value, "spectrumDemo");
            return (Criteria) this;
        }

        public Criteria andSpectrumDemoIn(List<String> values) {
            addCriterion("spectrum_demo in", values, "spectrumDemo");
            return (Criteria) this;
        }

        public Criteria andSpectrumDemoNotIn(List<String> values) {
            addCriterion("spectrum_demo not in", values, "spectrumDemo");
            return (Criteria) this;
        }

        public Criteria andSpectrumDemoBetween(String value1, String value2) {
            addCriterion("spectrum_demo between", value1, value2, "spectrumDemo");
            return (Criteria) this;
        }

        public Criteria andSpectrumDemoNotBetween(String value1, String value2) {
            addCriterion("spectrum_demo not between", value1, value2, "spectrumDemo");
            return (Criteria) this;
        }

        public Criteria andArriveTimeIsNull() {
            addCriterion("arrive_time is null");
            return (Criteria) this;
        }

        public Criteria andArriveTimeIsNotNull() {
            addCriterion("arrive_time is not null");
            return (Criteria) this;
        }

        public Criteria andArriveTimeEqualTo(Date value) {
            addCriterion("arrive_time =", value, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeNotEqualTo(Date value) {
            addCriterion("arrive_time <>", value, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeGreaterThan(Date value) {
            addCriterion("arrive_time >", value, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("arrive_time >=", value, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeLessThan(Date value) {
            addCriterion("arrive_time <", value, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeLessThanOrEqualTo(Date value) {
            addCriterion("arrive_time <=", value, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeIn(List<Date> values) {
            addCriterion("arrive_time in", values, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeNotIn(List<Date> values) {
            addCriterion("arrive_time not in", values, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeBetween(Date value1, Date value2) {
            addCriterion("arrive_time between", value1, value2, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andArriveTimeNotBetween(Date value1, Date value2) {
            addCriterion("arrive_time not between", value1, value2, "arriveTime");
            return (Criteria) this;
        }

        public Criteria andVoyageIdIsNull() {
            addCriterion("voyage_id is null");
            return (Criteria) this;
        }

        public Criteria andVoyageIdIsNotNull() {
            addCriterion("voyage_id is not null");
            return (Criteria) this;
        }

        public Criteria andVoyageIdEqualTo(Long value) {
            addCriterion("voyage_id =", value, "voyageId");
            return (Criteria) this;
        }

        public Criteria andVoyageIdNotEqualTo(Long value) {
            addCriterion("voyage_id <>", value, "voyageId");
            return (Criteria) this;
        }

        public Criteria andVoyageIdGreaterThan(Long value) {
            addCriterion("voyage_id >", value, "voyageId");
            return (Criteria) this;
        }

        public Criteria andVoyageIdGreaterThanOrEqualTo(Long value) {
            addCriterion("voyage_id >=", value, "voyageId");
            return (Criteria) this;
        }

        public Criteria andVoyageIdLessThan(Long value) {
            addCriterion("voyage_id <", value, "voyageId");
            return (Criteria) this;
        }

        public Criteria andVoyageIdLessThanOrEqualTo(Long value) {
            addCriterion("voyage_id <=", value, "voyageId");
            return (Criteria) this;
        }

        public Criteria andVoyageIdIn(List<Long> values) {
            addCriterion("voyage_id in", values, "voyageId");
            return (Criteria) this;
        }

        public Criteria andVoyageIdNotIn(List<Long> values) {
            addCriterion("voyage_id not in", values, "voyageId");
            return (Criteria) this;
        }

        public Criteria andVoyageIdBetween(Long value1, Long value2) {
            addCriterion("voyage_id between", value1, value2, "voyageId");
            return (Criteria) this;
        }

        public Criteria andVoyageIdNotBetween(Long value1, Long value2) {
            addCriterion("voyage_id not between", value1, value2, "voyageId");
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