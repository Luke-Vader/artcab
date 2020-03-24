import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { IonSlides, IonSelect, ToastController } from '@ionic/angular';
import { HttpClient } from '@angular/common/http';
import { Storage } from '@ionic/storage';
import * as $ from 'jquery';

@Component({
  	selector: 'app-tab2',
  	templateUrl: 'tab2.page.html',
  	styleUrls: ['tab2.page.scss']
})
export class Tab2Page implements OnInit {

	@ViewChild('slides', {static: true}) slides: IonSlides;
  	@ViewChild('gnrList', {static: true}) selectRef: IonSelect;

  	hideList = true;

  	constructor(private router: Router, private http: HttpClient, private storage: Storage, public toastController: ToastController) 
  	{
  		//this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  	}

  	genreList: any;
  	filterGenreList: any;
  	ideaList: any = [];
  	genre_url: any;

  	gen: any;
  	about_gen: any;

  	ngOnInit() 
  	{
  		this.storage.get('userID').then((data) => {
        	if(data)
        	{
          		this.viewGenreIdea(null, data);
        	}    
        	else
        	{
        		this.router.navigate(['/identify']);
        	}
      	});
  	}

  	displayGenreList()
  	{
  		this.selectRef.open();
  	}

  	viewGenreIdea(ev, user_ID)
  	{
  		if(ev != null)
  		{
  			let selected_genre = ev.target.value;
  			this.genre_url = 'http://cpsprojects.co.in/Admin/getSelectedGenre/?selected_genre='+selected_genre;
  		}
  		else
  		{
  			this.genre_url = 'http://cpsprojects.co.in/Admin/getGenre';
  		}

  		this.http.get(this.genre_url).subscribe((data: any) => { 		    	

	    	if(ev != null)
	    	{
	    		this.filterGenreList = data;
	    	}
	    	else
	    	{
	    		this.filterGenreList = this.genreList = data;
	    	}

	    	for(let item of data)
			{				
				this.ideaList = [];
			    this.http.get('http://cpsprojects.co.in/Admin/getIdeaByGenre/'+item.genre_sl+'/'+user_ID).subscribe((idata: any) => { 
			    	this.ideaList.push(idata);
			    }, error => console.error(error));			    
			}
	    }, error => console.error(error));
  	}

  	shareIdea()
  	{
  		this.storage.get('userID').then((data) => {
        	if(data)
        	{
        		let msg;
        		const that = this;
          		$.post('http://cpsprojects.co.in/Admin/shareNewIdea/?gen='+this.gen+'&about_gen='+this.about_gen+'&userID='+data, function(gdata)
          		{ 
			    	if(gdata)
			    	{
			    		msg = "Idea shared successfully";
			    		that.gen = that.about_gen = "";
			    		that.presentToastWithOptions(msg, gdata);
			    	}
					else
					{
						msg = "Something wents wrong..!";				
						that.presentToastWithOptions(msg, null);	
					}
			    });
        	}    
        	else
        	{
        		this.router.navigate(['/identify']);
        	}
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
		                $('#myModal').hide();
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
		            	this.shareIdea();	                
		            },
		        }]
	    	});
	    	toast.present();
    	}
	}

}
