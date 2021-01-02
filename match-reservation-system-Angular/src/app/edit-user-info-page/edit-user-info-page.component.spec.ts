import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditUserInfoPageComponent } from './edit-user-info-page.component';

describe('EditUserInfoPageComponent', () => {
  let component: EditUserInfoPageComponent;
  let fixture: ComponentFixture<EditUserInfoPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditUserInfoPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditUserInfoPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
