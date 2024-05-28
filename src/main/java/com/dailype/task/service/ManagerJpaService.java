package com.dailype.task.service;

import com.dailype.task.dto.ManagerDto;
import com.dailype.task.exception.ManagerNotFoundOrActiveException;
import com.dailype.task.model.Manager;
import com.dailype.task.repository.ManagerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class ManagerJpaService {
    @Autowired
    private ManagerJpaRepository managerJpaRepository;

    public Manager createManager(ManagerDto managerDto) {
        Manager newManager = Manager.build(0, managerDto.getManagerName(), true);
        System.out.println(newManager);

        return managerJpaRepository.save(newManager);
    }

    public Manager updateManager(int managerId, ManagerDto managerDto) throws ManagerNotFoundOrActiveException {
        Manager manager = managerJpaRepository.findByManagerId(managerId);
        if (manager != null) {
            if (managerDto.getManagerName() != null) {
                manager.setManagerName(managerDto.getManagerName());
            }
            manager.setActive(!manager.isActive());

            return managerJpaRepository.save(manager);
        } else {
            throw new ManagerNotFoundOrActiveException("Manager Not Found with id: " + managerId);
        }
    }
}
