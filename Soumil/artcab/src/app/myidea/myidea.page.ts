import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Storage } from '@ionic/storage';
import * as $ from 'jquery';

@Component({
  selector: 'app-myidea',
  templateUrl: './myidea.page.html',
  styleUrls: ['./myidea.page.scss'],
})
export class MyideaPage implements OnInit {

  constructor(private router: Router, private http: HttpClient, private storage: Storage, public modalCtrl: ModalController) { }

  ideaList: any;

  ngOnInit() 
  {
  		this.storage.get('userID').then((data) => {
    	if(data)
    	{
      		this.http.get('http://cpsprojects.co.in/Admin/getMyIdea/'+data).subscribe(idata => {
                this.ideaList = idata;
            }, error => console.error(error));
    	}    
    	else
    	{
    		this.router.navigate(['/identify']);
    	}
  	});
  }

  dismiss()
  {
  		this.modalCtrl.dismiss();
  }

}
