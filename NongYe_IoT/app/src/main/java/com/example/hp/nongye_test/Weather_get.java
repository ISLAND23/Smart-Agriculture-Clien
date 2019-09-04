package com.example.hp.nongye_test;

import java.util.List;

public class Weather_get {
    /**
     * msg : success
     * result : [{"airCondition":"优","airQuality":{"aqi":36,"city":"韶关","district":"韶关","fetureData":[{"aqi":40,"date":"2018-12-14","quality":"优"},{"aqi":51,"date":"2018-12-15","quality":"良"},{"aqi":59,"date":"2018-12-16","quality":"良"},{"aqi":40,"date":"2018-12-17","quality":"优"},{"aqi":66,"date":"2018-12-18","quality":"良"},{"aqi":98,"date":"2018-12-19","quality":"良"}],"hourData":[{"aqi":36,"dateTime":"2018-12-13 12:00:00"},{"aqi":42,"dateTime":"2018-12-13 11:00:00"},{"aqi":46,"dateTime":"2018-12-13 10:00:00"},{"aqi":46,"dateTime":"2018-12-13 09:00:00"},{"aqi":50,"dateTime":"2018-12-13 08:00:00"},{"aqi":50,"dateTime":"2018-12-13 07:00:00"},{"aqi":49,"dateTime":"2018-12-13 06:00:00"},{"aqi":49,"dateTime":"2018-12-13 05:00:00"},{"aqi":46,"dateTime":"2018-12-13 04:00:00"},{"aqi":43,"dateTime":"2018-12-13 03:00:00"},{"aqi":40,"dateTime":"2018-12-13 02:00:00"},{"aqi":40,"dateTime":"2018-12-13 01:00:00"},{"aqi":39,"dateTime":"2018-12-13 00:00:00"},{"aqi":39,"dateTime":"2018-12-12 23:00:00"},{"aqi":39,"dateTime":"2018-12-12 22:00:00"},{"aqi":38,"dateTime":"2018-12-12 21:00:00"},{"aqi":38,"dateTime":"2018-12-12 20:00:00"},{"aqi":38,"dateTime":"2018-12-12 19:00:00"},{"aqi":38,"dateTime":"2018-12-12 18:00:00"},{"aqi":39,"dateTime":"2018-12-12 17:00:00"},{"aqi":38,"dateTime":"2018-12-12 16:00:00"},{"aqi":36,"dateTime":"2018-12-12 15:00:00"},{"aqi":36,"dateTime":"2018-12-12 14:00:00"},{"aqi":38,"dateTime":"2018-12-12 13:00:00"}],"no2":15,"pm10":28,"pm25":25,"province":"广东","quality":"优","so2":8,"updateTime":"2018-12-13 13:00:00"},"city":"韶关","coldIndex":"易发期","date":"2018-12-13","distrct":"韶关","dressingIndex":"毛衣类","exerciseIndex":"不适宜","future":[{"date":"2018-12-13","dayTime":"小雨","night":"阴","temperature":"10°C / 7°C","week":"今天","wind":"无持续风向 小于3级"},{"date":"2018-12-14","dayTime":"多云","night":"多云","temperature":"12°C / 7°C","week":"星期五","wind":"无持续风向 小于3级"},{"date":"2018-12-15","dayTime":"多云","night":"多云","temperature":"15°C / 8°C","week":"星期六","wind":"无持续风向 小于3级"},{"date":"2018-12-16","dayTime":"多云","night":"多云","temperature":"14°C / 7°C","week":"星期日","wind":"微风 小于3级"},{"date":"2018-12-17","dayTime":"晴","night":"晴","temperature":"18°C / 8°C","week":"星期一","wind":"微风 小于3级"},{"date":"2018-12-18","dayTime":"晴","night":"阴","temperature":"20°C / 12°C","week":"星期二","wind":"微风 小于3级"},{"date":"2018-12-19","dayTime":"局部多云","night":"局部多云","temperature":"22°C / 15°C","week":"星期三","wind":"东南风 2级"},{"date":"2018-12-20","dayTime":"阵雨","night":"阵雨","temperature":"24°C / 14°C","week":"星期四","wind":"东南偏东风 2级"}],"humidity":"湿度：73%","pollutionIndex":"36","province":"广东","sunrise":"07:01","sunset":"17:39","temperature":"10℃","time":"13:23","updateTime":"20181213133519","washIndex":"不太适宜","weather":"阴","week":"周四","wind":"北风2级"}]
     * retCode : 200
     */

