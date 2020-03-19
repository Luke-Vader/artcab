import { Component, OnInit, ViewChild } from '@angular/core';
import { ActionSheetController, LoadingController, ModalController, AlertController, ToastController } from '@ionic/angular';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Storage } from '@ionic/storage';
import { UpdatepassPage } from '../updatepass/updatepass.page';
import { MyideaPage } from '../myidea/myidea.page';
import * as $ from 'jquery';

@Component({
  selector: 'app-tab3',
  templateUrl: 'tab3.page.html',
  styleUrls: ['tab3.page.scss']
})
export class Tab3Page implements OnInit {

  	constructor(private router: Router, private http: HttpClient, private storage: Storage, public actionSheetController: ActionSheetController, public loadingController: LoadingController, public modalController: ModalController, public alertController: AlertController, public toastController: ToastController) {}

  	profileInfo: any;
  	ideaInfo: any;
  	portfl: any;
  	selectedfile: any;

  	fblink: any;
  	twlink: any;
  	lnkdlink: any;
  	instalink: any;

  	email: any;
  	phone: any;
  	whatsapp: any;

  	link1: any;
  	link2: any;
  	link3: any;

  	gnrSelection: any = true;
  	tstSelection: any = true;
  	genreList: any;
  	tasteList: any;
  	checkList: any;
  	checkBoxes: any;

  	ngOnInit() 
  	{
  		this.storage.get('userID').then((data) => {
        	if(data)
        	{
          		this.viewYourProfile(data);
        	}    
        	else
        	{
        		this.router.navigate(['/identify']);
        	}
      	});
  	}

  	getGenre()
  	{
  		let gList = [];
  		for(let g of this.profileInfo[0]['genreInfo'])
  		{
  			gList.push(g.genre_sl);
  		}
  		
  		this.http.get('http://cpsprojects.co.in/Admin/getLeftGenre/?gList='+gList).subscribe((gdata: any) => { 
	    	this.genreList = gdata;
	    }, error => console.error(error));
  	}

  	addGenre()
  	{
  		let genre_selected = this.genreList.filter(value => {
            return value.isChecked;
        });

        let genre_val = [];
        for(let x of genre_selected)
        {
            genre_val.push(x.genre_sl);            
        }

        this.storage.get('userID').then((data) => {
        	if(data)
        	{        		
          		this.http.get("http://cpsprojects.co.in/Admin/addSpecificGenre/?genre_val="+genre_val+"&userID="+data).subscribe((gdata: any) =>
		        {
		        	this.viewYourProfile(data);
		        	$('#addGenre').hide();
		        });
        	}    
        	else
        	{
        		this.router.navigate(['/identify']);
        	}
      	});
  	}

  	getTaste()
  	{
  		let tList = [];
  		for(let t of this.profileInfo[0]['tasteInfo'])
  		{
  			tList.push(t.taste_sl);
  		}
  		
  		this.http.get('http://cpsprojects.co.in/Admin/getLeftTaste/?tList='+tList).subscribe((tdata: any) => { 
	    	this.tasteList = tdata;
	    }, error => console.error(error));
  	}

  	addTaste()
  	{
  		let taste_selected = this.tasteList.filter(value => {
            return value.isChecked;
        });

        let taste_val = [];
        for(let x of taste_selected)
        {
            taste_val.push(x.taste_sl);            
        }

        this.storage.get('userID').then((data) => {
        	if(data)
        	{        		
          		this.http.get("http://cpsprojects.co.in/Admin/addSpecificTaste/?taste_val="+taste_val+"&userID="+data).subscribe((gdata: any) =>
		        {
		        	this.viewYourProfile(data);
		        	$('#addTaste').hide();
		        });
        	}    
        	else
        	{
        		this.router.navigate(['/identify']);
        	}
      	});
  	}

