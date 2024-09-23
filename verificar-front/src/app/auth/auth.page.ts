import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import {
  IonButton,
  IonContent,
  IonInput,
  IonItem,
  IonLabel,
} from '@ionic/angular/standalone';
import {
  LoginUserRequest,
  RegisterRequest,
  UsuariosService,
} from 'src/generated/openapi';
import { PreferencesService } from '../preferences.services';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.page.html',
  styleUrls: ['./auth.page.scss'],
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    IonButton,
    IonContent,
    IonInput,
    IonItem,
    IonLabel,
  ],
})
export class AuthPage {
  public isLoginMode = true;
  public loginData = { user: '', password: '' };
  public registerData = {
    user: '',
    password: '',
    confirmPassword: '',
    nombre: '',
    fecha_nacimiento: '',
    sexo: '',
    direccion: '',
    apellido1: '',
    apellido2: '',
  };

  constructor(
    private usuariosService: UsuariosService,
    private preferencesService: PreferencesService,
    private router: Router,
  ) {
    console.log('AuthPage constructor');
  }

  toggleMode() {
    this.isLoginMode = !this.isLoginMode;
  }

  login() {
    const loginRequest: LoginUserRequest = {
      username: this.loginData.user,
      password: this.loginData.password,
    };
    this.usuariosService.loginUser(loginRequest).subscribe({
      next: (response) => {
        this.preferencesService.setSession(response.token);
        this.preferencesService.saveIdUser(response.id);
        this.router.navigate(['/tabs']);
        //reload
        location.reload();
      },
      error: (error) => {
        console.error('Login error:', error);
      },
    });
  }

  register() {
    if (this.registerData.password !== this.registerData.confirmPassword) {
      console.error('Passwords do not match');
      return;
    }

    const registerRequest: RegisterRequest = {
      username: this.registerData.user,
      password: this.registerData.password,
      nombre: this.registerData.nombre,
      fecha_nacimiento: this.registerData.fecha_nacimiento,
      sexo: this.registerData.sexo,
      direccion: this.registerData.direccion,
      apellido1: this.registerData.apellido1,
      apellido2: this.registerData.apellido2,
    };

    this.usuariosService.registerUser(registerRequest).subscribe({
      next: (response) => {
        this.preferencesService.setSession(response.token);
        this.preferencesService.saveIdUser(response.id);
        this.router.navigateByUrl('/tabs');
        location.reload();
      },
      error: (error) => {
        console.error('Registration error:', error);
      },
    });
  }
}
