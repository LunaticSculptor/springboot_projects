package com.example.SpringJPA;

import com.example.SpringJPA.model.Student;
import com.example.SpringJPA.repository.StudentRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringJpaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringJpaApplication.class, args);

		StudentRepo repo = context.getBean(StudentRepo.class);

		Student s1 = context.getBean(Student.class);
		Student s2 = context.getBean(Student.class);
		Student s3 = context.getBean(Student.class);

		s1.setRollNo(101);
		s1.setName("John");
		s1.setMarks(99);

		s2.setRollNo(102);
		s2.setName("Ramu");
		s2.setMarks(77);

		s3.setRollNo(103);
		s3.setName("Ashu");
		s3.setMarks(61);

//		repo.save(s2);        // Create
//		repo.save(s3);

//		System.out.println(repo.findAll());

//		Optional<Student> s = repo.findById(109);
//		System.out.println(s.orElse(new Student()));

		List<Student> nameFind = repo.findBySName("Ramu");     // Retrieve
		System.out.println(nameFind);

		System.out.println(repo.findByMarksGreaterThan(55));

		s2.setRollNo(102);
		s2.setName("Ramu");
		s2.setMarks(55);     // Marks Changed
		repo.save(s2);		// Update

		System.out.println(repo.findAll());

		repo.delete(s1);		// Delete
		System.out.println(repo.findAll());
	}

}
