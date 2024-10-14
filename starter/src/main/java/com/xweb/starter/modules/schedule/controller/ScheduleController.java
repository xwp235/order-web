package com.xweb.starter.modules.schedule.controller;

import com.xweb.starter.common.resp.JsonResp;
import com.xweb.starter.modules.schedule.req.JobReq;
import com.xweb.starter.modules.schedule.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("schedules")
public class ScheduleController {

    private final SchedulerService schedulerService;

    @GetMapping
    public JsonResp list() {
        return JsonResp.data(schedulerService.list());
    }

    @PostMapping
    public JsonResp saveJob(@RequestBody JobReq req) {
        return JsonResp.data(schedulerService.saveJob(req));
    }

    @PatchMapping("{action}")
    public JsonResp changeState(@PathVariable String action,@RequestBody JobReq jobReq) {
      switch (action) {
        case "run" -> schedulerService.run(jobReq);
        case "pause" -> schedulerService.pause(jobReq);
        case "resume" -> schedulerService.resume(jobReq);
      }
      return JsonResp.ok();
    }

    @DeleteMapping
    public JsonResp delete(@RequestBody JobReq jobReq) {
        return JsonResp.data(schedulerService.delete(jobReq));
    }

    @PutMapping
    public JsonResp rescheduleJob(@RequestBody JobReq req){
        return JsonResp.data(schedulerService.rescheduleJob(req));
    }

}
