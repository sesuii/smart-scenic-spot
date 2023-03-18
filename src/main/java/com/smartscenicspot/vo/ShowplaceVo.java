package com.smartscenicspot.vo;

import com.smartscenicspot.constant.ShowplaceConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/24 15:39
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowplaceVo {

    private Long showplaceId;
    private String name;
    private String address;
    private Double longitude;
    private Double latitude;
    private String imgsUrl;
    private String tels;
    private String introduction;
    private Integer capacity;
    private String openNote;
    private String category;
    private Double score;
    private String statusMsg;

    public void setStatusMsg(Byte status) {
        if(status == ShowplaceConstant.SHOWPLACE_OPEN) {
            this.statusMsg = ShowplaceConstant.OPEN_MSG;
        }
        else if(status == ShowplaceConstant.SHOWPLACE_CLOSE) {
            this.statusMsg = ShowplaceConstant.CLOSE_MSG;
        }
        else {
            this.statusMsg = ShowplaceConstant.UNKNOWN;
        }
    }
}
