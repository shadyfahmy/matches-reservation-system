import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateStadiumPageComponent } from './create-stadium-page.component';

describe('CreateStadiumPageComponent', () => {
  let component: CreateStadiumPageComponent;
  let fixture: ComponentFixture<CreateStadiumPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateStadiumPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateStadiumPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
