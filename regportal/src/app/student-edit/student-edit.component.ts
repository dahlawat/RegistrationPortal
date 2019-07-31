import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {StudentService} from "../service/student.service";
import {Student} from "../model/student/student";

@Component({
  selector: 'app-student-edit',
  templateUrl: './student-edit.component.html',
  styleUrls: ['./student-edit.component.css']
})
export class StudentEditComponent implements OnInit {

  student: Student;

  constructor(private route: ActivatedRoute, private router: Router, private studentService: StudentService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.studentService.editStudent(params['id']).subscribe(data => {
        this.student = data;
      });
    });
  }

  onSubmit() {
    this.studentService.save(this.student).subscribe(result => this.gotoStudentList());
  }

  gotoStudentList() {
    this.router.navigate(['/student/all']);
  }

}
