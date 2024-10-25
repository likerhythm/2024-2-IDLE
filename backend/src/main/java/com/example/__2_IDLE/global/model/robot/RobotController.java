package com.example.__2_IDLE.global.model.robot;

import com.example.__2_IDLE.global.model.robot.request.AddRobotRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
public class RobotController {
    private RobotService robotService;


    //로봇 추가 (FRONT -> SPRING -> ROS)
    @PostMapping("/robot/add")
    public void addRobot(@RequestBody AddRobotRequest request) {
        // TODO ROS 서버로 요청 보내고 응답 받기
        Robot robot = new Robot(request.getNamespace(), request.getPose());
        robotService.addRobot(robot);
    }

    // 로봇 제거 (FRONT -> SPRING -> ROS)
    @DeleteMapping("/robot/remove")
    public void removeRobot(@RequestParam String namespace) {
        // TODO ROS 서버로 요청 보내고 응답 받기
        robotService.removeRobot(namespace);
    }
}