    private String msg;
    private String retCode;
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * airCondition : 优
         * airQuality : {"aqi":36,"city":"韶关","district":"韶关","fetureData":[{"aqi":40,"date":"2018-12-14","quality":"优"},{"aqi":51,"date":"2018-12-15","quality":"良"},{"aqi":59,"date":"2018-12-16","quality":"良"},{"aqi":40,"date":"2018-12-17","quality":"优"},{"aqi":66,"date":"2018-12-18","quality":"良"},{"aqi":98,"date":"2018-12-19","quality":"良"}],"hourData":[{"aqi":36,"dateTime":"2018-12-13 12:00:00"},{"aqi":42,"dateTime":"2018-12-13 11:00:00"},{"aqi":46,"dateTime":"2018-12-13 10:00:00"},{"aqi":46,"dateTime":"2018-12-13 09:00:00"},{"aqi":50,"dateTime":"2018-12-13 08:00:00"},{"aqi":50,"dateTime":"2018-12-13 07:00:00"},{"aqi":49,"dateTime":"2018-12-13 06:00:00"},{"aqi":49,"dateTime":"2018-12-13 05:00:00"},{"aqi":46,"dateTime":"2018-12-13 04:00:00"},{"aqi":43,"dateTime":"2018-12-13 03:00:00"},{"aqi":40,"dateTime":"2018-12-13 02:00:00"},{"aqi":40,"dateTime":"2018-12-13 01:00:00"},{"aqi":39,"dateTime":"2018-12-13 00:00:00"},{"aqi":39,"dateTime":"2018-12-12 23:00:00"},{"aqi":39,"dateTime":"2018-12-12 22:00:00"},{"aqi":38,"dateTime":"2018-12-12 21:00:00"},{"aqi":38,"dateTime":"2018-12-12 20:00:00"},{"aqi":38,"dateTime":"2018-12-12 19:00:00"},{"aqi":38,"dateTime":"2018-12-12 18:00:00"},{"aqi":39,"dateTime":"2018-12-12 17:00:00"},{"aqi":38,"dateTime":"2018-12-12 16:00:00"},{"aqi":36,"dateTime":"2018-12-12 15:00:00"},{"aqi":36,"dateTime":"2018-12-12 14:00:00"},{"aqi":38,"dateTime":"2018-12-12 13:00:00"}],"no2":15,"pm10":28,"pm25":25,"province":"广东","quality":"优","so2":8,"updateTime":"2018-12-13 13:00:00"}
         * city : 韶关
         * coldIndex : 易发期
         * date : 2018-12-13
         * distrct : 韶关
         * dressingIndex : 毛衣类
         * exerciseIndex : 不适宜
         * future : [{"date":"2018-12-13","dayTime":"小雨","night":"阴","temperature":"10°C / 7°C","week":"今天","wind":"无持续风向 小于3级"},{"date":"2018-12-14","dayTime":"多云","night":"多云","temperature":"12°C / 7°C","week":"星期五","wind":"无持续风向 小于3级"},{"date":"2018-12-15","dayTime":"多云","night":"多云","temperature":"15°C / 8°C","week":"星期六","wind":"无持续风向 小于3级"},{"date":"2018-12-16","dayTime":"多云","night":"多云","temperature":"14°C / 7°C","week":"星期日","wind":"微风 小于3级"},{"date":"2018-12-17","dayTime":"晴","night":"晴","temperature":"18°C / 8°C","week":"星期一","wind":"微风 小于3级"},{"date":"2018-12-18","dayTime":"晴","night":"阴","temperature":"20°C / 12°C","week":"星期二","wind":"微风 小于3级"},{"date":"2018-12-19","dayTime":"局部多云","night":"局部多云","temperature":"22°C / 15°C","week":"星期三","wind":"东南风 2级"},{"date":"2018-12-20","dayTime":"阵雨","night":"阵雨","temperature":"24°C / 14°C","week":"星期四","wind":"东南偏东风 2级"}]
         * humidity : 湿度：73%
         * pollutionIndex : 36
         * province : 广东
         * sunrise : 07:01
         * sunset : 17:39
         * temperature : 10℃
         * time : 13:23
         * updateTime : 20181213133519
         * washIndex : 不太适宜
         * weather : 阴
         * week : 周四
         * wind : 北风2级
         */

