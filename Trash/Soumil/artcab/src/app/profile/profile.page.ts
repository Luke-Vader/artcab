import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Storage } from '@ionic/storage';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class ProfilePage implements OnInit {

  constructor(private router: Router, private http: HttpClient, private storage: Storage) { }

  user_full_name: any;
  user_film_quote: any;
  user_spec: any;
  user_portfolio: any;
  fb_link: any;
  tw_link: any;
  lnkd_link: any;
  insta_link: any;
  work_link1: any;
  work_link2: any;
  work_link3: any;

  ngOnInit() {
  	this.storage.get('u_id').then((val) => {
  		this.http.get('http://cpsprojects.co.in/Admin/getProfileInfo/'+val).subscribe((data: any) => { 
			    this.user_full_name = data[0]['user_name'];
			    this.user_film_quote = data[0]['film_quote'];
			    this.user_spec = data[0]['spec_name'];
			    this.user_portfolio = data[0]['portfolio'];
			    this.fb_link = data[0]['fb_link'];
			    this.tw_link = data[0]['tw_link'];
			    this.lnkd_link = data[0]['lnkd_link'];
			    this.insta_link = data[0]['insta_link'];
			    this.work_link1 = data[0]['work_link1'];
			    this.work_link2 = data[0]['work_link2'];
			    this.work_link3 = data[0]['work_link3'];
		}, error => console.error(error));
  	});
  }

}
