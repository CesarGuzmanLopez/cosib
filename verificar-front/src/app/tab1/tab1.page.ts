import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Camera, CameraResultType, CameraSource } from '@capacitor/camera';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {
  faCamera,
  faCircle,
  faCoffee,
  faTimesCircle,
  faUpload,
} from '@fortawesome/free-solid-svg-icons';
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
  IonLoading,
  IonTitle,
  IonToolbar,
  ModalController,
} from '@ionic/angular/standalone';
import { ImageCropperComponent } from 'ngx-image-cropper';
import {
  DocumentosService,
  UserDataResponse,
  UsuariosService,
} from 'src/generated/openapi';
import { ImageCropperModalComponent } from '../image-cropper-modal/image-cropper-modal.component';
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
    ImageCropperComponent,
    FontAwesomeModule,
    IonLoading,
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
  faCoffee = faCoffee;
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
  datosIneRespuesta: any = {
    Nombre: '',
    Apellido1: '',
    Apellido2: '',
  };
  private documents: any = {
    ineFrente: null,
    ineReverso: null,
    csf: null,
    bancario: null,
  };

  // Variables para manejar el crop
  croppedImage: { [key: string]: string } = {};
  imageToCrop: string = '';
  documentType: string = '';
  faCamera = faCamera;
  faUpload = faUpload;
  faCircle = faCircle;
  faTimesCircle = faTimesCircle;

  // Variable para manejar el estado del spinner
  isLoading = false;

  constructor(
    private preferencesService: PreferencesService,
    private documentosService: DocumentosService,
    private usuariosService: UsuariosService,
    private router: Router,
    private alertController: AlertController,
    private modalController: ModalController,
  ) {}

  ngOnInit() {
    this.loadUserData();
  }

  async loadUserData() {
    this.isLoading = true;
    const userId = this.preferencesService.getIdUser();
    if (userId) {
      this.usuariosService.obtenerDatosUsuario(userId).subscribe({
        next: (response) => {
          this.userData = response;
          this.loadUserDocuments(userId);
        },
        error: (error) => {
          console.error('Error loading user data:', error);
          this.isLoading = false;
        },
      });
    } else {
      this.isLoading = false;
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
                    this.datosIneRespuesta.Nombre = docResponse.nombre;
                    this.datosIneRespuesta.Apellido1 = docResponse.apellido1;
                    this.datosIneRespuesta.Apellido2 = docResponse.apellido2;
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
                    error,
                  );
                },
              });
          }
        });
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error loading documents:', error);
        this.isLoading = false;
      },
    });
  }

  async takePhotoFrente() {
    this.documentType = 'ineFrente';

    const image = await Camera.getPhoto({
      quality: 90,
      allowEditing: false,
      resultType: CameraResultType.DataUrl,
      source: CameraSource.Camera,
    });

    this.imageToCrop = image.dataUrl!;
    this.openCropperModal(this.documentType);
  }

  async takePhotoReverso() {
    this.documentType = 'ineReverso';

    const image = await Camera.getPhoto({
      quality: 90,
      allowEditing: false,
      resultType: CameraResultType.DataUrl,
      source: CameraSource.Camera,
    });

    this.imageToCrop = image.dataUrl!;
    this.openCropperModal(this.documentType);
  }

  async openCropperModal(documentType: string) {
    const modal = await this.modalController.create({
      component: ImageCropperModalComponent,
      componentProps: {
        imageBase64: this.imageToCrop,
        documentType: documentType, // Pasar el tipo de documento al modal
      },
    });

    modal.onDidDismiss().then((result) => {
      if (result.data) {
        const { croppedImage, documentType } = result.data;
        this.croppedImage[documentType] = croppedImage;
        this.documentPreviews[documentType] = croppedImage;
        this.documents[documentType] = this.dataURLtoFile(
          croppedImage,
          `${documentType}.jpg`,
        );
      }
    });

    return await modal.present();
  }

  onFileChange(event: any, tipoDocumento: string) {
    const file = event.target.files[0];
    if (file) {
      this.documents[tipoDocumento] = file;
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.imageToCrop = e.target.result; // Guardar la imagen para recortar
        this.openCropperModal(tipoDocumento);
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

    this.isLoading = true;
    this.documentosService
      .verificarINE(userId, this.documents.ineFrente, this.documents.ineReverso)
      .subscribe({
        next: (response) => {
          this.documentResponses.ineFrente = response;
          this.documentResponses.ineReverso = response;
          console.log('INE documents uploaded successfully:', response);
          this.datosIneRespuesta.Nombre = response.nombre;
          this.datosIneRespuesta.Apellido1 = response.apellido1;
          this.datosIneRespuesta.Apellido2 = response.apellido2;
          this.isLoading = false;
        },
        error: async (error) => {
          await this.showAlert(
            `Error al subir los documentos INE: ${error.message}`,
          );
          this.isLoading = false;
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
        'Por favor, sube el archivo del Certificado de Situación Fiscal.',
      );
      return;
    }

    this.isLoading = true;
    this.documentosService.verificarCSF(userId, this.documents.csf).subscribe({
      next: (response) => {
        this.documentResponses.csf = response;
        console.log('CSF document uploaded successfully:', response);
        this.isLoading = false;
      },
      error: async (error) => {
        this.documentPreviews.csf = null;
        this.documentResponses.csf = `Error uploading CSF document: ${error.message}`;
        console.error('Error uploading CSF document:', error);
        await this.showAlert(
          `Error al subir el documento CSF: ${error.message}`,
        );
        this.isLoading = false;
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
        'Por favor, sube el archivo del Comprobante Bancario.',
      );
      return;
    }

    this.isLoading = true;
    this.documentosService
      .verificarDocumentoBancario(userId, this.documents.bancario)
      .subscribe({
        next: (response) => {
          this.documentResponses.bancario = response;
          console.log('Bancario document uploaded successfully:', response);
          this.isLoading = false;
        },
        error: async (error) => {
          this.documentPreviews.bancario = null;
          this.documentResponses.bancario = `Error uploading Bancario document: ${error.message}`;
          console.error('Error uploading Bancario document:', error);
          await this.showAlert(
            `Error al subir el documento bancario: ${error.message}`,
          );
          this.isLoading = false;
        },
      });
  }

  dataURLtoFile(dataurl: string, filename: string) {
    const arr = dataurl.split(',');
    const mimeMatch = arr[0].match(/:(.*?);/);
    const mime = mimeMatch ? mimeMatch[1] : '';
    const bstr = atob(arr[1]);
    let n = bstr.length;
    const u8arr = new Uint8Array(n);
    while (n--) {
      u8arr[n] = bstr.charCodeAt(n);
    }
    return new File([u8arr], filename, { type: mime });
  }
}
