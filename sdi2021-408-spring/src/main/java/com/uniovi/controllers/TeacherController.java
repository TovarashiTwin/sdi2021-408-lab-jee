package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Teacher;
import com.uniovi.services.TeacherService;

@RestController
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping( value = "/teacher/add", method = RequestMethod.POST)
	public String setTeacher(@ModelAttribute Teacher teacher) {
		teacherService.addTeacher(teacher);
		return "Profesor añadido";
	}
	@RequestMapping(value = "teacher/add")
	public String getMark() {
		return "Inicio profesor añadido";
	}
	
	@RequestMapping("/teacher/details/{id}")
	public String getDetail(@PathVariable Long id) {
		return teacherService.getTeacher(id).toString();
	}
	@RequestMapping("/teacher/delete/{id}")
	public String deleteTeacher(@PathVariable Long id) {
		teacherService.deleteTeacher(id);
		return "Profesor borrado";
	}
	@RequestMapping(value = "/teacher/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("teacher",teacherService.getTeacher(id));
		return "Inicio edit profesor";
	}
	@RequestMapping(value = "/teacher/edit/{id}",method=RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Teacher teacher) {
		teacher.setId(id);
		teacherService.addTeacher(teacher);
		return "Profesor editado";
	}
	
}
