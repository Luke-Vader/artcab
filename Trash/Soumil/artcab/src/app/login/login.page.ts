import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Storage } from '@ionic/storage';
import * as $ from 'jquery';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  constructor(private router: Router, private http: HttpClient, private storage: Storage) { }

  ngOnInit() {
  }

  	usr: any = "";
  	pwd: any = "";
  	login_error: any = "";

  	login()
  	{
  		this.http.get('http://cpsprojects.co.in/Admin/login/?emailid='+this.usr+'&password='+this.pwd).subscribe(data => {
            if(data)
            {
            	this.storage.set('userID', data);
            	this.login_error = "";
            	this.router.navigate(['/tabs']);
            }
            else
            {
            	this.login_error = "Email ID or password is wrong..!";
            }
        }, error => console.error(error));
  	}

}
