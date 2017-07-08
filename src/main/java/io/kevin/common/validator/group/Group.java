package io.kevin.common.validator.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 * @author ZGJ
 * @date 2017/7/3 21:04
*/
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
