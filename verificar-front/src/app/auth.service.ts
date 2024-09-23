// auth.service.ts
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {
  LoginResponse,
  LoginUserRequest,
  UsuariosService,
} from 'src/generated/openapi';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private isLoggedIn = false;
  constructor(private usuariosService: UsuariosService) {}

  login(user: string, password: string): Observable<LoginResponse> {
    const loginRequest: LoginUserRequest = { user, password };
    return this.usuariosService.loginUser(loginRequest);
  }

  register(user: string, password: string): Observable<LoginResponse> {
    const registerRequest: RegisterRequest = { user, password };
    return this.usuariosService.registerUser(registerRequest);
  }

  isAuthenticated(): boolean {
    return this.isLoggedIn;
  }

  setLoggedIn(status: boolean) {
    this.isLoggedIn = status;
  }
}