  	checkMark(x)
    {       
        if(x == 'genre') { this.checkList = this.genreList; }
        else if(x == 'taste') { this.checkList = this.tasteList; }
        
        this.checkBoxes = this.checkList.filter(value => {
            return value.isChecked;
        });        

        //alert(this.checkedItems.length);
        //if ($.isEmptyObject(this.checkedItems))
        if (this.checkBoxes.length != 0)
        {
            if(x == 'genre') { this.gnrSelection = false; }
            else if(x == 'taste') { this.tstSelection = false; }            
        }
        else
        {
            if(x == 'genre') { this.gnrSelection = true; }
            else if(x == 'taste') { this.tstSelection = true; }            
        }
    }

  	viewYourProfile(user_ID)
  	{
  		this.http.get('http://cpsprojects.co.in/Admin/getYourProfile/'+user_ID).subscribe((prodata: any) => { 
	    	this.profileInfo = prodata;
	    	this.portfl = prodata[0]['portfolio'];
	    	this.fblink = prodata[0]['fb_link'];
	    	this.twlink = prodata[0]['tw_link'];
	    	this.lnkdlink = prodata[0]['lnkd_link'];
	    	this.instalink = prodata[0]['insta_link'];
	    	this.email = prodata[0]['email_id'];
	    	this.phone = prodata[0]['mobile_no'];
	    	this.whatsapp = prodata[0]['wa_no'];
	    	this.link1 = prodata[0]['work_link1'];
	    	this.link2 = prodata[0]['work_link2'];
	    	this.link3 = prodata[0]['work_link3'];
	    }, error => console.error(error));
  	}

  	deleteGenre(genre_rel_sl)
  	{
  		this.gnrDltAlertConfirm(genre_rel_sl);
  	}

  	deleteTaste(taste_rel_sl)
  	{
		this.tstDltAlertConfirm(taste_rel_sl);
  	}

  	onselect(ev)
  	{  		
  		this.selectedfile = ev.target.files[0];
  	}

  	postImage(formData) 
  	{  		  		
    	return this.http.post('http://cpsprojects.co.in/Admin/setImage', formData);
  	}

  	updateProfilePic()
  	{
  		this.storage.get('userID').then((data) => {
  			console.log(data);
	  		const form = new FormData();
	  		form.set('user_id', data);
		    form.append('file', this.selectedfile);
		    //form.append('file', this.userform.get('file').value);
		    console.log(form);
		    let msg;
		    this.postImage(form).subscribe(data=>{
		    	if(data)
		    	{
		    		msg = "Photo updated succesfully";
					this.presentToastWithOptions(msg, data);
		    	}
		    	else
		    	{
		    		msg = "Something wents wrong..!";
					this.presentToastWithOptions(msg, null);
		    	}
		      	error=> console.log(error);
		    });
		});
  	}

  	async presentToastWithOptions(msg, data) 
	{		
		if(data)
		{
			const toast=await this.toastController.create( 
	    	{
	        	message: msg, position: 'bottom', buttons: [ {
		            text: 'Done', handler: ()=> {
		                this.toastController.dismiss();
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
		            	this.updateProfilePic();	                
		            },
		        }]
	    	});
	    	toast.present();
    	}
	}

  	updatePortfolio()
  	{
  		this.storage.get('userID').then((data) => {
        	if(data)
        	{        		
          		this.http.get("http://cpsprojects.co.in/Admin/updatePortfolio/?about="+this.portfl+"&userID="+data).subscribe((pdata: any) =>
		        {
		        	this.viewYourProfile(data);
		        	$('#editPortfolio').hide();
		        });
        	}    
        	else
        	{
        		this.router.navigate(['/identify']);
        	}
      	});
  	}

  	updateSocial()
  	{
  		this.storage.get('userID').then((data) => {
        	if(data)
        	{        		
          		this.http.get("http://cpsprojects.co.in/Admin/updateSocial/?fblink="+this.fblink+"&twlink="+this.twlink+"&lnkdlink="+this.lnkdlink+"&instalink="+this.instalink+"&userID="+data).subscribe((pdata: any) =>
		        {
		        	this.viewYourProfile(data);
		        	$('#editSocial').hide();
		        });
        	}    
        	else
        	{
        		this.router.navigate(['/identify']);
        	}
      	});
  	}

