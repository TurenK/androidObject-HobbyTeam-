package com.example.activitys.Exercise;

import java.io.Serializable;

/**
 * Created by TurenK on 2017/7/9.
 */

public class ItemListEventInfo implements Serializable {
        private long id;
        private String name;
        private String intro;
        private String head_img;
        private String addr;
        private String lat;
        private String lon;
        private String start_time;

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public long getId() {
            return id;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public void setId(long id) {
            this.id = id;
        }
}
