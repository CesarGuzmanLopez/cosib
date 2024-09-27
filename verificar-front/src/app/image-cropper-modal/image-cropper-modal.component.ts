import { CommonModule } from '@angular/common';
import {
  Component,
  EventEmitter,
  inject,
  Input,
  OnInit,
  Output,
} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ModalController } from '@ionic/angular';
import {
  IonButton,
  IonButtons,
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
  Dimensions,
  ImageCroppedEvent,
  ImageCropperComponent,
} from 'ngx-image-cropper';

@Component({
  selector: 'app-image-cropper-modal',
  templateUrl: './image-cropper-modal.component.html',
  styleUrls: ['./image-cropper-modal.component.scss'],
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
    IonButtons,
  ],
  providers: [ModalController],
})
export class ImageCropperModalComponent implements OnInit {
  @Input() imageBase64: string | undefined;
  @Input() documentType: string | undefined; // Añadir el tipo de documento como entrada
  @Output() imageCropped = new EventEmitter<string>();

  croppedImage: SafeUrl | undefined;
  croppedImageBase64: string | undefined;
  private modalController: ModalController = inject(ModalController);
  constructor(private sanitizer: DomSanitizer) {}
  ngOnInit(): void {
    // Aquí puedes configurar algo si es necesario
  }
  onCropperReady($event: Dimensions) {
    console.log('Cropper ready', $event);
  }
  async onImageCropped(event: ImageCroppedEvent) {
    this.croppedImage = this.sanitizer.bypassSecurityTrustUrl(
      event.objectUrl as string,
    );
    //convierto el blob a base64
    const reader = new FileReader();
    reader.readAsDataURL(event.blob as Blob);
    reader.onloadend = () => {
      this.croppedImageBase64 = reader.result as string;
    };
  }

  closeModal() {
    this.modalController.dismiss();
  }

  async confirmCrop() {
    // Espero que this.croppedImage tenga la imagen recortada
    if (this.croppedImageBase64) {
      await this.modalController.dismiss({
        croppedImage: this.croppedImageBase64,
        documentType: this.documentType, // Incluir el tipo de documento en los datos de retorno
      });
    } else {
      // Manejar el caso en el que no hay imagen recortada
      await this.modalController.dismiss({
        error: 'No se pudo recortar la imagen. Inténtalo de nuevo.',
      });
    }
  }
}
