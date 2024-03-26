import { Component } from '@angular/core';
import { FormGroup, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import {MatCard, MatCardTitle, MatCardContent} from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatFormField } from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {MatIcon} from '@angular/material/icon'
import { AuthService } from '../services/auth.service';
import { JWTRes } from '../interfaces/JWTres';
import { Router, RouterLink } from '@angular/router';
import { NgClass } from '@angular/common';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    MatCard, MatCardTitle, MatCardContent, MatFormField, 
    MatInputModule, MatButtonModule, MatIcon, ReactiveFormsModule, NgClass,
    RouterLink
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  profileForm = new FormGroup({
   username: new FormControl('', [Validators.required, Validators.maxLength(20), Validators.minLength(3), Validators.pattern(new RegExp("\\S"))]),
   password: new FormControl('', [Validators.required, Validators.maxLength(50), Validators.minLength(6), Validators.pattern(new RegExp("\\S"))])
  });

  errorMessage: string = '';

  title: string = 'Login';

  hide = true;

  constructor(private authService: AuthService, private router: Router){

  }

  ngOnInit(){
    if(this.router.url === '/register'){
      this.title = 'Register';
    } else {
      this.title = 'Login';
    }
  }


  submit() {
    if(this.profileForm.valid){
      const username: string = this.profileForm.controls.username.value!;
      const password = this.profileForm.controls.password.value!;
      this.authService.login(username, password).subscribe({
        next: (data: JWTRes) => {
          console.log(data);
        }, error: err => {
          console.log(err);
        }
      })
      
    }
  }

  navigateToLogin() {

  }

}
