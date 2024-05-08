package cn.weedien.csust.uniapp.webaccess.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author weedien
 * @since 2023-12-15
 */
@Getter
@Setter
@ApiModel(value = "Studentinfo对象", description = "")
public class Studentinfo {

    private Integer studentId;

    private String studentName;

    private String studentClass;
}