        private String airCondition;
        private AirQualityBean airQuality;
        private String city;
        private String coldIndex;
        private String date;
        private String distrct;
        private String dressingIndex;
        private String exerciseIndex;
        private String humidity;
        private String pollutionIndex;
        private String province;
        private String sunrise;
        private String sunset;
        private String temperature;
        private String time;
        private String updateTime;
        private String washIndex;
        private String weather;
        private String week;
        private String wind;
        private List<FutureBean> future;

        public String getAirCondition() {
            return airCondition;
        }

        public void setAirCondition(String airCondition) {
            this.airCondition = airCondition;
        }

        public AirQualityBean getAirQuality() {
            return airQuality;
        }

        public void setAirQuality(AirQualityBean airQuality) {
            this.airQuality = airQuality;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getColdIndex() {
            return coldIndex;
        }

        public void setColdIndex(String coldIndex) {
            this.coldIndex = coldIndex;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDistrct() {
            return distrct;
        }

        public void setDistrct(String distrct) {
            this.distrct = distrct;
        }

        public String getDressingIndex() {
            return dressingIndex;
        }

        public void setDressingIndex(String dressingIndex) {
            this.dressingIndex = dressingIndex;
        }

        public String getExerciseIndex() {
            return exerciseIndex;
        }

        public void setExerciseIndex(String exerciseIndex) {
            this.exerciseIndex = exerciseIndex;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPollutionIndex() {
            return pollutionIndex;
        }

        public void setPollutionIndex(String pollutionIndex) {
            this.pollutionIndex = pollutionIndex;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getWashIndex() {
            return washIndex;
        }

        public void setWashIndex(String washIndex) {
            this.washIndex = washIndex;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public List<FutureBean> getFuture() {
            return future;
        }

        public void setFuture(List<FutureBean> future) {
            this.future = future;
        }

        public static class AirQualityBean {
            /**
             * aqi : 36
             * city : 韶关
             * district : 韶关
             * fetureData : [{"aqi":40,"date":"2018-12-14","quality":"优"},{"aqi":51,"date":"2018-12-15","quality":"良"},{"aqi":59,"date":"2018-12-16","quality":"良"},{"aqi":40,"date":"2018-12-17","quality":"优"},{"aqi":66,"date":"2018-12-18","quality":"良"},{"aqi":98,"date":"2018-12-19","quality":"良"}]
             * hourData : [{"aqi":36,"dateTime":"2018-12-13 12:00:00"},{"aqi":42,"dateTime":"2018-12-13 11:00:00"},{"aqi":46,"dateTime":"2018-12-13 10:00:00"},{"aqi":46,"dateTime":"2018-12-13 09:00:00"},{"aqi":50,"dateTime":"2018-12-13 08:00:00"},{"aqi":50,"dateTime":"2018-12-13 07:00:00"},{"aqi":49,"dateTime":"2018-12-13 06:00:00"},{"aqi":49,"dateTime":"2018-12-13 05:00:00"},{"aqi":46,"dateTime":"2018-12-13 04:00:00"},{"aqi":43,"dateTime":"2018-12-13 03:00:00"},{"aqi":40,"dateTime":"2018-12-13 02:00:00"},{"aqi":40,"dateTime":"2018-12-13 01:00:00"},{"aqi":39,"dateTime":"2018-12-13 00:00:00"},{"aqi":39,"dateTime":"2018-12-12 23:00:00"},{"aqi":39,"dateTime":"2018-12-12 22:00:00"},{"aqi":38,"dateTime":"2018-12-12 21:00:00"},{"aqi":38,"dateTime":"2018-12-12 20:00:00"},{"aqi":38,"dateTime":"2018-12-12 19:00:00"},{"aqi":38,"dateTime":"2018-12-12 18:00:00"},{"aqi":39,"dateTime":"2018-12-12 17:00:00"},{"aqi":38,"dateTime":"2018-12-12 16:00:00"},{"aqi":36,"dateTime":"2018-12-12 15:00:00"},{"aqi":36,"dateTime":"2018-12-12 14:00:00"},{"aqi":38,"dateTime":"2018-12-12 13:00:00"}]
             * no2 : 15
             * pm10 : 28
             * pm25 : 25
             * province : 广东
             * quality : 优
             * so2 : 8
             * updateTime : 2018-12-13 13:00:00
             */

            private int aqi;
            private String city;
            private String district;
            private int no2;
            private int pm10;
            private int pm25;
            private String province;
            private String quality;
            private int so2;
            private String updateTime;
            private List<FetureDataBean> fetureData;
            private List<HourDataBean> hourData;

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public int getNo2() {
                return no2;
            }

            public void setNo2(int no2) {
                this.no2 = no2;
            }

            public int getPm10() {
                return pm10;
            }

            public void setPm10(int pm10) {
                this.pm10 = pm10;
            }

            public int getPm25() {
                return pm25;
            }

            public void setPm25(int pm25) {
                this.pm25 = pm25;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getQuality() {
                return quality;
            }

            public void setQuality(String quality) {
                this.quality = quality;
            }

            public int getSo2() {
                return so2;
            }

            public void setSo2(int so2) {
                this.so2 = so2;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public List<FetureDataBean> getFetureData() {
                return fetureData;
            }

            public void setFetureData(List<FetureDataBean> fetureData) {
                this.fetureData = fetureData;
            }

            public List<HourDataBean> getHourData() {
                return hourData;
            }

            public void setHourData(List<HourDataBean> hourData) {
                this.hourData = hourData;
            }

            public static class FetureDataBean {
                /**
                 * aqi : 40
                 * date : 2018-12-14
                 * quality : 优
                 */

                private int aqi;
                private String date;
                private String quality;

                public int getAqi() {
                    return aqi;
                }

                public void setAqi(int aqi) {
                    this.aqi = aqi;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getQuality() {
                    return quality;
                }

                public void setQuality(String quality) {
                    this.quality = quality;
                }
            }

            public static class HourDataBean {
                /**
                 * aqi : 36
                 * dateTime : 2018-12-13 12:00:00
                 */

                private int aqi;
                private String dateTime;

                public int getAqi() {
                    return aqi;
                }

                public void setAqi(int aqi) {
                    this.aqi = aqi;
                }

                public String getDateTime() {
                    return dateTime;
                }

                public void setDateTime(String dateTime) {
                    this.dateTime = dateTime;
                }
            }
        }

        public static class FutureBean {
            /**
             * date : 2018-12-13
             * dayTime : 小雨
             * night : 阴
             * temperature : 10°C / 7°C
             * week : 今天
             * wind : 无持续风向 小于3级
             */

            private String date;
            private String dayTime;
            private String night;
            private String temperature;
            private String week;
            private String wind;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getDayTime() {
                return dayTime;
            }

            public void setDayTime(String dayTime) {
                this.dayTime = dayTime;
            }

            public String getNight() {
                return night;
            }

            public void setNight(String night) {
                this.night = night;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }
        }
    }
}
