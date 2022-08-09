package hntech.hntechserver.utils.scheduler

import hntech.hntechserver.utils.PropertiesManager
import hntech.hntechserver.utils.logger
import org.springframework.context.annotation.Lazy
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.SchedulingConfigurer
import org.springframework.scheduling.config.ScheduledTaskRegistrar
import org.springframework.scheduling.support.CronTrigger
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cron")
class SchedulerController(private val scheduleTask: ScheduleTask) {

    @GetMapping("/mail")
    fun setMailCron(@RequestParam("time") time: String) {

    }
}

@Lazy(false)
@Component
@EnableScheduling
@EnableAsync
class ScheduleTask(
    private val mailService: MailService
): SchedulingConfigurer {
    private val log = logger()

    // cron 정규 표현식, 초기값 : 매일 낮 12시
    private var cron: String = "0/3 * * * * ?"
    
    fun setCron(time: String) {
        this.cron = "0 0 $time * * ?"
        log.info("Updated cron: {}", this.cron)
    }

    // 스케쥴러 작업 등록 메소드
    override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {
        taskRegistrar.addTriggerTask(
            {
                // cron 시간마다 실행할 작업
                mailService.sendMail()
            },
            {
                // 위에 정의한 작업의 실행 트리거 설정
                CronTrigger(cron).nextExecutionTime(it);
            }
        )
    }
}