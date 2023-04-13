package com.smartscenicspot.task;

import com.smartscenicspot.db.neo4j.entity.AttractionNode;
import com.smartscenicspot.dto.NoticeDto;
import com.smartscenicspot.service.Neo4jService;
import com.smartscenicspot.service.NoticeService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 人流量监控预警任务
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/14 10:21
 **/

@Component
public class TrafficWarningTask {
    @Resource
    Neo4jService neo4jService;

    @Resource
    NoticeService noticeService;

    static final String SUBJECT_TEMPLATE = "🔥人流量预警-%s";

    static final String CONTENT_TEMPLATE = "当前时间 %s,【%s】景点当前人数为 %s，即将到达最大承载量，" +
            "为保证游玩体验，请各位游客避免在高峰期前往！";
    final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * 定时任务监测人流量变化，当超出承载量 90% 时自动推送景区公告
     * 第一次延迟 1 分钟执行，之后每 5 分钟执行一次
     */
    @Async
    @Scheduled(initialDelay = 1000, fixedRate = 1000 * 60 * 5)
    public void neo4jTrafficWarningTask() {
        List<AttractionNode> overCapacityNodes = neo4jService.getOverCapacity();
        if(overCapacityNodes.isEmpty()) {
            return;
        }
        overCapacityNodes.forEach(node -> {
            NoticeDto newNotice = NoticeDto.builder()
                    .publishTime(new Date())
                    .publishWay("自动推送")
                    .subject(String.format(SUBJECT_TEMPLATE, node.getName()))
                    .content(String.format(CONTENT_TEMPLATE, formatter.format(new Date()),
                            node.getName(), node.getCurrent()))
                    .build();
            noticeService.broadCast(newNotice);
        });
    }
}
