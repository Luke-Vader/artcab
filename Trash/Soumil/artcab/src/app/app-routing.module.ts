import {
    NgModule
}
from '@angular/core';
import {
    PreloadAllModules, RouterModule, Routes
}
from '@angular/router';

const routes: Routes = [{
    path: '',
    redirectTo: 'welcome',
    pathMatch: 'full'
}, {
    path: 'welcome',
    loadChildren: () =>
        import ('./welcome/welcome.module').then(m => m.WelcomePageModule)
}, {
    path: 'banner',
    loadChildren: () =>
        import ('./banner/banner.module').then(m => m.BannerPageModule)
}, {
    path: '',
    loadChildren: () =>
        import ('./tabs/tabs.module').then(m => m.TabsPageModule)
}, {
    path: 'option',
    loadChildren: () =>
        import ('./option/option.module').then(m => m.OptionPageModule)
}, {
    path: 'identify',
    loadChildren: () =>
        import ('./identify/identify.module').then(m => m.IdentifyPageModule)
}, {
    path: 'login',
    loadChildren: () =>
        import ('./login/login.module').then(m => m.LoginPageModule)
}, {
    path: 'signup',
    loadChildren: () =>
        import ('./signup/signup.module').then(m => m.SignupPageModule)
}, {
    path: 'profile',
    loadChildren: () =>
        import ('./profile/profile.module').then(m => m.ProfilePageModule)
}, {
    path: 'updatepass',
    loadChildren: () => import('./updatepass/updatepass.module').then( m => m.UpdatepassPageModule)
}, {
    path: 'myidea',
    loadChildren: () => import('./myidea/myidea.module').then( m => m.MyideaPageModule)
}]

;
@NgModule({
    imports: [
        RouterModule.forRoot(routes, {
            preloadingStrategy: PreloadAllModules
        })
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {}