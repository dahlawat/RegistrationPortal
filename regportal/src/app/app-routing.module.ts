import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {StudentListComponent} from './student-list/student-list.component';
import {StudentFormComponent} from './student-form/student-form.component';
import {StudentEditComponent} from "./student-edit/student-edit.component";


const routes: Routes = [
  {path: 'student/all', component: StudentListComponent},
  {path: 'student/add', component: StudentFormComponent},
  {path: 'student/:id', component: StudentEditComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
