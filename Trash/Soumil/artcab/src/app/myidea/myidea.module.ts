import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { MyideaPageRoutingModule } from './myidea-routing.module';

import { MyideaPage } from './myidea.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    MyideaPageRoutingModule
  ],
  declarations: [MyideaPage]
})
export class MyideaPageModule {}
