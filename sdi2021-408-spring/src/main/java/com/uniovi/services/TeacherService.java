package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.uniovi.entities.Teacher;

@Service
public class TeacherService {

private List<Teacher> teacherList = new ArrayList<Teacher>();
	
	@PostConstruct
	public void init(){
		teacherList.add(new Teacher(1,"123456J","Federico","Garcia","Lorca"));
		teacherList.add(new Teacher(2,"77311O","Eustaquio","Perez", "de la Fuente"));		
	}
	
	public void addTeacher(Teacher teacher){
		if(teacher.getId() == null) {
			teacher.setId(teacherList.get(teacherList.size() -1).getId() + 1); 
		}
		teacherList.add(teacher);
	}
	public void deleteTeacher(Long id){
		teacherList.removeIf(teacher-> teacher.getId().equals(id));
		}
	public Teacher getTeacher(Long id){
		return teacherList.stream().filter(teacher-> teacher.getId().equals(id)).findFirst().get();}
}
