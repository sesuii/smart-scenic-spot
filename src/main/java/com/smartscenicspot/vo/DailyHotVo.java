package com.smartscenicspot.vo;

import lombok.*;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 每日热门展示层对象
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/12 8:42
 **/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DailyHotVo implements Serializable {

    final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 今日到访总人数
     */
    private int dailyVisitors;

    private Double amrtAvg;

    /**
     * key: 景点名称，value: 景点人数
     */
    private Map<String, Long> topAttraction;

    private Date startTime;

    private Date endTime = new Date();

    public void setStartTime() {
        try {
            this.startTime = dateFormat.parse(dateFormat.format(new Date()) + "00:00:00");
        } catch (ParseException e) {
            this.startTime = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000L);
        }
    }
}
