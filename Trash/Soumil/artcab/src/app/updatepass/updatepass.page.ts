import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Storage } from '@ionic/storage';
import { ModalController, ToastController } from '@ionic/angular';
import * as $ from 'jquery';

@Component({
  selector: 'app-updatepass',
  templateUrl: './updatepass.page.html',
  styleUrls: ['./updatepass.page.scss'],
})
export class UpdatepassPage implements OnInit {

  constructor(private router: Router, private http: HttpClient, private storage: Storage, public viewCtrl: ModalController, public toastController: ToastController) { }

  ngOnInit() {
  }

  	password: any;
  	conf_password: any;
  	pass_error: any;

    dismiss() 
    {
		this.viewCtrl.dismiss();
	}

	updatePass()
	{
		if(this.password == this.conf_password)
		{
			this.storage.get('userID').then((data) => {
	        	if(data)
	        	{
	          		this.pass_error = "";
					let msg;
					this.http.get("http://cpsprojects.co.in/Admin/changePassword/?pass="+this.password+"&conf_pass="+this.conf_password+"&userID="+data).subscribe((passdata: any) => {
						if(passdata)
						{
							msg = "Password updated succesfully";
							this.password = this.conf_password = "";
							this.presentToastWithOptions(msg, passdata);
						}
						else
						{
							msg = "Something wents wrong..!";				
							this.presentToastWithOptions(msg, null);	
						}
					});
	        	}    
	        	else
	        	{
	        		this.router.navigate(['/identify']);
	        	}
	      	});  	
		}
		else
		{
			this.pass_error = "Passwords mismatched..!";
		}
	}

	async presentToastWithOptions(msg, data) 
	{		
		if(data)
		{
			const toast=await this.toastController.create( 
	    	{
	        	message: msg, position: 'bottom', buttons: [ {
		            text: 'Done', handler: ()=> {
		                this.dismiss();
		            }
		        }]
	    	});
	    	toast.present();
		}
    	else
    	{
    		const toast=await this.toastController.create( 
	    	{
	        	message: msg, position: 'bottom', buttons: [ {
		            text: 'OK', role: 'cancel', handler: ()=> {	
		            		                
		            },
		        },{
		            text: 'Try again', handler: ()=> {	
		            	this.updatePass();	                
		            },
		        }]
	    	});
	    	toast.present();
    	}
	}

}
