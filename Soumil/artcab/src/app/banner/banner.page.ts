import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.page.html',
  styleUrls: ['./banner.page.scss'],
})
export class BannerPage implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  showNetwork()
  {
  		this.router.navigate(['/tabs']);
  }

}
