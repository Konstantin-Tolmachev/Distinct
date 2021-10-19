package com.naumen.distinct.controllers;

import com.naumen.distinct.models.Staff;
import com.naumen.distinct.repo.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AdminController {


    @Autowired
    private StaffRepository staffRepository;

    /* Выводим таблицу сотрудников */

    @GetMapping("/admin")
    public String admin( Model model) {
        Iterable<Staff> staffs = staffRepository.findAllByOrderByIdDesc();
        model.addAttribute("staffs", staffs);
        model.addAttribute("title", "Администратор");
        return "adminHTML/admin";
    }

    /* Добавить нового сотрудника */

    @PostMapping("/admin")
    public String allStaff( @RequestParam String fname,
                            @RequestParam String lname,
                            @RequestParam String pname,
                            @RequestParam String position,
                            Model model) {
        Staff post = new Staff (fname,lname,pname,position);
        staffRepository.save(post);
        return "redirect:/admin";
    }

    /* Удалить сотрудника */

    @PostMapping("/admin/{id}/remove")
    public String staffDelete(@PathVariable(value = "id") long id, Model model) throws Exception {
        Staff post = staffRepository.findById(id).orElseThrow(Exception::new);
        staffRepository.delete(post);
        return "redirect:/admin";
    }

    /* Фильтр для поиска сотрудников по должности */

    @PostMapping("filter-staff")
    public String adminFilterStaff (@RequestParam String filter, Model model) {
//        Iterable<Staff> staffs;
        Iterable<Staff> staffs = staffRepository.findAll();


        if (filter !=null && !filter.isEmpty()){
            staffs = staffRepository.findDistinctByPositionOrderByIdDesc(filter);
        } else {
            staffs = staffRepository.findAllByOrderByIdDesc();
        }
//        Set<String> positions = staffs.stream().map(Staff::getPosition).collect(Collectors.toSet());
//        model.addAttribute("positions", positions);
        model.addAttribute("staffs", staffs);
        return "adminHTML/admin";
    }

}
//    Iterable<Staff> staffs = staffRepository.findAll();
//    Set<String> positions = staffs.stream().map(Staff::getPosition).collect(Collectors.toSet());
//    model.addAttribute("positions", positions);
