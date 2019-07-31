import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Student} from '../model/student/student';
import {Observable} from 'rxjs';

@Injectable()
export class StudentService {

  private readonly baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:9090/student/';
  }

  public findAll(): Observable<Student[]> {
    return this.http.get<Student[]>(this.baseUrl.concat('all'));
  }

  public save(student: Student) {
    console.log(student.address.street);
    console.log(student.address.houseNumber);
    console.log(student.address.pinCode);
    return this.http.post<Student>(this.baseUrl.concat('add'), student);
  }

  public editStudent(id) {
    return this.http.get<Student>(this.baseUrl.concat(id));
  }

}
