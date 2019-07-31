import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Student} from "../model/student/student";
import {StudentService} from "../service/student.service";
import {Address} from "../model/address/address";

@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrls: ['./student-form.component.css']
})
export class StudentFormComponent {

  student: Student;
  address: Address;

  constructor(private route: ActivatedRoute, private router: Router, private studentService: StudentService) {
    this.student = new Student();
    this.address = new Address();
    this.student.address = this.address;
    this.student.registration = this.generateStudentRegistration();
  }

  onSubmit() {
    this.studentService.save(this.student).subscribe(result => this.gotoStudentList());
  }

  gotoStudentList() {
    this.router.navigate(['/student/all']);
  }

  generateStudentRegistration() {
    let result = '';
    let regLength = 10;
    let characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let charactersLength = characters.length;
    for (var i = 0; i < regLength; i++) {
      result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    return result;
  }
}
