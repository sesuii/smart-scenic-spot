package com.smartscenicspot.vo;

import com.smartscenicspot.constant.ShowplaceConstant;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * A DTO for the {@link com.smartscenicspot.pojo.Attraction} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AttractionVo implements Serializable {
    private Long id;
    @NotBlank
    private String name;
    private String address;
    private Double longitude;
    private Double latitude;
    private String imgsUrl;
    @NotBlank
    private String introduction;
    private String tel;
    private String category;
    private List<Map<String, String>> infrastructure;
    private Double score;
    private Integer capacity;
    private Integer amrt;
    private Byte status;
    private String openNote;
    private double price;
    private String statusMsg;
    private Long parentId;

    public void setStatusMsg() {
        if(this.status == ShowplaceConstant.SHOWPLACE_OPEN) {
            this.statusMsg = ShowplaceConstant.OPEN_MSG;
        }
        else if(this.status == ShowplaceConstant.SHOWPLACE_CLOSE) {
            this.statusMsg = ShowplaceConstant.CLOSE_MSG;
        }
        else {
            this.statusMsg = ShowplaceConstant.UNKNOWN;
        }
    }
}