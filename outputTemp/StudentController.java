package scaffold.framework.demo.controller;

import org.springframework.stereotype.Controller ;
import org.springframework.ui.Model ;
import org.springframework.web.bind.annotation.GetMapping ;
import org.springframework.web.bind.annotation.PathVariable ;
import org.springframework.web.bind.annotation.PostMapping ;
import org.springframework.web.bind.annotation.RequestMapping ;

import scaffold.framework.demo.entity.Student ;

import scaffold.framework.demo.service.StudentService ;

@Controller
@RequestMapping("/student")        

public class StudentController {

    private StudentService studentService ;
       // tokony hisy anle Service anzay foreign classe

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
           // tokony hisy anle Service anzay foreign classe
    }
    @GetMapping("/list")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
           // tokony hisy anle Service anzay foreign classe
        return "pages/student/list";
    }
    @GetMapping("/add")
    public String showAddStudentForm(Model model) {
        return "pages/student/add";
    }
    @PostMapping("/add")
    public String addStudent( Student student) { 
    // @RequestParam String promotionId,
    // Promotion promotion = new Promotion();
    // promotion.setId(Long.parseLong(promotionId));
        studentService.save(student);
        return "redirect:/students/list";
    }
    @GetMapping("/edit{id}")
    public String showEditStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        // List<Promotion> promotions = promotionService.findAll();
        // promotions.remove(student.getPromotion());
        // model.addAttribute("promotions", promotions);
        return "pages/student/edit";
    }
    @PostMapping("/edit")
    // @RequestParam Long promotionId
    public String editStudent(Student student) {
        // No error handling for yet
        if (student.getId() != null) {
            studentService.save(student);
        }
        // if (student.getId() != null && promotionId != null) {
        //     Promotion promotion = new Promotion();
        //     promotion.setId(promotionId);
        //     student.setPromotion(promotion);
        //     studentService.save(student);
        // }
        return "redirect:/students/list";
    }
    @GetMapping("/delete{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return "redirect:/students/list";
    }
}    

