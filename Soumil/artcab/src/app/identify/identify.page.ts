import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-identify',
  templateUrl: './identify.page.html',
  styleUrls: ['./identify.page.scss'],
})
export class IdentifyPage implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  signupPage()
  {
  		this.router.navigate(['/signup']);
  }

  loginPage()
  {  		
  		this.router.navigate(['/login']);
  }

}
