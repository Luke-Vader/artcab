import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MyideaPage } from './myidea.page';

const routes: Routes = [
  {
    path: '',
    component: MyideaPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MyideaPageRoutingModule {}
