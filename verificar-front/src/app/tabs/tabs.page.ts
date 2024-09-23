// tabs.page.ts
import { CommonModule } from '@angular/common';
import { Component, EnvironmentInjector, inject } from '@angular/core';
import {
  IonIcon,
  IonLabel,
  IonTabBar,
  IonTabButton,
  IonTabs,
} from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import { ellipse, square, triangle } from 'ionicons/icons';
import { AuthPage } from '../auth/auth.page';
import { PreferencesService } from '../preferences.services';

@Component({
  selector: 'app-tabs',
  templateUrl: 'tabs.page.html',
  styleUrls: ['tabs.page.scss'],
  standalone: true,
  imports: [
    CommonModule,
    IonTabs,
    IonTabBar,
    IonTabButton,
    IonIcon,
    IonLabel,
    AuthPage,
  ],
})
export class TabsPage {
  public environmentInjector = inject(EnvironmentInjector);
  public isAuthenticated: boolean;

  constructor(private preferencesService: PreferencesService) {
    addIcons({ triangle, ellipse, square });
    this.isAuthenticated = preferencesService.isAuth();
    console.log('isAuthenticated', this.isAuthenticated);
  }
}
