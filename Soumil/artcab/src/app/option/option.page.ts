import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-option',
  templateUrl: './option.page.html',
  styleUrls: ['./option.page.scss'],
})
export class OptionPage implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  /*identify()
  {
  		this.router.navigate(['/identify']);
  }*/

  tabPage()
  {
  		//this.router.navigate(['/identify']);
  }

}
