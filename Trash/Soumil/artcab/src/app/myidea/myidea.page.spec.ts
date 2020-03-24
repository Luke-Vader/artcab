import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { MyideaPage } from './myidea.page';

describe('MyideaPage', () => {
  let component: MyideaPage;
  let fixture: ComponentFixture<MyideaPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyideaPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(MyideaPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
