import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import {
  IonButton,
  IonContent,
  IonHeader,
  IonInput,
  IonItem,
  IonLabel,
  IonTitle,
  IonToolbar,
} from '@ionic/angular/standalone';
import {
  UserDataResponse,
  UserUpdateRequest,
  UsuariosService,
} from 'src/generated/openapi';
import { PreferencesService } from '../preferences.services';

@Component({
  selector: 'app-tab3',
  templateUrl: 'tab3.page.html',
  styleUrls: ['tab3.page.scss'],
  standalone: true,
  imports: [
    IonHeader,
    CommonModule,
    IonToolbar,
    IonTitle,
    IonContent,
    IonButton,
    IonItem,
    IonLabel,
    IonInput,
    FormsModule,
  ],
})
export class Tab3Page implements OnInit {
  public userData: UserDataResponse = {
    nombre: '',
    apellido1: '',
    apellido2: '',
    fecha_nacimiento: '',
    sexo: '',
    direccion: '',
    email: '',
  };

  constructor(
    private preferencesService: PreferencesService,
    private usuariosService: UsuariosService,
    private router: Router,
  ) {}

  ngOnInit() {
    this.loadUserData();
  }

  loadUserData() {
    const userId = this.preferencesService.getIdUser();
    if (userId) {
      this.usuariosService.obtenerDatosUsuario(userId).subscribe({
        next: (response) => {
          this.userData = response;
        },
        error: (error) => {
          console.error('Error loading user data:', error);
        },
      });
    }
  }

  updateUserData() {
    const userId = this.preferencesService.getIdUser();
    if (userId) {
      const updateRequest: UserUpdateRequest = {
        nombre: this.userData.nombre,
        apellido1: this.userData.apellido1,
        apellido2: this.userData.apellido2,
        fecha_nacimiento: this.userData.fecha_nacimiento,
        sexo: this.userData.sexo,
        direccion: this.userData.direccion,
      };
      this.usuariosService
        .modificarDatosUsuario(userId, updateRequest)
        .subscribe({
          next: (response) => {
            console.log('User data updated successfully:', response);
            location.reload(); // Forzar la recarga de la página después de cargar los datos
          },
          error: (error) => {
            console.error('Error updating user data:', error);
          },
        });
    }
  }

  logout() {
    this.preferencesService.clearSession();
    this.router.navigate(['/tabs']);
    location.reload();
  }
}
