package com.smartscenicspot.constant;

/**
 * 景区信息常量枚举
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/24 16:07
 **/
public class ShowplaceConstant {

    public static final int SHOWPLACE_CLOSE = 0;

    public static final int SHOWPLACE_OPEN = 1;

    public static final String CLOSE_MSG = "暂未开放";

    public static final String OPEN_MSG = "正常开放";

    public static final String UNKNOWN = "开放情况未知";

    public static final double EARTH_RADIUS = 6371.0;

    public static final double LOCATION_LIMIT = 3.0;

    public static final double CALC_LATITUDE = (ShowplaceConstant.LOCATION_LIMIT
            / ShowplaceConstant.EARTH_RADIUS) * 180 / Math.PI;

    public static final double CALC_HALF = Math.sin(ShowplaceConstant.LOCATION_LIMIT/
            (2 * ShowplaceConstant.LOCATION_LIMIT));

}
