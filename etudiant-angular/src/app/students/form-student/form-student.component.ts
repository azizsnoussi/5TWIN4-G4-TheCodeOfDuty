import { Component, OnInit } from '@angular/core';
import { StudentService } from '../../Core/Services/student.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { student } from '../../Core/Modals/etudiant';


@Component({
  selector: 'app-form-student',
  templateUrl: './form-student.component.html',
  styleUrls: ['./form-student.component.css']
})
export class FormStudentComponent implements OnInit {
//studentDetails = null as any;

  public idselected:number[]=[];

  studentToUpdate = {
        op:"",
    idEtudiant:"",
    nomE:"",
    prenomE:"",
  }

  constructor(private studentService: StudentService,private router:Router) {
    
   }

  ngOnInit(): void {

  }

   displayStyle = "none";
  
  openPopup() {
    this.displayStyle = "block";
  }
  closePopup() {
    this.displayStyle = "none";
  }

  register(registerForm: NgForm) {

       const {departement:_,...etud}=registerForm.value;


        var i =+registerForm.value.departement;
    

    this.studentService.registerStudent(etud).subscribe(
      
      (resp:student) => { 
        
    if(this.idselected.length !== 0 )
        this.idselected.forEach((e)=> this.studentService.assignstudenttoequipe(resp.idEtudiant,e).subscribe( ));

    if (i!==0){
   this.studentService.assignStudent(resp.idEtudiant,i).subscribe();
    console.log("lalaal")}else console.log("hello ");

      },(err) => {
        console.log(err);
      
      },  
      () => {
        setTimeout(()=>{                         
      this.router.navigate(['/students'])
 }, 500);
         
      }

       
    );

  
   
  }  

  getid(e){

 console.log(e)
 if(e.target.checked){
  this.idselected.push(+e.target.value);
  console.log(this.idselected);
 }else{
  this.idselected=this.idselected.filter((id)=>id !== +e.target.value);
  console.log(this.idselected);
 }

}


}



