import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import {
  AlertController,
  IonButton,
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardSubtitle,
  IonCardTitle,
  IonContent,
  IonHeader,
  IonInput,
  IonItem,
  IonLabel,
  IonTitle,
  IonToolbar,
} from '@ionic/angular/standalone';
import {
  DocumentosService,
  UserDataResponse,
  UsuariosService,
} from 'src/generated/openapi';
import { PreferencesService } from '../preferences.services';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss'],
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
    IonCard,
    FormsModule,
    IonCardContent,
    IonCardTitle,
    IonCardHeader,
    IonCardSubtitle,
  ],
})
export class Tab1Page implements OnInit {
  public userData: UserDataResponse = {
    nombre: '',
    apellido1: '',
    apellido2: '',
    fecha_nacimiento: '',
    sexo: '',
    direccion: '',
    email: '',
  };

  public documentPreviews: any = {
    ineFrente: null,
    ineReverso: null,
    csf: null,
    bancario: null,
  };

  public documentResponses: any = {
    ineFrente: null,
    ineReverso: null,
    csf: null,
    bancario: null,
  };

  private documents: any = {
    ineFrente: null,
    ineReverso: null,
    csf: null,
    bancario: null,
  };

  constructor(
    private preferencesService: PreferencesService,
    private documentosService: DocumentosService,
    private usuariosService: UsuariosService,
    private router: Router,
    private alertController: AlertController
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
          this.loadUserDocuments(userId);
        },
        error: (error) => {
          console.error('Error loading user data:', error);
        },
      });
    }
  }
  loadUserDocuments(userId: string) {
    this.documentosService.obtenerEstadoDocumentos(userId).subscribe({
      next: (response) => {
        response.documentos?.forEach((documento) => {
          if (documento.tipo_documento) {
            this.documentosService
              .obtenerDocumentoPorTipo(userId, documento.tipo_documento)
              .subscribe({
                next: (docResponse) => {
                  if (documento.tipo_documento === 'ine_frente') {
                    this.documentPreviews.ineFrente = `data:image/jpeg;base64,${docResponse.archivo?.base64}`;
                    var aresponse = docResponse;
                    aresponse.archivo = undefined;
                    this.documentResponses.ineFrente = docResponse;
                  } else if (documento.tipo_documento === 'ine_reverso') {
                    this.documentPreviews.ineReverso = `data:image/jpeg;base64,${docResponse.archivo?.base64}`;
                    var aresponse = docResponse;
                    aresponse.archivo = undefined;
                    this.documentResponses.ineReverso = docResponse;
                  } else if (documento.tipo_documento === 'csf') {
                    this.documentPreviews.csf = `data:${docResponse.archivo?.format};base64,${docResponse.archivo?.base64}`;
                    var aresponse = docResponse;
                    aresponse.archivo = undefined;
                    this.documentResponses.csf = docResponse;
                  } else if (
                    documento.tipo_documento === 'documento_bancario'
                  ) {
                    this.documentPreviews.bancario = `data:${docResponse.archivo?.format};base64,${docResponse.archivo?.base64}`;
                    var aresponse = docResponse;
                    aresponse.archivo = undefined;
                    this.documentResponses.bancario = docResponse;
                  }
                },
                error: (error) => {
                  console.error(
                    `Error loading document ${documento.tipo_documento}:`,
                    error
                  );
                },
              });
          }
        });
      },
      error: (error) => {
        console.error('Error loading documents:', error);
      },
    });
  }
  onFileChange(event: any, tipoDocumento: string) {
    const file = event.target.files[0];
    if (file) {
      this.documents[tipoDocumento] = file;
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.documentPreviews[tipoDocumento] = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }
  isPdf(url: string): boolean {
    return url?.endsWith('.pdf');
  }
  async showAlert(message: string) {
    const alert = await this.alertController.create({
      header: 'Error',
      message: message,
      buttons: ['OK'],
    });
    await alert.present();
  }
  async uploadINE() {
    const userId = this.preferencesService.getIdUser();
    if (!userId) {
      await this.showAlert('Usuario no encontrado.');
      return;
    }
    if (!this.documents.ineFrente || !this.documents.ineReverso) {
      await this.showAlert('Por favor, sube ambos archivos de INE.');
      return;
    }
    this.documentosService
      .verificarINE(userId, this.documents.ineFrente, this.documents.ineReverso)
      .subscribe({
        next: (response) => {
          this.documentResponses.ineFrente = response;
          this.documentResponses.ineReverso = response;
          console.log('INE documents uploaded successfully:', response);
        },
        error: (error) => {
          this.documentPreviews.ineFrente = null;
          this.documentPreviews.ineReverso = null;
          this.documentResponses.ineFrente = `Error uploading INE documents: ${error.message}`;
          this.documentResponses.ineReverso = `Error uploading INE documents: ${error.message}`;
          console.error('Error uploading INE documents:', error);
        },
      });
  }

  async uploadCSF() {
    const userId = this.preferencesService.getIdUser();
    if (!userId) {
      await this.showAlert('Usuario no encontrado.');
      return;
    }

    if (!this.documents.csf) {
      await this.showAlert(
        'Por favor, sube el archivo del Certificado de Situación Fiscal.'
      );
      return;
    }

    this.documentosService.verificarCSF(userId, this.documents.csf).subscribe({
      next: (response) => {
        this.documentResponses.csf = response;
        console.log('CSF document uploaded successfully:', response);
      },
      error: (error) => {
        this.documentPreviews.csf = null;
        this.documentResponses.csf = `Error uploading CSF document: ${error.message}`;
        console.error('Error uploading CSF document:', error);
      },
    });
  }

  async uploadBancario() {
    const userId = this.preferencesService.getIdUser();
    if (!userId) {
      await this.showAlert('Usuario no encontrado.');
      return;
    }
    if (!this.documents.bancario) {
      await this.showAlert(
        'Por favor, sube el archivo del documento bancario.'
      );
      return;
    }
    this.documentosService
      .verificarDocumentoBancario(userId, this.documents.bancario)
      .subscribe({
        next: (response) => {
          this.documentResponses.bancario = response;
          console.log('Bank document uploaded successfully:', response);
        },
        error: (error) => {
          this.documentPreviews.bancario = null;
          this.documentResponses.bancario = `Error uploading bank document: ${error.message}`;
          console.error('Error uploading bank document:', error);
        },
      });
  }
}