  	updateContact()
  	{
  		this.storage.get('userID').then((data) => {
        	if(data)
        	{        		
          		this.http.get("http://cpsprojects.co.in/Admin/updateContact/?email="+this.email+"&phone="+this.phone+"&whatsapp="+this.whatsapp+"&userID="+data).subscribe((pdata: any) =>
		        {
		        	this.viewYourProfile(data);
		        	$('#editContact').hide();
		        });
        	}    
        	else
        	{
        		this.router.navigate(['/identify']);
        	}
      	});
  	}

  	updateWorkLinks()
  	{
  		this.storage.get('userID').then((data) => {
        	if(data)
        	{        		
          		this.http.get("http://cpsprojects.co.in/Admin/updateWorkLinks/?link1="+this.link1+"&link2="+this.link2+"&link3="+this.link3+"&userID="+data).subscribe((pdata: any) =>
		        {
		        	this.viewYourProfile(data);
		        	$('#editWorkLink').hide();
		        });
        	}    
        	else
        	{
        		this.router.navigate(['/identify']);
        	}
      	});
  	}

  	async moreOption() 
  	{    	
	    const actionSheet=await this.actionSheetController.create( 
	    {
		    header: 'Option', buttons: [ 
		    {
		        text: 'My Ideas', icon: 'md-bulb', handler: ()=> 
		        {
		            this.viewMyIdea();
		        }
		    },
		    {
		        text: 'Update Password', icon: 'md-key', handler: ()=> 
		        {
		            this.viewModal();
		        }
		    },
		    {
		        text: 'Logout', icon: 'md-log-out', handler: ()=> 
		        {
		            this.storage.clear();
		            this.router.navigate(['/tabs']);
		        }
		    }]
		});
		await actionSheet.present();  
    }    

    async viewModal()
    {
    	const modal = await this.modalController.create({
		component: UpdatepassPage});
		await modal.present();
    }

    async viewMyIdea()
    {
    	const modal = await this.modalController.create({
		component: MyideaPage});
		await modal.present();
    }

    async gnrDltAlertConfirm(genre_rel_sl) 
    {
	    const alert=await this.alertController.create( {
	        header: 'Confirm!', message: 'Sure to remove ?', buttons: [ {
	            text: 'Cancel', role: 'cancel', cssClass: 'secondary', handler: (val)=> {}
	        }
	        , {
	            text: 'Okay', handler: ()=> {
	                this.storage.get('userID').then((data) => {
			        	if(data)
			        	{        		
			          		this.http.get("http://cpsprojects.co.in/Admin/remSpecificGenre/"+genre_rel_sl).subscribe((gdata: any) =>
					        {
					        	this.viewYourProfile(data);
					        });
			        	}    
			        	else
			        	{
			        		this.router.navigate(['/identify']);
			        	}
			      	});  
	            }
	        }]
	    });
	    await alert.present();
	}

	async tstDltAlertConfirm(taste_rel_sl) 
    {
	    const alert=await this.alertController.create( {
	        header: 'Confirm!', message: 'Sure to remove ?', buttons: [ {
	            text: 'Cancel', role: 'cancel', cssClass: 'secondary', handler: (val)=> {}
	        }
	        , {
	            text: 'Okay', handler: ()=> {
	                this.storage.get('userID').then((data) => {
			        	if(data)
			        	{        		
			          		this.http.get("http://cpsprojects.co.in/Admin/remSpecificTaste/"+taste_rel_sl).subscribe((tdata: any) =>
					        {
					        	this.viewYourProfile(data);
					        });
			        	}    
			        	else
			        	{
			        		this.router.navigate(['/identify']);
			        	}
			      	});  
	            }
	        }]
	    });
	    await alert.present();
	}
}
