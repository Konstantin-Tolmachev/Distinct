package com.naumen.distinct.repo;

import com.naumen.distinct.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    // Вывод сотрудников в обратном порядке
    List<Staff> findAllByOrderByIdDesc();

    // Вывод сотрудников по фильтру должности в обратном порядке
    List<Staff> findAllByPositionOrderByIdDesc(String filter);

    // Вывод сотрудников по фильтру должности без повторений в обратном порядке (Не работает)
    List<Staff> findDistinctByPositionOrderByIdDesc(String filter);


}
