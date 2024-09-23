// preferences.service.ts
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class PreferencesService {
  private readonly SESSION_KEY = 'user_session';

  constructor() {}

  setSession(sessionData: any): void {
    localStorage.setItem(this.SESSION_KEY, JSON.stringify(sessionData));
  }

  getSession(): any {
    const sessionData = localStorage.getItem(this.SESSION_KEY);
    return sessionData ? JSON.parse(sessionData) : null;
  }

  clearSession(): void {
    localStorage.clear();
  }
  saveIdUser(idUser?: string): void {
    localStorage.setItem('idUser', JSON.stringify(idUser));
  }
  getIdUser(): string {
    const idUser = localStorage.getItem('idUser');
    return idUser ? JSON.parse(idUser) : null;
  }
  isAuth(): boolean {
    return this.getSession() !== null;
  }
}
