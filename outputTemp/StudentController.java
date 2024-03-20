package scaffold.framework.demo.controller;

import org.springframework.stereotype.Controller ;
import org.springframework.ui.Model ;
import org.springframework.web.bind.annotation.GetMapping ;
import org.springframework.web.bind.annotation.PathVariable ;
import org.springframework.web.bind.annotation.PostMapping ;
import org.springframework.web.bind.annotation.RequestMapping ;
import org.springframework.web.bind.annotation.RequestParam ;

import scaffold.framework.demo.entity.Student ;

import scaffold.framework.demo.service.StudentService ;

import scaffold.framework.demo.service.PromotionService ;

import scaffold.framework.demo.entity.PromotionModel ;

@Controller
@RequestMapping("/student")        

public class StudentController {

    private StudentService studentService ;

    private PromotionModel promotionModel ;

