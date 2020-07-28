package org.simpleframework.inject;

import com.huangxi.controller.frontend.MainPageController;
import com.huangxi.service.combine.HeadLineShopCategoryCombineService;
import com.huangxi.service.combine.impl.HeadLineShopCategoryCombineServiceImpl;
import com.huangxi.service.combine.impl.HeadLineShopCategoryCombineServiceImpl2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.BeanContainer;

public class DependencyInjectorTest {

    @DisplayName("依赖注入: doIoc")
    @Test
    public void doIocTest(){
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.huangxi");
        Assertions.assertEquals(true,beanContainer.isLoaded());
        MainPageController mainPageController = (MainPageController)beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true, mainPageController instanceof MainPageController);
        Assertions.assertEquals(null,mainPageController.getHeadLineShopCategoryCombineService());
        //依赖注入
        new DependencyInjector().doIoc();
        Assertions.assertNotEquals(null,mainPageController.getHeadLineShopCategoryCombineService());
        Assertions.assertEquals(true,mainPageController.getHeadLineShopCategoryCombineService() instanceof HeadLineShopCategoryCombineServiceImpl);
        Assertions.assertEquals(false,mainPageController.getHeadLineShopCategoryCombineService() instanceof HeadLineShopCategoryCombineServiceImpl2);

    }
}
