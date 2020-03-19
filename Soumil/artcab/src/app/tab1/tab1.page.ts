import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { IonSlides, IonSelect } from '@ionic/angular';
import { HttpClient } from '@angular/common/http';
import { Storage } from '@ionic/storage';
import * as $ from 'jquery';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class Tab1Page implements OnInit {
  @ViewChild('slides', {static: true}) slides: IonSlides;
  @ViewChild('spList', {static: true}) selectRef: IonSelect;

  hideList = true;

  constructor(private router: Router, private http: HttpClient, private storage: Storage) {}

  specList: any;
  filterSpecList: any;
  personList: any = [];
  subSpecList: any = [];

  ngOnInit() {

  	/*$.post("http://cpsprojects.co.in/Admin/logout", function(data){});*/
  	this.viewUserSpec(null);
  		
  }

  displaySpecList()
  {
  		this.selectRef.open();
  }

  displaySubSpecList(spec_sl)
  {
  		$('#subspecs'+spec_sl).focus().click();
  }

  spec_url: any;

  viewUserSpec(ev)
  {
  		if(ev != null)
  		{
  			let selected_spec = ev.target.value;
  			this.spec_url = 'http://cpsprojects.co.in/Admin/getSelectedSpec/?selected_spec='+selected_spec;
  		}
  		else
  		{
  			this.spec_url = 'http://cpsprojects.co.in/Admin/getSpec';
  		}

  		console.log(this.spec_url);

  		this.http.get(this.spec_url).subscribe((data: any) => { 		    	

	    	if(ev != null)
	    	{
	    		this.filterSpecList = data;
	    	}
	    	else
	    	{
	    		this.filterSpecList = this.specList = data;
	    	}

	    	for(let item of data)
			{				
				this.personList = [];
			    this.http.get('http://cpsprojects.co.in/Admin/getPersonBySpec/'+item.spec_sl).subscribe((pdata: any) => { 
			    	if(pdata) { this.personList.push(pdata); }
			    	/*const iterator = this.personList.keys();
			    	console.log(iterator);
					for (const key of iterator) 
					{
					  	console.log(key);
					}*/
			    }, error => console.error(error));			

			    this.subSpecList = [];
			    this.http.get('http://cpsprojects.co.in/Admin/getSubspecBySpec/'+item.spec_sl).subscribe((sdata: any) => { 
			    	this.subSpecList.push(sdata);
			    }, error => console.error(error));
			}
			console.log(this.personList);
	    }, error => console.error(error));
  }

  subList: any;
  subLength: any;

  viewSubSpec(ev, spec_sl)
  {
  		let selected_subspec = ev.target.value;

  		this.http.get('http://cpsprojects.co.in/Admin/getPersonBySubspec/?selected_subspec='+selected_subspec).subscribe((ssdata: any) => { 
	    	this.subList = ssdata;
	    	this.subLength = this.subList.length;
	    	console.log(this.subList.length);
	    	console.log(this.subList);
	    }, error => console.error(error));
  }

  showProfile(user_id)
  {
  		this.storage.get('userID').then((data) => {
        	if(data)
        	{
          		this.storage.set('u_id', user_id);
  				this.router.navigate(['/profile']);
        	}    
        	else
        	{
        		this.router.navigate(['/identify']);
        	}
      	});  	
  }

}
