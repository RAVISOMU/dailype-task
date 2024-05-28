package com.dailype.task.controller;

import com.dailype.task.dto.ManagerDto;
import com.dailype.task.exception.ManagerNotFoundOrActiveException;
import com.dailype.task.model.Manager;
import com.dailype.task.service.ManagerJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ManagerController {
    @Autowired
    private ManagerJpaService managerJpaService;

    @PostMapping("/manager")
    public ResponseEntity<Manager> createManager(@RequestBody ManagerDto managerDto) {
        return new ResponseEntity<>(managerJpaService.createManager(managerDto), HttpStatus.CREATED);
    }

    @PutMapping("/manager/{managerId}")
    public ResponseEntity<Manager> updateManager(@PathVariable int managerId, @RequestBody ManagerDto managerDto) throws ManagerNotFoundOrActiveException {
        return new ResponseEntity<>(managerJpaService.updateManager(managerId, managerDto), HttpStatus.OK);
    }
}
