package org.simpleframework.aop.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 定义一个切面
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class AspectInfo {
    private int orderIndex;
    private DefaultAspect aspectObject;
}
