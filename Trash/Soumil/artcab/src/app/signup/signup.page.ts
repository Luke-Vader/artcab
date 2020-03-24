import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { IonSlides } from '@ionic/angular';
import { HttpClient } from '@angular/common/http';
import { Storage } from '@ionic/storage';
import * as $ from 'jquery';

@Component({
    selector: 'app-signup',
    templateUrl: './signup.page.html',
    styleUrls: ['./signup.page.scss'],
})
export class SignupPage implements OnInit {
    @ViewChild('slides', {
        static: true
    }) slides: IonSlides;

    constructor(private router: Router, private http: HttpClient, private storage: Storage) {}

    ngOnInit() {}

    ionViewDidEnter() {
        //lock manual swipe for main slider
        this.slides.lockSwipeToNext(true);
        this.slides.lockSwipeToPrev(true);

        /*for(let i = 1; i <= 3; i++)
        {
            this.slides.slideNext();
        }
        this.getSpec();*/
    }

    prevSlide() {
        this.slides.lockSwipeToPrev(false);
        this.slides.slidePrev();
        this.slides.lockSwipeToPrev(true);
    }

    nextSlide() {
        this.slides.lockSwipeToNext(false);
        this.slides.slideNext();
        this.slides.lockSwipeToNext(true);
    }

    specList: any;
    subSpecList: any = [];
    genreList: any;
    tasteList: any;
    checkedItems: any;

    email_error: any;
    email_warning: any;

    specSelection: any = true;
    subSpecSelection: any = true;
    genreSelection: any = true;
    tasteSelection: any = true;

    //specList = [];

    getSpec() {
        this.http.get('http://cpsprojects.co.in/Admin/getSpec').subscribe(data => {
            //this.specList.push(data);
            this.specList = data;
            console.clear();
            console.log(JSON.stringify(this.specList));

            /*for(var s in this.specList)
            {                
                for(var sp in this.specList[s])
                {
                    console.log(this.specList[s][sp]['spec_sl'] + ", " + this.specList[s][sp]['spec_name']);
                }
            }
            console.log('\n\n');
            for(var i in data)
            {
                console.log(data[i]['spec_sl'] + ", " + data[i]['spec_name']);
            }
            console.log('\n\n');
            for (const d of (data as any)) 
            {
                console.log(d['spec_sl'] + ", " + d['spec_name']);
            }*/
        }, error => console.error(error));
    }

    checkList: any;    
    checkBoxes: any;

    checkMark(x)
    {
        if(x == 'spec') { this.checkList = this.specList; }        
        else if(x == 'genre') { this.checkList = this.genreList; }
        else if(x == 'taste') { this.checkList = this.tasteList; }
        else if(x == 'subspec') 
        { 
            let subboxes = []; 
            for(let y of this.subSpecList) 
            {
                for(let z of y)
                {
                    subboxes.push(z);
                }
            }
            this.checkList = subboxes;            
        }
       
        this.checkBoxes = this.checkList.filter(value => {
            return value.isChecked;
        });        

        //alert(this.checkedItems.length);
        //if ($.isEmptyObject(this.checkedItems))
        if (this.checkBoxes.length != 0)
        {
            if(x == 'spec') { this.specSelection = false; }            
            else if(x == 'genre') { this.genreSelection = false; }
            else if(x == 'taste') { this.tasteSelection = false; }
            else if(x == 'subspec') { this.subSpecSelection = false; }
        }
        else
        {
            if(x == 'spec') { this.specSelection = true; }            
            else if(x == 'genre') { this.genreSelection = true; }
            else if(x == 'taste') { this.tasteSelection = true; }
            else if(x == 'subspec') { this.subSpecSelection = true; }
        }
    }

    getCheckedBoxes() {
        this.checkedItems = this.specList.filter(value => {
            return value.isChecked;
        });

        console.clear();

        for (let item of this.checkedItems) {
            this.subSpecList = [];
            this.http.get('http://cpsprojects.co.in/Admin/getSubSpec/' + item.spec_sl).subscribe(data => {
                this.subSpecList.push(data);
            }, error => console.error(error));
        }
        console.log(this.subSpecList);
    }

    getGenre() {
        this.http.get('http://cpsprojects.co.in/Admin/getGenre').subscribe(data => {
            this.genreList = data;
        }, error => console.error(error));
    }

    getTaste() {
        this.http.get('http://cpsprojects.co.in/Admin/getTaste').subscribe(data => {
            this.tasteList = data;
        }, error => console.error(error));
    }

    validateEmail(email) 
    {
        var re = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
        return re.test(String(email).toLowerCase());
    }

    validateUrl(url) 
    {
        //var re = /(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
        var re = /^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:[/?#]\S*)?$/i;
        return re.test(url);
    }

    checkEmailExist(emailid) 
    {
        if(this.validateEmail(emailid))
        {
            this.http.get('http://cpsprojects.co.in/Admin/checkEmailExist/' + emailid).subscribe(data => {
                if (data == "1")
                {
                    this.email_error = true;
                    this.email_warning = "This email is already used";
                }
                else if(data == "0")
                {
                    this.email_error = false;
                    this.email_warning = "";
                }
            }, error => console.error(error));
        }
        else
        {
            this.email_error = true;
            this.email_warning = "";
        }
    }

    fullname: any = 'Bishop';
    emailid: any = 'bs@gmail.co';
    password: any = '123';
    conf_password: any = '123';
    filmqt: any = 'Abc Def Ghi';
    link1: any = 'http://abc.com';
    link2: any = 'http://def.com';
    link3: any = 'http://ghi.com';

    confirmRegister()
    {
        let subboxes = []; 
        for(let x of this.subSpecList) 
        {
            for(let y of x)
            {
                subboxes.push(y);
            }
        }        

        // Subspec
        let sub_selected = subboxes.filter(value => {
            return value.isChecked;
        });

        let subspec_val = [];
        for(let z of sub_selected)
        {
            subspec_val.push(z.sub_spec_sl+'_'+z.spec_sl_no);            
        }

        // Genre
        let genre_selected = this.genreList.filter(value => {
            return value.isChecked;
        });

        let genre_val = [];
        for(let z of genre_selected)
        {
            genre_val.push(z.genre_sl);            
        }

        // Taste
        let taste_selected = this.tasteList.filter(value => {
            return value.isChecked;
        });

        let taste_val = [];
        for(let z of taste_selected)
        {
            taste_val.push(z.taste_sl);            
        }

        console.log(subspec_val);
        console.log(genre_val);
        console.log(taste_val);

        let body = '?fname='+this.fullname+'&email='+this.emailid+'&password='+this.password+'&subspec='+subspec_val+'&genre='+genre_val+'&taste='+taste_val+'&filmqt='+this.filmqt+'&link1='+encodeURIComponent(this.link1)+'&link2='+encodeURIComponent(this.link2)+'&link3='+encodeURIComponent(this.link3);

        const that = this;
        $.post('http://cpsprojects.co.in/Admin/registerUser/'+body, function(data) 
        {
            if(data) { that.storage.set('userID', data); that.router.navigate(['/tabs']); }
        });
    }

    /*overflow(v) {
        if (v == 0) {
            $('ion-slides').css({
                "overflow-y": "hidden"                
            });
        } else if (v == 1) {
            $('ion-slides').css({
                "overflow-y": "visible"                
            });
        }
    }*/    

}
