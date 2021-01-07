package com.gxw.store.project.schedule.user;

import com.gxw.store.project.user.service.BusinessService;
import com.gxw.store.project.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class VipTask {
    private static final Logger log = LoggerFactory.getLogger(VipTask.class);

    @Resource
    private BusinessService businessService;

    @Resource
    private UserService userService;

    @Scheduled(cron="0 0 1 * * * ")
    public void scheduledTask() {
        log.info("开始执行Vip信息更新任务");
        List<Long> businessIds = businessService.getAllBusinessId();
        if(businessIds == null){
            return;
        }
        for (Long id : businessIds){
            userService.freshAllUserVip(id);
            log.info("商户"+id+"执行完毕");
        }
        log.info("Vip信息更新任务结束");
    }


}
